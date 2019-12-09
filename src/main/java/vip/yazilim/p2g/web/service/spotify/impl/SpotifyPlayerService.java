package vip.yazilim.p2g.web.service.spotify.impl;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.wrapper.spotify.enums.ModelObjectType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vip.yazilim.p2g.web.constant.QueueStatus;
import vip.yazilim.p2g.web.entity.User;
import vip.yazilim.p2g.web.entity.relation.Song;
import vip.yazilim.p2g.web.entity.relation.SpotifyToken;
import vip.yazilim.p2g.web.entity.relation.UserDevice;
import vip.yazilim.p2g.web.exception.PlayerException;
import vip.yazilim.p2g.web.exception.QueueException;
import vip.yazilim.p2g.web.exception.RequestException;
import vip.yazilim.p2g.web.service.p2g.ITokenService;
import vip.yazilim.p2g.web.service.p2g.IUserService;
import vip.yazilim.p2g.web.service.p2g.relation.IRoomQueueService;
import vip.yazilim.p2g.web.service.p2g.relation.IUserDeviceService;
import vip.yazilim.p2g.web.service.spotify.ISpotifyPlayerService;
import vip.yazilim.p2g.web.service.spotify.ISpotifyRequestService;
import vip.yazilim.p2g.web.service.spotify.model.PlayerModel;
import vip.yazilim.spring.core.exception.general.InvalidArgumentException;
import vip.yazilim.spring.core.exception.general.InvalidUpdateException;
import vip.yazilim.spring.core.exception.general.database.DatabaseException;

import javax.transaction.Transactional;
import java.util.*;

/**
 * @author mustafaarifsisman - 28.11.2019
 * @contact mustafaarifsisman@gmail.com
 */
@Transactional
@Service
public class SpotifyPlayerService implements ISpotifyPlayerService {

    @Autowired
    private ITokenService tokenService;

    @Autowired
    private ISpotifyRequestService spotifyRequest;

    @Autowired
    private IUserService userService;

    @Autowired
    private IUserDeviceService userDeviceService;

    @Autowired
    private IRoomQueueService roomQueueService;

    @Override
    public List<Song> play(Song song) throws RequestException, PlayerException, DatabaseException, QueueException, InvalidUpdateException, InvalidArgumentException {
        String songUri = song.getSongUri();
        String roomUuid = song.getRoomUuid();

        if (!songUri.contains(ModelObjectType.TRACK.getType())) {
            String err = String.format("URI[%s] does not match with an Track URI", songUri);
            throw new PlayerException(err);
        } else {
            // JsonArray with song, because uris needs JsonArray as input
            List<String> songList = Collections.singletonList(songUri);
            JsonArray urisJson = new GsonBuilder().create().toJsonTree(songList).getAsJsonArray();

            // Start playback
            spotifyRequest.execRequestListAsync((spotifyApi, device) -> spotifyApi.startResumeUsersPlayback().uris(urisJson).device_id(device).build(), getPlayerModel(roomUuid));

            // Update queue status
            roomQueueService.updateRoomQueueStatus(song);

            // Update nowPlaying
            song.setVotes(0);
            song.setPlayingTime(new Date());
            roomQueueService.update(song);
        }

        return roomQueueService.getRoomQueueListByRoomUuid(roomUuid);
    }

    @Override
    public List<Song> startResume(String roomUuid) throws RequestException, DatabaseException, InvalidUpdateException, PlayerException, InvalidArgumentException, QueueException {
        Optional<Song> pausedOpt = roomQueueService.getRoomQueuePaused(roomUuid);

        if (pausedOpt.isPresent()) {
            Song paused = pausedOpt.get();
            int currentMs = Math.toIntExact(paused.getCurrentMs());

            // Resume playback
            spotifyRequest.execRequestListAsync((spotifyApi, device) -> spotifyApi.startResumeUsersPlayback().device_id(device).position_ms(currentMs).build(), getPlayerModel(roomUuid));

            // Update nowPlaying
            paused.setQueueStatus(QueueStatus.PLAYING.getQueueStatus());
            roomQueueService.update(paused);
        } else {
            // Get first queued
            Optional<Song> firstQueued = roomQueueService.getRoomQueueNext(roomUuid);

            play(firstQueued.orElseThrow(() -> new PlayerException("Queue is empty.")));

        }

        return roomQueueService.getRoomQueueListByRoomUuid(roomUuid);
    }

    @Override
    public List<Song> pause(String roomUuid) throws RequestException, DatabaseException, InvalidUpdateException, PlayerException, InvalidArgumentException {
        Optional<Song> nowPlayingOpt = roomQueueService.getRoomQueueNowPlaying(roomUuid);

        if (nowPlayingOpt.isPresent() && (nowPlayingOpt.get().getQueueStatus().equals(QueueStatus.PLAYING.getQueueStatus()))) {
            Song nowPlaying = nowPlayingOpt.get();

            // Pause playback
            spotifyRequest.execRequestListAsync((spotifyApi, device) -> spotifyApi.pauseUsersPlayback().device_id(device).build(), getPlayerModel(roomUuid));

            // Update nowPlaying
            nowPlaying.setCurrentMs(System.currentTimeMillis() - nowPlaying.getPlayingTime().getTime());
            nowPlaying.setQueueStatus(QueueStatus.PAUSED.getQueueStatus());
            roomQueueService.update(nowPlaying);
        } else {
            String err = String.format("Not playing any song in Room[%s]", roomUuid);
            throw new PlayerException(err);
        }

        return roomQueueService.getRoomQueueListByRoomUuid(roomUuid);
    }

    @Override
    public List<Song> next(String roomUuid) throws RequestException, DatabaseException, PlayerException, QueueException, InvalidUpdateException, InvalidArgumentException {
        Optional<Song> next = roomQueueService.getRoomQueueNext(roomUuid);
        return play(next.orElseThrow(() -> new PlayerException("Next song is empty.")));
    }

    @Override
    public List<Song> previous(String roomUuid) throws RequestException, DatabaseException, PlayerException, QueueException, InvalidUpdateException, InvalidArgumentException {
        Optional<Song> previous = roomQueueService.getRoomQueueNext(roomUuid);
        return play(previous.orElseThrow(() -> new PlayerException("Previous song is empty.")));
    }

    @Override
    public int seek(String roomUuid, Integer ms) throws RequestException, DatabaseException, PlayerException, InvalidArgumentException {
        Optional<Song> nowPlayingOpt = roomQueueService.getRoomQueueNowPlaying(roomUuid);
        Optional<Song> pausedOpt = roomQueueService.getRoomQueuePaused(roomUuid);

        if (nowPlayingOpt.isPresent() && pausedOpt.isPresent()) {
            spotifyRequest.execRequestListAsync((spotifyApi, device) -> spotifyApi.seekToPositionInCurrentlyPlayingTrack(ms).device_id(device).build(), getPlayerModel(roomUuid));
        } else {
            String err = String.format("Not playing or paused any song in Room[%s]", roomUuid);
            throw new PlayerException(err);
        }

        return ms;
    }

    @Override
    public boolean repeat(String roomUuid) throws RequestException, DatabaseException, PlayerException, InvalidArgumentException, InvalidUpdateException {
        Optional<Song> nowPlayingOpt = roomQueueService.getRoomQueueNowPlaying(roomUuid);
        Boolean repeatFlag;

        if (nowPlayingOpt.isPresent()) {
            Song nowPlaying = nowPlayingOpt.get();
            String repeatMode;
            repeatFlag = nowPlaying.getRepeatFlag();

            if (repeatFlag == null || !repeatFlag) {
                repeatMode = ModelObjectType.TRACK.getType();
                repeatFlag = true;
            } else {
                repeatMode = "off";
                repeatFlag = false;
            }

            spotifyRequest.execRequestListAsync((spotifyApi, device) -> spotifyApi.setRepeatModeOnUsersPlayback(repeatMode).device_id(device).build(), getPlayerModel(roomUuid));

            nowPlaying.setRepeatFlag(repeatFlag);
            roomQueueService.update(nowPlaying);
        } else {
            String err = String.format("Not playing any song in Room[%s]", roomUuid);
            throw new PlayerException(err);
        }

        return repeatFlag;
    }

    private PlayerModel getPlayerModel(String roomUuid) throws DatabaseException, InvalidArgumentException {
        List<String> spotifyTokenList = new LinkedList<>();
        List<String> userDeviceList = new LinkedList<>();

        List<User> userList = userService.getUsersByRoomUuid(roomUuid);

        for (User u : userList) {
            String userUuid = u.getUuid();
            Optional<SpotifyToken> token = tokenService.getTokenByUserUuid(userUuid);

            token.ifPresent(spotifyToken -> spotifyTokenList.add(spotifyToken.getAccessToken()));

            List<UserDevice> userDevices = userDeviceService.getUserDevicesByUserUuid(userUuid);
            if (!userDevices.isEmpty())
                userDeviceList.add(userDevices.get(0).getDeviceId());
        }

        PlayerModel playerModel = new PlayerModel();
        playerModel.setRoomUuid(roomUuid);
        playerModel.setSpotifyTokenList(spotifyTokenList);
        playerModel.setUserDeviceList(userDeviceList);

        return playerModel;
    }

}

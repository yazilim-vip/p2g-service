package vip.yazilim.p2g.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import vip.yazilim.p2g.web.constant.enums.FriendRequestStatus;
import vip.yazilim.p2g.web.constant.enums.OnlineStatus;
import vip.yazilim.p2g.web.constant.enums.SongStatus;
import vip.yazilim.p2g.web.entity.*;
import vip.yazilim.p2g.web.service.p2g.*;
import vip.yazilim.p2g.web.service.p2g.impl.FriendRequestService;
import vip.yazilim.p2g.web.util.TimeHelper;
import vip.yazilim.spring.core.exception.GeneralException;

import java.util.Collections;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private Logger LOGGER = LoggerFactory.getLogger(DataInitializer.class);

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoomService roomService;

    @Autowired
    private IRoomUserService roomUserService;

    @Autowired
    private IRoomInviteService roomInviteService;

    @Autowired
    private FriendRequestService friendRequestService;

    @Autowired
    private ISongService songService;

    @Override
    public void run(String... args) throws GeneralException {
        User arif = userService.createUser("mustafaarifsisman", "mustafaarifsisman@gmail.com", "Mustafa Arif Sisman");
        User emre = userService.createUser("emresen", "maemresen@gmail.com", "Emre Sen");
        User u2 = userService.createUser("2", "2@gmail.com", "Test User 2");
        User u3 = userService.createUser("3", "3@gmail.com", "Test User 3");
        User u5 = userService.createUser("5", "5@gmail.com", "Test User 5");
        User u6 = userService.createUser("6", "6@gmail.com", "Test User 6");
        User u4 = userService.createUser("4", "4@gmail.com", "Test User 4");
        User u7 = userService.createUser("7", "7@gmail.com", "Test User 7");
        User u8 = userService.createUser("8", "8@gmail.com", "Test User 8");

        Room testRoom1 = roomService.createRoom(arif.getId(), "Test Room 1", null);

        Room testRoom2 = roomService.createRoom(u2.getId(), "Test Room 2", "123");
        Room testRoom3 = roomService.createRoom(u3.getId(), "Test Room 3", "123");
        Room testRoom4 = roomService.createRoom(u4.getId(), "Test Room 4", null);
        Room testRoom5 = roomService.createRoom(u5.getId(), "Test Room 5", null);
        Room testRoom6 = roomService.createRoom(u6.getId(), "Test Room 6", null);
        Room testRoom7 = roomService.createRoom(u7.getId(), "Test Room 7", null);
        Room testRoom8 = roomService.createRoom(u8.getId(), "Test Room 8", null);

        Long roomId = testRoom1.getId();
        addSongToRoom(roomId, "4VqPOruhp5EdPBeR92t6lQ", "Uprising", Collections.singletonList("Muse"), 1200000, 0);
        addSongToRoom(roomId, "0c4IEciLCDdXEhhKxj4ThA", "Madness", Collections.singletonList("Muse"), 1200000, 1);
        addSongToRoom(roomId, "7ouMYWpwJ422jRcDASZB7P", "Knights of Cydonia", Collections.singletonList("Muse"), 1200000, 2);
        addSongToRoom(roomId, "2takcwOaAZWiXQijPHIx7B", "Time Is Running Out", Collections.singletonList("Muse"), 1200000, 0);

        u2.setImageUrl("https://randomuser.me/api/portraits/men/47.jpg");
        userService.update(u2);

        u3.setImageUrl("https://randomuser.me/api/portraits/women/40.jpg");
        userService.update(u3);

        u4.setImageUrl("https://randomuser.me/api/portraits/men/42.jpg");
        userService.update(u4);

        u4.setOnlineStatus(OnlineStatus.AWAY.getOnlineStatus());
        userService.update(u4);

        u5.setOnlineStatus(OnlineStatus.OFFLINE.getOnlineStatus());
        userService.update(u5);

        createRoomInvite(u2, arif, testRoom2);
        createRoomInvite(u3, arif, testRoom3);
        createRoomInvite(u4, arif, testRoom4);
        createRoomInvite(u5, arif, testRoom5);
        createRoomInvite(u6, arif, testRoom6);
        createRoomInvite(u7, arif, testRoom7);
        createRoomInvite(u8, arif, testRoom8);

        createFriendRequest(u2, arif, FriendRequestStatus.WAITING);
        createFriendRequest(u3, arif, FriendRequestStatus.WAITING);
        createFriendRequest(u4, arif, FriendRequestStatus.WAITING);
        createFriendRequest(u5, arif, FriendRequestStatus.WAITING);
        createFriendRequest(u6, arif, FriendRequestStatus.ACCEPTED);
        createFriendRequest(u7, arif, FriendRequestStatus.ACCEPTED);
        createFriendRequest(u8, arif, FriendRequestStatus.ACCEPTED);
    }

    private void createRoomInvite(User inviter, User receiver, Room testRoom2) throws GeneralException {
        RoomInvite roomInvite = new RoomInvite();
        roomInvite.setRoomId(testRoom2.getId());
        roomInvite.setInviterId(inviter.getId());
        roomInvite.setReceiverId(receiver.getId());
        roomInvite.setInvitationDate(TimeHelper.getLocalDateTimeNow());
        roomInviteService.create(roomInvite);
    }

    private void createFriendRequest(User sender, User receiver, FriendRequestStatus status) throws GeneralException {
        FriendRequest friendRequest = new FriendRequest();
        friendRequest.setSenderId(sender.getId());
        friendRequest.setReceiverId(receiver.getId());
        friendRequest.setRequestStatus(status.getFriendRequestStatus());
        friendRequest.setRequestDate(TimeHelper.getLocalDateTimeNow());
        friendRequestService.create(friendRequest);
    }

    private Song addSongToRoom(Long roomId, String songId, String songName, List<String> artistNames, Integer durationMs, int votes) throws GeneralException {
        Song song = new Song();
        song.setRoomId(roomId);
        song.setSongId(songId);
        song.setSongName(songName);
        song.setArtistNames(artistNames);
        song.setDurationMs(durationMs);
        song.setQueuedTime(TimeHelper.getLocalDateTimeNow());
        song.setSongStatus(SongStatus.NEXT.getSongStatus());
        song.setVotes(votes);

        song = songService.create(song);

        LOGGER.info("songId: {} - songName: {}", song.getId(), songName);

        return song;
    }
}

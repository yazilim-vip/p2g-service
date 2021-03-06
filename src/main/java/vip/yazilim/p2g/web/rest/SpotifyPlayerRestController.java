package vip.yazilim.p2g.web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vip.yazilim.libs.springcore.rest.model.RestResponse;
import vip.yazilim.p2g.web.config.annotation.HasRoomPrivilege;
import vip.yazilim.p2g.web.config.annotation.HasSystemRole;
import vip.yazilim.p2g.web.config.annotation.UpdateRoomSongs;
import vip.yazilim.p2g.web.entity.Song;
import vip.yazilim.p2g.web.enums.Privilege;
import vip.yazilim.p2g.web.enums.Role;
import vip.yazilim.p2g.web.service.spotify.IPlayerService;
import vip.yazilim.p2g.web.util.SecurityHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static vip.yazilim.p2g.web.constant.Constants.API_SPOTIFY;

/**
 * @author mustafaarifsisman - 29.06.2020
 * @contact mustafaarifsisman@gmail.com
 */
@RestController
@RequestMapping(API_SPOTIFY)
public class SpotifyPlayerRestController {

    private final IPlayerService spotifyPlayerService;

    public SpotifyPlayerRestController(IPlayerService spotifyPlayerService) {
        this.spotifyPlayerService = spotifyPlayerService;
    }

    @HasRoomPrivilege(privilege = Privilege.SONG_CONTROL)
    @UpdateRoomSongs
    @PostMapping("/room/play")
    public RestResponse<Boolean> playSong(HttpServletRequest request, HttpServletResponse response, @RequestBody Song song) {
        return RestResponse.generateResponse(spotifyPlayerService.roomPlay(song, 0, true), HttpStatus.OK, request, response);
    }

    @HasRoomPrivilege(privilege = Privilege.SONG_CONTROL)
    @UpdateRoomSongs
    @PostMapping("/room/{roomId}/playPause")
    public RestResponse<Boolean> playPause(HttpServletRequest request, HttpServletResponse response, @PathVariable Long roomId) {
        return RestResponse.generateResponse(spotifyPlayerService.roomPlayPause(roomId), HttpStatus.OK, request, response);
    }

    @HasRoomPrivilege(privilege = Privilege.SONG_CONTROL)
    @UpdateRoomSongs
    @PostMapping("/room/{roomId}/next")
    public RestResponse<Boolean> next(HttpServletRequest request, HttpServletResponse response, @PathVariable Long roomId) {
        return RestResponse.generateResponse(spotifyPlayerService.roomNext(roomId), HttpStatus.OK, request, response);
    }

    @HasRoomPrivilege(privilege = Privilege.SONG_CONTROL)
    @UpdateRoomSongs
    @PostMapping("/room/{roomId}/previous")
    public RestResponse<Boolean> previous(HttpServletRequest request, HttpServletResponse response, @PathVariable Long roomId) {
        return RestResponse.generateResponse(spotifyPlayerService.roomPrevious(roomId), HttpStatus.OK, request, response);
    }

    @HasRoomPrivilege(privilege = Privilege.SONG_CONTROL)
    @UpdateRoomSongs
    @PostMapping("/room/{roomId}/seek/{ms}")
    public RestResponse<Boolean> seek(HttpServletRequest request, HttpServletResponse response, @PathVariable Long roomId, @PathVariable Integer ms) {
        return RestResponse.generateResponse(spotifyPlayerService.roomSeek(roomId, ms), HttpStatus.OK, request, response);
    }

    @HasRoomPrivilege(privilege = Privilege.SONG_CONTROL)
    @UpdateRoomSongs
    @PostMapping("/room/{roomId}/repeat")
    public RestResponse<Boolean> repeat(HttpServletRequest request, HttpServletResponse response, @PathVariable Long roomId) {
        return RestResponse.generateResponse(spotifyPlayerService.roomRepeat(roomId), HttpStatus.OK, request, response);
    }

    @HasSystemRole(role = Role.P2G_USER)
    @PostMapping("/room/sync")
    public RestResponse<Boolean> sync(HttpServletRequest request, HttpServletResponse response) {
        return RestResponse.generateResponse(spotifyPlayerService.syncWithRoom(SecurityHelper.getUserId()), HttpStatus.OK, request, response);
    }
}

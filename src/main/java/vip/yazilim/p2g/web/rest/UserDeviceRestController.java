package vip.yazilim.p2g.web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vip.yazilim.libs.springcore.rest.model.RestResponse;
import vip.yazilim.p2g.web.config.annotation.HasSystemRole;
import vip.yazilim.p2g.web.entity.UserDevice;
import vip.yazilim.p2g.web.enums.Role;
import vip.yazilim.p2g.web.service.p2g.IUserDeviceService;
import vip.yazilim.p2g.web.service.spotify.ISpotifyUserService;
import vip.yazilim.p2g.web.util.SecurityHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static vip.yazilim.p2g.web.constant.Constants.API_SPOTIFY;

/**
 * @author mustafaarifsisman - 29.06.2020
 * @contact mustafaarifsisman@gmail.com
 */
@RestController
@RequestMapping(API_SPOTIFY)
public class UserDeviceRestController {

    private final IUserDeviceService userDeviceService;
    private final ISpotifyUserService spotifyUserService;

    public UserDeviceRestController(IUserDeviceService userDeviceService, ISpotifyUserService spotifyUserService) {
        this.userDeviceService = userDeviceService;
        this.spotifyUserService = spotifyUserService;
    }

    @HasSystemRole(role = Role.P2G_USER)
    @GetMapping({"/user/device"})
    public RestResponse<List<UserDevice>> getUserDeviceList(HttpServletRequest request, HttpServletResponse response) {
        return RestResponse.generateResponse(spotifyUserService.getUsersAvailableDevices(SecurityHelper.getUserId()), HttpStatus.OK, request, response);
    }

    @HasSystemRole(role = Role.P2G_USER)
    @PutMapping({"/user/device"})
    public RestResponse<UserDevice> changeUsersActiveDevice(HttpServletRequest request, HttpServletResponse response, @RequestBody UserDevice userDevice) {
        return RestResponse.generateResponse(userDeviceService.changeUsersActiveDevice(SecurityHelper.getUserId(), userDevice), HttpStatus.OK, request, response);
    }
}

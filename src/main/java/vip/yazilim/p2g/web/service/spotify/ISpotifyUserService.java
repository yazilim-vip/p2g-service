package vip.yazilim.p2g.web.service.spotify;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.User;
import vip.yazilim.p2g.web.entity.UserDevice;
import vip.yazilim.spring.core.exception.general.InvalidArgumentException;
import vip.yazilim.spring.core.exception.general.database.DatabaseException;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @author mustafaarifsisman - 26.11.2019
 * @contact mustafaarifsisman@gmail.com
 */
public interface ISpotifyUserService {

    User getSpotifyUser(String spotifyAccountId) throws IOException, SpotifyWebApiException;
    User getCurrentSpotifyUser(UUID userUuid) throws DatabaseException, IOException, SpotifyWebApiException;

    List<UserDevice> getUsersAvailableDevices(UUID userUuid) throws DatabaseException, IOException, SpotifyWebApiException;
    List<UserDevice> updateUsersAvailableDevices(UUID userUuid) throws DatabaseException, InvalidArgumentException, IOException, SpotifyWebApiException;
}
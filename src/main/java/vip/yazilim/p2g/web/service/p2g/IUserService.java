package vip.yazilim.p2g.web.service.p2g;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import vip.yazilim.p2g.web.constant.Privilege;
import vip.yazilim.p2g.web.constant.Role;
import vip.yazilim.p2g.web.entity.User;
import vip.yazilim.p2g.web.model.UserModel;
import vip.yazilim.spring.core.exception.general.InvalidArgumentException;
import vip.yazilim.spring.core.exception.general.database.DatabaseException;
import vip.yazilim.spring.core.service.ICrudService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * @author mustafaarifsisman - 29.10.2019
 * @contact mustafaarifsisman@gmail.com
 */
public interface IUserService extends ICrudService<User, String> {

    Optional<User> getUserByUsername(String username);
    Optional<User> getUserByEmail(String email) throws DatabaseException;
    UserModel getUserModelByUserId(String userId) throws DatabaseException, InvalidArgumentException;
    List<User> getUsersByroomId(Long roomId) throws DatabaseException, InvalidArgumentException;

    User createUser(String id, String email, String username, String password) throws DatabaseException, InvalidArgumentException;

    User setSpotifyInfo(com.wrapper.spotify.model_objects.specification.User spotifyUser, User user) throws DatabaseException, InvalidArgumentException, IOException, SpotifyWebApiException;

    // Rest
    boolean hasSystemRole(String userId, Role role) throws DatabaseException, InvalidArgumentException;

    boolean hasSystemPrivilege(String userId, Privilege privilege) throws DatabaseException, InvalidArgumentException;
}

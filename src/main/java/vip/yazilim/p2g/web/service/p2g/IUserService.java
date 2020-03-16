package vip.yazilim.p2g.web.service.p2g;

import vip.yazilim.libs.springcore.service.ICrudService;
import vip.yazilim.p2g.web.constant.enums.Privilege;
import vip.yazilim.p2g.web.constant.enums.Role;
import vip.yazilim.p2g.web.entity.User;
import vip.yazilim.p2g.web.model.UserModel;

import java.util.List;

/**
 * @author mustafaarifsisman - 29.10.2019
 * @contact mustafaarifsisman@gmail.com
 */
public interface IUserService extends ICrudService<User, String> {

    UserModel getUserModelByUserId(String userId);

    List<User> getUsersByRoomId(Long roomId);

    User createUser(String id, String email, String username);

    User setSpotifyInfo(com.wrapper.spotify.model_objects.specification.User spotifyUser, User user);

    boolean hasSystemRole(String userId, Role role);

    boolean hasSystemPrivilege(String userId, Privilege privilege);
}

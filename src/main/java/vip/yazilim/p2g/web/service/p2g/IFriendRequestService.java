package vip.yazilim.p2g.web.service.p2g;

import vip.yazilim.p2g.web.entity.FriendRequest;
import vip.yazilim.p2g.web.entity.User;
import vip.yazilim.spring.core.exception.general.InvalidArgumentException;
import vip.yazilim.spring.core.exception.general.InvalidUpdateException;
import vip.yazilim.spring.core.exception.general.database.DatabaseException;
import vip.yazilim.spring.core.exception.general.database.DatabaseReadException;
import vip.yazilim.spring.core.service.ICrudService;

import java.util.List;
import java.util.Optional;

/**
 * @author mustafaarifsisman - 2.11.2019
 * @contact mustafaarifsisman@gmail.com
 */
public interface IFriendRequestService extends ICrudService<FriendRequest, Long> {

    List<User> getFriendsByUserId(String userId) throws DatabaseException, InvalidArgumentException;
    List<User> getFriendRequestsUsersByUserId(String userId) throws DatabaseException, InvalidArgumentException;
    List<FriendRequest> getFriendRequestsByUserId(String userId) throws DatabaseException;
    Optional<FriendRequest> getFriendRequestByUserAndFriendId(String user1, String user2) throws DatabaseReadException;
    boolean createFriendRequest(String user1, String user2) throws DatabaseException, InvalidArgumentException;
    boolean acceptFriendRequest(Long friendRequestId) throws InvalidUpdateException, DatabaseException, InvalidArgumentException;
    boolean ignoreFriendRequest(Long friendRequestId) throws InvalidUpdateException, DatabaseException, InvalidArgumentException;
    boolean rejectFriendRequest(Long friendRequestId) throws InvalidUpdateException, DatabaseException, InvalidArgumentException;
}

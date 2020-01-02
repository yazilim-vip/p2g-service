package vip.yazilim.p2g.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vip.yazilim.p2g.web.entity.FriendRequest;

import java.util.List;

/**
 * @author mustafaarifsisman - 29.10.2019
 * @contact mustafaarifsisman@gmail.com
 */
public interface IFriendRequestRepo extends JpaRepository<FriendRequest, Long> {

    List<FriendRequest> findByUserUuid(String userUuid);
    List<FriendRequest> findByFriendUuid(String friendUuid);

    FriendRequest findByUserUuidAndFriendUuid(String userUuid, String friendUuid);

}

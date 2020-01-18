package vip.yazilim.p2g.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vip.yazilim.p2g.web.entity.RoomUser;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author mustafaarifsisman - 29.10.2019
 * @contact mustafaarifsisman@gmail.com
 */
public interface IRoomUserRepo extends JpaRepository<RoomUser, Long> {

    List<RoomUser> findRoomUserByRoomUuidOrderById(UUID roomUuid);
    Optional<RoomUser> findRoomUserByRoomUuidAndUserUuid(UUID roomUuid, UUID userUuid);
    Optional<RoomUser> findRoomUserByUserUuid(UUID userUuid);

}
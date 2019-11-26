package vip.yazilim.p2g.web.repository.relation;

import org.springframework.data.jpa.repository.JpaRepository;
import vip.yazilim.p2g.web.entity.relation.RoomQueue;

import java.util.List;
import java.util.Optional;

/**
 * @author mustafaarifsisman - 29.10.2019
 * @contact mustafaarifsisman@gmail.com
 */
public interface IRoomQueueRepo extends JpaRepository<RoomQueue, String> {

    Optional<RoomQueue> findByUuid(String uuid);

    List<RoomQueue> findQueuesByRoomUuid(String roomUuid);

}

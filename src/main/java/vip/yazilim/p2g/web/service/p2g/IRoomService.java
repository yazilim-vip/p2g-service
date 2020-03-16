package vip.yazilim.p2g.web.service.p2g;

import vip.yazilim.libs.springcore.service.ICrudService;
import vip.yazilim.p2g.web.entity.Room;
import vip.yazilim.p2g.web.model.RoomModel;
import vip.yazilim.p2g.web.model.RoomModelSimplified;

import java.util.List;
import java.util.Optional;

/**
 * @author mustafaarifsisman - 29.10.2019
 * @contact mustafaarifsisman@gmail.com
 */
public interface IRoomService extends ICrudService<Room, Long> {

    Optional<Room> getRoomByUserId(String userId);

    List<RoomModelSimplified> getSimplifiedRoomModels();

    RoomModel getRoomModelByRoomId(Long roomId);

    RoomModel getRoomModelByUserId(String userId);

    RoomModelSimplified getRoomModelSimplifiedByRoomId(Long roomId);

    RoomModelSimplified getRoomModelSimplifiedWithRoom(Room room);

    Room createRoom(String ownerId, String roomName, String roomPassword);

}
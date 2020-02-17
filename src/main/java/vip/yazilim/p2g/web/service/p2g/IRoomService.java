package vip.yazilim.p2g.web.service.p2g;

import vip.yazilim.p2g.web.entity.Room;
import vip.yazilim.p2g.web.model.RoomModel;
import vip.yazilim.p2g.web.model.RoomModelSimplified;
import vip.yazilim.spring.core.exception.GeneralException;
import vip.yazilim.spring.core.exception.InvalidArgumentException;
import vip.yazilim.spring.core.exception.database.DatabaseException;
import vip.yazilim.spring.core.service.ICrudService;

import java.util.List;
import java.util.Optional;

/**
 * @author mustafaarifsisman - 29.10.2019
 * @contact mustafaarifsisman@gmail.com
 */
public interface IRoomService extends ICrudService<Room, Long> {

    Optional<Room> getRoomByUserId(String userId) throws DatabaseException;

    //Rest
    List<RoomModelSimplified> getSimplifiedRoomModels() throws DatabaseException, InvalidArgumentException;
    RoomModel getRoomModelByRoomId(Long roomId) throws DatabaseException, InvalidArgumentException;
    RoomModelSimplified getRoomModelSimplifiedByRoomId(Long roomId) throws DatabaseException, InvalidArgumentException;
    Room createRoom(String ownerId, String roomName, String roomPassword) throws GeneralException;

}
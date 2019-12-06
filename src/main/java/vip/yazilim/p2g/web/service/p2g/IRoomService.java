package vip.yazilim.p2g.web.service.p2g;

import vip.yazilim.p2g.web.entity.Room;
import vip.yazilim.p2g.web.exception.RoomException;
import vip.yazilim.p2g.web.model.RoomModel;
import vip.yazilim.spring.core.exception.InvalidArgumentException;
import vip.yazilim.spring.core.exception.database.DatabaseException;
import vip.yazilim.spring.core.service.ICrudService;

import java.util.Optional;

/**
 * @author mustafaarifsisman - 29.10.2019
 * @contact mustafaarifsisman@gmail.com
 */
public interface IRoomService extends ICrudService<Room, String> {

    Optional<Room> getRoomByUserUuid(String userUuid) throws DatabaseException, RoomException;

    //Rest
    Optional<RoomModel> getRoomModelByRoomUuid(String uuid) throws DatabaseException, RoomException, InvalidArgumentException;
    Room createRoom(String name, String ownerUuid, String password, Integer maxUsers, Boolean usersAllowedQueue, Boolean usersAllowedControl, String chatUuid) throws RoomException;
    boolean deleteRoom(String roomUuid) throws DatabaseException, InvalidArgumentException, RoomException;

}
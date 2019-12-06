package vip.yazilim.p2g.web.service.p2g.relation;

import vip.yazilim.p2g.web.entity.relation.RoomUser;
import vip.yazilim.spring.core.exception.database.DatabaseException;
import vip.yazilim.spring.core.service.ICrudService;

import java.util.List;
import java.util.Optional;

/**
 * @author mustafaarifsisman - 29.10.2019
 * @contact mustafaarifsisman@gmail.com
 */
public interface IRoomUserService extends ICrudService<RoomUser, String> {

    List<RoomUser> getRoomUsersByRoomUuid(String roomUuid) throws DatabaseException;
    Optional<RoomUser> getRoomUser(String userUuid) throws DatabaseException;
    Optional<RoomUser> getRoomUser(String roomUuid, String userUuid) throws DatabaseException;

}

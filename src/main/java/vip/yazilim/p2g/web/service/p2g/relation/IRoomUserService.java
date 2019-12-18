package vip.yazilim.p2g.web.service.p2g.relation;

import vip.yazilim.p2g.web.constant.Privilege;
import vip.yazilim.p2g.web.constant.Role;
import vip.yazilim.p2g.web.entity.relation.RoomInvite;
import vip.yazilim.p2g.web.entity.relation.RoomUser;
import vip.yazilim.p2g.web.exception.InviteException;
import vip.yazilim.p2g.web.exception.RoomException;
import vip.yazilim.spring.core.exception.general.InvalidArgumentException;
import vip.yazilim.spring.core.exception.general.InvalidUpdateException;
import vip.yazilim.spring.core.exception.general.database.DatabaseException;
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

    RoomUser joinRoom(String roomUuid, String userUuid, String password, Role role) throws DatabaseException, InvalidArgumentException;
    RoomUser joinRoomOwner(String roomUuid, String userUuid) throws DatabaseException, InvalidArgumentException;

    RoomUser acceptRoomInvite(RoomInvite roomInvite) throws DatabaseException, InviteException, InvalidArgumentException;

    Role getRoleByRoomUuidAndUserUuid(String roomUuid, String userUuid) throws DatabaseException;

    // Rest
    boolean deleteRoomUsers(String roomUuid) throws DatabaseException;

    List<Privilege> promoteUserRole(String roomUserUuid) throws DatabaseException, InvalidUpdateException, InvalidArgumentException, RoomException;
    List<Privilege> demoteUserRole(String roomUserUuid) throws DatabaseException, InvalidUpdateException, InvalidArgumentException, RoomException;

    boolean hasRoomPrivilege(String userUuid, Privilege privilege) throws DatabaseException;

    boolean hasRoomRole(String userUuid, Role role) throws DatabaseException;
}

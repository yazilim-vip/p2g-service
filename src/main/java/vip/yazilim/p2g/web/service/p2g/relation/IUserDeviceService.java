package vip.yazilim.p2g.web.service.p2g.relation;

import vip.yazilim.p2g.web.entity.relation.UserDevice;
import vip.yazilim.spring.utils.exception.DatabaseException;
import vip.yazilim.spring.utils.service.ICrudService;

import java.util.List;

/**
 * @author mustafaarifsisman - 30.11.2019
 * @contact mustafaarifsisman@gmail.com
 */
public interface IUserDeviceService extends ICrudService<UserDevice, String> {

    List<UserDevice> getDevicesByUserUuid(String userUuid) throws DatabaseException;

    List<UserDevice> getDevicesByRoomUuid(String roomUuid) throws DatabaseException;
}

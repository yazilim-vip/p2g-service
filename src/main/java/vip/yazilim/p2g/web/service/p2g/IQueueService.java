package vip.yazilim.p2g.web.service.p2g;

import vip.yazilim.p2g.web.constant.QueueStatus;
import vip.yazilim.p2g.web.entity.relation.RoomQueue;
import vip.yazilim.p2g.web.model.SearchModel;
import vip.yazilim.spring.utils.exception.DatabaseException;
import vip.yazilim.spring.utils.service.ICrudService;

import java.util.List;

/**
 * @author mustafaarifsisman - 29.10.2019
 * @contact mustafaarifsisman@gmail.com
 */
public interface IQueueService extends ICrudService<RoomQueue, String> {

    List<RoomQueue> getQueueListByRoomUuid(String roomUuid) throws DatabaseException;
    RoomQueue addToQueue(String roomUuid, SearchModel searchModel) throws DatabaseException;
    boolean removeFromQueue(RoomQueue roomQueue) throws DatabaseException;
    List<RoomQueue> getQueueListByRoomUuidAndStatus(String roomUuid, QueueStatus queueStatus) throws DatabaseException;

}

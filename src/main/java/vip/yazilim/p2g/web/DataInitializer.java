package vip.yazilim.p2g.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import vip.yazilim.p2g.web.entity.Room;
import vip.yazilim.p2g.web.entity.User;
import vip.yazilim.p2g.web.exception.RoomException;
import vip.yazilim.p2g.web.exception.UserException;
import vip.yazilim.p2g.web.service.p2g.IRoomService;
import vip.yazilim.p2g.web.service.p2g.IUserService;
import vip.yazilim.p2g.web.service.p2g.relation.IRoomQueueService;
import vip.yazilim.p2g.web.service.p2g.relation.IRoomUserService;
import vip.yazilim.spring.core.exception.InvalidArgumentException;
import vip.yazilim.spring.core.exception.database.DatabaseException;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoomService roomService;

    @Autowired
    private IRoomUserService roomUserService;

    @Autowired
    private IRoomQueueService roomQueueService;

    @Override
    public void run(String... args) throws RoomException, DatabaseException, InvalidArgumentException, UserException {
        User arif = userService.createUser("1", "arif", "arif", "0");
        User emre = userService.createUser("2", "emre", "emre", "0");

        Room room = roomService.createRoom("Test Room", arif.getUuid(), null, 5, false, false, null);
        String roomUuid = room.getUuid();

        roomUserService.joinRoom(roomUuid, arif.getUuid(), null);
        roomUserService.joinRoom(roomUuid, emre.getUuid(), null);

        roomQueueService.addToRoomQueue(roomUuid, "4VqPOruhp5EdPBeR92t6lQ", "spotify:track:4VqPOruhp5EdPBeR92t6lQ", "Uprising", 1200000L);
        roomQueueService.addToRoomQueue(roomUuid, "12Chz98pHFMPJEknJQMWvI", "spotify:artist:12Chz98pHFMPJEknJQMWvI", "Madness", 1200000L);
        roomQueueService.addToRoomQueue(roomUuid, "7ouMYWpwJ422jRcDASZB7P", "spotify:track:7ouMYWpwJ422jRcDASZB7P", "Knights of Cydonia", 1200000L);
        roomQueueService.addToRoomQueue(roomUuid, "12Chz98pHFMPJEknJQMWvI", "spotify:track:12Chz98pHFMPJEknJQMWvI", "Time Is Running Out", 1200000L);
        roomQueueService.addToRoomQueue(roomUuid, "0eFHYz8NmK75zSplL5qlfM", "spotify:track:0eFHYz8NmK75zSplL5qlfM", "The Resistance", 1200000L);

    }

}

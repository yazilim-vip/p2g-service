package vip.yazilim.p2g.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import vip.yazilim.p2g.web.constant.QueueStatus;
import vip.yazilim.p2g.web.entity.Room;
import vip.yazilim.p2g.web.entity.User;
import vip.yazilim.p2g.web.entity.relation.RoomQueue;
import vip.yazilim.p2g.web.service.p2g.IRoomService;
import vip.yazilim.p2g.web.service.p2g.IUserService;
import vip.yazilim.p2g.web.service.p2g.relation.IRoomQueueService;
import vip.yazilim.spring.utils.exception.DatabaseException;

import java.util.Date;

@Component
public class DataInitializer implements CommandLineRunner {

    private Logger LOGGER = LoggerFactory.getLogger(DataInitializer.class);

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoomService roomService;

    @Autowired
    private IRoomQueueService roomQueueService;

    @Override
    public void run(String... args) {
        User arif = createUser("1", "arif", "arif", "0");
        User emre = createUser("2", "emre", "emre", "0");

        Room room = createRoom("Test Room", arif.getUuid());

        roomService.joinRoom(room.getUuid(), arif.getUuid());
//        roomService.joinRoom(room.getUuid(), emre.getUuid());

        createRoomQueue("1", "0R0o8uOkDz40XsR6uwThfQ", "spotify:track:0R0o8uOkDz40XsR6uwThfQ", "geceleeer", 1200000L);
        createRoomQueue("1", "7MXVkk9YMctZqd1Srtv4MB", "spotify:track:7MXVkk9YMctZqd1Srtv4MB", "sitarboy", 1200000L);
        createRoomQueue("1", "5CMjjywI0eZMixPeqNd75R", "spotify:track:5CMjjywI0eZMixPeqNd75R", "lusyorselftudens", 1200000L);
        createRoomQueue("1", "5GXAXm5YOmYT0kL5jHvYBt", "spotify:track:5GXAXm5YOmYT0kL5jHvYBt", "ayfilitkaming", 1200000L);
        createRoomQueue("1", "0DiWol3AO6WpXZgp0goxAV", "spotify:track:0DiWol3AO6WpXZgp0goxAV", "vanmortaym", 1200000L);

    }

    private User createUser(String uuid, String email, String username, String password) {
        User user = new User();
        user.setUuid(uuid);
        user.setEmail(email);
        user.setDisplayName(username);
        user.setPassword(password);

        try {
            userService.create(user);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }

        return user;
    }

    private Room createRoom(String name, String owner) {
        Room room = new Room();

        room.setUuid("1");
        room.setName(name);
        room.setOwnerUuid(owner);
        room.setPrivateFlag(false);

        try {
            roomService.create(room);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }

        return room;
    }

    private RoomQueue createRoomQueue(String roomUuid, String songId, String songUri, String songName, Long durationMs) {
        RoomQueue roomQueue = new RoomQueue();
        roomQueue.setRoomUuid(roomUuid);
        roomQueue.setSongId(songId);
        roomQueue.setSongUri(songUri);
        roomQueue.setSongName(songName);
        roomQueue.setDurationMs(durationMs);
        roomQueue.setQueuedTime(new Date());
        roomQueue.setQueueStatus(QueueStatus.IN_QUEUE.getQueueStatus());

        try {
            roomQueue = roomQueueService.create(roomQueue);
            LOGGER.info("queueUuid: {} - songName: {}", roomQueue.getUuid(), songName);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }

        return roomQueue;
    }

}

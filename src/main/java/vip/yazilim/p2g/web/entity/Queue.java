package vip.yazilim.p2g.web.entity;

import lombok.Data;
import vip.yazilim.p2g.web.constant.Constants;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = Constants.TABLE_PREFIX + "queue")
@Data
public class Queue implements Serializable {

    @Id
    private String uuid;

    @Column(name = "room_uuid")
    private String roomUuid;

    @Column(name = "song_uuid")
    private String songUuid;

    @Column(name = "queue_time")
    private Timestamp queuedTime;

    private String status;

    private int votes;

}
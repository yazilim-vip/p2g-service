package vip.yazilim.p2g.web.entity.relation;

import lombok.Data;
import vip.yazilim.p2g.web.constant.Constants;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = Constants.RELATION_TABLE_PREFIX + "user_friends")
@Data
public class UserFriends implements Serializable {

    @Id
    private String uuid;

    @Column(name = "user_uuid")
    private String userUuid;

    @Column(name = "friend_uuid")
    private String friendUuid;

    @Column(name = "request_status")
    private String requestStatus;

    @Column(name = "request_date")
    private Date requestDate;
}

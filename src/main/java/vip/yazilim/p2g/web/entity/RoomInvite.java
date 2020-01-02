package vip.yazilim.p2g.web.entity;

import lombok.Data;
import vip.yazilim.p2g.web.constant.Constants;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = Constants.TABLE_PREFIX + "room_invite")
@Data
public class RoomInvite {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "room_id", length = 64)
    private Long roomId;

    @Column(name = "user_uuid", columnDefinition = "uuid")
    private UUID userUuid;

    @Column(name = "invitation_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime invitationDate;

    @Column(name = "accepted_flag")
    private Boolean acceptedFlag;

}

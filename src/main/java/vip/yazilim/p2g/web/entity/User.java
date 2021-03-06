package vip.yazilim.p2g.web.entity;

import lombok.Data;
import vip.yazilim.p2g.web.constant.Constants;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author mustafaarifsisman - 29.10.2019
 * @contact mustafaarifsisman@gmail.com
 */
@Entity
@Table(name = Constants.TABLE_PREFIX + "user")
@Data
public class User implements Serializable {

    @Id
    @Column(name = "id", unique = true, updatable = false, nullable = false)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "system_role", nullable = false, length = 31)
    private String systemRole;

    @Column(name = "online_status", length = 31, nullable = false)
    private String onlineStatus;

    @Column(name = "country_code", length = 7)
    private String countryCode;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "creation_date", columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "last_login", columnDefinition = "TIMESTAMP")
    private LocalDateTime lastLogin;

}

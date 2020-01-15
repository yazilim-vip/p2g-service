package vip.yazilim.p2g.web.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import vip.yazilim.p2g.web.constant.Constants;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author mustafaarifsisman - 29.10.2019
 * @contact mustafaarifsisman@gmail.com
 */
@Entity
@Table(name = Constants.TABLE_PREFIX + "user")
@Data
public class User implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "uuid", unique = true, updatable = false, nullable = false)
    private UUID uuid;

    @Column(name = "display_name", nullable = false, length = 64)
    private String displayName;

    @Column(unique = true, nullable = false, length = 64)
    private String email;

    @Column(nullable = false, length = 64)
    private String password;

    @Column(name = "role_name", nullable = false, length = 16)
    private String roleName;

    @Column(name = "online_status", length = 16)
    private String onlineStatus;

    @Column(name = "country_code", length = 4)
    private String countryCode;

    @Column(name = "image_url", length = 128)
    private String imageUrl;

    @Column(length = 64)
    private String anthem;

    @Column(name = "spotify_product_type", length = 16)
    private String spotifyProductType;

    @Column(name = "spotify_account_id", length = 64)
    private String spotifyAccountId;

    @Column(name = "show_activity_flag")
    private Boolean showActivityFlag;

    @Column(name = "show_friends_flag")
    private Boolean showFriendsFlag;

    @Column(name = "creation_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime creationDate;

}

package vip.yazilim.p2g.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vip.yazilim.p2g.web.entity.UserDevice;

import java.util.List;
import java.util.Optional;

/**
 * @author mustafaarifsisman - 30.11.2019
 * @contact mustafaarifsisman@gmail.com
 */
public interface IUserDeviceRepo extends JpaRepository<UserDevice, String> {

    List<UserDevice> findByUserIdOrderByActiveFlagDesc(String userId);

    Optional<UserDevice> findByUserIdAndActiveFlag(String userId, Boolean activeFlag);

}

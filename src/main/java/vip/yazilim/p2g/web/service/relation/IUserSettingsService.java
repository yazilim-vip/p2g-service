package vip.yazilim.p2g.web.service.relation;

import vip.yazilim.p2g.web.entity.relation.UserSettings;
import vip.yazilim.spring.utils.service.ICrudService;

/**
 * @author mustafaarifsisman - 29.10.2019
 * @contact mustafaarifsisman@gmail.com
 */
public interface IUserSettingsService extends ICrudService<UserSettings, String> {

    UserSettings getUserSettings(String userUuid);

}

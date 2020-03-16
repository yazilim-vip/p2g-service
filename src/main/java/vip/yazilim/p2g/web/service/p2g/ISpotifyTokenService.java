package vip.yazilim.p2g.web.service.p2g;

import vip.yazilim.libs.springcore.service.ICrudService;
import vip.yazilim.p2g.web.entity.OAuthToken;

import java.util.List;
import java.util.Optional;

/**
 * @author mustafaarifsisman - 29.10.2019
 * @contact mustafaarifsisman@gmail.com
 */
public interface ISpotifyTokenService extends ICrudService<OAuthToken, String> {

    String getAccessTokenByUserId(String userId);

    Optional<OAuthToken> getTokenByUserId(String userId);

    OAuthToken saveUserToken(String userId, String accessToken, String refreshToken);

    String saveUserToken(String userId, String accessToken);

    List<OAuthToken> getTokenListByRoomId(Long roomId);
}
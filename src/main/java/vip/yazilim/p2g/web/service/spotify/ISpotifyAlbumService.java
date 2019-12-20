package vip.yazilim.p2g.web.service.spotify;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import vip.yazilim.p2g.web.model.SearchModel;

import java.io.IOException;
import java.util.List;

/**
 * @author mustafaarifsisman - 29.11.2019
 * @contact mustafaarifsisman@gmail.com
 */
public interface ISpotifyAlbumService {

    List<SearchModel> getSongs(String albumId) throws IOException, SpotifyWebApiException;

}

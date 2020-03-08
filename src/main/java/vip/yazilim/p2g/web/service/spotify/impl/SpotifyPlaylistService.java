package vip.yazilim.p2g.web.service.spotify.impl;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vip.yazilim.p2g.web.model.SearchModel;
import vip.yazilim.p2g.web.service.p2g.ISpotifyTokenService;
import vip.yazilim.p2g.web.service.spotify.ISpotifyPlaylistService;
import vip.yazilim.p2g.web.service.spotify.ISpotifyRequestService;
import vip.yazilim.p2g.web.util.SecurityHelper;
import vip.yazilim.spring.core.exception.database.DatabaseException;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author mustafaarifsisman - 29.11.2019
 * @contact mustafaarifsisman@gmail.com
 */
@Transactional
@Service
public class SpotifyPlaylistService implements ISpotifyPlaylistService {

    @Autowired
    private ISpotifyRequestService spotifyRequest;

    @Autowired
    private ISpotifyTokenService tokenService;

    @Override
    public List<SearchModel> getSongs(String playlistId) throws IOException, SpotifyWebApiException, DatabaseException {
        List<SearchModel> searchModelList = new LinkedList<>();

        String userId = SecurityHelper.getUserId();
        String accessToken = tokenService.getAccessTokenByUserId(userId);

        Paging<PlaylistTrack> dataRequest = spotifyRequest.execRequestSync(spotifyApi -> spotifyApi.getPlaylistsTracks(playlistId).build(), accessToken);
        PlaylistTrack[] playlistTracks = dataRequest.getItems();

        for (PlaylistTrack p : playlistTracks) {
            SearchModel searchModel = new SearchModel(p.getTrack());
            searchModelList.add(searchModel);
        }

        return searchModelList;
    }
}

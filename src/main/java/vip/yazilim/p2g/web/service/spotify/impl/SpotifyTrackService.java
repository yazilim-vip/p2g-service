package vip.yazilim.p2g.web.service.spotify.impl;

import com.wrapper.spotify.model_objects.specification.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vip.yazilim.p2g.web.model.SearchModel;
import vip.yazilim.p2g.web.service.spotify.ISpotifyRequestService;
import vip.yazilim.p2g.web.service.spotify.ISpotifyTrackService;
import vip.yazilim.p2g.web.util.SpotifyHelper;

import java.util.List;

/**
 * @author mustafaarifsisman - 29.11.2019
 * @contact mustafaarifsisman@gmail.com
 */
@Service
public class SpotifyTrackService implements ISpotifyTrackService {

    @Autowired
    private ISpotifyRequestService spotifyRequest;

    @Override
    public SearchModel getTrack(String id) {
        return new SearchModel(spotifyRequest.execRequestSync((spotifyApi) -> spotifyApi.getTrack(id).build()));
    }

    @Override
    public List<SearchModel> getSeveralTracks(String[] ids) {
        Track[] tracks = spotifyRequest.execRequestSync((spotifyApi) -> spotifyApi.getSeveralTracks(ids).build());
        return SpotifyHelper.convertToSearchModelList(tracks);
    }
}

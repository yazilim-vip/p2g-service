package vip.yazilim.p2g.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import vip.yazilim.p2g.web.entity.Song;
import vip.yazilim.p2g.web.exception.DatabaseException;
import vip.yazilim.p2g.web.repository.ISongRepo;
import vip.yazilim.p2g.web.service.ISongService;

import java.util.List;
import java.util.Optional;

/**
 * @author mustafaarifsisman - 29.10.2019
 * @contact mustafaarifsisman@gmail.com
 */
@Service
public class SongServiceImpl extends ACrudServiceImpl<Song, String> implements ISongService {

    @Autowired
    ISongRepo songRepo;

    @Override
    protected JpaRepository<Song, String> getRepository() {
        return songRepo;
    }

    @Override
    protected String getId(Song entity) {
        return entity.getUuid();
    }

    @Override
    public Optional<Song> getSongByName(String songName) {
        return songRepo.findBySongName(songName);
    }
}
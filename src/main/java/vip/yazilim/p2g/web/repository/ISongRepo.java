package vip.yazilim.p2g.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vip.yazilim.p2g.web.entity.Song;

import java.util.Optional;

/**
 * @author mustafaarifsisman - 29.10.2019
 * @contact mustafaarifsisman@gmail.com
 */
public interface ISongRepo extends JpaRepository<Song, String> {

    Optional<Song> findByUuid(String uuid);
    Optional<Song> findBySongId(String singId);
    Optional<Song> findByArtistName(String artistName);
    Optional<Song> findBySongName(String songName);

}
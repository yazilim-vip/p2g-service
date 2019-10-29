package vip.yazilim.p2g.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vip.yazilim.p2g.web.entity.Album;

import java.util.Optional;

/**
 * @author mustafaarifsisman - 29.10.2019
 * @contact mustafaarifsisman@gmail.com
 */
public interface IAlbumRepo extends JpaRepository<Album, String> {

    Optional<Album> findByUuid(String uuid);
    Iterable<Album> findByAlbumId(String albumId);

}
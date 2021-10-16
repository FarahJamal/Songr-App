package me.farah.songrapp.repository;

import me.farah.songrapp.model.Album;
import me.farah.songrapp.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface SongRepo extends JpaRepository<Song,Long> {
  List  <Song> findByAlbumId(Long album_id);
List<Song> findAllByAlbum_Title(String title);

}
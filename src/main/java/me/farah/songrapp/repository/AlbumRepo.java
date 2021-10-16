package me.farah.songrapp.repository;

import me.farah.songrapp.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepo extends JpaRepository<Album,Long> {
    Album findByTitle(String title);
}

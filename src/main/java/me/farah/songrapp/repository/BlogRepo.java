package me.farah.songrapp.repository;

import me.farah.songrapp.model.Album;
import me.farah.songrapp.model.Blogs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepo extends JpaRepository<Blogs, Long> {
    List<Blogs> findAllByAppUser_Username(String name);
    void deletePostByAppUser_UsernameAndId(String username, Long id);

}

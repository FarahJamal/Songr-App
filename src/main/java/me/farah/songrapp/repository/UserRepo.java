package me.farah.songrapp.repository;

import me.farah.songrapp.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<AppUser, Long> {
    AppUser findAppUserByEmail(String email);
    AppUser findAppUsersByUsername(String username);
}

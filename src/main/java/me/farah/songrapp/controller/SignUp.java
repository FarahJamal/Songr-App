package me.farah.songrapp.controller;


import me.farah.songrapp.model.AppUser;
import me.farah.songrapp.repository.UserRepo;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.result.view.*;

@Controller
public class SignUp {


    // constructor injection!
    private final UserRepo userRepository;
    public SignUp(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/signup")
    public String getSignUpPage() {
        return "signup";
    }
    @PostMapping("/signup/users")
    public String signUpNewUser(@ModelAttribute AppUser appUser) {
        appUser.setPassword(BCrypt.hashpw(appUser.getPassword(), BCrypt.gensalt())); // we have encrypted the user password
        userRepository.save(appUser);

        // we should then show the post creation page
        return ("redirect:/signin/users");
    }
}

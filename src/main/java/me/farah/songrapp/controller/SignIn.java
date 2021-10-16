package me.farah.songrapp.controller;


import me.farah.songrapp.model.AppUser;
import me.farah.songrapp.repository.UserRepo;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.result.view.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.*;

@Controller
public class SignIn {
    // constructor injection!
    private final UserRepo userRepository;

    public SignIn(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/signin/users")
    public String getSignInPage() {
        return "signin";
    }

    @PostMapping("/signin/users")
    public String signInUser(HttpServletRequest request, @ModelAttribute AppUser appUser) {

        AppUser foundUser = userRepository.findAppUsersByUsername(appUser.getUsername());
        String savedUserPassword = foundUser.getPassword();

        if (BCrypt.checkpw(appUser.getPassword(), savedUserPassword)) { // successfully sign in
            // store session data - in this case the username
            HttpSession session = request.getSession();
            session.setAttribute("username", foundUser.getUsername());

            return ("redirect:/v2/blogs");
        } else {
           // RedirectView redirectView = new RedirectView("", HttpStatus.OK);
//            attributes.addAttribute("username", foundUser.getUsername());
            return ("redirect:/failedSignIn");

        }
    }

    @GetMapping("/failedSignIn")
    public String getSignInErrorPage() {
        return "failedSignIn";
    }

    @GetMapping("/sessiondata")
    @ResponseBody
    public String getSessionData(HttpServletRequest request) {
        return (String) request.getSession().getAttribute("username");
    }
}

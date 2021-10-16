package me.farah.songrapp.controller;

import me.farah.songrapp.exception.NotFoundEception;
import me.farah.songrapp.model.Album;
import me.farah.songrapp.model.AppUser;
import me.farah.songrapp.model.Blogs;
import me.farah.songrapp.model.Song;
import me.farah.songrapp.model.dto.BlogDTO;
import me.farah.songrapp.model.dto.SongDTO;
import me.farah.songrapp.repository.BlogRepo;
import me.farah.songrapp.repository.UserRepo;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.*;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@Controller
public class BlogsController {
    @Autowired
    private BlogRepo blogRepo;

    // this way is better
    // constructor injection
    private final UserRepo userRepository;

    public BlogsController(UserRepo userRepository) {
        this.userRepository = userRepository;
    }




    @GetMapping("/v2/blogs")
    public String getBlogs(Model model, HttpServletRequest request) {
        if(request.getSession().getAttribute("username") != null){

        model.addAttribute("blogs", blogRepo.findAll());

        String username = (String) request.getSession().getAttribute("username");

        model.addAttribute("username", username);
            return "blogs";

}
        else {
            return "error";
        }
    }

    @PostMapping("/v2/blogs")
    public String createNewBlogPost(@ModelAttribute BlogDTO blogDTO) { // modelattribute when working with fomr data
        AppUser user = userRepository.findAppUsersByUsername(blogDTO.getUser());
        Blogs newBlog = new Blogs(user, blogDTO.getContent());
        blogRepo.save(newBlog);

        return ("redirect:/v2/blogs");
    }
    @PostMapping("/edit/blog/{id}")
    public String updateBlog(@PathVariable(value = "id") Long blogId,@ModelAttribute Blogs blogDetails)  throws NotFoundEception {
        Blogs blog = blogRepo.findById(blogId).orElseThrow(()-> new NotFoundEception("Blog not found for this id :: " + blogId));
        blogRepo.save(blog);
        return "redirect:/v2/blogs";
    }


    @GetMapping("/edit/blog/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Blogs blog = blogRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Blog Id:" + id));
        model.addAttribute("blog", blog);
        System.out.println(blog);
        return "updateBlog";
    }


    @PostMapping("/update/blog/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid Blogs blog, BindingResult result) {
        if (result.hasErrors()) {
            blog.setId(id);
            return "updateBlog";
        }

        blogRepo.save(blog);

        return "redirect:/v2/blogs";
    }
    @GetMapping("/v2/blogs/users/{username}")
    public String findPostByUsername(@PathVariable String username, Model model,HttpServletRequest request) {
        if (request.getSession().getAttribute("username") != null) {

            List<Blogs> blogs = blogRepo.findAllByAppUser_Username(username);
            model.addAttribute("authorBlog", blogs);

            return "blog";
        }
        else{
            return "error";

        }
    }

    @RequestMapping("/delete/blog/{id}")
    public String deleteAlbum(@PathVariable long id){
        blogRepo.deleteById(id);
        return "redirect:/v2/blogs";
    }



}

package me.farah.songrapp.controller;
import javax.validation.Valid;
import me.farah.songrapp.exception.NotFoundEception;
import me.farah.songrapp.model.Album;
import me.farah.songrapp.repository.AlbumRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import org.springframework.transaction.annotation.Transactional;
@Controller
public class AlbumsController {

    @Autowired
    private AlbumRepo albumRepository;


    @PostMapping("/albums")
    public String createNewAlbum(@ModelAttribute Album album) {
        albumRepository.save(album);
        return ("redirect:albums");
    }

    @GetMapping("/albums")
    public String getAlbums(Model model) {
        model.addAttribute("albums", albumRepository.findAll());
        return "albums";
    }


    @RequestMapping("/delete/{id}")
    public String deleteAlbum(@PathVariable long id){
        albumRepository.deleteById(id);
        return "redirect:/albums";
    }




    @PostMapping("/edit/{id}")
    public String updateAlbum(@PathVariable(value = "id") Long albumId, @ModelAttribute Album albumDetails)  throws NotFoundEception {
        Album album = albumRepository.findById(albumId).orElseThrow(()-> new NotFoundEception("Album not found for this id :: " + albumId));
        Album updatedAlbum = albumRepository.save(album);
        return "redirect:/albums";
    }
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Album album = albumRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Album Id:" + id));
        model.addAttribute("album", album);

        return "updateAlbum";
    }



    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid Album album, BindingResult result) {
        if (result.hasErrors()) {
            album.setId(id);
            return "updateAlbum";
        }

        albumRepository.save(album);

        return "redirect:/albums";
    }

}

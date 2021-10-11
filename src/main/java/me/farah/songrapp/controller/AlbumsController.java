package me.farah.songrapp.controller;
import me.farah.songrapp.model.Album;
import me.farah.songrapp.repository.AlbumRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.reactive.result.view.RedirectView;

import java.util.List;

@Controller
public class AlbumsController {

    @Autowired
    AlbumRepo albumRepository;

    @GetMapping("/albums")
    public String generateAlbums(Model album) {
        List<Album> albums = albumRepository.findAll();
        album.addAttribute("albums", albums);
//        albumRepository.saveAll(albums);
        return "albums";
    }

    @PostMapping("/albums")
    public RedirectView createAlbum(@ModelAttribute String title, String artist, int songCount, int length, String imgUrl){
        Album newAlbum = new Album(title, artist, songCount, length, imgUrl);
        albumRepository.save(newAlbum);
        System.out.println(newAlbum.getImageUrl());
        return new RedirectView("albums");
    }
}

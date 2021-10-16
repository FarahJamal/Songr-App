package me.farah.songrapp.controller;

import me.farah.songrapp.model.Album;
import me.farah.songrapp.model.Song;
import me.farah.songrapp.model.dto.SongDTO;
import me.farah.songrapp.repository.AlbumRepo;
import me.farah.songrapp.repository.SongRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class SongController {

    @Autowired
    private AlbumRepo albumRepository;

    @Autowired
    private SongRepo songRepository;

    @GetMapping("/songs")
    public String showAllSongs(Model model) {
        model.addAttribute("songs",songRepository.findAll());
        model.addAttribute("albums",albumRepository.findAll());

        return "songs";
    }

    @PostMapping("/songs")
    public String addSong(SongDTO song,Model model) {

        Album newAlbum = albumRepository.findByTitle(song.getAlbum());
        Song newSong = new Song(song.getTitle(),song.getLength(),newAlbum);

        songRepository.save(newSong);
        return ("redirect:songs");
    }
    @GetMapping("/songs/{album_id}")
    public String showSongByAlbum(@PathVariable Long album_id,Model model){
        List <Song> songs =songRepository.findByAlbumId(album_id);
        model.addAttribute("albumSongs",songs);
        return "songByAlbum";
    }

    @GetMapping("/songs/album/{album}")
    public String getSongsByAlbum(@PathVariable String album, Model model){
        List<Song> songs = songRepository.findAllByAlbum_Title(album);
        model.addAttribute("songs", songs);
        return "songByAlbumName";
    }
    /**
     *  List<Post> posts = postRepository.findAllByAuthor_Username(author);
     *         model.addAttribute("authorPost", posts);
     *
     * */
}

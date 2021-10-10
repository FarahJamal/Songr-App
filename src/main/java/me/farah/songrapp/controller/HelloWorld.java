package me.farah.songrapp.controller;
import com.sun.org.apache.xpath.internal.operations.Or;
import me.farah.songrapp.model.Album;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;


@Controller
public class HelloWorld {

    @PostMapping("/hello")
    public String test(@RequestParam(name = "name", required = false, defaultValue = "Guest") String name,
                       Model model) {
        model.addAttribute("name", name); // this is passed to the template automatically
        return "hello"; // this represents the name of the html template which will be rendered
    }

    @GetMapping("/capitalize/{word}")
    public String getCapitalize(Model model, @PathVariable String word) {
        model.addAttribute("word", word.toUpperCase());
        return "cap";
    }

    @GetMapping("/")
    public String splashScreen() {
        return "splash";
    }

    @GetMapping("/album")
    public String getAlbum(Model model) {

        Album Origins = new Album("Origins", "Imagine Dragons", 13, 4000, "https://bloximages.newyork1.vip.townnews.com/kentwired.com/content/tncms/assets/v3/editorial/1/02/10251604-e847-11e8-a68c-ebe02c431a82/5bec7d8ba5878.image.jpg");
        Album Night_Visions = new Album("Night_Visions", "Imagine Dragons", 11, 3500, "https://upload.wikimedia.org/wikipedia/en/3/3f/Night_Visions_Album_Cover.jpeg");
        Album Native = new Album("Native", "OneRepublic", 17, 2800, "https://cdns-images.dzcdn.net/images/cover/63bb716faf92942d967f5c053fc8720c/500x500.jpg");
        List<Album> albums = new ArrayList<Album>();
        albums.add(Origins);
        albums.add(Night_Visions);
        albums.add(Native);
        model.addAttribute("albums", albums);
        return "albumCards";
    }

    // stretch goals
    @GetMapping("/numbers/{number}")
    public String getFact(Model model, @PathVariable String number) {

        final String uri = "http://numbersapi.com/"+number;

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        System.out.println(result);
        model.addAttribute("number",result);
        model.addAttribute("added",number);
        return "fact_from_number";
    }

        @GetMapping("/print-all-headers")
        public String getAllheaders(@RequestHeader Map<String,String> headers,Model model){
        List<String>list=new ArrayList<>();
            headers.forEach((key,value) ->{
                System.out.println("Header Name: "+key+" Header Value: "+value);
                list.add('{'+"Header Name: "+key+" Header Value: "+value+"\n"+"}");



            });
                model.addAttribute("header", Arrays.toString(list.toArray())); // [John, Mary, Bob]);


           return "info";
        }



}

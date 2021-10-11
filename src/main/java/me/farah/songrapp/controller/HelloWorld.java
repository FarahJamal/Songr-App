package me.farah.songrapp.controller;
import me.farah.songrapp.model.Album;
import me.farah.songrapp.repository.AlbumRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.result.view.RedirectView;

import java.util.*;


@Controller
public class HelloWorld {
    @Autowired
    private AlbumRepo repository;

    @GetMapping("/hello")
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

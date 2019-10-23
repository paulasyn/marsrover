package com.example.nasapic.controller;

import com.example.nasapic.service.NasaPicApplicationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class MainController {
    @Autowired
    NasaPicApplicationService appService;

//    @RequestMapping("/")
//    public String home(){
//        return "Hello World!";
//    }

    // Was working on taking in any date input and request it from the API
//    @RequestMapping("/getPhotoURL")
//    public String getPhotoURL(@RequestBody String jsonInput){
//        try {
//            return appService.getPhotoURLFromUI(jsonInput);
//        }catch (IllegalArgumentException e){
//            return "Error: Bad Input. Please reformat.";
//        }
//    }

    @RequestMapping("/getURLsFromFiles")
    List<String> getURLsFromFile(){
        try {
            return appService.getURLsFromFile();
        }catch (IllegalArgumentException e){
            return Arrays.asList("Error: Bad Input. Please reformat");
        }
    }

}
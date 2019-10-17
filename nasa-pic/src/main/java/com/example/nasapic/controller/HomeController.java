package com.example.nasapic.controller;

import com.example.nasapic.model.InputDate;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.GsonBuilder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@RestController
public class HomeController {

    @RequestMapping("/")
    public String home(){
        return "Hello World!";
    }


    @RequestMapping("/photos")
    public String photoURL(@RequestBody String jsonInput){
        JsonObject jsonObject = new JsonParser().parse(jsonInput).getAsJsonObject();
        String jsonDate = jsonObject.get("Date").getAsString();
        InputDate dateObject = new InputDate();
        dateObject.setDate(jsonDate);
        System.out.println(dateObject.getDateString());
        return ("nice");
    }
}
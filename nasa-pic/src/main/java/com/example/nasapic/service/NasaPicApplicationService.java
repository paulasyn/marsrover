package com.example.nasapic.service;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface NasaPicApplicationService {

    String getPhotoURLFromUI(String inputJson);
    List<String> getURLsFromFile();

}

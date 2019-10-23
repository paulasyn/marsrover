package com.example.nasapic.service;

import com.example.nasapic.model.NasaResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface NasaPicApplicationService {

    String getPhotoURLFromUI(String inputJson);
    List<String> getURLsFromFile();

}

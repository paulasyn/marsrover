package com.example.nasapic.impl;

import com.example.nasapic.model.InputDate;
import com.example.nasapic.model.NasaResponse;
import com.example.nasapic.service.*;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NasaPicApplicationImpl implements NasaPicApplicationService {
    @Autowired
    ParseService parseService;

    @Autowired
    DateCleaningService dateCleaningService;

    @Autowired
    FormatDatesService formatDatesService;

    @Autowired
    ApiService apiService;

    @Autowired
    DownloadService downloadService;

    public String getPhotoURLFromUI(String jsonInput) throws IllegalArgumentException{

        JsonObject jsonObject = new JsonParser().parse(jsonInput).getAsJsonObject();
        String jsonDate = jsonObject.get("Date").getAsString();
        InputDate dateObject = new InputDate();
        dateObject.setDate(jsonDate);
        System.out.println(dateObject.getDateString());
        return dateObject.getDateString();

        // TODO: take input from frontend text box
    }

    public List<String> getURLsFromFile() throws IllegalArgumentException {
        List<String> inputList = parseService.processFile();

        List<String> cleanedDates = dateCleaningService.cleanDates(inputList);

        List<String> formattedDates = formatDatesService.formatDates(cleanedDates);

        if (formattedDates == null || formattedDates.size() != inputList.size()) throw new IllegalArgumentException();

        List<NasaResponse> responses = apiService.callApi(formattedDates);

        downloadService.downloadPhotos(responses);

        return formattedDates;

        // TODO: Test cases for controller
    }
}

package com.example.nasapic.impl;

import com.example.nasapic.model.NasaResponse;
import com.example.nasapic.service.ApiService;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class ApiServiceImpl implements ApiService {

    /*
     * Creates string for API call using a formatted date
     *
     * @param String - cleaned and formatted date
     * @return String - URL string with key and value parameters for API call
     * */
    private String buildApiCall(String formattedDate){
        String baseUrl = "https://api.nasa.gov/planetary/apod?api_key=hXeSMClQ1QYWddQZV3tYBTCk7D3j6iieiqBrmUEB";
        return baseUrl + "&date=" + formattedDate + "&hd=false";
    }

    /*
     * Executes API calls for all the URLs in the list and saves the response body contents
     * into a NasaResponse class that I created to contain the response variables that I can access later
     *
     * @param List<String> - list of clean and formatted dates
     * @return List<NasaResponse> list of responses from API calls
     * */
    public List<NasaResponse> callApi(List<String> formattedDates){
        List<NasaResponse> result = new ArrayList<>();

        for (String date: formattedDates) {

            RestTemplate restTemplate = new RestTemplate();
            result.add(restTemplate.getForObject(buildApiCall(date), NasaResponse.class));
        }

        return result;
    }

}

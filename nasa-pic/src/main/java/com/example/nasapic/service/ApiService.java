package com.example.nasapic.service;

import com.example.nasapic.model.NasaResponse;

import java.util.List;

public interface ApiService {

    List<NasaResponse> callApi(List<String> formattedDates);

}
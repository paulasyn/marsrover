package com.example.nasapic.service;

import com.example.nasapic.model.NasaResponse;

import java.util.List;

public interface DownloadService {

    void downloadPhotos(List<NasaResponse> responses);

}

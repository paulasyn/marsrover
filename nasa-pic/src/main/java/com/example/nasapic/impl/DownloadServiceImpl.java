package com.example.nasapic.impl;

import com.example.nasapic.model.NasaResponse;
import com.example.nasapic.service.DownloadService;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Component
public class DownloadServiceImpl implements DownloadService {

    /*
     * Creates directory in build path and uses response bodies to copy the image referenced in the
     * hdurl to the locally created directory
     *
     * @param List<NasaResponse> - list of response bodies from calling the NASA API
     * @return void
     * */
    public void downloadPhotos(List<NasaResponse> responses){
        Path path = Paths.get(System.getProperty("user.dir") + "/photos");

        try{
            Files.createDirectory(path);
        } catch (IOException e) {}

        for(NasaResponse response: responses){
            try(InputStream in = new URL(response.getHdurl()).openStream()){
                System.out.println("Getting photo for " +  response.getTitle());
                Files.copy(in, Paths.get(path + "/" + response.getDate() + ".jpeg"));
            } catch (MalformedURLException e){}
            catch (IOException e){}
        }
    }
}

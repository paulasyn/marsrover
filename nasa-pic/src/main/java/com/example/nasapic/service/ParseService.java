package com.example.nasapic.service;

import java.util.List;

public interface ParseService {

    List<String> processFile(String filePath);
    String getInputFilePath(String filename);

    }
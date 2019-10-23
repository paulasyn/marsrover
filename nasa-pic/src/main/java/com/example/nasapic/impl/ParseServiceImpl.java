package com.example.nasapic.impl;

import com.example.nasapic.service.ParseService;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class ParseServiceImpl implements ParseService {

    /*
     * Reads the input file and saves the date entries to a list
     *
     * @param void
     * @return List<String> - list of dates from input file
     * */
    public List<String> processFile(String filePath){
        // read the file and save the entries to a list
        List<String> inputList = new ArrayList<>();
        File file = new File(filePath);

        try(Scanner scanner = new Scanner(file)){
            while(scanner.hasNextLine()){
                inputList.add(scanner.nextLine());
            }
        } catch(FileNotFoundException e){
            System.out.println(e);
        }
        return inputList;
    }

    public String getInputFilePath(String filename){
        String currentDirectory = System.getProperty("user.dir");
        return currentDirectory + "/" + filename;
    }


}



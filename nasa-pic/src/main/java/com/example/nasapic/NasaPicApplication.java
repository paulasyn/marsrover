package com.example.nasapic;

import java.io.FileNotFoundException;
import java.util.*;
import java.io.File;
import java.lang.System.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class NasaPicApplication {

	public static void main(String[] args) {
		SpringApplication.run(NasaPicApplication.class, args);

		// 1. Read from file
		List<String> inputList = processFile();

		// 2. Process dates
		List<String> dateList = parseDates(inputList);

		// 3. Build api call

		// 4. Call api

		// 5. Display photos
	}

	public static List<String> processFile(){
		// read the file and save the entries to a list

		String currentDirectory = System.getProperty("user.dir");
		String filePath = currentDirectory + "/inputFile.txt";
		List<String> inputList = new ArrayList<>();
		File file = new File(filePath);

		try(Scanner scanner = new Scanner(file)){
			int i = 0;
			while(scanner.hasNextLine()){
				inputList.add(scanner.nextLine());
			}
		} catch(FileNotFoundException e){
			System.out.println(e);
		}
		return inputList;
	}

	public static List<String> parseDates(List<String> inputDates){
		// process the input dates to match format

        // TODO: figure out how to do last date format
		List<String> formatDates = Arrays.asList("MM/dd/yy", "M-d-yyyy", "lastDate");
		List<String> newDates = new ArrayList<>();
		String wantedDate = "YYYY-MM-DD";

		return newDates;
	}


}

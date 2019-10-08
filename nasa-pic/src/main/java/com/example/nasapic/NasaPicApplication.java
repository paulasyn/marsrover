package com.example.nasapic;

import java.io.FileNotFoundException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.File;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class NasaPicApplication {

	public static void main(String[] args) {
		SpringApplication.run(NasaPicApplication.class, args);

		// 1. Read from file
		List<String> inputList = processFile();

		// 2A. Clean Dates
		List<String> cleanedDates = cleanDates(inputList);

		// 2B. Format Dates
		List<String> formattedDates = formatDates(cleanedDates);

		// TODO: do we take in invalid dates???
		// 3. Build api call
		callApi(formattedDates);

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

	public static List<String> cleanDates(List<String> inputDates){
		// clean strings of commas
		List<String> cleanedDates = new ArrayList<>();

		for (String date: inputDates){
			if(date.contains(",")){
				date = date.replaceAll(",", "");
			}
			cleanedDates.add(date);
//			System.out.println(date);
		}

		return cleanedDates;
	}

	public static List<String> formatDates(List<String> cleanedDates){
		// TODO: figure out how to do last date format
		List<String> knownPatterns = Arrays.asList("MM/dd/yy", "M-d-yyyy", "MMMM-dd-yyyy", "MMMM d yyyy");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		List<String> formattedDates = new ArrayList<>();

		for (String date: cleanedDates){
			for (String pattern : knownPatterns){
				try {
					Date reformattedDate  = new SimpleDateFormat(pattern).parse(date);
					formattedDates.add(formatter.format(reformattedDate));
					System.out.println(formatter.format(reformattedDate));

				} catch (ParseException e) {}

			}
		}
		return formattedDates;
	}

	public static String buildApiCall(String formattedDate){
		String baseUrl = "https://api.nasa.gov/planetary/apod?api_key=hXeSMClQ1QYWddQZV3tYBTCk7D3j6iieiqBrmUEB";
		return baseUrl + "&date=" + formattedDate + "&hd=false";
	}

	public static void callApi(List<String> formattedDates){
		for (String date: formattedDates) {

			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<String> result = restTemplate.getForEntity(buildApiCall(date), String.class);

			System.out.println(result);
		}

	}


}

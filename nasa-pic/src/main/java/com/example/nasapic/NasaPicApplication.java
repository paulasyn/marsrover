package com.example.nasapic;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.File;

import com.example.nasapic.model.NasaResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
		// 3. Build & call api
		List<NasaResponse> responses = callApi(formattedDates);

		// 4. Download & store photos
		downloadPhotos(responses);
	}

	public static List<String> processFile(){
		// read the file and save the entries to a list

		String currentDirectory = System.getProperty("user.dir");
		String filePath = currentDirectory + "/inputFile.txt";
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

	/*
	 * Remove commas from date
	 *
	 * @param List<String> dirty input date strings
	 * @return List<String> of dates without commas
	 * */
	public static List<String> cleanDates(List<String> inputDates){
		// clean strings of commas
		List<String> cleanedDates = new ArrayList<>();

		for (String date: inputDates){
			if(date.contains(",")){
				date = date.replaceAll(",", "");
			}
			cleanedDates.add(date);
		}

		return cleanedDates;
	}

	public static List<String> formatDates(List<String> cleanedDates){
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

	/*
	 * Builds and executes API call
	 *
	 * @param List<String> formattedDates - cleaned and formatted dates
	 * @return List<NasaResponse> response from api call
	 * */
	public static List<NasaResponse> callApi(List<String> formattedDates){
		List<NasaResponse> result = new ArrayList<>();

		for (String date: formattedDates) {

			RestTemplate restTemplate = new RestTemplate();
			result.add(restTemplate.getForObject(buildApiCall(date), NasaResponse.class));
		}

		return result;
	}

	public static void downloadPhotos(List<NasaResponse> responses){
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

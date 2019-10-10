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

import static java.lang.System.exit;

@SpringBootApplication
public class NasaPicApplication {

	public static void main(String[] args) {
		SpringApplication.run(NasaPicApplication.class, args);

		List<String> inputList = processFile();

		List<String> cleanedDates = cleanDates(inputList);

		List<String> formattedDates = formatDates(cleanedDates);

		// TODO: do we take in invalid dates??? Could reject those dates, but logic as is gives the next day if not found.
		List<NasaResponse> responses = callApi(formattedDates);

		downloadPhotos(responses);
		exit(0);
	}

	/*
	 * Reads the input file and saves the date entries to a list
	 *
	 * @param void
	 * @return List<String> - list of dates from input file
	 * */
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
	 * @param List<String> - dirty input date strings
	 * @return List<String> - list of dates without commas
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

	/*
	 * Takes a list of clean date strings and each date string is changed into a date object that its original format closely matches
	 * The date object can now be changed to the format we want
	 *
	 * @param List<String> - list of clean dates with no commas
	 * @return List<String> - list of dates that are formatted to match the api input
	 * */
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

	/*
	 * Creates string for API call using a formatted date
	 *
	 * @param String - cleaned and formatted date
	 * @return String - URL string with key and value parameters for API call
	 * */
	public static String buildApiCall(String formattedDate){
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
	public static List<NasaResponse> callApi(List<String> formattedDates){
		List<NasaResponse> result = new ArrayList<>();

		for (String date: formattedDates) {

			RestTemplate restTemplate = new RestTemplate();
			result.add(restTemplate.getForObject(buildApiCall(date), NasaResponse.class));
		}

		return result;
	}

	/*
	 * Creates directory in build path and uses response bodies to copy the image referenced in the
	 * hdurl to the locally created directory
	 *
	 * @param List<NasaResponse> - list of response bodies from calling the NASA API
	 * @return void
	 * */
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

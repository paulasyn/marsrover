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
		/*
		* 1. Read from file
		* 2. Process dates
		* 3. Build api call
		* 4. Call api
		* 5. Display photos
		* */


		SpringApplication.run(NasaPicApplication.class, args);

		String currentDirectory = System.getProperty("user.dir");
		String filePath = currentDirectory + "/inputFile.txt";


		List<String> inputList = new ArrayList<>();
		File file = new File(filePath);

		// read the file and save the entries to a list
		try(Scanner scanner = new Scanner(file)){
			int i = 0;
			while(scanner.hasNextLine()){
				inputList.add(scanner.nextLine());
			}
		} catch(FileNotFoundException e){
			System.out.println(e);
		}
	}

}

# marsrover
Simple application that exposes the backend to consume NASA Mars Rover API

## Cloning and Building

Clone the repo using `git clone https://github.com/paulasyn/marsrover.git`

Navigate to the directory within "marsrover" and open the project "nasa-pic" in your IDE of choice.

In the terminal, pull the dependencies by running `<mvn clean install>`

To run unit tests and create jacoco exec code coverage file, run `<mvn test>`

To view code coverage (using IntelliJ) go to Analyze > Show Coverage Data
  1. Click the plus sign
  1. Select nasa-pic/target/jacoco.exec
  1. Click "Show selected"

To run the project, run `<mvn spring-boot:run>`

The backend is now up and running.

This next part assumes that the UI is also up and running. You can find the UI at `https://github.com/paulasyn/marsrover.git`.

## Application Flow

I have the project setup so that when an invalid date is found in the given text file, it will throw a Parse Exception and will not send a request to the API. example: April 31, 2018 - this date will not be process, therefore must be removed from the file or changed.

In the first run to demonstrate this, I use the file `invalidFile.txt` as the input and will result in an error.

In order to have the application send a request, you must navigate to `src/main/java/com/example/nasapic/impl/NasaPicApplicationImpl.java` and uncomment the line `String file = parseService.getInputFilePath("validInputFile.txt");` as well as comment out the line above it.

Run the project again, click the button in the UI, and you will see the application send a request to the API.
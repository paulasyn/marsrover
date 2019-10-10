# marsrover
Simple application to consume NASA Mars Rover API

## Cloning and Building

Clone the repo using `<git clone https://github.com/paulasyn/marsrover.git>`

Navigate to the directory within "marsrover" and open the project "nasa-pic" in your IDE of choice.

In the terminal, pull the dependencies by running `<mvn clean install>`

To run unit tests and create jacoco exec code coverage file, run `<mvn test>`

To view code coverage (using IntelliJ) go to Analyze > Show Coverage Data
  1. Click the plus sign
  1. Select nasa-pic/target/jacoco.exec
  1. Click "Show selected"

To run the project, run `<mvn spring-boot:run>`

After running the project, photos pulled using the NASA API can be found in `<marsrover/nasa-pic/photos>`
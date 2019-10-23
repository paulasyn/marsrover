package com.example.nasapic.impl;

import com.example.nasapic.service.FormatDatesService;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class FormatDatesServiceImpl implements FormatDatesService {
    /*
     * Takes a list of clean date strings and each date string is changed into a date object that its original format closely matches
     * The date object can now be changed to the format we want
     * If the date does not exist, it is removed from the list.
     *
     * @param List<String> - list of clean dates with no commas
     * @return List<String> - list of dates that are formatted to match the api input
     * */
    public List<String> formatDates(List<String> cleanedDates){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        formatter.setLenient(false);
        List<String> knownPatterns = Arrays.asList("MM/dd/yy", "M-d-yyyy", "MMMM-dd-yyyy", "MMMM d yyyy");
        List<String> formattedDates = new ArrayList<>();

        for (String date: cleanedDates){
            for (String pattern : knownPatterns){
                try{
                    SimpleDateFormat newFormatter = new SimpleDateFormat(pattern);
                    newFormatter.setLenient(false);
                    Date reformattedDate = newFormatter.parse(date);
                    formattedDates.add(formatter.format(reformattedDate));
//                    System.out.println(formatter.format(reformattedDate) + " was able to be reformatted using this pattern: " + pattern);
                } catch (ParseException e){

//                    System.out.println("Date " + date + " is not a valid date for the format: " + pattern + ".");
                }
            }
        }
        if(cleanedDates.size() != formattedDates.size()){
            System.out.println("The following dates were valid, please check that ALL your dates are valid. \nRequest was not sent.");
        }
        System.out.println(formattedDates);
        return formattedDates;
    }
}

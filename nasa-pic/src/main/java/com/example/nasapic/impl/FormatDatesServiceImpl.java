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
     *
     * @param List<String> - list of clean dates with no commas
     * @return List<String> - list of dates that are formatted to match the api input
     * */
    public List<String> formatDates(List<String> cleanedDates){

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
}


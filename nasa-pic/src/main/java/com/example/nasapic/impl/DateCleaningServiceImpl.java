package com.example.nasapic.impl;

import com.example.nasapic.service.DateCleaningService;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class DateCleaningServiceImpl implements DateCleaningService {

    /*
     * Remove commas from date
     *
     * @param List<String> - dirty input date strings
     * @return List<String> - list of dates without commas
     * */

    public List<String> cleanDates(List<String> inputDates){
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

}

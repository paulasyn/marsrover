package com.example.nasapic;

import com.example.nasapic.impl.FormatDatesServiceImpl;
import com.example.nasapic.impl.ParseServiceImpl;
import com.example.nasapic.service.NasaPicApplicationService;

import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


public class FormatDatesServiceTest {
    @InjectMocks
    private FormatDatesServiceImpl service;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void givenFormattedDates(){
        List<String> formattedDates = Arrays.asList("September 14 2012", "December 21 2014", "03/15/2000", "October-5-2016");
        List<String> actualDates = Arrays.asList("2012-09-14", "2014-12-21", "2000-03-15", "2016-10-05");
        assertEquals(service.formatDates(formattedDates), actualDates);
    }

    @Test
//            (expected = ParseException.class)
    public void givenOneBadDate(){
        List<String> formattedDates = Arrays.asList("September 14 2012", "December 21 2014", "03/15/2000", "February-30-2016");
        List<String> actualDates = Arrays.asList("2012-09-14", "2014-12-21", "2000-03-15");
        List<String> finalDates = service.formatDates(formattedDates);
        assertEquals(finalDates.size(), 3);
    }
}

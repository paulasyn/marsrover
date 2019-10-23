package com.example.nasapic;

import com.example.nasapic.impl.DateCleaningServiceImpl;
import com.example.nasapic.service.NasaPicApplicationService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class DateCleaningServiceTest {
    @Mock
    private NasaPicApplicationService NasaService;

    @InjectMocks
    private DateCleaningServiceImpl service;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void givenListOfUncleanDates(){
        List<String> uncleanDates = Arrays.asList("September 14, 2012", "December 21, 2014");
        List<String> expectedDates = Arrays.asList("September 14 2012", "December 21 2014");
        assertEquals(service.cleanDates(uncleanDates), expectedDates);
    }
}

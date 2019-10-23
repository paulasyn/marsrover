package com.example.nasapic;

import com.example.nasapic.impl.ParseServiceImpl;
import com.example.nasapic.service.NasaPicApplicationService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;

public class ParseServiceTest {
    @InjectMocks
    private ParseServiceImpl service;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void givenFilenameInput(){
        String filepath = "testfilepath.txt";
        String finalPath = service.getInputFilePath(filepath);
        String currentDirectory = System.getProperty("user.dir");

        assertEquals(finalPath, currentDirectory + "/" + filepath);
    }


}

package com.example.nasapic;

import com.example.nasapic.impl.NasaPicApplicationImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class NasaPicApplicationImplTests {

	public NasaPicApplicationImpl testClass = new NasaPicApplicationImpl();

	@Test
	public void testGetURLsFromFiles(){
		List<String> expectedList = Arrays.asList("2017-02-27", "2018-06-02", "2016-07-13", "2018-05-01");
		Assert.assertEquals(testClass.getURLsFromFile(), expectedList);

		// TODO: test case the controller to call the getFromFile() make sure the dates given back is correct and make sure the folder has the correct number of files needed
	}

	@Test
	public void test(String jsonInput){
		Assert.assertEquals(testClass.getURLsFromFile(), jsonInput);

		// TODO: test case the controller to call the getFromFile() make sure the dates given back is correct and make sure the folder has the correct number of files needed
	}

//	@Test
//	public void testDatesCleanedOfCommas(){
//		List<String> inputList = Arrays.asList("April 3, 2014", "October 11, 2015", "May 25, 2011");
//
//		List<String> expectedList = Arrays.asList("April 3 2014", "October 11 2015", "May 25 2011");
//		Assert.assertEquals(testClass.getURLsFromFile(), expectedList);
//
//		// TODO: test case the controller to call the getFromFile() make sure the dates given back is correct and make sure the folder has the correct number of files needed
//	}
//
//	@Test
//	public void testBadDatesGiven(){
//		List<String> badInput = Arrays.asList("April 3, 2014", "10/11/2015", "May-25-2011", "March 20 2002");
//
//		List<String> expectedList = Arrays.asList("2015-10-11", "2011-05-25", "2002-03-20");
//		Assert.assertEquals(testClass.formatDates(badInput), expectedList);
//	}
//
//	@Test
//	public void testBuildApiCall(){
//		String inputDate = "2015-10-11";
//		String expectedOutput = "https://api.nasa.gov/planetary/apod?api_key=hXeSMClQ1QYWddQZV3tYBTCk7D3j6iieiqBrmUEB&date=2015-10-11&hd=false";
//		Assert.assertEquals(testClass.buildApiCall(inputDate), expectedOutput);
//	}

//	@Test
//	public void testBuildApiCall(){
//		String inputDate = "2015-10-11";
//		String expectedOutput = "https://api.nasa.gov/planetary/apod?api_key=hXeSMClQ1QYWddQZV3tYBTCk7D3j6iieiqBrmUEB&date=2015-10-11&hd=false";
//		Assert.assertEquals(testClass.buildApiCall(inputDate), expectedOutput);
//	}


}
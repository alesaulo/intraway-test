package com.intraway.exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.intraway.exercise.props.MultipleList;
import com.intraway.exercise.props.MultiplePropertyConverter;

public class MultiplesPropertyConverterTest {

	@Test
	void threeFizzFiveBuzzAreLoadedCorrectly() {
		MultipleList multipleList = new MultiplePropertyConverter()
			.convert("3-Fizz,5-Buzz");
		
		MultipleList expectedMultipleList = new MultipleList();
		expectedMultipleList.add(3, "Fizz");
		expectedMultipleList.add(5, "Buzz");
		
		assertEquals(multipleList, expectedMultipleList);
	}
	
	@Test
	void threeFizzFiveBuzzFiveZappAreLoadedCorrectly() {
		MultipleList multipleList = new MultiplePropertyConverter()
			.convert("3-Fizz,5-Buzz, 5- Zapp");
		
		MultipleList expectedMultipleList = new MultipleList();
		expectedMultipleList.add(3, "Fizz");
		expectedMultipleList.add(5, "Zapp");
		
		assertEquals(multipleList, expectedMultipleList);
	}

}

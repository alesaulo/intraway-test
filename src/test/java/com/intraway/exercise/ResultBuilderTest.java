package com.intraway.exercise;

import org.junit.jupiter.api.Test;

import com.intraway.exercise.service.result.Result;
import com.intraway.exercise.service.result.ResultBuilderFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ResultBuilderTest {
	@Test
	void resultBuilderBuildsCorrectResult() {
		Result result = ResultBuilderFactory
			.getBuilder()
			.addAvailableMultiple(3)
			.addNumberAsString("1")
			.addNumberAsString("2")
			.addNumberAsString("Fizz")
			.getResult();
		
		Result expectedResult = mock(Result.class);
		
		Set<Integer> availableMultiples = new HashSet<>();
		availableMultiples.add(3);
		
		when(expectedResult.getAvailableMultiples())
			.thenReturn(availableMultiples);
		
		when(expectedResult.isMinGreaterThanMaxError())
			.thenReturn(false);
		
		when(expectedResult.getReplacedNumbers())
			.thenReturn(Arrays.asList(new String[] {
				"1", "2", "Fizz"
			}));
		
		assertEquals(result, expectedResult);
	}
	
	@Test
	void resultBuilderBuildsCorrectResult2() {
		Result result = ResultBuilderFactory
			.getBuilder()
			.addAvailableMultiple(3)
			.addAvailableMultiple(5)
			.addNumberAsString("1")
			.addNumberAsString("2")
			.addNumberAsString("Fizz")
			.addNumberAsString("4")
			.addNumberAsString("Buzz")
			.addNumberAsString("Fizz")
			.addNumberAsString("7")
			.addNumberAsString("8")
			.addNumberAsString("Fizz")
			.addNumberAsString("Buzz")
			.getResult();
		
		Result expectedResult = mock(Result.class);
		
		Set<Integer> availableMultiples = new HashSet<>();
		availableMultiples.add(3);
		availableMultiples.add(5);
		
		when(expectedResult.getAvailableMultiples())
			.thenReturn(availableMultiples);
		
		when(expectedResult.isMinGreaterThanMaxError())
			.thenReturn(false);
		
		when(expectedResult.getReplacedNumbers())
			.thenReturn(Arrays.asList(new String[] {
				"1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8", "Fizz", "Buzz"
			}));
		
		assertEquals(result, expectedResult);
	}
	
	@Test
	void resultBuilderBuildsCorrectResult3() {
		Result result = ResultBuilderFactory.newMinGreaterThanMaxErrorResult();
		
		Result expectedResult = mock(Result.class);
		
		when(expectedResult.getAvailableMultiples())
			.thenReturn(new HashSet<>());
		
		when(expectedResult.isMinGreaterThanMaxError())
			.thenReturn(true);
		
		when(expectedResult.getReplacedNumbers())
			.thenReturn(
				Arrays.asList(new String[] {})
			);
		
		assertEquals(result, expectedResult);
	}
}

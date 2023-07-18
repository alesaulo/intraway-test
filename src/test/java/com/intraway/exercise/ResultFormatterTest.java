package com.intraway.exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.intraway.exercise.model.response.GoodRequest;
import com.intraway.exercise.service.result.Result;
import com.intraway.exercise.service.result.ResultBuilderFactory;
import com.intraway.exercise.util.ResultFormatter;

public class ResultFormatterTest {
	@Test
	void resultIsFormattedSuccessfullyTest() {
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
		
		GoodRequest goodRequest = ResultFormatter.formatResult(result);
		
		GoodRequest expectedGoodRequest = new GoodRequest();
		
		expectedGoodRequest.setTimestamp(goodRequest.getTimestamp());
		expectedGoodRequest.setDescription("Se encontraron múltiplos de 3 y 5");
		expectedGoodRequest.setList("1, 2, Fizz, 4, Buzz, Fizz, 7, 8, Fizz, Buzz");
		expectedGoodRequest.setCode(0);
		
		assertEquals(goodRequest, expectedGoodRequest);
	}
	
	@Test
	void resultIsFormattedSuccessfullyTest2() {
		Result result = ResultBuilderFactory
			.getBuilder()
			.addAvailableMultiple(3)
			.addNumberAsString("1")
			.addNumberAsString("2")
			.addNumberAsString("Fizz")
			.getResult();
		
		GoodRequest goodRequest = ResultFormatter.formatResult(result);
		
		GoodRequest expectedGoodRequest = new GoodRequest();
		
		expectedGoodRequest.setTimestamp(goodRequest.getTimestamp());
		expectedGoodRequest.setDescription("Se encontraron múltiplos de 3");
		expectedGoodRequest.setList("1, 2, Fizz");
		expectedGoodRequest.setCode(0);
		
		assertEquals(goodRequest, expectedGoodRequest);
	}
	
	@Test
	void resultIsFormattedSuccessfullyTest3() {
		Result result = ResultBuilderFactory
			.getBuilder()
			.addNumberAsString("1")
			.addNumberAsString("2")
			.getResult();
		
		GoodRequest goodRequest = ResultFormatter.formatResult(result);
		
		GoodRequest expectedGoodRequest = new GoodRequest();
		
		expectedGoodRequest.setTimestamp(goodRequest.getTimestamp());
		expectedGoodRequest.setDescription("No se encontraron múltiplos");
		expectedGoodRequest.setList("1, 2");
		expectedGoodRequest.setCode(0);
		
		assertEquals(goodRequest, expectedGoodRequest);
	}
	
}

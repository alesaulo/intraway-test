package com.intraway.exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.intraway.exercise.service.CalculatorService;
import com.intraway.exercise.service.result.Result;
import com.intraway.exercise.service.result.ResultBuilderFactory;

@SpringBootTest(classes = ExerciseApplication.class)
@TestPropertySource( locations = "classpath:application-test.properties")
class CalculatorServiceTests {
	
	@Autowired
	CalculatorService calculator;

	@Test
	void calculationForMin1Max5YieldsExpectedResultTest() {
		Result result = calculator.doCalculation(1, 5);
		
		Result expectedResult = ResultBuilderFactory
			.getBuilder()
			.addAvailableMultiple(3)
			.addAvailableMultiple(5)
			.addNumberAsString("1")
			.addNumberAsString("2")
			.addNumberAsString("Fizz")
			.addNumberAsString("4")
			.addNumberAsString("Buzz")
			.getResult();
		
		assertEquals(result, expectedResult);
	}

	@Test
	void calculationForMin1MaxMinus10YieldsErrorTest() {
		Result result = calculator.doCalculation(1, -10);
		
		Result expectedResult = ResultBuilderFactory
			.newMinGreaterThanMaxErrorResult();
		
		assertEquals(result, expectedResult);
	}
	
}

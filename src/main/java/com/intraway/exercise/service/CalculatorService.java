package com.intraway.exercise.service;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.intraway.exercise.props.MultipleList;
import com.intraway.exercise.service.result.Result;
import com.intraway.exercise.service.result.ResultBuilder;
import com.intraway.exercise.service.result.ResultBuilderFactory;

@Component
@Service
@Configuration
@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "com.intraway.exercise")
@ConfigurationPropertiesScan 
public class CalculatorService  {
	
	/*
	 * This property contains the numbers which the max/min list
	 * has to be multipliers, in this case:
	 * 
	 * 3-Fizz
	 * 5-Buzz
	 * */
	private MultipleList multiples;
	
	public void setMultiples(MultipleList multiples) {
		this.multiples = multiples;
	}
	
	private boolean noMultiples(StringBuilder valueBuilder) {
		return valueBuilder.length() == 0;
	}
	
	public Result doCalculation(int min, int max) {
		if(min >= max) {
			return ResultBuilderFactory.newMinGreaterThanMaxErrorResult();
		}
		
		ResultBuilder resultBuilder = ResultBuilderFactory.getBuilder();
		for(int currentNumber = min; currentNumber <= max; currentNumber++) {
			StringBuilder currentNumberAsString = new StringBuilder();
			
			int finalCurrentNumber = currentNumber;
			multiples.getList().forEach( multiple -> {
								
				if(finalCurrentNumber % multiple.getNumber() == 0) {
					resultBuilder.addAvailableMultiple(multiple.getNumber());	
					currentNumberAsString.append(multiple.getWord());
				} 
			});
			
			if(noMultiples(currentNumberAsString)) {
				currentNumberAsString.append(currentNumber);
			}
			
			resultBuilder.addNumberAsString(currentNumberAsString.toString());
		}
		
		return resultBuilder.getResult();
	}

	
}

package com.intraway.exercise.service.result;

public class ResultBuilder {
	Result result = new Result();
	
	ResultBuilder() {
	}
	
	public ResultBuilder addAvailableMultiple(int multiple) {
		result.availableMultiples.add(multiple);
		
		return this;
	}
	
	public ResultBuilder addNumberAsString(String number) {
		result.replacedNumbers.add(number);
		
		return this;
	}
	
	public Result getResult() {
		return result;
	}
}

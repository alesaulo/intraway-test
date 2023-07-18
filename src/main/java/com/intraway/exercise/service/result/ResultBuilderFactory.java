package com.intraway.exercise.service.result;

public class ResultBuilderFactory {
	public static Result newMinGreaterThanMaxErrorResult() {
		Result errorResult = new Result();
		errorResult.minGreaterThanMaxError = true;	
		return errorResult;
	}
		
	public static ResultBuilder getBuilder() {
		return new ResultBuilder();
	}		
}

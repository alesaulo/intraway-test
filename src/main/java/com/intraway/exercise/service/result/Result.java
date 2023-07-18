package com.intraway.exercise.service.result;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Result {
	Set<Integer> availableMultiples = new HashSet<>();
	boolean minGreaterThanMaxError = false;
	List<String> replacedNumbers = new ArrayList<>();
	
	public Set<Integer> getAvailableMultiples() {
		return availableMultiples;
	}
	
	public boolean isMinGreaterThanMaxError() {
		return minGreaterThanMaxError;
	}
	
	public List<String> getReplacedNumbers() {
		return replacedNumbers;
	}
	
	@Override
	public boolean equals(Object other) {
		if(other == null) {
			return false;
		}
		
		if(!(other instanceof Result)) {
			return false;
		}
		
		Result otherResult = (Result)other;
		
		if(this == otherResult) {
			return true;
		}
		
		return this.getAvailableMultiples().equals(otherResult.getAvailableMultiples())
			&& this.isMinGreaterThanMaxError() == otherResult.isMinGreaterThanMaxError()
			&& this.getReplacedNumbers().equals(otherResult.getReplacedNumbers());
	}
}

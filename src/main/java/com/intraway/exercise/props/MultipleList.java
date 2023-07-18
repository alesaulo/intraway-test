package com.intraway.exercise.props;

import java.util.ArrayList;
import java.util.List;

/*
 * This is the list that holds:
 * 
 * 3-Fizz
 * 5-Buzz
 * */
public class MultipleList {
	
	public class Multiple {
		int number;
		String word;
		
		public Multiple(int number, String word) {
			this.number = number;
			this.word = word;
		}
		
		public int getNumber() {
			return number;
		}

		public String getWord() {
			return word;
		}
		
		@Override
		public boolean equals(Object other) {
			if(other == null) {
				return false;
			}
			
			if(!(other instanceof Multiple)) {
				return false;
			}
			
			Multiple otherMultiple = (Multiple)other;
			
			if(this == otherMultiple) {
				return true;
			}
			
			return this.number == otherMultiple.number
				&& this.word.equals(otherMultiple.word);
		}
	}
		
	List<Multiple> list = new ArrayList<>();
	
	public void add(int number, String word) {
		list.removeIf( element -> element.getNumber() == number);
		
		list.add(new Multiple(number, word));
	}
	
	public List<Multiple> getList() {
		return this.list;
	}
	
	@Override
	public boolean equals(Object other) {
		if(other == null) {
			return false;
		}
		
		if(!(other instanceof MultipleList)) {
			return false;
		}
		
		MultipleList otherMultipleList = (MultipleList)other;
		
		if(this == otherMultipleList) {
			return true;
		}
		
		return this.list.equals(otherMultipleList.list);
	}
}

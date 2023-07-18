package com.intraway.exercise.props;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/*
 * THis converts the property value 
 * 
 * com.intraway.exercise.multiples=3-Fizz,5-Buzz
 * 
 * Into a list of:
 * 
 * 3-Fizz
 * 5-Buzz
 * */

@Component
@ConfigurationPropertiesBinding
public class MultiplePropertyConverter implements Converter<String, MultipleList>  {

	Logger logger = LoggerFactory.getLogger(MultiplePropertyConverter.class);
	
	private static final String ELEMENT_SEPARATOR = ",";
	private static final String FIELD_SEPARATOR = "-";
	
	private Optional<Integer> parseInt(String num) {
		Optional<Integer> optionalResult = Optional.empty();
		
		try {
			optionalResult = Optional.of(Integer.parseInt(num));
		} catch(Throwable t) {
			logger.warn("Couldn't convert " + num);
		}
		
		return optionalResult;
	}
	
	private void tryAddMultiple(String multiple, MultipleList multipleList) {
		int NUMBER_INDEX = 0;
		int WORD_INDEX = 1;
		
		String[] fields = multiple.split(FIELD_SEPARATOR);
		
		if(fields.length > 1) {
		
			String word = fields[WORD_INDEX].trim();
			
			if(!word.isEmpty()) {
			
				parseInt(fields[NUMBER_INDEX].trim())
					.ifPresent( number -> {
						multipleList.add(
							number, 
							fields[WORD_INDEX].trim()
						);
				});			
			}
		}
	}
	
	@Override
	public MultipleList convert(String source) {
		List<String> multiples = Arrays.asList(source.split(ELEMENT_SEPARATOR));
		
		MultipleList multipleList = new MultipleList();
		
		multiples.forEach( multiple -> {
			tryAddMultiple(multiple, multipleList); 
		});
		
		return multipleList;
	}
	
}

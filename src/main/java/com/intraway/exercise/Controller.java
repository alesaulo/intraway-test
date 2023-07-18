package com.intraway.exercise;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.intraway.exercise.exceptions.BadRequest;
import com.intraway.exercise.model.response.GoodRequest;
import com.intraway.exercise.service.CalculatorService;
import com.intraway.exercise.service.PersistanceService;
import com.intraway.exercise.service.result.Result;
import com.intraway.exercise.util.ResultFormatter;

@RestController()
@RequestMapping("intraway/api")
public class Controller {
	public static final String ERROR_MESSAGE = "Los parámetros enviados son incorrectos";
	
	@Autowired
	PersistanceService persistance;
	
	@Autowired
	CalculatorService calculator;
	
	@GetMapping("/fizzbuzz/{min}/{max}")  
	public GoodRequest get(
		@PathVariable("min") int min, 
		@PathVariable("max") int max
	) throws IOException, BadRequest   
	{  
		Result result = calculator.doCalculation(min, max);
		
		if(result.isMinGreaterThanMaxError()) {
			throw new BadRequest(ERROR_MESSAGE);
		}
		
		GoodRequest goodRequestResult = ResultFormatter.formatResult(result);
		
		return persistance.addNew(goodRequestResult);
	}  
}

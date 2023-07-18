package com.intraway.exercise;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.core.Is.is;

import java.sql.Timestamp;
import java.time.Instant;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.intraway.exercise.exceptions.BadRequest;
import com.intraway.exercise.model.response.GoodRequest;

@ExtendWith(SpringExtension.class)
@WebMvcTest(Controller.class)
public class ControllerTest {
	public static final String ERROR_MESSAGE = "Los parámetros enviados son incorrectos";
	
	@MockBean
	private Controller controller;
	
	@Autowired
	private MockMvc mvc;
	
	@Test
	void testMin1Max3Request() throws Exception {
		
		Timestamp currentTs = Timestamp.from(Instant.now());
		
		GoodRequest expectedGoodRequest = new GoodRequest();
		
		expectedGoodRequest.setTimestamp(currentTs);
		expectedGoodRequest.setDescription("Se encontraron múltiplos de 3 y 5");
		expectedGoodRequest.setList("1, 2, Fizz, 4, Buzz");
		expectedGoodRequest.setCode(0);
		
		when(controller.get(1, 5)).thenReturn(expectedGoodRequest);
		
		mvc.perform(get("/intraway/api/fizzbuzz/1/5")
           .contentType(APPLICATION_JSON))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.list", is("1, 2, Fizz, 4, Buzz")));
	}
	
	@Test
	void testMin1MaxMinus1Request() throws Exception {
		
		when(controller.get(1, -1)).thenThrow(new BadRequest(ERROR_MESSAGE));
		
		mvc.perform(get("/intraway/api/fizzbuzz/1/-1")
           .contentType(APPLICATION_JSON))
           .andExpect(status().isBadRequest());
	}
}

package com.intraway.exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Timestamp;
import java.time.Instant;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.intraway.exercise.model.response.GoodRequest;
import com.intraway.exercise.persistance.GoodRequestRepository;
import com.intraway.exercise.service.PersistanceService;

@ExtendWith(MockitoExtension.class)
public class PersistanceServiceTest {
	@InjectMocks
    private PersistanceService persistance;
	
	@Mock
	private GoodRequestRepository goodRequestRepository;
	
	@Test
	void addTwoNewGoodRequestsTest() {
		GoodRequest exampleGoodRequest1 = new GoodRequest();
		GoodRequest expectedGoodRequest1 = new GoodRequest();
		GoodRequest exampleGoodRequest2 = new GoodRequest();
		GoodRequest expectedGoodRequest2 = new GoodRequest();
		
		Timestamp currentTs = Timestamp.from(Instant.now());
		
		exampleGoodRequest1.setTimestamp(currentTs);
		exampleGoodRequest1.setDescription("No se encontraron múltiplos");
		exampleGoodRequest1.setList("1, 2");
		
		expectedGoodRequest1.setTimestamp(currentTs);
		expectedGoodRequest1.setDescription("No se encontraron múltiplos");
		expectedGoodRequest1.setList("1, 2");
		expectedGoodRequest1.setCode(0);
		
		Mockito
			.when(goodRequestRepository.save(exampleGoodRequest1))
			.thenReturn(exampleGoodRequest1);
		
		assertEquals(expectedGoodRequest1, persistance.addNew(exampleGoodRequest1));
		
		currentTs = Timestamp.from(Instant.now());
		
		exampleGoodRequest2.setTimestamp(currentTs);
		exampleGoodRequest2.setDescription("Se encontraron múltiplos de 3");
		exampleGoodRequest2.setList("1, 2, Fizz");
		
		expectedGoodRequest2.setTimestamp(currentTs);
		expectedGoodRequest2.setDescription("Se encontraron múltiplos de 3");
		expectedGoodRequest2.setList("1, 2, Fizz");
		expectedGoodRequest2.setCode(1);
		
		Mockito
			.when(goodRequestRepository.save(exampleGoodRequest2))
			.thenReturn(exampleGoodRequest2);
		
		assertEquals(expectedGoodRequest2, persistance.addNew(exampleGoodRequest2));
	}
}

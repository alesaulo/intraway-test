package com.intraway.exercise.service;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.intraway.exercise.model.response.GoodRequest;
import com.intraway.exercise.persistance.GoodRequestRepository;

@Component
@Service
public class PersistanceService {
	public static final int INITIAL_CODE = 0;
	public static AtomicInteger currentCode = new AtomicInteger(INITIAL_CODE);
	
	@Autowired
	private GoodRequestRepository goodRequestRepository;
	
	public GoodRequest addNew(GoodRequest goodRequest)   
	{  
		goodRequest.setCode(currentCode.getAndIncrement());
		return goodRequestRepository.save(goodRequest); 
	}  
}

package com.intraway.exercise.persistance;

import org.springframework.data.repository.CrudRepository;

import com.intraway.exercise.model.response.GoodRequest;  

public interface GoodRequestRepository extends CrudRepository<GoodRequest, Integer>   {

}

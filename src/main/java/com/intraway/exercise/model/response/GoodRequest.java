package com.intraway.exercise.model.response;

import java.sql.Timestamp;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class GoodRequest {
	@Column
	private
	Timestamp timestamp = Timestamp.from(Instant.now());
	
	@Id
	private
	int code;
	
	@Column
	private
	String description;
	
	@Column(length = 1024)
	private
	String list;

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
	public Timestamp getTimestamp() {
		return timestamp;
	}


	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getList() {
		return list;
	}

	public void setList(String list) {
		this.list = list;
	}
	
	@Override
	public boolean equals(Object other) {
		if(other == null) {
			return false;
		}
		
		if(!(other instanceof GoodRequest)) {
			return false;
		}
		
		GoodRequest otherGoodRequest = (GoodRequest)other;
		
		if(this == otherGoodRequest) {
			return true;
		}
		
		return this.timestamp.equals(otherGoodRequest.timestamp)
			&& this.code == otherGoodRequest.code
			&& this.description.equals(otherGoodRequest.description)
			&& this.list.equals(otherGoodRequest.list);
	}
}

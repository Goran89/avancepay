package com.avancepay.example.model;

import java.util.Date;

public class DeviceData {
	
	private Integer id;
	private String name;
	private Integer locationNumber;
	private Date insertedDateTime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getLocationNumber() {
		return locationNumber;
	}
	public void setLocationNumber(Integer locationNumber) {
		this.locationNumber = locationNumber;
	}
	public Date getInsertedDateTime() {
		return insertedDateTime;
	}
	public void setInsertedDateTime(Date insertedDateTime) {
		this.insertedDateTime = insertedDateTime;
	}

}

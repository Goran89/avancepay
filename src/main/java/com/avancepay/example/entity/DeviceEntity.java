package com.avancepay.example.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true)
@Table(name = "Device", uniqueConstraints = { @UniqueConstraint(columnNames = "ID"),
		@UniqueConstraint(columnNames = "ID") })
public class DeviceEntity implements Serializable {
	
	private static final long serialVersionUID = -1798070786993154676L;
	@Id
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;
	@Column(name = "NAME", unique = true, nullable = false, length = 100)
	private String name;
	@Column(name = "LOCATIONNUMBER", unique = false, nullable = false)
	private Integer locationNumber;
	@Column(name = "INSERTEDDATETIME", columnDefinition = "DATETIME", unique = false, nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateTime;
	
	public DeviceEntity(){
		
	}
	
	public DeviceEntity(Long id, String name, Integer locationNumber, Date dateTime){
		this.id = id;
		this.name = name;
		this.locationNumber = locationNumber;
		this.dateTime = dateTime;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLocationNumber() {
		return locationNumber;
	}
	public void setLocationNumber(int locationNumber) {
		this.locationNumber = locationNumber;
	}
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
}

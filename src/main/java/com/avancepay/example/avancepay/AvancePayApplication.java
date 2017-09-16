package com.avancepay.example.avancepay;

import java.util.Date;

import javax.annotation.PreDestroy;

import org.hibernate.Session;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.avancepay.example.entity.DeviceEntity;
import com.avancepay.example.util.HibernateUtil;

@SpringBootApplication
@ComponentScan({ "com.avancepay.example.controllers", "com.avancepay.example.dao", "com.avancepay.example.rest.handler" })
public class AvancePayApplication {

	public static void main(String[] args) {
		SpringApplication.run(AvancePayApplication.class, args);
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		// Add new Device object
		DeviceEntity dev = new DeviceEntity();
		dev.setId(1L);
		dev.setLocationNumber(2);
		dev.setDateTime(new Date());
		dev.setName("AvancePay");
		session.save(dev);
		session.getTransaction().commit();
	}
	
	@PreDestroy
	public void databaseShutDown() {
		System.out.println("Spring Container is destroy! In memory DB shut down");
		HibernateUtil.shutdown();
	}
}

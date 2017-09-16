package com.avancepay.example.avancepay;

import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.hibernate.Session;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.avancepay.example.entity.DeviceEntity;
import com.avancepay.example.util.HibernateUtil;

@SpringBootApplication
@ComponentScan({ "com.avancepay.example.controllers", "com.avancepay.example.dao", "com.avancepay.example.rest.handler" })
public class AvancePayApplication {

	public static void main(String[] args) {
		SpringApplication.run(AvancePayApplication.class, args);
	}
	
	@Bean
	protected ServletContextListener listener(){
		return new ServletContextListener() {
			
			@Override
			public void contextInitialized(ServletContextEvent arg0) {
				System.out.println("Spring Context started!!!");
				Session session = HibernateUtil.getSessionFactory().openSession();
				session.beginTransaction();
				// Add new Device object(s)
				DeviceEntity dev1 = new DeviceEntity();
				dev1.setId(1L);
				dev1.setLocationNumber(1);
				dev1.setDateTime(new Date());
				dev1.setName("AvancePay_1");
				session.save(dev1);
				
				DeviceEntity dev2 = new DeviceEntity();
				dev2.setId(2L);
				dev2.setLocationNumber(2);
				dev2.setDateTime(new Date());
				dev2.setName("AvancePay_2");
				session.save(dev2);
				
				DeviceEntity dev3 = new DeviceEntity();
				dev3.setId(3L);
				dev3.setLocationNumber(3);
				dev3.setDateTime(new Date());
				dev3.setName("AvancePay_3");
				session.save(dev3);
				
				DeviceEntity dev4 = new DeviceEntity();
				dev4.setId(4L);
				dev4.setLocationNumber(4);
				dev4.setDateTime(new Date());
				dev4.setName("AvancePay_4");
				session.save(dev4);
				
				DeviceEntity dev5 = new DeviceEntity();
				dev5.setId(5L);
				dev5.setLocationNumber(5);
				dev5.setDateTime(new Date());
				dev5.setName("AvancePay_5");
				session.save(dev5);
				session.getTransaction().commit();
			}
			
			@Override
			public void contextDestroyed(ServletContextEvent arg0) {
				System.out.println("Spring Context is destroyed!!! In memory DB shut down");
				HibernateUtil.shutdown();				
			}
		};
	}
	
}

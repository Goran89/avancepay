package com.avancepay.example.avancepay;

import java.util.Date;

import org.hibernate.Session;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.avancepay.example.controllers.DeviceController;
import com.avancepay.example.dao.DeviceDao;
import com.avancepay.example.entity.DeviceEntity;
import com.avancepay.example.rest.handler.RestHandler;
import com.avancepay.example.util.HibernateUtil;

@RunWith(SpringRunner.class)
@WebMvcTest(RestHandler.class)
public class AvancePayApplicationTests {

	@Autowired
	private WebApplicationContext ctx;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@BeforeClass
	public static void setUpBeforeClass(){
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

	@AfterClass
	public static void tearDownAfterClass(){
		HibernateUtil.shutdown();	
	}
	@Configuration
	public static class TestConfiguration {
		
		@Bean("deviceController")
		public DeviceController simpleDrehorteController() {
			return new DeviceController();
		}
		
		@Bean("deviceDao")
		public DeviceDao simpleDeviceDao() {
			return new DeviceDao();
		}
		
		@Bean
		public RestHandler simpleRestHandler() {
			return new RestHandler();
		}
		
	}

	@Test
	public void testDeviceServiceNonEmptyNoInputParameterResponse() throws Exception {
		MvcResult r = mockMvc.perform(MockMvcRequestBuilders.get("/DeviceService")
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		Assert.assertNotNull(r.getResponse().getContentAsString());
		Assert.assertTrue(r.getResponse().getContentAsString().trim().length() > 0);
		Assert.assertTrue(r.getResponse().getContentType().trim().equals("application/json;charset=UTF-8"));
		Assert.assertTrue(r.getResponse().getContentAsString().contains("\"id\":1")
		&& r.getResponse().getContentAsString().contains("\"id\":2")
		&& r.getResponse().getContentAsString().contains("\"id\":3")
		&& r.getResponse().getContentAsString().contains("\"id\":4")
		&& r.getResponse().getContentAsString().contains("\"id\":5"));
	}

	@Test
	public void testDeviceServiceNonEmptyOneInputParameterResponse() throws Exception {
		MvcResult r = mockMvc.perform(MockMvcRequestBuilders.get("/DeviceService?nameContains=a")
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		Assert.assertNotNull(r.getResponse().getContentAsString());
		Assert.assertTrue(r.getResponse().getContentAsString().trim().length() > 0);
		Assert.assertTrue(r.getResponse().getContentType().trim().equals("application/json;charset=UTF-8"));
		Assert.assertTrue(r.getResponse().getContentAsString().contains("\"id\":1")
		&& r.getResponse().getContentAsString().contains("\"id\":2")
		&& r.getResponse().getContentAsString().contains("\"id\":3")
		&& r.getResponse().getContentAsString().contains("\"id\":4")
		&& r.getResponse().getContentAsString().contains("\"id\":5"));
	}
	
	@Test
	public void testDeviceServiceEmptyOneInputParameterResponse() throws Exception {
		MvcResult r = mockMvc.perform(MockMvcRequestBuilders.get("/DeviceService?nameContains=ab")
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		Assert.assertNotNull(r.getResponse().getContentAsString());
		Assert.assertTrue(r.getResponse().getContentAsString().trim().length() > 0);
		Assert.assertTrue(r.getResponse().getContentType().trim().equals("application/json;charset=UTF-8"));
		Assert.assertTrue(r.getResponse().getContentAsString().contains(""));
	}

	@Test
	public void testDeviceServiceNonEmptyTwoInputParametersResponse() throws Exception {
		MvcResult r = mockMvc.perform(MockMvcRequestBuilders.get("/DeviceService?nameContains=a&&locationNumberBiggerThan=0")
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		Assert.assertNotNull(r.getResponse().getContentAsString());
		Assert.assertTrue(r.getResponse().getContentAsString().trim().length() > 0);
		Assert.assertTrue(r.getResponse().getContentType().trim().equals("application/json;charset=UTF-8"));
		Assert.assertTrue(r.getResponse().getContentAsString().contains("\"id\":1")
		&& r.getResponse().getContentAsString().contains("\"id\":2")
		&& r.getResponse().getContentAsString().contains("\"id\":3")
		&& r.getResponse().getContentAsString().contains("\"id\":4")
		&& r.getResponse().getContentAsString().contains("\"id\":5"));
	}
	
	@Test
	public void testDeviceServiceEmptyTwoInputParametersResponse() throws Exception {
		MvcResult r = mockMvc.perform(MockMvcRequestBuilders.get("/DeviceService?nameContains=nn&&locationNumberBiggerThan=0")
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		Assert.assertNotNull(r.getResponse().getContentAsString());
		Assert.assertTrue(r.getResponse().getContentAsString().trim().length() > 0);
		Assert.assertTrue(r.getResponse().getContentType().trim().equals("application/json;charset=UTF-8"));
		Assert.assertTrue(r.getResponse().getContentAsString().contains(""));
	}
	
	@Test
	public void testDeviceServiceNonEmptyThreeInputParametersResponse() throws Exception {
		MvcResult r = mockMvc.perform(MockMvcRequestBuilders.get("/DeviceService?nameStartsWith=A&&nameContains=a&&locationNumberBiggerThan=0")
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		Assert.assertNotNull(r.getResponse().getContentAsString());
		Assert.assertTrue(r.getResponse().getContentAsString().trim().length() > 0);
		Assert.assertTrue(r.getResponse().getContentType().trim().equals("application/json;charset=UTF-8"));
		Assert.assertTrue(r.getResponse().getContentAsString().contains("\"id\":1")
		&& r.getResponse().getContentAsString().contains("\"id\":2")
		&& r.getResponse().getContentAsString().contains("\"id\":3")
		&& r.getResponse().getContentAsString().contains("\"id\":4")
		&& r.getResponse().getContentAsString().contains("\"id\":5"));
	}
	
	@Test
	public void testDeviceServiceEmptyThreeInputParametersResponse() throws Exception {
		MvcResult r = mockMvc.perform(MockMvcRequestBuilders.get("/DeviceService?nameStartsWith=a&&nameContains=a&&locationNumberBiggerThan=0")
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		Assert.assertNotNull(r.getResponse().getContentAsString());
		Assert.assertTrue(r.getResponse().getContentAsString().trim().length() > 0);
		Assert.assertTrue(r.getResponse().getContentType().trim().equals("application/json;charset=UTF-8"));
		Assert.assertTrue(r.getResponse().getContentAsString().contains(""));
	}
	
	@Test
	public void testDeviceServiceNonEmptyFourInputParametersResponse() throws Exception {
		MvcResult r = mockMvc.perform(MockMvcRequestBuilders.get("/DeviceService?nameEndsWith=4&&nameStartsWith=A&&nameContains=a&&locationNumberBiggerThan=0")
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		Assert.assertNotNull(r.getResponse().getContentAsString());
		Assert.assertTrue(r.getResponse().getContentAsString().trim().length() > 0);
		Assert.assertTrue(r.getResponse().getContentType().trim().equals("application/json;charset=UTF-8"));
		Assert.assertTrue( r.getResponse().getContentAsString().contains("\"id\":4"));
	}
	
	@Test
	public void testDeviceServiceEmptyFourInputParametersResponse() throws Exception {
		MvcResult r = mockMvc.perform(MockMvcRequestBuilders.get("/DeviceService?nameEndsWith=3&&nameStartsWith=A&&nameContains=a&&locationNumberBiggerThan=0")
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		Assert.assertNotNull(r.getResponse().getContentAsString());
		Assert.assertTrue(r.getResponse().getContentAsString().trim().length() > 0);
		Assert.assertTrue(r.getResponse().getContentType().trim().equals("application/json;charset=UTF-8"));
		Assert.assertTrue( r.getResponse().getContentAsString().contains(""));
	}
	
	@Test
	public void testDeviceServiceNonEmptyFiveInputParametersResponse() throws Exception {
		MvcResult r = mockMvc.perform(MockMvcRequestBuilders.get("/DeviceService?nameEndsWith=5&&nameStartsWith=A&&nameContains=a&&locationNumberBiggerThan=0"
				+ "&&locationNumberLessOrEqualThan=6")
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		Assert.assertNotNull(r.getResponse().getContentAsString());
		Assert.assertTrue(r.getResponse().getContentAsString().trim().length() > 0);
		Assert.assertTrue(r.getResponse().getContentType().trim().equals("application/json;charset=UTF-8"));
		Assert.assertTrue(r.getResponse().getContentAsString().contains("\"id\":5"));
	}
	
	@Test
	public void testDeviceServiceEmptyFiveInputParametersResponse() throws Exception {
		MvcResult r = mockMvc.perform(MockMvcRequestBuilders.get("/DeviceService?nameEndsWith=4&&nameStartsWith=A&&nameContains=a&&locationNumberBiggerThan=0"
				+ "&&locationNumberLessOrEqualThan=6")
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		Assert.assertNotNull(r.getResponse().getContentAsString());
		Assert.assertTrue(r.getResponse().getContentAsString().trim().length() > 0);
		Assert.assertTrue(r.getResponse().getContentType().trim().equals("application/json;charset=UTF-8"));
		Assert.assertTrue(r.getResponse().getContentAsString().contains(""));
	}
}

package com.avancepay.example.avancepay;

import org.junit.Assert;
import org.junit.Before;
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
import com.avancepay.example.rest.handler.RestHandler;

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

	@Configuration
	public static class TestConfiguration {
		
		@Bean("drehorteController")
		public DeviceController simpleDrehorteController() {
			return new DeviceController();
		}
		
		@Bean
		public RestHandler simpleRestHandler() {
			return new RestHandler();
		}
		
	}

	@Test
	public void testServiceA() throws Exception {
		MvcResult r = mockMvc.perform(MockMvcRequestBuilders.get("/ServiceA")
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		String content = r.getResponse().getContentAsString();
		Assert.assertNotNull(content);
		Assert.assertTrue(content.trim().length() > 0);
	}

	@Test
	public void testServiceBNonEmptyResponse() throws Exception {
		MvcResult r = mockMvc.perform(MockMvcRequestBuilders.get("/ServiceB?title=180")
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		String content = r.getResponse().getContentAsString();
		Assert.assertNotNull(content);
		Assert.assertTrue(content.trim().length() > 2);
	}

	@Test
	public void testServiceBEmptyResponse() throws Exception {
		MvcResult r = mockMvc.perform(MockMvcRequestBuilders.get("/ServiceB?title=1800")
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		String content = r.getResponse().getContentAsString();
		Assert.assertNotNull(content);
		Assert.assertTrue(content.trim().length() == 2);
	}
}

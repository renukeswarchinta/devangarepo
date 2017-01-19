package com.devangam;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.devangam.controller.UserRestController;
import com.devangam.dto.UserRequestDTO;
import com.devangam.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class DevangamApplicationTests {

	@Autowired
	private WebApplicationContext context;
	@Autowired
	UserRestController userRestController;

	private MockMvc mvc;
	@Before
	    public void setup() {
	        this.mvc = MockMvcBuilders.webAppContextSetup(context).build();
	    }

	@Test
	public void testHome() throws Exception {

		this.mvc.perform(get("/userdto")).andExpect(status().isOk())
				.equals(User.class);
	}
	@Test
	public void testUserSignUp() throws Exception {
		UserRequestDTO dto = new UserRequestDTO();
		  String json = "{\"email\":\"crenukeswar2010@gmail.com\",\"username\":\"crenukeswar2010@gmail.com\",\"password\":\"renu\","
		  		+ "\"firstname\":\"renu\",\"lastname\":\"chinta\",\"country\":\"india\","
		  		+ "\"state\":\"AP\",\"district\":\"ATP\"}";
		dto.setEmail("renukeswar2010@gmail.com");dto.setUsername("renu.javatechnews@gmail.com");
		dto.setFirstname("Renukeswar");dto.setLastname("Chinta");
		dto.setCountry("India");dto.setState("AP");dto.setDistrict("STP");
		this.mvc.perform(post("/api/user/signupUser").content(json).contentType("application/json"))
				.andExpect(status().isOk());
				//.andExpect(jsonPath("$.id").value(3));
				
	}
}

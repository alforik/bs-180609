package kr.ac.slipp.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;



public class UserControllerTest {
	
	//@Autowired
	private MockMvc mockMvc;
	
	
	@Before
	public void setup() throws Exception {
		this.mockMvc = standaloneSetup(new UserController())
				.alwaysExpect(status().isFound()).build();
	}

	@Test
	public void create() throws Exception {
		this.mockMvc.perform(post("/users")
				.param("userId", "c")
				.param("password", "c")
				.param("name", "c_name")
				.param("email", "c@naver.com")
				)
				.andExpect(redirectedUrl("/"));
	}

}

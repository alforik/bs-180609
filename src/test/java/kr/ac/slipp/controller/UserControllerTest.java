package kr.ac.slipp.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;



public class UserControllerTest {
	
	@InjectMocks
	private UserController userController;
	
	//@Autowired
	private MockMvc mockMvc;
	
	
	@Before
	public void setup() throws Exception {
		//this.mockMvc = standaloneSetup(new UserController())
		//this.mockMvc = standaloneSetup(userController)
		this.mockMvc = MockMvcBuilders.standaloneSetup(userController)
				//.alwaysExpect(status().isFound())
				.build();
	}

//	@Test
//	public void create() throws Exception {
//		this.mockMvc.perform(post("/users")
//				.param("userId", "c")
//				.param("password", "c")
//				.param("name", "c_name")
//				.param("email", "c@naver.com")
//				)
//				.andExpect(redirectedUrl("/"));
//	}
	@Test
	public void list() throws Exception {
		this.mockMvc.perform(get("/users"))
				.andExpect(status().isOk());
		
	}

}

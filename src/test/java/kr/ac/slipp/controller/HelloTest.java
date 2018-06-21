package kr.ac.slipp.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Test;

public class HelloTest {

	@Test
	public void testhello() throws Exception {
		standaloneSetup(new Hello()).build()
		.perform(get("/hello"))
		.andExpect(status().isOk())
		.andExpect(content().contentType("text/plain;charset=ISO-8859-1"))
		.andExpect(content().string("hello rest"));
	}

}

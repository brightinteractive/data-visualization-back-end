package com.example.datavisualizationbackend;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
public class DemoApplicationTests {

	//Test all servlet related calls. Can test endpoints by calling them directly using MockMvc.
	private MockMvc mockMvc;

	@InjectMocks
	private TimeController timeController;

	@Before
	public void setUp() throws Exception{
		mockMvc = MockMvcBuilders.standaloneSetup(timeController).build();
	}

	@Test
	public void testTimeController() throws Exception{
		mockMvc.perform(get("/")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
}

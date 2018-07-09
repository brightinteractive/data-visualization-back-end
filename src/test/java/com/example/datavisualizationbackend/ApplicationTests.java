package com.example.datavisualizationbackend;

import com.example.datavisualizationbackend.simulator.controllers.UploadEventController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
public class ApplicationTests {

    //Test all servlet related calls. Can test endpoints by calling them directly using MockMvc.
    private MockMvc mockMvc;

    @InjectMocks
    private UploadEventController uploadEventController;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(uploadEventController).build();
    }

    @Test
    public void testUploadEventController() throws Exception {
        mockMvc.perform(get("/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}

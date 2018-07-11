package com.example.datavisualizationbackend.visualizer.controllers;

import com.example.datavisualizationbackend.visualizer.services.EventStorageService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static java.util.Collections.emptyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class VisualizeEventsControllerTest {

    private MockMvc mockMvc;

    @Mock
    private EventStorageService eventStorageService;

    @InjectMocks
    private VisualizeEventsController visualizeEventsController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this.getClass());
        mockMvc = MockMvcBuilders.standaloneSetup(visualizeEventsController).build();
    }

    @Test
    public void getUploadEventsReturnsEmptyListWhenNoEvents() throws Exception {

        when(eventStorageService.getAllUploadEvents()).thenReturn(emptyList());

        mockMvc.perform(get("/getEvents")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }
}
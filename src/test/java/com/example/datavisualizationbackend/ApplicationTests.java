package com.example.datavisualizationbackend;

import com.example.datavisualizationbackend.simulator.controllers.UploadEventController;
import com.example.datavisualizationbackend.simulator.models.Event;
import com.example.datavisualizationbackend.simulator.services.UploadEventService;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import sun.plugin2.util.PojoUtil;

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
public class ApplicationTests {

    //Test all servlet related calls. Can test endpoints by calling them directly using MockMvc.
    private MockMvc mockMvc;

    @Mock
    private UploadEventService uploadEventService;

    @InjectMocks
    private UploadEventController uploadEventController;

    @Before
    public void setUp() throws Exception {
        initMocks(this.getClass());
        mockMvc = MockMvcBuilders.standaloneSetup(uploadEventController).build();
    }

    @Test
    public void canSimulateUploadEvent() throws Exception {

        Event event = new Event("", "", "", "", 123, "", new Date());
        String content = PojoUtil.toJson(event);

        mockMvc.perform(
                post("/upload-event")
                        .content(content)
                        .contentType((MediaType.APPLICATION_JSON))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(uploadEventService).simulateUploadEvent(eq(event));
    }
}

package com.example.datavisualizationbackend.simulator.controllers;

import com.example.datavisualizationbackend.Application;
import com.example.datavisualizationbackend.shared.models.Event;
import com.example.datavisualizationbackend.simulator.services.UploadEventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/upload-event")
public class UploadEventController {

    @Autowired
    UploadEventService uploadEventService;

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    @PostMapping
    public Event uploadEvent(@RequestBody Event event) {
        uploadEventService.simulateUploadEvent(event);
        logger.info("--EVENT UPLOADED--");
        return event;
    }


}

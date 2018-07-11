package com.example.datavisualizationbackend.simulator.controllers;

import com.example.datavisualizationbackend.simulator.models.Event;
import com.example.datavisualizationbackend.simulator.services.SimulateUploadEventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/upload-event")
public class SimulateUploadEventController {

    @Autowired
    SimulateUploadEventService uploadEventService;

    private static final Logger logger = LoggerFactory.getLogger(SimulateUploadEventController.class);

    @PostMapping
    public void simulateUploadEvent(@RequestBody Event event) {
        uploadEventService.simulateUploadEvent(event);

    }


}

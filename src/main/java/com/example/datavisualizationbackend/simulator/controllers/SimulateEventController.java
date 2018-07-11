package com.example.datavisualizationbackend.simulator.controllers;

import com.example.datavisualizationbackend.shared.models.Event;
import com.example.datavisualizationbackend.simulator.services.SimulateEventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/simulate-event")
public class SimulateEventController {

    @Autowired
    SimulateEventService simulateEventService;

    private static final Logger logger = LoggerFactory.getLogger(SimulateEventController.class);

    @PostMapping
    public void simulateEvent(@RequestBody Event event) {
        simulateEventService.simulateEvent(event);

    }


}

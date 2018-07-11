package com.example.datavisualizationbackend.visualizer.controllers;

import com.example.datavisualizationbackend.shared.models.Event;
import com.example.datavisualizationbackend.visualizer.models.StoredEvent;
import com.example.datavisualizationbackend.visualizer.services.EventStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/events")
public class VisualizeEventsController {

    @Autowired
    EventStorageService eventStorageService;

    private static final Logger logger = LoggerFactory.getLogger(VisualizeEventsController.class);

    @GetMapping
    public List<Event> getEvents() {
        List<Event> events = eventStorageService.getAllEvents();
        return events;
    }

}


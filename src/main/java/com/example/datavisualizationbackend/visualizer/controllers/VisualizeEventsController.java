package com.example.datavisualizationbackend.visualizer.controllers;

import com.example.datavisualizationbackend.simulator.models.Event;
import com.example.datavisualizationbackend.simulator.services.UploadEventService;
import com.example.datavisualizationbackend.visualizer.models.StoredEvent;
import com.example.datavisualizationbackend.visualizer.services.EventStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/getEvents")
public class VisualizeEventsController {

    @Autowired
    EventStorageService eventStorageService;

    private static final Logger logger = LoggerFactory.getLogger(VisualizeEventsController.class);

    @GetMapping
    public List<StoredEvent> getEvents() {

        Date date1 = new Date(140000000);
        Date date2 = new Date(20000000000000L);
        List<StoredEvent> events = eventStorageService.getEventsBetweenDateRange(date1, date2);
        return events;
    }

}


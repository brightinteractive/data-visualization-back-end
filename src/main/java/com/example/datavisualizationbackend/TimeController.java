package com.example.datavisualizationbackend;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class TimeController {

    @RequestMapping("/")
    public String getServerTime(){
        Date dateToBeFormatted = new Date();
        SimpleDateFormat datePattern = new SimpleDateFormat("HH:mm a");
        String currentServerTime = datePattern.format(dateToBeFormatted);
        return "The current time on the server is " + currentServerTime;
    }

}

package com.example.datavisualizationbackend;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@CrossOrigin
@RestController
@RequestMapping("/")
public class TimeController {

    @GetMapping
    public Time getServerTime(){
        Date dateToBeFormatted = new Date();
        SimpleDateFormat datePattern = new SimpleDateFormat("HH:mm a");
        Time serverTime = new Time(datePattern.format(dateToBeFormatted));
        return serverTime;
    }

}

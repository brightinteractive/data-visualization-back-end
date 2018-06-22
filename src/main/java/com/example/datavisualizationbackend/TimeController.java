package com.example.datavisualizationbackend;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import java.text.SimpleDateFormat;
import java.util.Date;

@CrossOrigin
@RestController
public class TimeController {

    @RequestMapping(value = "/", method = GET, produces = "application/json")
    public String getServerTime(){
        Date dateToBeFormatted = new Date();
        SimpleDateFormat datePattern = new SimpleDateFormat("HH:mm a");
        String currentServerTime = datePattern.format(dateToBeFormatted);
        return "{\"time\":\"" + currentServerTime + "\"}";
    }

}

package com.example.datavisualizationbackend;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import java.text.SimpleDateFormat;
import java.util.Date;

@CrossOrigin
@RestController
public class TimeController {

    @RequestMapping(value = "/", method=RequestMethod.GET)
    public Time getServerTime(){
        Date dateToBeFormatted = new Date();
        SimpleDateFormat datePattern = new SimpleDateFormat("HH:mm a");
        Time serverTime = new Time(datePattern.format(dateToBeFormatted));
        return serverTime;
    }

}

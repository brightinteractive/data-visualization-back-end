package com.example.datavisualizationbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;

import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
@SpringBootApplication
public class DemoApplication {

	@RequestMapping("/")
	@ResponseBody
	String home() {
		String serverTime = getServerTime();
		return serverTime;
	}

	private String getServerTime(){
		Date dateToBeFormatted = new Date();
		SimpleDateFormat datePattern = new SimpleDateFormat("HH:mm a");
		String currentServerTime = datePattern.format(dateToBeFormatted);
		return "The current time on the server is " + currentServerTime;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
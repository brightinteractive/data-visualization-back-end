package com.example.datavisualizationbackend.visualizer.repository;

import com.example.datavisualizationbackend.visualizer.models.StoredEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Repository;

@Repository
public class CrudRepository {

    private static final Logger logger = LoggerFactory.getLogger(CrudRepository.class);

    public void storeEvent(StoredEvent event){
        logger.info("Event logged : " + event);
    }

}





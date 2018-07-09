package com.example.datavisualizationbackend.visualizer.repository;

import com.example.datavisualizationbackend.visualizer.models.StoredEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EventRepository extends ElasticsearchRepository <StoredEvent, Integer> {

    Page<StoredEvent> findByAssetId(String id, Pageable pageable);

    Page<StoredEvent> findByAssetIdUsingCusomQuery(String id, Pageable pageable);

}





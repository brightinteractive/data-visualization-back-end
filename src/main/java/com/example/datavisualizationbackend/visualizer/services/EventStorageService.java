package com.example.datavisualizationbackend.visualizer.services;

import com.example.datavisualizationbackend.shared.models.Event;
import com.example.datavisualizationbackend.visualizer.models.StoredEvent;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Bulk;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.IndicesExists;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventStorageService {

    @Autowired
    JestClient jestClient;

    private String _indexName = "events";
    private String _typeName = "event";

    private static final Logger logger = LoggerFactory.getLogger(EventStorageService.class);

    public void indexEvent(StoredEvent event) {

        try {
            IndicesExists indicesExists = new IndicesExists.Builder(_indexName).build();
            JestResult result = jestClient.execute(indicesExists);

            if (!result.isSucceeded()) {
                CreateIndex createIndex = new CreateIndex.Builder(_indexName).build();
                jestClient.execute(createIndex);
            }

            Bulk bulk = new Bulk.Builder()
                    .addAction(new Index.Builder(event).index(_indexName).type(_typeName).build())
                    .build();

            jestClient.execute(bulk);

        } catch (IOException e) {
            logger.error("Indexing error", e);
        } catch (Exception e) {
            logger.error("Indexing error", e);
        }
    }

    public List<Event> getAllEvents() {
        try {
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.size(10000);
            searchSourceBuilder.query(QueryBuilders.matchAllQuery());

            Search search = new Search.Builder(searchSourceBuilder.toString())
                    .addIndex(_indexName)
                    .addType(_typeName)
                    .build();

            JestResult result = jestClient.execute(search);

            return result.getSourceAsObjectList(StoredEvent.class).stream().map(storedEvent -> storedEvent.toEvent()).collect(Collectors.toList());

        } catch (IOException e) {
            logger.error("Search error", e);
        } catch (Exception e) {
            logger.error("Search error", e);
        }
        return null;
    }


    public void storeEvent(StoredEvent event) {
        logger.info("--STORED EVENT :" + event + " --");
        indexEvent(event);
    }
}

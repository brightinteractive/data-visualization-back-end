package com.example.datavisualizationbackend.visualizer.services;

import com.example.datavisualizationbackend.visualizer.models.StoredEvent;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Bulk;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.IndicesExists;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class EventStorageService {

    @Autowired
    JestClient jestClient;

    private static final Logger logger = LoggerFactory.getLogger(EventStorageService.class);

    public void indexSampleEvents() {
        StoredEvent event1 = new StoredEvent("someType1", "test", "someUserName1", "someGroup1", 123, "someAssetTitle1", 1235345345);
        StoredEvent event2 = new StoredEvent("test", "someId2", "someUserName2", "someGroup2", 234, "someAssetTitle2", 1235345346);

        try {
            IndicesExists indicesExists = new IndicesExists.Builder("events").build();
            JestResult result = jestClient.execute(indicesExists);

            if (!result.isSucceeded()) {
                CreateIndex createIndex = new CreateIndex.Builder("events").build();
                jestClient.execute(createIndex);
            }

            Bulk bulk = new Bulk.Builder()
                    .addAction(new Index.Builder(event1).index("events").type("event").build())
                    .addAction(new Index.Builder(event2).index("events").type("event").build())
                    .build();

            result = jestClient.execute(bulk);

            System.out.println(result.getJsonString());

        } catch (IOException e) {
            logger.error("Indexing error", e);
        } catch (Exception e) {
            logger.error("Indexing error", e);
        }
    }

    public List<StoredEvent> searchEvents(String param) {
        try {
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(QueryBuilders.queryStringQuery(param));

            Search search = new Search.Builder(searchSourceBuilder.toString())
                    .addIndex("events")
                    .addType("event")
                    .build();

            JestResult result = jestClient.execute(search);
            return result.getSourceAsObjectList(StoredEvent.class);

        } catch (IOException e) {
            logger.error("Search error", e);
        } catch (Exception e) {
            logger.error("Search error", e);
        }
        return null;
    }


    public void storeEvent(StoredEvent event) {
        logger.info("--STORED EVENT :" + event + " --");
        indexSampleEvents();
        String query = "test";
        List<StoredEvent> events = searchEvents(query);
    }
}

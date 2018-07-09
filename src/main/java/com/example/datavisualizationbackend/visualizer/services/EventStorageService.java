package com.example.datavisualizationbackend.visualizer.services;

import com.example.datavisualizationbackend.visualizer.models.StoredEvent;
import com.example.datavisualizationbackend.visualizer.repository.EventRepository;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import io.searchbox.indices.CreateIndex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventStorageService {

    @Autowired
    JestClient jestClient;

    private static final Logger logger = LoggerFactory.getLogger(EventStorageService.class);

    private EventRepository repository;

    public Page<StoredEvent> findEventByAssetId(String id, Pageable pageable) {
        return repository.findByAssetIdUsingCusomQuery(id, pageable);
    }

    public void IndexEvent(){
        CreateEventIndex();
        StoredEvent event = CreateTestEvent();
        IndexEvent(event);
    }

    public void CreateEventIndex() {
        try {
            jestClient.execute(new CreateIndex.Builder("events").build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public StoredEvent CreateTestEvent() {
        StoredEvent source = new StoredEvent("someType", "someId", "someUserName", "someGroup", 123, "someAssetTitle", 1235345345);
        return source;
    }
    public void IndexEvent(StoredEvent source) {
        Index index = new Index.Builder(source).index("events").type("event").build();
    }


    public void SearchRepository() {
        String query = "{\n" +
                "    \"query\": {\n" +
                "        \"filtered\" : {\n" +
                "            \"query\" : {\n" +
                "                \"query_string\" : {\n" +
                "                    \"query\" : \"Lord\"\n" +
                "                }\n" +
                "            }\n"+
                "        }\n" +
                "    }\n" +
                "}";

        Search search = (Search) new Search.Builder(query).addIndex("events").addType("article").build();

        try {
            SearchResult result = jestClient.execute(search);
            System.out.println(result);
            System.out.println("SUCCESSFUL SEARCH REQUEST");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("FAILED SEARCH REQUEST");
        }


    }




    public void storeEvent(StoredEvent event) {
        logger.info("--STORED EVENT :" + event + " --");
    }
}

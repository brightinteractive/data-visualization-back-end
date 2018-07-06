package com.example.datavisualizationbackend.services;


import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Bulk;
import io.searchbox.core.Index;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.IndicesExists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;

@Service
public class JestService {

//    @Autowired @Qualifier("jpaRepository") CustomerService jpaCustomerService;
//
//    @Autowired
//    JestClient jestClient;
//
//    @Autowired
//    public void createEventIndex(){
//        try {
//            IndicesExists indicesExists = new IndicesExists.Builder().build();
//            JestResult result = jestClient.execute(indicesExists);
//
//            if (!result.isSucceeded()) {
//                // Create customer index
//                CreateIndex createIndex = new CreateIndex.Builder("customers").build();
//                jestClient.execute(createIndex);
//            }
//
//            Bulk bulk = new Bulk.Builder()
//                    .addAction(new Index.Builder(jpaCustomerService.getAllCustomers()).index("customer").type("customer").build())
//                    .build();
//
//            result = jestClient.execute(bulk);
//
//            System.out.println(result.getJsonString());
//
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }

}

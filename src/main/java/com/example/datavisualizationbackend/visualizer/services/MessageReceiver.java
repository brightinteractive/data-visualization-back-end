package com.example.datavisualizationbackend.visualizer.services;

import com.example.datavisualizationbackend.shared.models.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {

    private static final Logger logger = LoggerFactory.getLogger(MessageReceiver.class);

    @RabbitHandler
    public void receiveMessage(Event event) {
        logger.info("You logged this event: " + event);
    }
//    public void handleMessage(Message message) {
//        logger.info("--LISTENING FOR MESSAGES ON QUEUE--");
//        System.out.println("Recieved the message " + message);
//
//    }

//    private static void setMessageListener(SimpleMessageListenerContainer listenerContainer) {
//        listenerContainer.setMessageListener(new MessageListener() {
//            @Override
//            public void onMessage(Message message) {
//                byte[] body = message.getBody();
//                Gson gson = new GsonBuilder().create();
//                EventHack event = gson.fromJson(new String(body), EventHack.class);
//                Event eventOut = new Event(event.getEventType(), event.getUserId(), event.getUserName(), event.getGroup(), event.getAssetId(), event.getAssetTitle(), new Date(event.getDate()));
//            }
//        });
//    }
//
//    private static void addShutDownHook(SimpleMessageListenerContainer listenerContainer) {
//        Runtime.getRuntime().addShutdownHook(new Thread() {
//            @Override
//            public void run() {
//                listenerContainer.shutdown();
//            }
//        });
//    }
//
//    private static void SetErrorHandler(SimpleMessageListenerContainer listenerContainer) {
//        listenerContainer.setErrorHandler(new ErrorHandler() {
//            public void handleError(Throwable t) {
//                t.printStackTrace();
//            }
//        });
//    }
}





package com.example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SNSEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;

/**
 * Processes events from EventBridge.
 */

public class EventProcessor implements RequestHandler<Map<String,Object>, String> {

    private static final Logger logger = LoggerFactory.getLogger(EventProcessor.class);

    /**
     * Handles the incoming event and processes it.
     *
     * @param event The event data.
     * @param context The context of the Lambda function.
     * @return The response message.
     */
    @Override
    public String handleRequest(Map<String,Object> event, Context context) {
        if (event == null || event.isEmpty()) {
            logger.error("Event or event records are null");
            return "Error: Event or event records are null";
        }

        logger.info("Event received: {}", event);
        // processing logic here
        processEvent(event);
        return "Event processed successfully";
    }

    /**
     * Processes the received event.
     *
     * @param event The event data.
     */

    private void processEvent(Map<String,Object> event) {
        // Example processing logic
        logger.info("Processing event: {}", event);
    }
}




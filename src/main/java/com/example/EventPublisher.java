package com.example;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.eventbridge.EventBridgeClient;
import software.amazon.awssdk.services.eventbridge.model.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Publishes events to EventBridge.
 */
public class EventPublisher {

    private final EventBridgeClient eventBridgeClient;
    // Constructor to initialize EventBridge client using configuration from AppConfig
    public EventPublisher() {
        this.eventBridgeClient = EventBridgeClient.builder()
                .region(Region.US_EAST_1)
                .build();
    }

    /**
     * Publishes an event to EventBridge.
     *
     * @param detailType The detail type of the event.
     * @param source The source of the event.
     * @param detail The event details.
     */
    public void publishEvent(String detailType, String source, String detail) {
        try {
            // Create event request entry with the event details
            PutEventsRequestEntry requestEntry = PutEventsRequestEntry.builder()
                    .detailType(detailType)
                    .source(source)
                    .detail(detail)
                    .eventBusName("default")
                    .build();
            // Add entry to the request list
            List<PutEventsRequestEntry> requestEntries = new ArrayList<>();
            requestEntries.add(requestEntry);
            // Create a PutEventsRequest with the list of entries
            PutEventsRequest request = PutEventsRequest.builder()
                    .entries(requestEntries)
                    .build();

            // Publish event to the EventBridge
            PutEventsResponse response = eventBridgeClient.putEvents(request);
            // Handle the response, checking for success or failure
            for (PutEventsResultEntry resultEntry : response.entries()) {
                if (resultEntry.eventId() != null) {
                    System.out.println("Event published successfully: " + resultEntry.eventId());
                } else {
                    System.out.println("Event publishing failed: " + resultEntry.errorMessage());
                }
            }
        } catch (EventBridgeException e) {
            e.printStackTrace();
        }
    }

    /**
     * Main method to publish a sample event.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        EventPublisher eventPublisher = new EventPublisher();
        // Define the event details
        String detailType = "CLMS Data Change";
        String source = "clms.system";
        String detail = "{ \"changeType\": \"update\", \"data\": { \"id\": \"123\", \"name\": \"New Channel\", \"category\": \"Music\" } }";
        // Publish the event
        eventPublisher.publishEvent(detailType, source, detail);
    }
}

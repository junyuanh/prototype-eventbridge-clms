package com.example;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.eventbridge.EventBridgeClient;
import java.net.URI;

/**
 * Configuration class for creating AWS EventBridge client.
 */

public class AppConfig {

    /**
     * Creates and returns an EventBridgeClient.
     *
     * @return EventBridgeClient
     */

    // Create and configure an EventBridge client
    public static EventBridgeClient createEventBridgeClient() {
        //String endpointUri = "http://192.168.5.15:30666"; //Kubernetes node IP and NodePort
        //URI endpoint = URI.create(endpointUri);

        // Create and return the EventBridge client
        return EventBridgeClient.builder()
                .region(Region.US_EAST_1)
                .build();

    }
}







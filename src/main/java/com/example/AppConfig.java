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
    public static EventBridgeClient createEventBridgeClient() {
        // String endpointUri = "http://192.168.5.15:30666";
        // URI endpoint = URI.create(endpointUri);

        return EventBridgeClient.builder()
                .region(Region.US_EAST_1)
                //.endpointOverride(endpoint) // Uncomment if using a custom endpoint
                .build();
    }
}







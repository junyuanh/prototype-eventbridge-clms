package com.example;

import software.amazon.awssdk.services.eventbridge.EventBridgeClient;
import software.amazon.awssdk.services.eventbridge.model.PutTargetsRequest;
import software.amazon.awssdk.services.eventbridge.model.PutTargetsResponse;
import software.amazon.awssdk.services.eventbridge.model.Target;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Adds a target to an EventBridge rule.
 */

public class AddTarget {
    private static final Logger logger = LoggerFactory.getLogger(AddTarget.class);

    /**
     * Adds a target to the specified EventBridge rule.
     *
     * @param ruleName The name of the EventBridge rule.
     * @param targetId The ID of the target.
     * @param arn The ARN of the target.
     */

    public void addTarget(String ruleName, String targetId, String arn) {
        // Create EventBridge client
        EventBridgeClient client = AppConfig.createEventBridgeClient();

        // Create target object
        Target target = Target.builder()
                .id(targetId)
                .arn(arn)
                .build();

        // Create PutTargetsRequest
        PutTargetsRequest request = PutTargetsRequest.builder()
                .rule(ruleName)
                .targets(target)
                .build();

        // Add target to the rule
        PutTargetsResponse response = client.putTargets(request);

        // Log the result
        if (response.failedEntryCount() > 0) {
            logger.error("Failed to add target: {}", response.failedEntries());
        } else {
            logger.info("Target added successfully");
        }
    }
}
package com.example;

import software.amazon.awssdk.services.eventbridge.EventBridgeClient;
import software.amazon.awssdk.services.eventbridge.model.PutRuleRequest;
import software.amazon.awssdk.services.eventbridge.model.PutRuleResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Creates an EventBridge rule.
 */

public class CreateEventRule {
    private static final Logger logger = LoggerFactory.getLogger(CreateEventRule.class);

    /**
     * Creates an EventBridge rule with the specified name and event pattern.
     *
     * @param ruleName The name of the rule.
     * @param eventPattern The event pattern for the rule.
     */

    public void createRule(String ruleName, String eventPattern) {
        // Create EventBridge client
        EventBridgeClient client = AppConfig.createEventBridgeClient();

        // Create PutRuleRequest
        PutRuleRequest request = PutRuleRequest.builder()
                .name(ruleName)
                .eventPattern(eventPattern)
                .build();

        // Create rule
        PutRuleResponse response = client.putRule(request);

        // Log the result
        logger.info("Rule created: {}", response.ruleArn());
    }
}


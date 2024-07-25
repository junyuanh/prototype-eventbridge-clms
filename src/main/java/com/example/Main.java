package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main class to run the EventBridge example.
 */
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Starting application...");

        // Create instances of classes to manage EventBridge
        CreateEventRule createEventRule = new CreateEventRule();
        AddTarget addTarget = new AddTarget();
        EventPublisher eventPublisher = new EventPublisher();

        // Define the rule name and pattern
        String ruleName = "TestRule";
        String eventPattern = "{ \"source\": [\"clms.system\"] }";
        // Define the target ID and ARN for CloudWatch Log Group
        String targetId = "TargetId";
        String arn = "arn:aws:logs:us-east-1:000000000000:log-group:TestLogGroup"; // Update with your CloudWatch Log Group ARN

        logger.info("Creating rule...");
        createEventRule.createRule(ruleName, eventPattern);

        logger.info("Adding target...");
        addTarget.addTarget(ruleName, targetId, arn);
        // Define event details
        String detailType = "CLMS Data Change";
        String source = "clms.system";
        String detail = "{ \"changeType\": \"update\", \"data\": { \"id\": \"123\", \"name\": \"New Channel\", \"category\": \"Music\" } }";

        logger.info("Publishing event...");
        eventPublisher.publishEvent(detailType, source, detail);

        logger.info("Application finished.");
    }
}

package com.example;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.eventbridge.EventBridgeClient;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;
import org.json.JSONObject;

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
        // Create a Secrets Manager client
        SecretsManagerClient secretsClient = SecretsManagerClient.builder().build();

        // Name of the secret which contains the AWS credentials
        String secretName = "eventbridge-test-credentials";

        // Create a request to get the secret value
        GetSecretValueRequest getSecretValueRequest = GetSecretValueRequest.builder()
                .secretId(secretName)
                .build();

        // Get the secret value from Secrets Manager
        GetSecretValueResponse getSecretValueResponse = secretsClient.getSecretValue(getSecretValueRequest);
        String secretString = getSecretValueResponse.secretString();

        // Parse the secret value (JSON format) to extract AWS credentials
        JSONObject secretJson = new JSONObject(secretString);
        String accessKey = secretJson.getString("AWS_ACCESS_KEY_ID");
        String secretKey = secretJson.getString("AWS_SECRET_ACCESS_KEY");

        // Create AWS credentials object using the extracted keys
        AwsBasicCredentials awsCredentials = AwsBasicCredentials.create(accessKey, secretKey);

        // Create and return the EventBridge client configured with the credentials and region
        return EventBridgeClient.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(StaticCredentialsProvider.create(awsCredentials))
                .build();
    }
}







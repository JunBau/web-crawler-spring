package com.junbau.web_crawler_spring.aws.s3;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import java.net.URI;

@Service
public class AwsS3ClientBuilderService {

    public S3Client createAwsS3Client(String accessKeyId, String secretAccessKey, String region){
        final AwsBasicCredentials basicAWSCredentials = AwsBasicCredentials.create(accessKeyId, secretAccessKey);
        // Make this more flexible so that it can be called on actual AWS.
        // If using EKS we'll need to assume pod identity.
        return S3Client
                .builder()
                .endpointOverride(URI.create("https://s3.localhost.localstack.cloud:4566"))
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(basicAWSCredentials))
                .build();
    }

}

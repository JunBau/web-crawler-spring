package com.junbau.web_crawler_spring.scraper;

import com.junbau.web_crawler_spring.aws.s3.AwsS3ClientBuilderService;
import com.junbau.web_crawler_spring.scraper.service.DocScraperService;
import com.junbau.web_crawler_spring.scraper.service.ScraperService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.util.StringUtils;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DocScraperServiceTest {

    @Mock
    AwsS3ClientBuilderService awsS3ClientBuilderService;
    @Mock
    S3Client s3Client;

    @InjectMocks
    ScraperService scraperService = new DocScraperService();

    @Test
    void shouldReturnDocumentWithValidURL() {
        String url = "https://www.scrapingcourse.com/ecommerce/";
        when(s3Client.putObject(any(PutObjectRequest.class), any(RequestBody.class))).thenReturn(mock(PutObjectResponse.class));
        when(awsS3ClientBuilderService.createAwsS3Client("test", "test", "eu-west-1")).thenReturn(s3Client);
        String document = scraperService.getDocument(url);
        assertTrue(StringUtils.isNotBlank(document));
    }

}
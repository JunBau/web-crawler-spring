package com.junbau.web_crawler_spring.scraper.service;

import com.junbau.web_crawler_spring.aws.s3.AwsS3ClientBuilderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

@Service
public class DocScraperService implements  ScraperService {

    protected Logger logger = LogManager.getLogger(DocScraperService.class);

    final String SCRAPED_PAGE_BUCKET = "webpage-html-bucket";

    @Autowired
    private AwsS3ClientBuilderService awsS3ClientBuilderService;

    @Override
    public String getDocument(String url) {
        try (S3Client s3Client = awsS3ClientBuilderService.createAwsS3Client("test", "test", "eu-west-1")) {
            Document document = Jsoup.connect(url).get();

            String documentHtml = document.html();

            uploadScrapedHtml(document.title().trim(), documentHtml, s3Client);

            return documentHtml;
        } catch (Exception e) {
            logger.warn("Could not parse document for {}", url, e);
        }
        return "";
    }

    private void uploadScrapedHtml(String fileName, String documentHtml, S3Client s3Client) {
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                        .bucket(SCRAPED_PAGE_BUCKET)
                                .key(fileName)
                                        .build();

        RequestBody scrapedHtml = RequestBody.fromString(documentHtml);

        PutObjectResponse response = s3Client.putObject(putObjectRequest, scrapedHtml);
        logger.info(response.toString());

        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(SCRAPED_PAGE_BUCKET)
                .key(fileName)
                .build();

        // Retrieving the object from the bucket.
        ResponseInputStream<GetObjectResponse> objResponse = s3Client.getObject(getObjectRequest);
        logger.info(objResponse.toString());
    }
}

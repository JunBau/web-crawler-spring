package com.junbau.web_crawler_spring.crawler.controller;

import com.junbau.web_crawler_spring.crawler.model.CrawlRequest;
import com.junbau.web_crawler_spring.crawler.service.Crawler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CrawlerController {

    @Autowired Crawler webCrawler;

    @PostMapping("/crawl")
    public ResponseEntity greeting(@RequestBody CrawlRequest crawlRequest) {
        crawlRequest.getUrls().forEach(webCrawler::crawl);
        return ResponseEntity.ok().build();
    }

}

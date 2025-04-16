package com.junbau.web_crawler_spring.crawler.service;


import com.junbau.web_crawler_spring.scraper.service.ScraperService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class WebCrawler implements Crawler {

    protected static Logger logger = LogManager.getLogger(WebCrawler.class);

    private static Set<String> visitedUrls = new HashSet<>();

    @Autowired private ScraperService scraper;


    @Override
    public void crawl(String url) {
        if (!url.startsWith("https://")) {
            logger.warn("Url must be secure: " + url);
        }

        logger.info("Attempting to crawl " + url);

        String htmlDoc = scraper.getDocument(url);

        logger.info(htmlDoc);
    }

    @Override
    public Set<String> getVisitedUrls() {
        return Set.of();
    }
}

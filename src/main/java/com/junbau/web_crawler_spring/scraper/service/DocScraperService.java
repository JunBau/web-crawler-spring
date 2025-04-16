package com.junbau.web_crawler_spring.scraper.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

@Service
public class DocScraperService implements  ScraperService {

    protected Logger logger = LogManager.getLogger(DocScraperService.class);

    @Override
    public String getDocument(String url) {
        try {
            Document document = Jsoup.connect(url).get();

            return document.html();
        } catch (Exception e) {
            logger.warn("Could not parse document for {}", url, e);
        }
        return "";
    }
}

package com.junbau.web_crawler_spring.scraper;

import com.junbau.web_crawler_spring.scraper.service.DocScraperService;
import com.junbau.web_crawler_spring.scraper.service.ScraperService;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import static org.junit.jupiter.api.Assertions.*;

class DocScraperServiceTest {

    ScraperService scraperService = new DocScraperService();

    @Test
    void shouldReturnDocumentWithValidURL() {
        String url = "https://www.scrapingcourse.com/ecommerce/";
        String document = scraperService.getDocument(url);
        assertTrue(StringUtils.isNotBlank(document));
    }

}
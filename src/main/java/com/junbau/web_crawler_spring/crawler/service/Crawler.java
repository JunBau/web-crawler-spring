package com.junbau.web_crawler_spring.crawler.service;

import java.util.Set;

public interface Crawler {

    void crawl(String url);

    Set<String> getVisitedUrls();

}

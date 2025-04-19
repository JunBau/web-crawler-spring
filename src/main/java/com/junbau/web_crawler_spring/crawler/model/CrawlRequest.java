package com.junbau.web_crawler_spring.crawler.model;


import java.util.List;

public class CrawlRequest {

    private List<String> urls;

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }
}

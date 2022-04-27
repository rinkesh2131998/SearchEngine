package com.project.searchengine.config;

import com.project.searchengine.service.crawler.CrawlerService;
import java.io.IOException;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Slf4j
@Data
@Component
@ConfigurationProperties(prefix = "crawl")
public class CrawlerRunConfig {

  //list of initial uris to crawl
  private final List<String> initialUrls;
  //crawler service used to run crawler
  private final CrawlerService crawler;

  @PostConstruct
  void startCrawlerService() {
//    Flux.fromStream(initialUrls.stream()).log().doOnNext(url -> {
//      try {
//        crawler.getPageLinks(url);
//      } catch (IOException ioException) {
//        log.error("Error on reading url: {}, cause: {}", url, ioException.getCause());
//      }
//    }).doOnError(throwable -> {
//      log.error("Error on multiStreaming urls to crawler service: {}", throwable);
//    }).doOnComplete(() -> {
//      log.info("Parsing of initial Urls: {} completed", initialUrls);
//    }).subscribe();
  }

}

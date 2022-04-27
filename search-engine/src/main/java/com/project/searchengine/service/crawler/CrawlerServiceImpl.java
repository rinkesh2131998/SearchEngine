package com.project.searchengine.service.crawler;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * implemetation of Crawler Service for web.
 */
@Slf4j
@Service
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "crawl")
public class CrawlerServiceImpl implements CrawlerService {

  //crawl timeoute
  private final Long timeoute;
  //crawl depth
  private final Long depth;

  private final Queue<URL> visitedUrls = new ConcurrentLinkedQueue<>();
  private final Queue<URL> toVisitUrls = new ConcurrentLinkedQueue<>();


  @Override
  public void crawlPages(List<String> urls) {
    Flux.fromStream(urls.stream()).log().doOnNext(url -> {
      crawlPage(url);
    }).doOnError(throwable -> {
      log.error("Error on processing url, cause: {}", throwable);
    }).doOnComplete(() -> {
      log.info("Processed all urls from config file");
    }).subscribe();
  }

  private void crawlPage(String url) {
    //delete "/" from end of link if present
    if (Pattern.compile("/$").matcher(url).find()) {
      url = url.substring(0, url.length() - 1);

    }
  }


}

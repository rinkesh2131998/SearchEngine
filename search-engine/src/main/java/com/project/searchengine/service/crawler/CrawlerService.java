package com.project.searchengine.service.crawler;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * Crawler service interfce.
 */
@Service
public interface CrawlerService {

  public void crawlPages(List<String> urls);

}

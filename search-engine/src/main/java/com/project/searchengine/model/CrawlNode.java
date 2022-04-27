package com.project.searchengine.model;

import java.util.List;
import lombok.Data;

/**
 * model class for data in each node of url crawled
 */
@Data
public class CrawlNode {

  private final String rootUrl;
  private final String url;
  private final String title;
  private final String text;
  private final List<CrawlNode> nodes;

}

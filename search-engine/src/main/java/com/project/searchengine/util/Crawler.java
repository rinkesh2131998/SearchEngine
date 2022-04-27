package com.project.searchengine.util;

import crawlercommons.robots.BaseRobotRules;
import crawlercommons.robots.SimpleRobotRules;
import crawlercommons.robots.SimpleRobotRulesParser;
import java.io.IOException;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.net.URL;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.validator.routines.UrlValidator;

/**
 * utility methods for web crawler
 */
@Slf4j
public class Crawler {

  public static boolean isValidUrl(String url) {
    final UrlValidator validator = new UrlValidator();
    return validator.isValid(url);
  }

  public static String getBaseUrlString(String link) {
    Matcher httpsMatcher = Pattern.compile("^https://www.(.*?)/").matcher(link);
    Matcher httpsMatcher1 = Pattern.compile("^https://www.(.*?)").matcher(link);
    Matcher httpsMatcher2 = Pattern.compile("^https://(.*?)/").matcher(link);
    Matcher httpsMatcher3 = Pattern.compile("^https://(.*?)").matcher(link);
    Matcher httpMatcher = Pattern.compile("^http://www.(.*?)/").matcher(link);
    Matcher httpMatcher1 = Pattern.compile("^http://www.(.*?)").matcher(link);
    Matcher httpMatcher2 = Pattern.compile("^http://(.*?)/").matcher(link);
    Matcher httpMatcher3 = Pattern.compile("^http://(.*?)").matcher(link);

    if (httpsMatcher.find()) {
      return httpsMatcher.group(1);
    }
    if (httpsMatcher1.find()) {
      return link.substring(12, link.length());
    }
    if (httpsMatcher2.find()) {
      return httpsMatcher2.group(1);
    }
    if (httpsMatcher3.find()) {
      return link.substring(8, link.length());
    }


    if (httpMatcher.find()) {
      return httpMatcher.group(1);
    }
    if (httpMatcher1.find()) {
      return link.substring(11, link.length());
    }
    if (httpMatcher2.find()) {
      return httpMatcher2.group(1);
    }
    if (httpMatcher3.find()) {
      return link.substring(7, link.length());
    }
    // Removing spaces from link
    link = link.replaceAll("\\s+", "");
    // Truncate first 255 char of the link
    link = link.substring(0, Math.min(link.length(), 255));
    return link;
  }

  public boolean checkRobots(String url, String link) {
    final SimpleRobotRulesParser simpleRobotRulesParser = new SimpleRobotRulesParser();
    final String baseUrlString = "https://www." + getBaseUrlString(url);
    try {
      final URLConnection connection = new URL(url).openConnection();
      final byte[] content = IOUtils.toByteArray(connection);
      final BaseRobotRules robotRules =
          simpleRobotRulesParser.parseContent(url, content, "text/plain", "*");
      return robotRules.isAllowed(link);
    } catch (IOException ioException) {
      log.error("Error on opening connection to URL: {}, cause: {}", url, ioException);
      return false;
    }
  }

}

import Crawler, { CrawlerRequestResponse } from 'crawler';
import { listOfInitialUris, PageMapping } from '../models/crawl.model';

const crawledUris: string[] = [];
const toCrawlUris: string[] = [...listOfInitialUris];
const crawlDocuments: PageMapping[] = [];

const crawler: Crawler = new Crawler({
  maxConnections: 10,
  skipDuplicate: true,
});

const crawlUris = (crawler: Crawler, url: string) => {
  console.log(`Crawling url: ${url}`);
  crawler.queue({
    uri: url,
    callback: (
      error: Error,
      res: CrawlerRequestResponse,
      done: () => void,
    ): void => {
      if (error) {
        console.log(`Error on crawling: ${error}`);
      }
      let $ = res.$;
      try {
        let urls = $('a');
        Object.keys(urls).forEach((item) => {
          if (urls[item].type === 'tag') {
            let href: string = urls[item].attribs.href;
            if (
              href &&
              (href.indexOf('http://') == 0 || href.indexOf('https://') == 0)
            ) {
              toCrawlUris.push(href);
              console.log(`Added Url: ${href}`);
            }
          }
        });
        const title = $('title').text();
        const descriptions = $('meta[name=description]').attr('content');
        const keywords = $('meta[name=keywords]').attr('content');

        crawlDocuments.push({
          index: 'crawled-pages',
          document: {
            url: url,
            title: title,
            description: descriptions,
            keywords: keywords,
          },
        });
      } catch (err) {
        console.log(`Encountered error ${err} during crawling of: ${url}`);
        done();
      }
      done();
    },
  });
};

export const runCrawler = () => {
  while (crawledUris.length < 7) {
    const url = toCrawlUris.pop();
    // console.log(`url: ${url} -> crawled: ${crawledUris.includes(url)}`);
    if (url && !crawledUris.includes(url)) {
      crawlUris(crawler, url);
      crawledUris.push(url);
    }
  }
  return;
};

type IndexName = 'crawled-pages' | 'crawled-images';

export interface PageMapping {
  index: IndexName;
  document: {
    url: string;
    title?: string;
    description?: string;
    keywords?: string;
  };
}

export interface ImageMapping {
  index: IndexName;
  document: {
    url: string;
    altText: string;
    imageUri: string;
  };
}

export const listOfInitialUris: string[] = [
  'https://www.geeksforgeeks.org/',
  'https://www.imdb.com/',
  'https://www.bbc.com/',
  'https://www.livescore.in/',
  'https://wiki.com/',
  'https://www.listennotes.com/',
  'https://news.ycombinator.com/',
];

import express from 'express';
import { runCrawler } from './services/crawl.service';

const app = express();

app.get('/', (req: any, res: any) => {
  console.log(`request: ${req}`);
  res.send('Hello World!!!');
});

const PORT = process.env.PORT || 3000;

app.listen(PORT, () => {
  runCrawler();
  console.log(`Server is in http://localhost:${PORT}`);
});

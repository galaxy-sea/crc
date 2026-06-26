import { copyFileSync, mkdirSync, readFileSync, rmSync, writeFileSync } from 'node:fs';
import { dirname, join } from 'node:path';

const dist = 'dist';
const files = [
  ['app.js', 'app.js'],
  ['robots.txt', 'robots.txt'],
  ['sitemap.xml', 'sitemap.xml'],
  ['node_modules/js-crc/build/crc.min.js', 'vendor/js-crc/crc.min.js'],
  ['node_modules/js-crc/build/models.min.js', 'vendor/js-crc/models.min.js'],
];

mkdirSync(dist, { recursive: true });
rmSync(join(dist, 'app.js'), { force: true });
rmSync(join(dist, 'index.html'), { force: true });
rmSync(join(dist, 'robots.txt'), { force: true });
rmSync(join(dist, 'sitemap.xml'), { force: true });
rmSync(join(dist, 'vendor'), { force: true, recursive: true });

for (const [from, to] of files) {
  const target = join(dist, to);
  mkdirSync(dirname(target), { recursive: true });
  copyFileSync(from, target);
}

const html = readFileSync('index.html', 'utf8')
  .replace('./node_modules/js-crc/build/crc.min.js', './vendor/js-crc/crc.min.js')
  .replace('./node_modules/js-crc/build/models.min.js', './vendor/js-crc/models.min.js');

writeFileSync(join(dist, 'index.html'), html);
console.log('Built site/dist');

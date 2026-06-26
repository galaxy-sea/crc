import { copyFileSync, cpSync, existsSync, mkdirSync, readFileSync, rmSync, writeFileSync } from 'node:fs';
import { dirname, join } from 'node:path';

const dist = 'dist';
const deployRoot = '../../resume';
const deployTarget = join(deployRoot, 'plus.wcj.crc');
const timestamp = Date.now();
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
  .replace('./node_modules/js-crc/build/models.min.js', './vendor/js-crc/models.min.js')
  .replace('./app.js', './app.js?v=' + timestamp);

writeFileSync(join(dist, 'index.html'), html);
console.log('Built site/dist');

if (existsSync(deployRoot)) {
  mkdirSync(deployTarget, { recursive: true });
  rmSync(join(deployTarget, 'app.js'), { force: true });
  rmSync(join(deployTarget, 'index.html'), { force: true });
  rmSync(join(deployTarget, 'robots.txt'), { force: true });
  rmSync(join(deployTarget, 'sitemap.xml'), { force: true });
  rmSync(join(deployTarget, 'vendor'), { force: true, recursive: true });
  cpSync(dist, deployTarget, { recursive: true });
  console.log('Copied site/dist to ' + deployTarget);
} else {
  console.log('Skipped copy: ' + deployRoot + ' does not exist');
}

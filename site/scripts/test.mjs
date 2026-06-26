import { readFileSync } from 'node:fs';
import models from 'js-crc/models';

const html = readFileSync('index.html', 'utf8');
const rowCount = (html.match(/<tr data-method=/g) || []).length;
const modelCount = Object.keys(models).length;

if (rowCount !== modelCount) {
  throw new Error('Expected ' + modelCount + ' CRC rows in index.html, got ' + rowCount);
}

if (!html.includes('CRC-32') || !html.includes('CRC-82/DARC')) {
  throw new Error('Expected CRC names were not found in index.html');
}

console.log('index.html contains ' + rowCount + ' CRC rows');

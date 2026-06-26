import { readFileSync, writeFileSync } from 'node:fs';

const source = readFileSync('node_modules/js-crc/src/models.js', 'utf8');
const body = source.slice(source.indexOf('var MODELS = ['), source.indexOf('var WINDOW ='));
const entries = [];
const seen = new Set();

for (const block of body.matchAll(/\{[\s\S]*?\n    \}/g)) {
  const name = block[0].match(/name:\s*'([^']+)'/)?.[1];
  if (!name) {
    continue;
  }
  add(name);

  const aliasText = block[0].match(/alias:\s*\[([^\]]+)\]/)?.[1];
  if (aliasText) {
    for (const alias of aliasText.matchAll(/'([^']+)'/g)) {
      add(alias[1]);
    }
  }
}

function add(name) {
  const method = name.replace(/[-/]/g, '_').toLocaleLowerCase();
  if (seen.has(method)) {
    return;
  }
  seen.add(method);
  entries.push({ name, method });
}

const start = '        <!-- CRC_MODELS_START -->';
const end = '        <!-- CRC_MODELS_END -->';
const rows = entries.map(function (entry) {
  return [
    '        <tr data-method="' + escapeHtml(entry.method) + '">',
    '          <td>' + escapeHtml(entry.name) + '</td>',
    '          <td><code></code></td>',
    '        </tr>',
  ].join('\n');
}).join('\n');

const html = readFileSync('index.html', 'utf8');
const before = html.indexOf(start);
const after = html.indexOf(end);

if (before === -1 || after === -1 || after < before) {
  throw new Error('CRC model markers not found in index.html');
}

writeFileSync(
  'index.html',
  html.slice(0, before + start.length) + '\n' + rows + '\n' + html.slice(after)
);

function escapeHtml(value) {
  return value
    .replace(/&/g, '&amp;')
    .replace(/"/g, '&quot;')
    .replace(/</g, '&lt;')
    .replace(/>/g, '&gt;');
}

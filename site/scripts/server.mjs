import { createReadStream, existsSync, statSync } from 'node:fs';
import { createServer } from 'node:http';
import { extname, join, normalize, resolve } from 'node:path';

const root = resolve('.');
const startPort = Number(process.env.PORT || 5173);
const host = '0.0.0.0';
const types = {
  '.html': 'text/html; charset=utf-8',
  '.js': 'text/javascript; charset=utf-8',
  '.css': 'text/css; charset=utf-8',
  '.json': 'application/json; charset=utf-8',
};

listen(startPort);

function listen(port) {
  const server = createServer(handleRequest);

  server.once('error', function (error) {
    if (error.code === 'EADDRINUSE' && port < startPort + 20) {
      listen(port + 1);
      return;
    }
    throw error;
  });

  server.listen(port, host, function () {
    console.log('http://localhost:' + port);
  });
}

function handleRequest(request, response) {
  const url = new URL(request.url || '/', 'http://localhost');
  const path = normalize(decodeURIComponent(url.pathname)).replace(/^(\.\.[/\\])+/, '');
  let file = resolve(join(root, path === '/' ? 'index.html' : path));

  if (!file.startsWith(root) || !existsSync(file)) {
    response.writeHead(404);
    response.end('Not found');
    return;
  }

  if (statSync(file).isDirectory()) {
    file = join(file, 'index.html');
  }

  response.writeHead(200, {
    'Content-Type': types[extname(file)] || 'application/octet-stream',
    'Cache-Control': 'no-store',
  });
  createReadStream(file).pipe(response);
}

(function () {
  var input = document.getElementById('input');
  var encoding = document.getElementById('encoding');
  var outputEncoding = document.getElementById('output-encoding');
  var list = document.getElementById('list');
  var message = document.getElementById('message');
  var storageKey = 'crc-pinned-methods';
  var messageTimer;
  var lastPointer = { x: window.innerWidth / 2, y: window.innerHeight / 2 };
  var rows = Array.prototype.slice.call(document.querySelectorAll('#list tr[data-method]'));

  applyPinnedRows();
  bindRows();

  function render() {
    var value = input.value;
    var bytes;

    resizeInput();

    try {
      bytes = encodeInput(value, encoding.value);
    } catch (error) {
      rows.forEach(function (row) {
        row.querySelector('code').textContent = value ? error.message : '';
      });
      return;
    }

    rows.forEach(function (row) {
      var method = row.dataset.method;
      var code = row.querySelector('code');

      code.textContent = value ? formatOutput(run(method, bytes)) : '';
    });
  }

  function run(method, bytes) {
    try {
      return window[method](bytes);
    } catch (error) {
      return error.message;
    }
  }

  function encodeInput(value, encodingName) {
    if (encodingName === 'utf8') {
      return Array.prototype.slice.call(new TextEncoder().encode(value));
    }
    if (encodingName === 'utf16le') {
      return utf16(value, true);
    }
    if (encodingName === 'utf16be') {
      return utf16(value, false);
    }
    if (encodingName === 'ascii') {
      return singleByte(value, 0x7f, 'ASCII');
    }
    if (encodingName === 'latin1') {
      return singleByte(value, 0xff, 'Latin-1');
    }
    if (encodingName === 'hex') {
      return hex(value);
    }
    if (encodingName === 'base64') {
      return base64(value);
    }
    return value;
  }

  function utf16(value, littleEndian) {
    var bytes = [];
    for (var i = 0; i < value.length; i++) {
      var code = value.charCodeAt(i);
      if (littleEndian) {
        bytes.push(code & 0xff, code >>> 8);
      } else {
        bytes.push(code >>> 8, code & 0xff);
      }
    }
    return bytes;
  }

  function singleByte(value, max, name) {
    var bytes = [];
    for (var i = 0; i < value.length; i++) {
      var code = value.charCodeAt(i);
      if (code > max) {
        throw new Error(name + ' cannot encode "' + value[i] + '"');
      }
      bytes.push(code);
    }
    return bytes;
  }

  function hex(value) {
    var normalized = value.replace(/\s+/g, '');
    if (normalized.length % 2 !== 0 || /[^0-9a-f]/i.test(normalized)) {
      throw new Error('Invalid Hex input');
    }
    var bytes = [];
    for (var i = 0; i < normalized.length; i += 2) {
      bytes.push(parseInt(normalized.slice(i, i + 2), 16));
    }
    return bytes;
  }

  function base64(value) {
    try {
      return Array.prototype.map.call(atob(value), function (char) {
        return char.charCodeAt(0);
      });
    } catch (error) {
      throw new Error('Invalid Base64 input');
    }
  }

  function formatOutput(value) {
    if (outputEncoding.value === 'hex-upper') {
      return value.toUpperCase();
    }
    return value.toLowerCase();
  }

  function bindRows() {
    rows.forEach(function (row) {
      row.cells[0].title = '点击置顶/取消置顶';
      row.cells[1].title = '点击复制';
      row.cells[0].addEventListener('click', function (event) {
        togglePinned(row, event);
      });
      row.cells[1].addEventListener('click', function (event) {
        copyResult(row, event);
      });
    });
  }

  function applyPinnedRows() {
    var pinned = readPinned();
    var byMethod = {};

    rows.forEach(function (row) {
      byMethod[row.dataset.method] = row;
      row.classList.remove('pinned');
    });

    pinned.slice().reverse().forEach(function (method) {
      var row = byMethod[method];
      if (row) {
        row.classList.add('pinned');
        list.insertBefore(row, list.firstElementChild);
      }
    });

    rows = Array.prototype.slice.call(document.querySelectorAll('#list tr[data-method]'));
  }

  function togglePinned(row, event) {
    var method = row.dataset.method;
    var name = row.cells[0].textContent;
    var point = getPoint(event);
    var pinned = readPinned();
    var index = pinned.indexOf(method);

    if (index === -1) {
      pinned.unshift(method);
      showMessage('Pinned: ' + name, point);
    } else {
      pinned.splice(index, 1);
      showMessage('Unpinned: ' + name, point);
    }

    writePinned(pinned);
    applyPinnedRows();
  }

  function readPinned() {
    try {
      return JSON.parse(localStorage.getItem(storageKey) || '[]');
    } catch (error) {
      return [];
    }
  }

  function writePinned(pinned) {
    try {
      localStorage.setItem(storageKey, JSON.stringify(pinned));
    } catch (error) {
      return;
    }
  }

  function copyResult(row, event) {
    var name = row.cells[0].textContent;
    var result = row.querySelector('code').textContent;
    var text = name + '：' + result;
    var point = getPoint(event);

    if (!result) {
      return;
    }

    if (navigator.clipboard && navigator.clipboard.writeText) {
      navigator.clipboard.writeText(text).then(function () {
        showMessage('Copied: ' + text, point);
      }).catch(function () {
        copyFallback(text, point);
      });
      return;
    }

    copyFallback(text, point);
  }

  function copyFallback(text, point) {
    var textarea = document.createElement('textarea');
    textarea.value = text;
    document.body.appendChild(textarea);
    textarea.select();
    if (document.execCommand('copy')) {
      showMessage('Copied: ' + text, point);
    } else {
      showMessage('Copy failed', point);
    }
    document.body.removeChild(textarea);
  }

  function showMessage(text, point) {
    point = point || lastPointer;
    clearTimeout(messageTimer);
    message.textContent = text;
    positionMessage(point);
    message.classList.add('show');
    messageTimer = setTimeout(function () {
      message.textContent = '';
      message.classList.remove('show');
    }, 2000);
  }

  function getPoint(event) {
    if (event) {
      lastPointer = { x: event.clientX, y: event.clientY };
    }
    return lastPointer;
  }

  function positionMessage(point) {
    var margin = 8;
    var x = point.x + margin;
    var y = point.y - message.offsetHeight - margin;

    if (x + message.offsetWidth + margin > window.innerWidth) {
      x = point.x - message.offsetWidth - margin;
    }
    if (y < margin) {
      y = point.y + margin;
    }

    message.style.left = Math.max(margin, x) + 'px';
    message.style.top = Math.max(margin, y) + 'px';
  }

  input.addEventListener('input', render);
  encoding.addEventListener('change', render);
  outputEncoding.addEventListener('change', render);
  render();

  function resizeInput() {
    input.style.height = 'auto';
    input.style.height = input.scrollHeight + 'px';
  }
})();

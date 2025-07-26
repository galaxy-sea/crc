/*
 * Copyright 2025-present the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package plus.wcj.crc;

/**
 * @author ChangJin Wei (魏昌进)
 */
public class StandardCRC implements CRC<Long> {

    public final int width, crcByteLength;

    public final long poly, init, xorout, mask;

    public final boolean refin, refout;


    public StandardCRC(int width, long poly, long init, boolean refin, boolean refout, long xorout) {
        this.width = width;
        this.crcByteLength = (width + 7) / 8;

        this.poly = poly;
        this.init = init;
        this.refin = refin;
        this.refout = refout;
        this.xorout = xorout;
        this.mask = (1L << width) - 1;
    }

    @Override
    public Long calculate(byte[] data, int ignoreTailBytes) {
        long crc = init;
        int limit = data.length - ignoreTailBytes;
        for (int i = 0; i < limit - ignoreTailBytes; i++) {
            int value = data[i] & 0xFF;
            if (refin) {
                value = CRCUtils.reverseBits(value, 8);
            }
            for (int j = 0; j < 8; j++) {
                boolean bit = ((value >> (7 - j)) & 1) != 0;
                boolean topBit = ((crc >> (width - 1)) & 1) != 0;
                crc = ((crc << 1) & mask);
                if (topBit ^ bit) {
                    crc ^= poly;
                }
            }
        }
        if (refout) {
            crc = CRCUtils.reverseBits(crc, width);
        }
        crc = (crc ^ xorout) & mask;
        return crc;
    }



    @Override
    public byte[] array(byte[] data, int ignoreTailBytes, boolean bigEndian) {
        long value = calculate(data, ignoreTailBytes);
        byte[] result = new byte[crcByteLength];
        for (int i = 0; i < crcByteLength; i++) {
            int index = bigEndian ? (crcByteLength - 1 - i) : i;
            result[index] = (byte) ((value >> (8 * i)) & 0xFF);
        }
        return result;
    }



}

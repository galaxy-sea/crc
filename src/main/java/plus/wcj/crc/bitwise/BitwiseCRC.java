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

package plus.wcj.crc.bitwise;

import plus.wcj.crc.CRC;
import plus.wcj.crc.CRCModel;

/**
 * @author ChangJin Wei (魏昌进)
 */
public class BitwiseCRC implements CRC<Long> {

    public final int width, crcByteLength;

    public final long poly, init, xorout, mask;

    public final boolean refin, refout;

    public final String[] names;

    public final long msbMask;

    private final int widthDiff;


    public BitwiseCRC(CRCModel<Long> crcParams) {
        this.width = crcParams.width;
        this.crcByteLength = crcParams.crcByteLength;

        this.poly = crcParams.poly;
        this.init = crcParams.init;
        this.xorout = crcParams.xorout;
        this.mask = crcParams.mask;

        this.refin = crcParams.refin;
        this.refout = crcParams.refout;

        this.names = crcParams.names;

        this.msbMask = 1L << (width - 1);
        this.widthDiff = 64 - width;
    }

    @Override
    public Long calculate(byte[] data, int offset, int length) {
        long crc = init;
        int end = offset + length;

        for (int i = offset; i < end; i++) {
            int value = data[i];
            if (refin) {
                value = Integer.reverse(value) >>> (32 - 8);
            }
            for (int j = 0x80; j != 0; j >>= 1) {
                long bit = crc & msbMask;
                // crc = (crc << 1) & mask;
                crc = (crc << 1);
                if ((value & j) != 0) {
                    bit ^= msbMask;
                }
                if (bit != 0) {
                    crc ^= poly;
                }
            }
        }
        if (refout) {
            crc = Long.reverse(crc) >>> widthDiff;
        }
        return (crc ^ xorout) & mask;
    }


    @Override
    public byte[] array(byte[] data, int offset, int length, boolean bigEndian) {
        long value = calculate(data, offset, length);
        byte[] result = new byte[crcByteLength];
        for (int i = 0; i < crcByteLength; i++) {
            int index = bigEndian ? (crcByteLength - 1 - i) : i;
            result[index] = (byte) ((value >> (8 * i)) & 0xFF);
        }
        return result;
    }


}

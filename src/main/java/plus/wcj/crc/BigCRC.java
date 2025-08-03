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

import lombok.Getter;

import java.math.BigInteger;

/**
 * @author ChangJin Wei (魏昌进)
 */
@Getter
public class BigCRC implements CRC<BigInteger> {

    public final int width, crcByteLength;

    public final BigInteger poly, init, xorout, mask;

    public final boolean refin, refout;

    public BigCRC(int width, String polyHex, String initHex, boolean refin, boolean refout, String xoroutHex) {
        this.width = width;
        this.crcByteLength = (width + 7) / 8;

        this.poly = new BigInteger(polyHex, 16);
        this.init = new BigInteger(initHex, 16);
        this.xorout = new BigInteger(xoroutHex, 16);
        this.mask = BigInteger.ONE.shiftLeft(width).subtract(BigInteger.ONE);

        this.refin = refin;
        this.refout = refout;
    }

    @Override
    public BigInteger calculate(byte[] data, int offset, int length) {
        BigInteger crc = init;
        int end = offset + length;
        for (int i = offset; i < end; i++) {
            int value = data[i] & 0xFF;
            if (refin) {
                value = CRCUtils.reverseBits(value, 8);
            }
            for (int j = 0; j < 8; j++) {
                boolean inputBit = ((value >> (7 - j)) & 1) != 0;
                boolean topBit = crc.testBit(width - 1);
                crc = crc.shiftLeft(1).and(mask);
                if (topBit ^ inputBit) {
                    crc = crc.xor(poly);
                }
            }
        }
        if (refout) {
            crc = CRCUtils.reverseBits(crc, width);
        }
        crc = crc.xor(xorout).and(mask);
        return crc;
    }


    @Override
    public byte[] array(byte[] data, int offset, int length, boolean bigEndian) {
        BigInteger value = calculate(data, offset, length);
        byte[] raw = value.toByteArray();

        byte[] result = new byte[crcByteLength];
        int copyStart = Math.max(0, raw.length - crcByteLength);
        int copyLength = Math.min(raw.length, crcByteLength);
        System.arraycopy(raw, copyStart, result, crcByteLength - copyLength, copyLength);

        if (!bigEndian) {
            for (int i = 0; i < crcByteLength / 2; i++) {
                byte tmp = result[i];
                result[i] = result[crcByteLength - 1 - i];
                result[crcByteLength - 1 - i] = tmp;
            }
        }
        return result;
    }


}

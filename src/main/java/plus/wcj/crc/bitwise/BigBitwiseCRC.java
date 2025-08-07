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
import plus.wcj.crc.CRCUtils;

import java.math.BigInteger;

/**
 * @author ChangJin Wei (魏昌进)
 */
public class BigBitwiseCRC implements CRC<BigInteger> {

    public final int width, crcByteLength;

    public final BigInteger poly, init, xorout, mask;

    public final boolean refin, refout;

    public final String[] names;

    public BigBitwiseCRC(CRCModel m) {
        this.width = m.width;
        this.crcByteLength = m.crcByteLength;
        this.poly = new BigInteger(String.valueOf(m.poly));
        this.init = new BigInteger(String.valueOf(m.init));
        this.xorout = new BigInteger(String.valueOf(m.xorout));
        this.refin = m.refin;
        this.refout = m.refout;
        this.names = m.names;
        // 若模型未提供 mask/msb，可根据 width 推导
        this.mask = BigInteger.ONE.shiftLeft(width).subtract(BigInteger.ONE);
    }


    @Override
    public BigInteger calculate(byte[] data, int offset, int length) {
        BigInteger crc = init;
        int end = offset + length;
        for (int i = offset; i < end; i++) {
            int value = data[i];
            if (refin) {
                value = Integer.reverse(value) >>> (32 - 8);
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
        byte[] result = new byte[crcByteLength];
        for (int i = 0; i < crcByteLength; i++) {
            int index = bigEndian ? (crcByteLength - 1 - i) : i;
            result[index] = (byte) (value.shiftRight(8 * i).and(BigInteger.valueOf(0xFF)).intValue());
        }
        return result;
    }
}
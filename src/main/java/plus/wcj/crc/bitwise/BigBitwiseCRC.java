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

import java.math.BigInteger;

/**
 * @author ChangJin Wei (魏昌进)
 */
public class BigBitwiseCRC extends CRC.bigCRC {


    public BigBitwiseCRC(CRCModel crcParams) {
        super(crcParams);
    }

    public BigInteger calculate(byte[] data) {
        return calculate(data, 0, data.length);
    }


    public BigInteger calculate(byte[] data, int offset, int length) {
        BigInteger crc = init;
        for (int i = offset; i < offset + length; i++) {
            int value = data[i];
            if (refin) {
                value = Integer.reverse(value) >>> (32 - 8);
            }
            for (int j = 0x80; j != 0; j >>= 1) {
                boolean inputBit = (value & j) != 0;
                boolean topBit = crc.testBit(width - 1);
                crc = crc.shiftLeft(1).and(mask);
                if (topBit ^ inputBit) {
                    crc = crc.xor(poly);
                }
            }
        }
        if (refout) {
            crc = reverseBits(crc, width);
        }
        return crc.xor(xorout).and(mask);
    }


}
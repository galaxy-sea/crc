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
public class BitwiseCRC extends CRC.LongCRC {


    public BitwiseCRC(CRCModel crcParams) {
        super(crcParams);
    }

    public long calculate(byte[] data, int offset, int length) {
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


}

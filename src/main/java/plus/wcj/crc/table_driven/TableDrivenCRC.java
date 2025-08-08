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

package plus.wcj.crc.table_driven;

import plus.wcj.crc.CRC;
import plus.wcj.crc.CRCModel;

import java.util.HashMap;

/**
 * @author ChangJin Wei (魏昌进)
 * @since 2025/8/8
 */
public class TableDrivenCRC extends CRC.LongCRC {

    public static final HashMap<String[], long[]> TABLE = new HashMap<String[], long[]>();

    public final long[] table;

    public final long init;


    public TableDrivenCRC(CRCModel<Long> crcParams) {
        super(crcParams);
        this.init = (refin) ? Long.reverse(crcParams.init) >>> widthDiff : crcParams.init;
        this.table = initTable(crcParams);
    }

    @Override
    public long calculate(byte[] data, int offset, int length) {
        long crc = init;
        int end = offset + length;
        for (int i = offset; i < end; i++) {
            int value = data[i];
            if (refin) {
                crc = ((crc >>> 8) ^ table[(value ^ (int) crc) & 0xFF]);
            } else if (width < 8) {
                crc = table[(value ^ (((int) crc) << (8 - width))) & 0xFF];
            } else {
                crc = table[(value ^ ((int) (crc >>> (width - 8)))) & 0xFF] ^ (crc << 8);
            }
        }
        return (((refout != refin) ? Long.reverse(crc) >>> widthDiff : crc) ^ xorout) & mask;
    }


    public static long[] initTable(final CRCModel<Long> crcModel) {
        synchronized (TABLE) {
            long[] table = TABLE.get(crcModel.names);
            if (table != null) {
                return table;
            }
            CRCModel<Long> crcModel0 =
                    CRCModel.create(crcModel.width, crcModel.poly,
                                    0, crcModel.refin,
                                    crcModel.refin, 0,
                                    "", crcModel.names);

            table = new long[256];
            for (int i = 0; i < table.length; i++) {
                table[i] = getCrc(crcModel0, i);
            }
            TABLE.put(crcModel.names, table);
            return table;
        }
    }

    private static long getCrc(CRCModel<Long> crcModel, int value) {
        long crc = crcModel.init;
        if (crcModel.refin) {
            value = Integer.reverse(value) >>> (32 - 8);
        }
        for (int j = 0x80; j != 0; j >>= 1) {
            long bit = crc & crcModel.msbMask;
            // crc = (crc << 1) & crcModel.mask;
            crc = (crc << 1);
            if ((value & j) != 0) {
                bit ^= crcModel.msbMask;
            }
            if (bit != 0) {
                crc ^= crcModel.poly;
            }
        }
        if (crcModel.refout) {
            crc = Long.reverse(crc) >>> crcModel.widthDiff;
        }
        return (crc ^ crcModel.xorout) & crcModel.mask;
    }
}


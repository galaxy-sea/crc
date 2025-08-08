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

import org.junit.Test;
import plus.wcj.crc.bitwise.BigBitwiseCRC;
import plus.wcj.crc.bitwise.BitwiseCRC;
import plus.wcj.crc.table_driven.TableDrivenCRC;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author ChangJin Wei (魏昌进)
 * @since 2025/8/4
 */

public class CRCTest {

    @Test
    public void testhex() {
        for (CRCModel crcModel : CRCModel.values) {
            testhex(crcModel);
        }
    }

    @Test
    public void test64hex() {
            testhex(CRCModel.CRC_64_ECMA_182);
            // testhex(CRCModel.CRC_82_DARC);
    }



    @Test
    public void testcalculate() {
        for (CRCModel crcModel : CRCModel.values) {
            BigBitwiseCRC crc1 = new BigBitwiseCRC(crcModel);
            if (crcModel.width == 82) {
                continue;
            }
            BitwiseCRC crc2 = new BitwiseCRC(crcModel);

            BigInteger BigBitwiseCRC = crc1.calculate("123456789".getBytes());
            long BitwiseCRC = crc2.calculate("123456789".getBytes());

            if (!BigBitwiseCRC.toString().equals(String.valueOf(BitwiseCRC)) && crcModel.width != 64) {

                throw new RuntimeException(Arrays.toString(crcModel.names) + " BigBitwiseCRC: " + BigBitwiseCRC + " BitwiseCRC: " + BitwiseCRC);



            }

        }
    }

    @Test
    public void bitwisehexCRC2() {
        Set<Integer> set = new TreeSet<>();
        for (CRCModel crcModel : CRCModel.values) {
            int width = crcModel.width;
            set.add(width);
        }
        System.out.println(set);
    }


    @Test
    public void bitwisehexCRC3() {
        TableDrivenCRC tableDrivenCRC = new TableDrivenCRC(CRCModel.CRC_12_UMTS);
        System.out.println(Arrays.toString(tableDrivenCRC.table));

    }


    private void testhex(CRCModel crcModel) {
        CRC crc1 = new BigBitwiseCRC(crcModel);
        CRC crc2 = crcModel.width == 82 ? crc1 : new BitwiseCRC(crcModel);
        CRC crc3 = crcModel.width == 82 ? crc1 : new TableDrivenCRC(crcModel);

        String BigBitwiseCRC = crc1.hex("123456789".getBytes());
        String BitwiseCRC = crc2.hex("123456789".getBytes());
        String TableDrivenCRC = crc3.hex("123456789".getBytes());


        if (!crcModel.check.equals(BigBitwiseCRC)
                || !crcModel.check.equals(BitwiseCRC)
                || !crcModel.check.equals(TableDrivenCRC)
        ) {
            // System.out.println(Arrays.toString(crcModel.names) + " check: " + crcModel.check + " BigBitwiseCRC: " + BigBitwiseCRC + " BitwiseCRC: " + BitwiseCRC + " TableDrivenCRC: " + TableDrivenCRC);
            throw new RuntimeException(Arrays.toString(crcModel.names) + " check: " + crcModel.check + " BigBitwiseCRC: " + BigBitwiseCRC + " BitwiseCRC: " + BitwiseCRC + " TableDrivenCRC: " + TableDrivenCRC);
        }
    }


}

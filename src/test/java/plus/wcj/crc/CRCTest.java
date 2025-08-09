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
import plus.wcj.crc.bitwise.BitwiseBigCRC;
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
    public void testAllCrc() {
        for (CRCModel crcModel : CRCModel.values) {
            CRC crc1 = new BitwiseBigCRC(crcModel);
            CRC crc2 = crcModel.width == 82 ? crc1 : new BitwiseCRC(crcModel);
            CRC crc3 = crcModel.width == 82 ? crc1 : new TableDrivenCRC(crcModel);
            testhex(crcModel, CRCModel.checkInput,crc1, crc2, crc3);
        }
    }

    @Test
    public void testAllLoopCrc() {
        for (CRCModel crcModel : CRCModel.values) {
            StringBuilder builder = new StringBuilder();
            CRC crc1 = new BitwiseBigCRC(crcModel);
            CRC crc2 = crcModel.width == 82 ? crc1 : new BitwiseCRC(crcModel);
            CRC crc3 = crcModel.width == 82 ? crc1 : new TableDrivenCRC(crcModel);
            for (int i = Byte.MIN_VALUE; i < Byte.MAX_VALUE; i++) {
                builder.append(i);
                try {
                    testhex1(crcModel, builder.toString(), crc1, crc2, crc3);
                } catch (Exception e) {
                    System.out.println(builder.toString());
                    throw e;
                }
            }

            for (int i = Byte.MAX_VALUE; i > Byte.MIN_VALUE; i--) {
                builder.append(i);
                try {
                    testhex1(crcModel, builder.toString(), crc1, crc2, crc3);
                } catch (Exception e) {
                    System.out.println(builder.toString());
                    throw e;
                }
            }
        }
    }





    @Test
    public void testcalculate() {
        for (CRCModel crcModel : CRCModel.values) {
            BitwiseBigCRC crc1 = new BitwiseBigCRC(crcModel);
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




    private void testhex(CRCModel crcModel, String data, CRC crc1, CRC  crc2, CRC  crc3) {

        String BigBitwiseCRC = crc1.hex(data.getBytes());
        String BitwiseCRC = crc2.hex(data.getBytes());
        String TableDrivenCRC = crc3.hex(data.getBytes());


        if (!crcModel.check.equals(BigBitwiseCRC)
                || !crcModel.check.equals(BitwiseCRC)
                || !crcModel.check.equals(TableDrivenCRC)
        ) {
            // System.out.println(Arrays.toString(crcModel.names) + " check: " + crcModel.check + " BigBitwiseCRC: " + BigBitwiseCRC + " BitwiseCRC: " + BitwiseCRC + " TableDrivenCRC: " + TableDrivenCRC);
            throw new RuntimeException(Arrays.toString(crcModel.names) + " check: " + crcModel.check + " BigBitwiseCRC: " + BigBitwiseCRC + " BitwiseCRC: " + BitwiseCRC + " TableDrivenCRC: " + TableDrivenCRC);
        }
    }


    private void testhex1(CRCModel crcModel, String data, CRC crc1, CRC crc2, CRC crc3) {

        String BigBitwiseCRC = crc1.hex(data.getBytes());
        String BitwiseCRC = crc2.hex(data.getBytes());
        String TableDrivenCRC = crc3.hex(data.getBytes());


        if (!BigBitwiseCRC.equals(BitwiseCRC) || !BigBitwiseCRC.equals(TableDrivenCRC)) {
            // System.out.println(Arrays.toString(crcModel.names) + " check: " + crcModel.check + " BigBitwiseCRC: " + BigBitwiseCRC + " BitwiseCRC: " + BitwiseCRC + " TableDrivenCRC: " + TableDrivenCRC);
            throw new RuntimeException(Arrays.toString(crcModel.names) + " check: " + crcModel.check + " BigBitwiseCRC: " + BigBitwiseCRC + " BitwiseCRC: " + BitwiseCRC + " TableDrivenCRC: " + TableDrivenCRC);
        }
    }


}

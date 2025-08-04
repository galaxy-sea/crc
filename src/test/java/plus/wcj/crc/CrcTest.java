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
import plus.wcj.crc.bitwise.BigCRC;
import plus.wcj.crc.bitwise.StandardCRC;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * @author ChangJin Wei (魏昌进)
 */
public class CrcTest {

    // @Test
    // public void test() {
    //     CRC crc = CRCModel.CRC_3_GSM.getCrc();
    //     // System.out.println(CRCUtils.bytesToHex(crc.array("1234567890")));
    //     System.out.println(CRCUtils.bytesToHex(crc.array("1234567890".getBytes())));
    // }

    // @Test
    // public void check() {
    //     for (CRCModel crcModel : CRCModel.values()) {
    //         byte[] bytes = crcModel.getCrc().array(CRCModel.checkInput.getBytes());
    //         String crc = CRCUtils.bytesToHex(bytes);
    //         String check = CRCUtils.bytesToHex(CRCUtils.hexToBytes(crcModel.getCheck()));
    //         if (!check.equalsIgnoreCase(crc)) {
    //             // throw new RuntimeException(Arrays.toString(crcModel.getNames()) + " " + check + " " + crc);
    //
    //             System.out.println(Arrays.toString(crcModel.getNames()) + "对比值：" + check + " 结果数据：" + crc);
    //         }
    //
    //     }
    // }

    // @Test
    // public void example() {
    //     String data = "1234567890";
    //     for (CRCModel crcModel : CRCModel.values()) {
    //         CRC crc = crcModel.getCrc();
    //         String names = Arrays.toString(crcModel.getNames());
    //
    //         System.out.println(names + " checkSum: " + Arrays.toString(crc.array(data.getBytes())));
    //         System.out.println(names + " checkSum: " + crc.hex(data.getBytes()));
    //     }
    // }


    // @Test
    // public void test64() {
    //     StandardCRC standardCRC = new StandardCRC(64, 0x42F0E1EBA9EA3693L, 0x00000000L, false, false, 0x00000000L);
    //     BigCRC bigCRC = new BigCRC(64, "42f0e1eba9ea3693", "0", false, false, "0");
    //
    //     Long calculate = standardCRC.calculate("123456789".getBytes());
    //     System.out.println("------------");
    //     BigInteger calculate1 = bigCRC.calculate("123456789".getBytes());
    //
    //     System.out.println("++++++++++++++++++++");
    //     System.out.println(calculate);
    //     System.out.println(calculate1);
    // }

    @Test
    public void test() {
        CRC crc3 = CRC.create(CRCModel.CRC_4_G_704);
        CRC crc = CRC.create(CRCModel.CRC_82_DARC);


        StandardCRC crc123 = (StandardCRC) CRC.create(CRCModel.CRC_4_G_704);
        BigCRC crc5643 = (BigCRC) CRC.create(CRCModel.CRC_82_DARC);

    }

}

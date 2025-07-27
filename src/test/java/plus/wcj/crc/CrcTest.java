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

import java.util.Arrays;

/**
 * @author ChangJin Wei (魏昌进)
 */
public class CrcTest {

    @Test
    public void test() {
        CRC crc = CRCModel.CRC_3_GSM.getCrc();
        System.out.println(CRCUtils.bytesToHex(crc.array("1234567890")));
        System.out.println(CRCUtils.bytesToHex(crc.array("1234567890", false)));
    }

    @Test
    public void check() {
        for (CRCModel crcModel : CRCModel.values()) {
            byte[] bytes = crcModel.getCrc().array(CRCModel.checkInput.getBytes());
            String crc = CRCUtils.bytesToHex(bytes);
            String check = CRCUtils.bytesToHex(CRCUtils.hexToBytes(crcModel.getCheck()));
            if (!check.equalsIgnoreCase(crc)) {
                throw new RuntimeException();
            }

        }
    }

    @Test
    public void example() {
        String data = "1234567890";
        for (CRCModel crcModel : CRCModel.values()) {
            CRC crc = crcModel.getCrc();
            String names = Arrays.toString(crcModel.getNames());

            System.out.println(names + " checkSum: " + Arrays.toString(crc.array(data)));
            System.out.println(names + " checkSum: " + crc.hex(data));
        }
    }


}

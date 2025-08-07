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

import java.util.Arrays;

/**
 * @author ChangJin Wei (魏昌进)
 * @since 2025/8/4
 */

public class CRCTest {

    @Test
    public void bitwisehexCRC() {
        for (CRCModel crcModel : CRCModel.values) {


            CRC crc1 = new BigBitwiseCRC(crcModel);
            CRC crc2 = crcModel.width == 82 ? crc1 : new BitwiseCRC(crcModel);

            String BigBitwiseCRC = crc1.hex("123456789".getBytes());
            String BitwiseCRC = crc2.hex("123456789".getBytes());

            System.out.println("AA " + crc1);
            System.out.println("BB " + crc2);
            if (!BigBitwiseCRC.equals(BitwiseCRC)) {
                throw new RuntimeException(Arrays.toString(crcModel.names) + " BigBitwiseCRC: " + BigBitwiseCRC + " BitwiseCRC: " + BitwiseCRC);
            }

        }
    }


}

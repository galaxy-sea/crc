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

import plus.wcj.crc.bitwise.BigCRC;
import plus.wcj.crc.bitwise.StandardCRC;


/**
 * @author ChangJin Wei (魏昌进)
 */
public interface CRC<T> {

    static CRC create(CRCModel crcParams) {
        if (crcParams.width > 64) {
            return new BigCRC(crcParams);
        }
        return new StandardCRC(crcParams);
    }


    default T calculate(byte[] data) {
        return calculate(data, 0, data.length);
    }

    T calculate(byte[] data, int offset, int length);

    // array byte
    default byte[] array(byte[] data) {
        return array(data, 0, data.length, true);
    }

    default byte[] array(byte[] data, boolean bigEndian) {
        return array(data, 0, data.length, bigEndian);
    }

    byte[] array(byte[] data, int offset, int length, boolean bigEndian);
    // array byte

    // array String
    default byte[] array(String data) {
        return array(data.getBytes(), 0, data.length(), true);
    }

    default byte[] array(String data, boolean bigEndian) {
        return array(data.getBytes(), 0, data.length(), bigEndian);
    }

    default byte[] array(String data, int offset, int length, boolean bigEndian) {
        return array(data.getBytes(), offset, length, bigEndian);
    }
    // array String


    // hex byte
    default String hex(byte[] data) {
        return CRCUtils.bytesToHex(array(data, 0, data.length, true));
    }

    default String hex(byte[] data, boolean bigEndian) {
        return CRCUtils.bytesToHex(array(data, 0, data.length, bigEndian));
    }

    default String hex(byte[] data, int offset, int length, boolean bigEndian) {
        return CRCUtils.bytesToHex(array(data, offset, length, bigEndian));
    }
    // hex byte


    // hex String
    default String hex(String data) {
        return CRCUtils.bytesToHex(array(data.getBytes(), 0, data.length(), true));
    }

    default String hex(String data, boolean bigEndian) {
        return CRCUtils.bytesToHex(array(data.getBytes(), 0, data.length(), bigEndian));
    }

    default String hex(String data, int offset, int length, boolean bigEndian) {
        return CRCUtils.bytesToHex(array(data.getBytes(), offset, length, bigEndian));
    }
    // hex String

}

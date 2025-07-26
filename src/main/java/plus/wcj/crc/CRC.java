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

/**
 * @author ChangJin Wei (魏昌进)
 */
public interface CRC<T> {

    default T calculate(byte[] data) {
        return calculate(data, 0);
    }

    T calculate(byte[] data, int ignoreTailBytes);

    byte[] array(byte[] data, int ignoreTailBytes, boolean bigEndian);

    // ======== default Method

    default byte[] array(byte[] data) {
        return array(data, 0, true);
    }

    default byte[] array(byte[] data, boolean bigEndian) {
        return array(data, 0, bigEndian);
    }

    default byte[] array(byte[] data, int ignoreTailBytes) {
        return array(data, ignoreTailBytes, true);
    }


    default String hex(byte[] data) {
        return hex(data, 0, true);
    }

    default String hex(byte[] data, boolean bigEndian) {
        return hex(data, 0, bigEndian);
    }

    default String hex(byte[] data, int ignoreTailBytes) {
        return hex(data, ignoreTailBytes, true);
    }

    default String hex(byte[] data, int ignoreTailBytes, boolean bigEndian) {
        return CRCUtils.bytesToHex(array(data, ignoreTailBytes, bigEndian));
    }

}

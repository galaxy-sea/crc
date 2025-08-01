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
public class NoOpCRC implements CRC<Long> {

    @Override
    public Long calculate(byte[] data, int ignoreTailBytes) {
        return 0L;
    }

    @Override
    public byte[] array(byte[] data, int ignoreTailBytes, boolean bigEndian) {
        return new byte[0];
    }

}

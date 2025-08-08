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

import java.math.BigInteger;

/**
 * @author ChangJin Wei (魏昌进)
 */
public final class CRCUtils {

    private CRCUtils() {

    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }


    public  static byte[] hexToBytes(String hex) {
        if (hex.length() % 2 != 0) {
            hex = "0" + hex;
        }
        int len = hex.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                    + Character.digit(hex.charAt(i + 1), 16));
        }
        return data;
    }

    public static BigInteger reverseBits(BigInteger value, int width) {
        BigInteger result = BigInteger.ZERO;
        for (int i = 0; i < width; i++) {
            if (value.testBit(i)) {
                result = result.setBit(width - 1 - i);
            }
        }
        return result;
    }


    /**
     * {@link java.lang.Integer#reverse(int)}
     *
     * @param value
     * @param width
     * @return
     */
    @Deprecated
    public static int reverseBits(int value, int width) {
        int result = 0;
        for (int i = 0; i < width; i++) {
            result <<= 1;
            result |= (value & 1);
            value >>= 1;
        }
        return result;
    }

    /**
     * {@link java.lang.Long#reverse(long)}
     * @param value
     * @param width
     * @return
     */
    @Deprecated
    public static long reverseBits(long value, int width) {
        long result = 0;
        for (int i = 0; i < width; i++) {
            result <<= 1;
            result |= (value & 1);
            value >>= 1;
        }
        return result;
    }
}

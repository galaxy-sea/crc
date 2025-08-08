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
public interface CRC {

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


    abstract class LongCRC implements CRC {

        public final int width, widthDiff, crcByteLength;

        public final long poly, init, xorout, mask, msbMask;

        public final boolean refin, refout;

        public final String[] names;

        public LongCRC(CRCModel crcParams) {
            this.width = crcParams.width;
            this.crcByteLength = crcParams.crcByteLength;
            this.poly = (long) crcParams.poly;
            this.init = (long) crcParams.init;
            this.xorout = (long) crcParams.xorout;
            this.mask = (long) crcParams.mask;
            this.refin = crcParams.refin;
            this.refout = crcParams.refout;
            this.names = crcParams.names;
            this.msbMask = (long) crcParams.msbMask;
            this.widthDiff = crcParams.widthDiff;
        }

        public long calculate(byte[] data) {
            return calculate(data, 0, data.length);
        }

        public abstract long calculate(byte[] data, int offset, int length);

        @Override
        public byte[] array(byte[] data, int offset, int length, boolean bigEndian) {
            long value = calculate(data, offset, length);
            byte[] result = new byte[crcByteLength];
            for (int i = 0; i < crcByteLength; i++) {
                int index = bigEndian ? (crcByteLength - 1 - i) : i;
                result[index] = (byte) ((value >> (8 * i)) & 0xFF);
            }
            return result;
        }

    }


    abstract class bigCRC implements CRC {

        public final int width, widthDiff ,crcByteLength;

        public final BigInteger poly, init, xorout, mask, msbMask;

        public final boolean refin, refout;

        public final String[] names;

        public bigCRC(CRCModel crcParams) {
            this.width = crcParams.width;
            this.crcByteLength = crcParams.crcByteLength;
            this.poly = new BigInteger(String.valueOf(crcParams.poly));
            this.init = new BigInteger(String.valueOf(crcParams.init));
            this.xorout = new BigInteger(String.valueOf(crcParams.xorout));
            this.mask = new BigInteger(String.valueOf(crcParams.mask));
            this.refin = crcParams.refin;
            this.refout = crcParams.refout;
            this.names = crcParams.names;
            this.msbMask = new BigInteger(String.valueOf(crcParams.msbMask));
            this.widthDiff = crcParams.widthDiff;
        }


        public BigInteger calculate(byte[] data) {
            return calculate(data, 0, data.length);
        }

        public abstract BigInteger calculate(byte[] data, int offset, int length);


        public static BigInteger reverseBits(BigInteger crc, int width) {
            BigInteger reversed = BigInteger.ZERO;
            for (int i = 0; i < width; i++) {
                reversed = reversed.shiftLeft(1).or(crc.and(BigInteger.ONE));
                crc = crc.shiftRight(1);
            }
            return reversed;
        }

        @Override
        public byte[] array(byte[] data, int offset, int length, boolean bigEndian) {
            BigInteger value = calculate(data, offset, length);
            byte[] result = new byte[crcByteLength];
            for (int i = 0; i < crcByteLength; i++) {
                int index = bigEndian ? (crcByteLength - 1 - i) : i;
                result[index] = (byte) (value.shiftRight(8 * i).and(BigInteger.valueOf(0xFF)).intValue());
            }
            return result;
        }

    }

}

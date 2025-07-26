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
            hex = "0" + hex;  // 左补0
        }
        int len = hex.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                    + Character.digit(hex.charAt(i + 1), 16));
        }
        return data;
    }


    public static int reverseBits(int value, int width) {
        int result = 0;
        for (int i = 0; i < width; i++) {
            result <<= 1;
            result |= (value & 1);
            value >>= 1;
        }
        return result;
    }

    public static BigInteger reverseBits(BigInteger input, int width) {
        BigInteger result = BigInteger.ZERO;
        for (int i = 0; i < width; i++) {
            if (input.testBit(i)) {
                result = result.setBit(width - 1 - i);
            }
        }
        return result;
    }


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

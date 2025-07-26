package plus.wcj.crc;

/**
 * @author ChangJin Wei (魏昌进)
 */
public class StandardCRC implements CRCAlgorithm<Long> {

    public final int width, crcByteLength;

    public final long poly, init, xorout, mask;

    public final boolean refin, refout;


    public StandardCRC(int width, long poly, long init, boolean refin, boolean refout, long xorout) {
        this.width = width;
        this.crcByteLength = (width + 7) / 8;

        this.poly = poly;
        this.init = init;
        this.refin = refin;
        this.refout = refout;
        this.xorout = xorout;
        this.mask = (1L << width) - 1;
    }

    @Override
    public Long calculate(byte[] data, int ignoreTailBytes) {
        long crc = init;
        int limit = data.length - ignoreTailBytes;
        for (int i = 0; i < limit - ignoreTailBytes; i++) {
            int value = data[i] & 0xFF;
            if (refin) {
                value = CRCUtils.reverseBits(value, 8);
            }
            for (int j = 0; j < 8; j++) {
                boolean bit = ((value >> (7 - j)) & 1) != 0;
                boolean topBit = ((crc >> (width - 1)) & 1) != 0;
                crc = ((crc << 1) & mask);
                if (topBit ^ bit) {
                    crc ^= poly;
                }
            }
        }
        if (refout) {
            crc = CRCUtils.reverseBits(crc, width);
        }
        crc = (crc ^ xorout) & mask;
        return crc;
    }



    @Override
    public byte[] array(byte[] data, int ignoreTailBytes, boolean bigEndian) {
        long value = calculate(data, ignoreTailBytes);
        byte[] result = new byte[crcByteLength];
        for (int i = 0; i < crcByteLength; i++) {
            int index = bigEndian ? (crcByteLength - 1 - i) : i;
            result[index] = (byte) ((value >> (8 * i)) & 0xFF);
        }
        return result;
    }



}

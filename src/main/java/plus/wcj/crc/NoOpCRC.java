package plus.wcj.crc;

/**
 * @author ChangJin Wei (魏昌进)
 */
public class NoOpCRC implements CRCAlgorithm<Long> {

    @Override
    public Long calculate(byte[] data, int ignoreTailBytes) {
        return 0L;
    }

    @Override
    public byte[] array(byte[] data, int ignoreTailBytes, boolean bigEndian) {
        return new byte[0];
    }

}

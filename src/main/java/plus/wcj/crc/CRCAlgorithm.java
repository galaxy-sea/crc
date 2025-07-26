package plus.wcj.crc;

/**
 * @author ChangJin Wei (魏昌进)
 */
public interface CRCAlgorithm<T> {

    default T calculate(byte[] data) {
        return calculate(data, 0);
    }

    T calculate(byte[] data, int ignoreTailBytes);


    default byte[] array(byte[] data) {
        return array(data, 0, true);
    }

    default byte[] array(byte[] data, boolean bigEndian) {
        return array(data, 0, bigEndian);
    }

    default byte[] array(byte[] data, int ignoreTailBytes) {
        return array(data, ignoreTailBytes, true);
    }

    byte[] array(byte[] data, int ignoreTailBytes, boolean bigEndian);

}

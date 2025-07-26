package plus.wcj.crc;


import org.junit.Test;

/**
 * @author ChangJin Wei (魏昌进)
 *
 */
public class CrcTest {

    @Test
    public void test() {
        CRCAlgorithm crcAlgorithm = CRCModel.CRC_3_GSM.getCrcAlgorithm();
        System.out.println(CRCUtils.bytesToHex(crcAlgorithm.array("1234567890".getBytes())));
        System.out.println(CRCUtils.bytesToHex(crcAlgorithm.array("1234567890".getBytes(), false)));
    }

    @Test
    public void check() {
        for (CRCModel crcModel : CRCModel.values()) {
            byte[] bytes = crcModel.getCrcAlgorithm().array(CRCModel.checkInput.getBytes());
            String crc = CRCUtils.bytesToHex(bytes);
            String check = CRCUtils.bytesToHex(CRCUtils.hexToBytes(crcModel.getCheck()));
            if (!check.equalsIgnoreCase(crc)) {
                throw new RuntimeException();
            }

        }
    }



}

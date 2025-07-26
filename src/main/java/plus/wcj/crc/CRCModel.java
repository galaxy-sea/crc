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
@SuppressWarnings("rawtypes")
public enum CRCModel {

    NO_OP_CRC(new NoOpCRC(), "", "NoOpCRC"),

    // CRC 3
    CRC_3_GSM(new StandardCRC(3, 0x3L, 0x0L, false, false, 0x7L), "4", "CRC-3/GSM"),
    CRC_3_ROHC(new StandardCRC(3, 0x3L, 0x7L, true, true, 0x0L), "6", "CRC-3/ROHC"),

    // CRC 4
    CRC_4_G_704(new StandardCRC(4, 0x3L, 0x0L, true, true, 0x0L), "7", "CRC-4/G-704", "CRC-4/ITU"),
    CRC_4_INTERLAKEN(new StandardCRC(4, 0x3L, 0xfL, false, false, 0xfL), "B", "CRC-4/INTERLAKEN"),

    // CRC 5
    CRC_5_EPC_C1G2(new StandardCRC(5, 0x9L, 0x9L, false, false, 0x0L), "00", "CRC-5/EPC-C1G2", "CRC-5/EPC"),
    CRC_5_G_704(new StandardCRC(5, 0x15L, 0x0L, true, true, 0x0L), "07", "CRC-5/G-704", "CRC-5/ITU"),
    CRC_5_USB(new StandardCRC(5, 0x5L, 0x1fL, true, true, 0x1fL), "19", "CRC-5/USB"),

    // CRC 6
    CRC_6_CDMA2000_A(new StandardCRC(6, 0x27L, 0x3fL, false, false, 0x0L), "0D", "CRC-6/CDMA2000-A"),
    CRC_6_CDMA2000_B(new StandardCRC(6, 0x7L, 0x3fL, false, false, 0x0L), "3B", "CRC-6/CDMA2000-B"),
    CRC_6_DARC(new StandardCRC(6, 0x19L, 0x0L, true, true, 0x0L), "26", "CRC-6/DARC"),
    CRC_6_G_704(new StandardCRC(6, 0x3L, 0x0L, true, true, 0x0L), "06", "CRC-6/G-704", "CRC-6/ITU"),
    CRC_6_GSM(new StandardCRC(6, 0x2fL, 0x0L, false, false, 0x3fL), "13", "CRC-6/GSM"),

    // CRC 7
    CRC_7_MMC(new StandardCRC(7, 0x9L, 0x0L, false, false, 0x0L), "75", "CRC-7/MMC", "CRC-7"),
    CRC_7_ROHC(new StandardCRC(7, 0x4fL, 0x7fL, true, true, 0x0L), "53", "CRC-7/ROHC"),
    CRC_7_UMTS(new StandardCRC(7, 0x45L, 0x0L, false, false, 0x0L), "61", "CRC-7/UMTS"),

    // CRC 8
    CRC_8_AUTOSAR(new StandardCRC(8, 0x2fL, 0xffL, false, false, 0xffL), "DF", "CRC-8/AUTOSAR"),
    CRC_8_BLUETOOTH(new StandardCRC(8, 0xa7L, 0x0L, true, true, 0x0L), "26", "CRC-8/BLUETOOTH"),
    CRC_8_CDMA2000(new StandardCRC(8, 0x9bL, 0xffL, false, false, 0x0L), "DA", "CRC-8/CDMA2000"),
    CRC_8_DARC(new StandardCRC(8, 0x39L, 0x0L, true, true, 0x0L), "15", "CRC-8/DARC"),
    CRC_8_DVB_S2(new StandardCRC(8, 0xd5L, 0x0L, false, false, 0x0L), "BC", "CRC-8/DVB-S2"),
    CRC_8_GSM_A(new StandardCRC(8, 0x1dL, 0x0L, false, false, 0x0L), "37", "CRC-8/GSM-A"),
    CRC_8_GSM_B(new StandardCRC(8, 0x49L, 0x0L, false, false, 0xffL), "94", "CRC-8/GSM-B"),
    CRC_8_HITAG(new StandardCRC(8, 0x1dL, 0xffL, false, false, 0x0L), "B4", "CRC-8/HITAG"),
    CRC_8_I_432_1(new StandardCRC(8, 0x7L, 0x0L, false, false, 0x55L), "A1", "CRC-8/I-432-1", "CRC-8/ITU"),
    CRC_8_I_CODE(new StandardCRC(8, 0x1dL, 0xfdL, false, false, 0x0L), "7E", "CRC-8/I-CODE"),
    CRC_8_LTE(new StandardCRC(8, 0x9bL, 0x0L, false, false, 0x0L), "EA", "CRC-8/LTE"),
    CRC_8_MAXIM_DOW(new StandardCRC(8, 0x31L, 0x0L, true, true, 0x0L), "A1", "CRC-8/MAXIM-DOW", "CRC-8/MAXIM", "DOW-CRC"),
    CRC_8_MIFARE_MAD(new StandardCRC(8, 0x1dL, 0xc7L, false, false, 0x0L), "99", "CRC-8/MIFARE-MAD"),
    CRC_8_NRSC_5(new StandardCRC(8, 0x31L, 0xffL, false, false, 0x0L), "F7", "CRC-8/NRSC-5"),
    CRC_8_OPENSAFETY(new StandardCRC(8, 0x2fL, 0x0L, false, false, 0x0L), "3E", "CRC-8/OPENSAFETY"),
    CRC_8_ROHC(new StandardCRC(8, 0x7L, 0xffL, true, true, 0x0L), "D0", "CRC-8/ROHC"),
    CRC_8_SAE_J1850(new StandardCRC(8, 0x1dL, 0xffL, false, false, 0xffL), "4B", "CRC-8/SAE-J1850"),
    CRC_8_SMBUS(new StandardCRC(8, 0x7L, 0x0L, false, false, 0x0L), "F4", "CRC-8/SMBUS", "CRC-8"),
    CRC_8_TECH_3250(new StandardCRC(8, 0x1dL, 0xffL, true, true, 0x0L), "97", "CRC-8/TECH-3250", "CRC-8/AES", "CRC-8/EBU"),
    CRC_8_WCDMA(new StandardCRC(8, 0x9bL, 0x0L, true, true, 0x0L), "25", "CRC-8/WCDMA"),

    // CRC 10
    CRC_10_ATM(new StandardCRC(10, 0x233L, 0x0L, false, false, 0x0L), "199", "CRC-10/ATM", "CRC-10", "CRC-10/I-610"),
    CRC_10_CDMA2000(new StandardCRC(10, 0x3d9L, 0x3ffL, false, false, 0x0L), "233", "CRC-10/CDMA2000"),
    CRC_10_GSM(new StandardCRC(10, 0x175L, 0x0L, false, false, 0x3ffL), "12A", "CRC-10/GSM"),

    // CRC 11
    CRC_11_FLEXRAY(new StandardCRC(11, 0x385L, 0x1aL, false, false, 0x0L), "5A3", "CRC-11/FLEXRAY", "CRC-11"),
    CRC_11_UMTS(new StandardCRC(11, 0x307L, 0x0L, false, false, 0x0L), "061", "CRC-11/UMTS"),

    // CRC 12
    CRC_12_CDMA2000(new StandardCRC(12, 0xf13L, 0xfffL, false, false, 0x0L), "D4D", "CRC-12/CDMA2000"),
    CRC_12_DECT(new StandardCRC(12, 0x80fL, 0x0L, false, false, 0x0L), "F5B", "CRC-12/DECT", "X-CRC-12"),
    CRC_12_GSM(new StandardCRC(12, 0xd31L, 0x0L, false, false, 0xfffL), "B34", "CRC-12/GSM"),
    CRC_12_UMTS(new StandardCRC(12, 0x80fL, 0x0L, false, true, 0x0L), "DAF", "CRC-12/UMTS", "CRC-12/3GPP"),

    // CRC 13
    CRC_13_BBC(new StandardCRC(13, 0x1cf5L, 0x0L, false, false, 0x0L), "04FA", "CRC-13/BBC"),

    // CRC 14
    CRC_14_DARC(new StandardCRC(14, 0x805L, 0x0L, true, true, 0x0L), "082D", "CRC-14/DARC"),
    CRC_14_GSM(new StandardCRC(14, 0x202dL, 0x0L, false, false, 0x3fffL), "30AE", "CRC-14/GSM"),

    // CRC 15
    CRC_15_CAN(new StandardCRC(15, 0x4599L, 0x0L, false, false, 0x0L), "059E", "CRC-15/CAN", "CRC-15"),
    CRC_15_MPT1327(new StandardCRC(15, 0x6815L, 0x0L, false, false, 0x1L), "2566", "CRC-15/MPT1327"),

    // CRC 16
    CRC_16_ARC(new StandardCRC(16, 0x8005L, 0x0L, true, true, 0x0L), "BB3D", "CRC-16/ARC", "ARC", "CRC-16", "CRC-16/LHA", "CRC-IBM"),
    CRC_16_CDMA2000(new StandardCRC(16, 0xc867L, 0xffffL, false, false, 0x0L), "4C06", "CRC-16/CDMA2000"),
    CRC_16_CMS(new StandardCRC(16, 0x8005L, 0xffffL, false, false, 0x0L), "AEE7", "CRC-16/CMS"),
    CRC_16_DDS_110(new StandardCRC(16, 0x8005L, 0x800dL, false, false, 0x0L), "9ECF", "CRC-16/DDS-110"),
    CRC_16_DECT_R(new StandardCRC(16, 0x589L, 0x0L, false, false, 0x1L), "007E", "CRC-16/DECT-R", "R-CRC-16"),
    CRC_16_DECT_X(new StandardCRC(16, 0x589L, 0x0L, false, false, 0x0L), "007F", "CRC-16/DECT-X", "X-CRC-16"),
    CRC_16_DNP(new StandardCRC(16, 0x3d65L, 0x0L, true, true, 0xffffL), "EA82", "CRC-16/DNP"),
    CRC_16_EN_13757(new StandardCRC(16, 0x3d65L, 0x0L, false, false, 0xffffL), "C2B7", "CRC-16/EN-13757"),
    CRC_16_GENIBUS(new StandardCRC(16, 0x1021L, 0xffffL, false, false, 0xffffL), "D64E", "CRC-16/GENIBUS", "CRC-16/DARC", "CRC-16/EPC", "CRC-16/EPC-C1G2", "CRC-16/I-CODE"),
    CRC_16_GSM(new StandardCRC(16, 0x1021L, 0x0L, false, false, 0xffffL), "CE3C", "CRC-16/GSM"),
    CRC_16_IBM_3740(new StandardCRC(16, 0x1021L, 0xffffL, false, false, 0x0L), "29B1", "CRC-16/IBM-3740", "CRC-16/AUTOSAR", "CRC-16/CCITT-FALSE"),
    CRC_16_IBM_SDLC(new StandardCRC(16, 0x1021L, 0xffffL, true, true, 0xffffL), "906E", "CRC-16/IBM-SDLC", "CRC-16/ISO-HDLC", "CRC-16/ISO-IEC-14443-3-B", "CRC-16/X-25", "CRC-B", "X-25"),
    CRC_16_ISO_IEC_14443_3_A(new StandardCRC(16, 0x1021L, 0xc6c6L, true, true, 0x0L), "BF05", "CRC-16/ISO-IEC-14443-3-A", "CRC-A"),
    CRC_16_KERMIT(new StandardCRC(16, 0x1021L, 0x0L, true, true, 0x0L), "2189", "CRC-16/KERMIT", "CRC-16/BLUETOOTH", "CRC-16/CCITT", "CRC-16/CCITT-TRUE", "CRC-16/V-41-LSB", "CRC-CCITT", "KERMIT"),
    CRC_16_LJ1200(new StandardCRC(16, 0x6f63L, 0x0L, false, false, 0x0L), "BDF4", "CRC-16/LJ1200"),
    CRC_16_M17(new StandardCRC(16, 0x5935L, 0xffffL, false, false, 0x0L), "772B", "CRC-16/M17"),
    CRC_16_MAXIM_DOW(new StandardCRC(16, 0x8005L, 0x0L, true, true, 0xffffL), "44C2", "CRC-16/MAXIM-DOW", "CRC-16/MAXIM"),
    CRC_16_MCRF4XX(new StandardCRC(16, 0x1021L, 0xffffL, true, true, 0x0L), "6F91", "CRC-16/MCRF4XX"),
    CRC_16_MODBUS(new StandardCRC(16, 0x8005L, 0xffffL, true, true, 0x0L), "4B37", "CRC-16/MODBUS", "MODBUS"),
    CRC_16_NRSC_5(new StandardCRC(16, 0x80bL, 0xffffL, true, true, 0x0L), "A066", "CRC-16/NRSC-5"),
    CRC_16_OPENSAFETY_A(new StandardCRC(16, 0x5935L, 0x0L, false, false, 0x0L), "5D38", "CRC-16/OPENSAFETY-A"),
    CRC_16_OPENSAFETY_B(new StandardCRC(16, 0x755bL, 0x0L, false, false, 0x0L), "20FE", "CRC-16/OPENSAFETY-B"),
    CRC_16_PROFIBUS(new StandardCRC(16, 0x1dcfL, 0xffffL, false, false, 0xffffL), "A819", "CRC-16/PROFIBUS", "CRC-16/IEC-61158-2"),
    CRC_16_RIELLO(new StandardCRC(16, 0x1021L, 0xb2aaL, true, true, 0x0L), "63D0", "CRC-16/RIELLO"),
    CRC_16_SPI_FUJITSU(new StandardCRC(16, 0x1021L, 0x1d0fL, false, false, 0x0L), "E5CC", "CRC-16/SPI-FUJITSU", "CRC-16/AUG-CCITT"),
    CRC_16_T10_DIF(new StandardCRC(16, 0x8bb7L, 0x0L, false, false, 0x0L), "D0DB", "CRC-16/T10-DIF"),
    CRC_16_TELEDISK(new StandardCRC(16, 0xa097L, 0x0L, false, false, 0x0L), "0FB3", "CRC-16/TELEDISK"),
    CRC_16_TMS37157(new StandardCRC(16, 0x1021L, 0x89ecL, true, true, 0x0L), "26B1", "CRC-16/TMS37157"),
    CRC_16_UMTS(new StandardCRC(16, 0x8005L, 0x0L, false, false, 0x0L), "FEE8", "CRC-16/UMTS", "CRC-16/BUYPASS", "CRC-16/VERIFONE"),
    CRC_16_USB(new StandardCRC(16, 0x8005L, 0xffffL, true, true, 0xffffL), "B4C8", "CRC-16/USB"),
    CRC_16_XMODEM(new StandardCRC(16, 0x1021L, 0x0L, false, false, 0x0L), "31C3", "CRC-16/XMODEM", "CRC-16/ACORN", "CRC-16/LTE", "CRC-16/V-41-MSB", "XMODEM", "ZMODEM"),

    // CRC 17
    CRC_17_CAN_FD(new StandardCRC(17, 0x1685bL, 0x0L, false, false, 0x0L), "04F03", "CRC-17/CAN-FD"),

    // CRC 21
    CRC_21_CAN_FD(new StandardCRC(21, 0x102899L, 0x0L, false, false, 0x0L), "0ED841", "CRC-21/CAN-FD"),

    // CRC 24
    CRC_24_BLE(new StandardCRC(24, 0x65bL, 0x555555L, true, true, 0x0L), "C25A56", "CRC-24/BLE"),
    CRC_24_FLEXRAY_A(new StandardCRC(24, 0x5d6dcbL, 0xfedcbaL, false, false, 0x0L), "7979BD", "CRC-24/FLEXRAY-A"),
    CRC_24_FLEXRAY_B(new StandardCRC(24, 0x5d6dcbL, 0xabcdefL, false, false, 0x0L), "1F23B8", "CRC-24/FLEXRAY-B"),
    CRC_24_INTERLAKEN(new StandardCRC(24, 0x328b63L, 0xffffffL, false, false, 0xffffffL), "B4F3E6", "CRC-24/INTERLAKEN"),
    CRC_24_LTE_A(new StandardCRC(24, 0x864cfbL, 0x0L, false, false, 0x0L), "CDE703", "CRC-24/LTE-A"),
    CRC_24_LTE_B(new StandardCRC(24, 0x800063L, 0x0L, false, false, 0x0L), "23EF52", "CRC-24/LTE-B"),
    CRC_24_OPENPGP(new StandardCRC(24, 0x864cfbL, 0xb704ceL, false, false, 0x0L), "21CF02", "CRC-24/OPENPGP", "CRC-24"),
    CRC_24_OS_9(new StandardCRC(24, 0x800063L, 0xffffffL, false, false, 0xffffffL), "200FA5", "CRC-24/OS-9"),

    // CRC 30
    CRC_30_CDMA(new StandardCRC(30, 0x2030b9c7L, 0x3fffffffL, false, false, 0x3fffffffL), "04C34ABF", "CRC-30/CDMA"),

    // CRC 31
    CRC_31_PHILIPS(new StandardCRC(31, 0x4c11db7L, 0x7fffffffL, false, false, 0x7fffffffL), "0CE9E46C", "CRC-31/PHILIPS"),

    // CRC 32
    CRC_32_AIXM(new StandardCRC(32, 0x814141abL, 0x0L, false, false, 0x0L), "3010BF7F", "CRC-32/AIXM", "CRC-32Q"),
    CRC_32_AUTOSAR(new StandardCRC(32, 0xf4acfb13L, 0xffffffffL, true, true, 0xffffffffL), "1697D06A", "CRC-32/AUTOSAR"),
    CRC_32_BASE91_D(new StandardCRC(32, 0xa833982bL, 0xffffffffL, true, true, 0xffffffffL), "87315576", "CRC-32/BASE91-D", "CRC-32D"),
    CRC_32_BZIP2(new StandardCRC(32, 0x4c11db7L, 0xffffffffL, false, false, 0xffffffffL), "FC891918", "CRC-32/BZIP2", "CRC-32/AAL5", "CRC-32/DECT-B", "B-CRC-32"),
    CRC_32_CD_ROM_EDC(new StandardCRC(32, 0x8001801bL, 0x0L, true, true, 0x0L), "6EC2EDC4", "CRC-32/CD-ROM-EDC"),
    CRC_32_CKSUM(new StandardCRC(32, 0x4c11db7L, 0x0L, false, false, 0xffffffffL), "765E7680", "CRC-32/CKSUM", "CKSUM", "CRC-32/POSIX"),
    CRC_32_ISCSI(new StandardCRC(32, 0x1edc6f41L, 0xffffffffL, true, true, 0xffffffffL), "E3069283", "CRC-32/ISCSI", "CRC-32/BASE91-C", "CRC-32/CASTAGNOLI", "CRC-32/INTERLAKEN", "CRC-32C", "CRC-32/NVME"),
    CRC_32_ISO_HDLC(new StandardCRC(32, 0x4c11db7L, 0xffffffffL, true, true, 0xffffffffL), "CBF43926", "CRC-32/ISO-HDLC", "CRC-32", "CRC-32/ADCCP", "CRC-32/V-42", "CRC-32/XZ", "PKZIP"),
    CRC_32_JAMCRC(new StandardCRC(32, 0x4c11db7L, 0xffffffffL, true, true, 0x0L), "340BC6D9", "CRC-32/JAMCRC", "JAMCRC"),
    CRC_32_MEF(new StandardCRC(32, 0x741b8cd7L, 0xffffffffL, true, true, 0x0L), "D2C22F51", "CRC-32/MEF"),
    CRC_32_MPEG_2(new StandardCRC(32, 0x4c11db7L, 0xffffffffL, false, false, 0x0L), "0376E6E7", "CRC-32/MPEG-2"),
    CRC_32_XFER(new StandardCRC(32, 0xafL, 0x0L, false, false, 0x0L), "BD0BE338", "CRC-32/XFER", "XFER"),

    // CRC 40
    CRC_40_GSM(new BigCRC(40, "4820009", "0", false, false, "ffffffffff"), "D4164FC646", "CRC-40/GSM"),

    // CRC 64
    CRC_64_ECMA_182(new BigCRC(64, "42f0e1eba9ea3693", "0", false, false, "0"), "6C40DF5F0B497347", "CRC-64/ECMA-182", "CRC-64"),
    CRC_64_GO_ISO(new BigCRC(64, "1b", "ffffffffffffffff", true, true, "ffffffffffffffff"), "B90956C775A41001", "CRC-64/GO-ISO"),
    CRC_64_MS(new BigCRC(64, "259c84cba6426349", "ffffffffffffffff", true, true, "0"), "75D4B74F024ECEEA", "CRC-64/MS"),
    CRC_64_NVME(new BigCRC(64, "ad93d23594c93659", "ffffffffffffffff", true, true, "ffffffffffffffff"), "AE8B14860A799888", "CRC-64/NVME"),
    CRC_64_REDIS(new BigCRC(64, "ad93d23594c935a9", "0", true, true, "0"), "E9C6D914C4B8D9CA", "CRC-64/REDIS"),
    CRC_64_WE(new BigCRC(64, "42f0e1eba9ea3693", "ffffffffffffffff", false, false, "ffffffffffffffff"), "62EC59E3F1A4F00A", "CRC-64/WE"),
    CRC_64_XZ(new BigCRC(64, "42f0e1eba9ea3693", "ffffffffffffffff", true, true, "ffffffffffffffff"), "995DC9BBDF1939FA", "CRC-64/XZ", "CRC-64/GO-ECMA"),

    // CRC 82
    CRC_82_DARC(new BigCRC(82, "308c0111011401440411", "0", true, true, "0"), "09EA83F625023801FD612", "CRC-82/DARC"),

    ;

    private final CRC crc;
    private final String check;
    private final String[] names;

    public static final String checkInput = "123456789";

    CRCModel(CRC crc, String check, String... names) {
        this.crc = crc;
        this.check = check;
        this.names = names;
    }

    public CRC getCrc() {
        return crc;
    }

    public String getCheck() {
        return check;
    }

    public String[] getNames() {
        return names;
    }
}

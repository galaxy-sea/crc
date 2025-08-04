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
public class CRCModel<T extends Number> {

    // NO_OP_CRC(new NoOpCRC(, "", "NoOpCRC"),

    // CRC 3
    public static final CRCModel<Long> CRC_3_GSM = create(3, 0x3L, 0x0L, false, false, 0x7L, "4", "CRC-3/GSM");
    public static final CRCModel<Long> CRC_3_ROHC = create(3, 0x3L, 0x7L, true, true, 0x0L, "6", "CRC-3/ROHC");

    // CRC 4
    public static final CRCModel<Long> CRC_4_G_704 = create(4, 0x3L, 0x0L, true, true, 0x0L, "7", "CRC-4/G-704", "CRC-4/ITU");
    public static final CRCModel<Long> CRC_4_INTERLAKEN = create(4, 0x3L, 0xfL, false, false, 0xfL, "B", "CRC-4/INTERLAKEN");

    // CRC 5
    public static final CRCModel<Long> CRC_5_EPC_C1G2 = create(5, 0x9L, 0x9L, false, false, 0x0L, "00", "CRC-5/EPC-C1G2", "CRC-5/EPC");
    public static final CRCModel<Long> CRC_5_G_704 = create(5, 0x15L, 0x0L, true, true, 0x0L, "07", "CRC-5/G-704", "CRC-5/ITU");
    public static final CRCModel<Long> CRC_5_USB = create(5, 0x5L, 0x1fL, true, true, 0x1fL, "19", "CRC-5/USB");

    // CRC 6
    public static final CRCModel<Long> CRC_6_CDMA2000_A = create(6, 0x27L, 0x3fL, false, false, 0x0L, "0D", "CRC-6/CDMA2000-A");
    public static final CRCModel<Long> CRC_6_CDMA2000_B = create(6, 0x7L, 0x3fL, false, false, 0x0L, "3B", "CRC-6/CDMA2000-B");
    public static final CRCModel<Long> CRC_6_DARC = create(6, 0x19L, 0x0L, true, true, 0x0L, "26", "CRC-6/DARC");
    public static final CRCModel<Long> CRC_6_G_704 = create(6, 0x3L, 0x0L, true, true, 0x0L, "06", "CRC-6/G-704", "CRC-6/ITU");
    public static final CRCModel<Long> CRC_6_GSM = create(6, 0x2fL, 0x0L, false, false, 0x3fL, "13", "CRC-6/GSM");

    // CRC 7
    public static final CRCModel<Long> CRC_7_MMC = create(7, 0x9L, 0x0L, false, false, 0x0L, "75", "CRC-7/MMC", "CRC-7");
    public static final CRCModel<Long> CRC_7_ROHC = create(7, 0x4fL, 0x7fL, true, true, 0x0L, "53", "CRC-7/ROHC");
    public static final CRCModel<Long> CRC_7_UMTS = create(7, 0x45L, 0x0L, false, false, 0x0L, "61", "CRC-7/UMTS");

    // CRC 8
    public static final CRCModel<Long> CRC_8_AUTOSAR = create(8, 0x2fL, 0xffL, false, false, 0xffL, "DF", "CRC-8/AUTOSAR");
    public static final CRCModel<Long> CRC_8_BLUETOOTH = create(8, 0xa7L, 0x0L, true, true, 0x0L, "26", "CRC-8/BLUETOOTH");
    public static final CRCModel<Long> CRC_8_CDMA2000 = create(8, 0x9bL, 0xffL, false, false, 0x0L, "DA", "CRC-8/CDMA2000");
    public static final CRCModel<Long> CRC_8_DARC = create(8, 0x39L, 0x0L, true, true, 0x0L, "15", "CRC-8/DARC");
    public static final CRCModel<Long> CRC_8_DVB_S2 = create(8, 0xd5L, 0x0L, false, false, 0x0L, "BC", "CRC-8/DVB-S2");
    public static final CRCModel<Long> CRC_8_GSM_A = create(8, 0x1dL, 0x0L, false, false, 0x0L, "37", "CRC-8/GSM-A");
    public static final CRCModel<Long> CRC_8_GSM_B = create(8, 0x49L, 0x0L, false, false, 0xffL, "94", "CRC-8/GSM-B");
    public static final CRCModel<Long> CRC_8_HITAG = create(8, 0x1dL, 0xffL, false, false, 0x0L, "B4", "CRC-8/HITAG");
    public static final CRCModel<Long> CRC_8_I_432_1 = create(8, 0x7L, 0x0L, false, false, 0x55L, "A1", "CRC-8/I-432-1", "CRC-8/ITU");
    public static final CRCModel<Long> CRC_8_I_CODE = create(8, 0x1dL, 0xfdL, false, false, 0x0L, "7E", "CRC-8/I-CODE");
    public static final CRCModel<Long> CRC_8_LTE = create(8, 0x9bL, 0x0L, false, false, 0x0L, "EA", "CRC-8/LTE");
    public static final CRCModel<Long> CRC_8_MAXIM_DOW = create(8, 0x31L, 0x0L, true, true, 0x0L, "A1", "CRC-8/MAXIM-DOW", "CRC-8/MAXIM", "DOW-CRC");
    public static final CRCModel<Long> CRC_8_NRSC_5 = create(8, 0x31L, 0xffL, false, false, 0x0L, "F7", "CRC-8/NRSC-5");
    public static final CRCModel<Long> CRC_8_OPENSAFETY = create(8, 0x2fL, 0x0L, false, false, 0x0L, "3E", "CRC-8/OPENSAFETY");
    public static final CRCModel<Long> CRC_8_ROHC = create(8, 0x7L, 0xffL, true, true, 0x0L, "D0", "CRC-8/ROHC");
    public static final CRCModel<Long> CRC_8_SAE_J1850 = create(8, 0x1dL, 0xffL, false, false, 0xffL, "4B", "CRC-8/SAE-J1850");
    public static final CRCModel<Long> CRC_8_SMBUS = create(8, 0x7L, 0x0L, false, false, 0x0L, "F4", "CRC-8/SMBUS", "CRC-8");
    public static final CRCModel<Long> CRC_8_TECH_3250 = create(8, 0x1dL, 0xffL, true, true, 0x0L, "97", "CRC-8/TECH-3250", "CRC-8/AES", "CRC-8/EBU");
    public static final CRCModel<Long> CRC_8_WCDMA = create(8, 0x9bL, 0x0L, true, true, 0x0L, "25", "CRC-8/WCDMA");

    // CRC 10
    public static final CRCModel<Long> CRC_10_ATM = create(10, 0x233L, 0x0L, false, false, 0x0L, "199", "CRC-10/ATM", "CRC-10", "CRC-10/I-610");
    public static final CRCModel<Long> CRC_10_CDMA2000 = create(10, 0x3d9L, 0x3ffL, false, false, 0x0L, "233", "CRC-10/CDMA2000");
    public static final CRCModel<Long> CRC_10_GSM = create(10, 0x175L, 0x0L, false, false, 0x3ffL, "12A", "CRC-10/GSM");

    // CRC 11
    public static final CRCModel<Long> CRC_11_FLEXRAY = create(11, 0x385L, 0x1aL, false, false, 0x0L, "5A3", "CRC-11/FLEXRAY", "CRC-11");
    public static final CRCModel<Long> CRC_11_UMTS = create(11, 0x307L, 0x0L, false, false, 0x0L, "061", "CRC-11/UMTS");

    // CRC 12
    public static final CRCModel<Long> CRC_12_CDMA2000 = create(12, 0xf13L, 0xfffL, false, false, 0x0L, "D4D", "CRC-12/CDMA2000");
    public static final CRCModel<Long> CRC_12_DECT = create(12, 0x80fL, 0x0L, false, false, 0x0L, "F5B", "CRC-12/DECT", "X-CRC-12");
    public static final CRCModel<Long> CRC_12_GSM = create(12, 0xd31L, 0x0L, false, false, 0xfffL, "B34", "CRC-12/GSM");
    public static final CRCModel<Long> CRC_12_UMTS = create(12, 0x80fL, 0x0L, false, true, 0x0L, "DAF", "CRC-12/UMTS", "CRC-12/3GPP");

    // CRC 13
    public static final CRCModel<Long> CRC_13_BBC = create(13, 0x1cf5L, 0x0L, false, false, 0x0L, "04FA", "CRC-13/BBC");

    // CRC 14
    public static final CRCModel<Long> CRC_14_DARC = create(14, 0x805L, 0x0L, true, true, 0x0L, "082D", "CRC-14/DARC");
    public static final CRCModel<Long> CRC_14_GSM = create(14, 0x202dL, 0x0L, false, false, 0x3fffL, "30AE", "CRC-14/GSM");

    // CRC 15
    public static final CRCModel<Long> CRC_15_CAN = create(15, 0x4599L, 0x0L, false, false, 0x0L, "059E", "CRC-15/CAN", "CRC-15");
    public static final CRCModel<Long> CRC_15_MPT1327 = create(15, 0x6815L, 0x0L, false, false, 0x1L, "2566", "CRC-15/MPT1327");

    // CRC 16
    public static final CRCModel<Long> CRC_16_ARC = create(16, 0x8005L, 0x0L, true, true, 0x0L, "BB3D", "CRC-16/ARC", "ARC", "CRC-16", "CRC-16/LHA", "CRC-IBM");
    public static final CRCModel<Long> CRC_16_CDMA2000 = create(16, 0xc867L, 0xffffL, false, false, 0x0L, "4C06", "CRC-16/CDMA2000");
    public static final CRCModel<Long> CRC_16_CMS = create(16, 0x8005L, 0xffffL, false, false, 0x0L, "AEE7", "CRC-16/CMS");
    public static final CRCModel<Long> CRC_16_DDS_110 = create(16, 0x8005L, 0x800dL, false, false, 0x0L, "9ECF", "CRC-16/DDS-110");
    public static final CRCModel<Long> CRC_16_DECT_X = create(16, 0x589L, 0x0L, false, false, 0x0L, "007F", "CRC-16/DECT-X", "X-CRC-16");
    public static final CRCModel<Long> CRC_16_DNP = create(16, 0x3d65L, 0x0L, true, true, 0xffffL, "EA82", "CRC-16/DNP");
    public static final CRCModel<Long> CRC_16_EN_13757 = create(16, 0x3d65L, 0x0L, false, false, 0xffffL, "C2B7", "CRC-16/EN-13757");
    public static final CRCModel<Long> CRC_16_GENIBUS = create(16, 0x1021L, 0xffffL, false, false, 0xffffL, "D64E", "CRC-16/GENIBUS", "CRC-16/DARC", "CRC-16/EPC", "CRC-16/EPC-C1G2", "CRC-16/I-CODE");
    public static final CRCModel<Long> CRC_16_GSM = create(16, 0x1021L, 0x0L, false, false, 0xffffL, "CE3C", "CRC-16/GSM");
    public static final CRCModel<Long> CRC_16_IBM_3740 = create(16, 0x1021L, 0xffffL, false, false, 0x0L, "29B1", "CRC-16/IBM-3740", "CRC-16/AUTOSAR", "CRC-16/CCITT-FALSE");
    public static final CRCModel<Long> CRC_16_IBM_SDLC = create(16, 0x1021L, 0xffffL, true, true, 0xffffL, "906E", "CRC-16/IBM-SDLC", "CRC-16/ISO-HDLC", "CRC-16/ISO-IEC-14443-3-B", "CRC-16/X-25", "CRC-B", "X-25");
    public static final CRCModel<Long> CRC_16_ISO_IEC_14443_3_A = create(16, 0x1021L, 0xc6c6L, true, true, 0x0L, "BF05", "CRC-16/ISO-IEC-14443-3-A", "CRC-A");
    public static final CRCModel<Long> CRC_16_KERMIT = create(16, 0x1021L, 0x0L, true, true, 0x0L, "2189", "CRC-16/KERMIT", "CRC-16/BLUETOOTH", "CRC-16/CCITT", "CRC-16/CCITT-TRUE", "CRC-16/V-41-LSB", "CRC-CCITT", "KERMIT");
    public static final CRCModel<Long> CRC_16_LJ1200 = create(16, 0x6f63L, 0x0L, false, false, 0x0L, "BDF4", "CRC-16/LJ1200");
    public static final CRCModel<Long> CRC_16_M17 = create(16, 0x5935L, 0xffffL, false, false, 0x0L, "772B", "CRC-16/M17");
    public static final CRCModel<Long> CRC_16_MAXIM_DOW = create(16, 0x8005L, 0x0L, true, true, 0xffffL, "44C2", "CRC-16/MAXIM-DOW", "CRC-16/MAXIM");
    public static final CRCModel<Long> CRC_16_MCRF4XX = create(16, 0x1021L, 0xffffL, true, true, 0x0L, "6F91", "CRC-16/MCRF4XX");
    public static final CRCModel<Long> CRC_16_MODBUS = create(16, 0x8005L, 0xffffL, true, true, 0x0L, "4B37", "CRC-16/MODBUS", "MODBUS");
    public static final CRCModel<Long> CRC_16_NRSC_5 = create(16, 0x80bL, 0xffffL, true, true, 0x0L, "A066", "CRC-16/NRSC-5");
    public static final CRCModel<Long> CRC_16_OPENSAFETY_A = create(16, 0x5935L, 0x0L, false, false, 0x0L, "5D38", "CRC-16/OPENSAFETY-A");
    public static final CRCModel<Long> CRC_16_OPENSAFETY_B = create(16, 0x755bL, 0x0L, false, false, 0x0L, "20FE", "CRC-16/OPENSAFETY-B");
    public static final CRCModel<Long> CRC_16_PROFIBUS = create(16, 0x1dcfL, 0xffffL, false, false, 0xffffL, "A819", "CRC-16/PROFIBUS", "CRC-16/IEC-61158-2");
    public static final CRCModel<Long> CRC_16_RIELLO = create(16, 0x1021L, 0xb2aaL, true, true, 0x0L, "63D0", "CRC-16/RIELLO");
    public static final CRCModel<Long> CRC_16_SPI_FUJITSU = create(16, 0x1021L, 0x1d0fL, false, false, 0x0L, "E5CC", "CRC-16/SPI-FUJITSU", "CRC-16/AUG-CCITT");
    public static final CRCModel<Long> CRC_16_T10_DIF = create(16, 0x8bb7L, 0x0L, false, false, 0x0L, "D0DB", "CRC-16/T10-DIF");
    public static final CRCModel<Long> CRC_16_TELEDISK = create(16, 0xa097L, 0x0L, false, false, 0x0L, "0FB3", "CRC-16/TELEDISK");
    public static final CRCModel<Long> CRC_16_TMS37157 = create(16, 0x1021L, 0x89ecL, true, true, 0x0L, "26B1", "CRC-16/TMS37157");
    public static final CRCModel<Long> CRC_16_UMTS = create(16, 0x8005L, 0x0L, false, false, 0x0L, "FEE8", "CRC-16/UMTS", "CRC-16/BUYPASS", "CRC-16/VERIFONE");
    public static final CRCModel<Long> CRC_16_USB = create(16, 0x8005L, 0xffffL, true, true, 0xffffL, "B4C8", "CRC-16/USB");
    public static final CRCModel<Long> CRC_16_XMODEM = create(16, 0x1021L, 0x0L, false, false, 0x0L, "31C3", "CRC-16/XMODEM", "CRC-16/ACORN", "CRC-16/LTE", "CRC-16/V-41-MSB", "XMODEM", "ZMODEM");

    // CRC 17
    public static final CRCModel<Long> CRC_17_CAN_FD = create(17, 0x1685bL, 0x0L, false, false, 0x0L, "04F03", "CRC-17/CAN-FD");

    // CRC 21
    public static final CRCModel<Long> CRC_21_CAN_FD = create(21, 0x102899L, 0x0L, false, false, 0x0L, "0ED841", "CRC-21/CAN-FD");

    // CRC 24
    public static final CRCModel<Long> CRC_24_BLE = create(24, 0x65bL, 0x555555L, true, true, 0x0L, "C25A56", "CRC-24/BLE");
    public static final CRCModel<Long> CRC_24_FLEXRAY_A = create(24, 0x5d6dcbL, 0xfedcbaL, false, false, 0x0L, "7979BD", "CRC-24/FLEXRAY-A");
    public static final CRCModel<Long> CRC_24_FLEXRAY_B = create(24, 0x5d6dcbL, 0xabcdefL, false, false, 0x0L, "1F23B8", "CRC-24/FLEXRAY-B");
    public static final CRCModel<Long> CRC_24_INTERLAKEN = create(24, 0x328b63L, 0xffffffL, false, false, 0xffffffL, "B4F3E6", "CRC-24/INTERLAKEN");
    public static final CRCModel<Long> CRC_24_LTE_A = create(24, 0x864cfbL, 0x0L, false, false, 0x0L, "CDE703", "CRC-24/LTE-A");
    public static final CRCModel<Long> CRC_24_LTE_B = create(24, 0x800063L, 0x0L, false, false, 0x0L, "23EF52", "CRC-24/LTE-B");
    public static final CRCModel<Long> CRC_24_OPENPGP = create(24, 0x864cfbL, 0xb704ceL, false, false, 0x0L, "21CF02", "CRC-24/OPENPGP", "CRC-24");
    public static final CRCModel<Long> CRC_24_OS_9 = create(24, 0x800063L, 0xffffffL, false, false, 0xffffffL, "200FA5", "CRC-24/OS-9");

    // CRC 30
    public static final CRCModel<Long> CRC_30_CDMA = create(30, 0x2030b9c7L, 0x3fffffffL, false, false, 0x3fffffffL, "04C34ABF", "CRC-30/CDMA");

    // CRC 31
    public static final CRCModel<Long> CRC_31_PHILIPS = create(31, 0x4c11db7L, 0x7fffffffL, false, false, 0x7fffffffL, "0CE9E46C", "CRC-31/PHILIPS");

    // CRC 32
    public static final CRCModel<Long> CRC_32_AIXM = create(32, 0x814141abL, 0x0L, false, false, 0x0L, "3010BF7F", "CRC-32/AIXM", "CRC-32Q");
    public static final CRCModel<Long> CRC_32_AUTOSAR = create(32, 0xf4acfb13L, 0xffffffffL, true, true, 0xffffffffL, "1697D06A", "CRC-32/AUTOSAR");
    public static final CRCModel<Long> CRC_32_BASE91_D = create(32, 0xa833982bL, 0xffffffffL, true, true, 0xffffffffL, "87315576", "CRC-32/BASE91-D", "CRC-32D");
    public static final CRCModel<Long> CRC_32_BZIP2 = create(32, 0x4c11db7L, 0xffffffffL, false, false, 0xffffffffL, "FC891918", "CRC-32/BZIP2", "CRC-32/AAL5", "CRC-32/DECT-B", "B-CRC-32");
    public static final CRCModel<Long> CRC_32_CD_ROM_EDC = create(32, 0x8001801bL, 0x0L, true, true, 0x0L, "6EC2EDC4", "CRC-32/CD-ROM-EDC");
    public static final CRCModel<Long> CRC_32_CKSUM = create(32, 0x4c11db7L, 0x0L, false, false, 0xffffffffL, "765E7680", "CRC-32/CKSUM", "CKSUM", "CRC-32/POSIX");
    public static final CRCModel<Long> CRC_32_ISCSI = create(32, 0x1edc6f41L, 0xffffffffL, true, true, 0xffffffffL, "E3069283", "CRC-32/ISCSI", "CRC-32/BASE91-C", "CRC-32/CASTAGNOLI", "CRC-32/INTERLAKEN", "CRC-32C", "CRC-32/NVME");
    public static final CRCModel<Long> CRC_32_ISO_HDLC = create(32, 0x4c11db7L, 0xffffffffL, true, true, 0xffffffffL, "CBF43926", "CRC-32/ISO-HDLC", "CRC-32", "CRC-32/ADCCP", "CRC-32/V-42", "CRC-32/XZ", "PKZIP");
    public static final CRCModel<Long> CRC_32_JAMCRC = create(32, 0x4c11db7L, 0xffffffffL, true, true, 0x0L, "340BC6D9", "CRC-32/JAMCRC", "JAMCRC");
    public static final CRCModel<Long> CRC_32_MEF = create(32, 0x741b8cd7L, 0xffffffffL, true, true, 0x0L, "D2C22F51", "CRC-32/MEF");
    public static final CRCModel<Long> CRC_32_MPEG_2 = create(32, 0x4c11db7L, 0xffffffffL, false, false, 0x0L, "0376E6E7", "CRC-32/MPEG-2");
    public static final CRCModel<Long> CRC_32_XFER = create(32, 0xafL, 0x0L, false, false, 0x0L, "BD0BE338", "CRC-32/XFER", "XFER");

    // CRC 40
    public static final CRCModel<Long> CRC_40_GSM = create(40, 0x4820009L, 0x0L, false, false, 0xffffffffffL, "D4164FC646", "CRC-40/GSM");

    // CRC  64
    public static final CRCModel<Long> CRC_64_ECMA_182 = create(64, 0x42f0e1eba9ea3693L, 0x0L, false, false, 0x0L, "6C40DF5F0B497347", "CRC-64/ECMA-182", "CRC-64");
    public static final CRCModel<Long> CRC_64_GO_ISO = create(64, 0x1bL, 0xffffffffffffffffL, true, true, 0xffffffffffffffffL, "B90956C775A41001", "CRC-64/GO-ISO");
    public static final CRCModel<Long> CRC_64_MS = create(64, 0x259c84cba6426349L, 0xffffffffffffffffL, true, true, 0x0L, "75D4B74F024ECEEA", "CRC-64/MS");
    public static final CRCModel<Long> CRC_64_NVME = create(64, 0xad93d23594c93659L, 0xffffffffffffffffL, true, true, 0xffffffffffffffffL, "AE8B14860A799888", "CRC-64/NVME");
    public static final CRCModel<Long> CRC_64_REDIS = create(64, 0xad93d23594c935a9L, 0x0L, true, true, 0x0L, "E9C6D914C4B8D9CA", "CRC-64/REDIS");
    public static final CRCModel<Long> CRC_64_WE = create(64, 0x42f0e1eba9ea3693L, 0xffffffffffffffffL, false, false, 0xffffffffffffffffL, "62EC59E3F1A4F00A", "CRC-64/WE");
    public static final CRCModel<Long> CRC_64_XZ = create(64, 0x42f0e1eba9ea3693L, 0xffffffffffffffffL, true, true, 0xffffffffffffffffL, "995DC9BBDF1939FA", "CRC-64/XZ", "CRC-64/GO-ECMA");

    // CRC 82
    public static final CRCModel<BigInteger> CRC_82_DARC = create(82, "308c0111011401440411", "0", true, true, "0", "09EA83F625023801FD612", "CRC-82/DARC");


    public final int width, crcByteLength;

    public final T poly, init, xorout, mask;

    public final boolean refin, refout;

    public final String check;

    public final String[] names;

    public static final String checkInput = "123456789";

    private CRCModel(int width, T poly, T init, boolean refin, boolean refout, T xorout,
                     T mask, String check, String... names) {
        this.width = width;
        this.crcByteLength = (width + 7) / 8;

        this.poly = poly;
        this.init = init;
        this.refout = refout;
        this.mask = mask;

        this.refin = refin;
        this.xorout = xorout;

        this.check = check;
        this.names = names;
    }

    public static CRCModel<Long> create(int width, long poly, long init, boolean refin, boolean refout, long xorout, String check, String... names) {
        return new CRCModel<>(width,
                              poly, init,
                              refin, refout,
                              xorout,
                              -1L >>> (64 - width),
                              check, names);
    }

    public static CRCModel<BigInteger> create(int width, String poly, String init, boolean refin, boolean refout, String xorout, String check, String... names) {
        return new CRCModel<>(width,
                              new BigInteger(poly, 16), new BigInteger(init, 16),
                              refin, refout,
                              new BigInteger(xorout, 16),
                              BigInteger.ONE.shiftLeft(width).subtract(BigInteger.ONE),
                              check, names);
    }
}

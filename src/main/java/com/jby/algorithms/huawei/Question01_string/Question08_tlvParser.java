package com.jby.algorithms.huawei.Question01_string;

import java.util.Scanner;

public class Question08_tlvParser {
    /**
     * TLV解析Ⅰ
     * 题目描述：
     * 码流以某信元的Tag开头，Tag固定占 一个字节，Length固定占 两个字节，字节序为 小端序 。
     * 现给定TLV格式编码的码流，以及需要解码的信元Tag，请输出该信元的Value。
     * 输入码流的16进制字符中，不包括小写字母，且要求输出的16进制字符串中也不要包含小写字母；
     * 码流字符串的最大长度不超过50000个字节。
     * 输入描述：
     * 输入的第一行为一个字符串，表示待解码信元的 Tag ；
     * 输入的第二行为一个字符串，表示待解码的 16进制码流 ，字节之间用 空格分隔
     * eg
     * 31
     * 32 01 00 AE 90 02 00 01 02 30 03 00 AB 32 31 31 02 00 32 33 33 01 00 CC
     * ----------- -------------- ----------------- -------------- -----------  , 输入数据中总共5个TLV单元
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String tag = in.nextLine();
        String[] tlv = in.nextLine().split(" ");
        for (int i = 0; i < tlv.length; ) {
            int length = Integer.parseInt(tlv[i + 2] + tlv[i + 1], 16);  // 将字符串的Length转为16进制，小端，需要反过来
            if (tag.equals(tlv[i])) {
                StringBuilder sb = new StringBuilder();
                for (int j = i + 3; j < i + 3 + length; j++) {
                    sb.append(tlv[j]).append(" ");
                }
                System.out.println(sb.toString());
                break;
            } else {
                i += length + 3;
            }
        }
    }
}

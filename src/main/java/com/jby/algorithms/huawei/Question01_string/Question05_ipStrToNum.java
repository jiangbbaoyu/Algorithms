package com.jby.algorithms.huawei.Question01_string;

import java.util.Scanner;

/**
 * HJ33 整数与IP地址间的转换
 * 原理：ip地址的每段可以看成是一个0-255的整数，把每段拆分成一个二进制形式组合起来，然后把这个二进制数转变成一个长整数。
 * 举例：一个ip地址为10.0.3.193
 * 每段数字             相对应的二进制数
 * 10                   00001010
 * 0                    00000000
 * 3                    00000011
 * 193                  11000001
 * 组合起来即为：00001010 00000000 00000011 11000001,转换为10进制数就是：167773121，即该IP地址转换后的数字就是它了
 *
 * 输入描述：
 * 输入
 * 1 输入IP地址
 * 2 输入10进制型的IP地址
 *
 * 输出描述：
 * 输出
 * 1 输出转换成10进制的IP地址
 * 2 输出转换后的IP地址
 */
public class Question05_ipStrToNum {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String line = in.nextLine();
            String res ="";
            if(line.contains(".")){
                res = parseIpStrToNum(line);
            }else{
                res = parseNumToIpStr(line);
            }
            System.out.println(res);
        }
    }

    private static String parseNumToIpStr(String line) {
        Long num = Long.valueOf(line);
        String[] parts =new String[4];
        parts[0] = String.valueOf(num>>24 & 255);
        parts[1] = String.valueOf(num>>16 & 255);
        parts[2] = String.valueOf(num>>8 & 255);
        parts[3] = String.valueOf(num & 255);

        return String.join(".",parts);
    }

    private static String parseIpStrToNum(String line) {
        String[] parts = line.split("\\.");
        long num =0;
        num += Long.parseLong(parts[0])<<24;
        num += Long.parseLong(parts[1])<<16;
        num += Long.parseLong(parts[2])<<8;
        num += Long.parseLong(parts[3]);
        return String.valueOf(num);
    }
}

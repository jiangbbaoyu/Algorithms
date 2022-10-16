package com.jby.algorithms.huawei.Question01_string;

import java.util.Scanner;

/**
 *  HJ5 进制转换
 * 接受一个十六进制的数，输出该数值的十进制表示。
 */
public class Question01_JinZhiConvert {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String line =in.nextLine();
            String numStr = line.trim().substring(2);
            int idx =0;
            int sum=0;
            while(idx<numStr.length()){ // 从高位到地位依次遍历每个字符
                sum *= 16;
                char c = numStr.charAt(idx);
                int curNum = parseNum(c);
                sum+= curNum;
                idx ++;
            }

            System.out.println(sum);
        }
    }
    private static int parseNum(char c){
        if(c>='0'&& c<='9'){
            return c-'0';
        }
        if(c>='A' && c<='F'){
            return c-'A'+10;
        }
        return -1;
    }
}

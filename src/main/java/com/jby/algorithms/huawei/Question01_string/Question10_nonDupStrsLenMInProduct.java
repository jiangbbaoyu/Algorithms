package com.jby.algorithms.huawei.Question01_string;

import java.util.Scanner;

public class Question10_nonDupStrsLenMInProduct {
    /**
     * 无重复字符的元素长度乘积的最大值
     * 题目描述：
     * 给定一个元素类型为小写字符串的数组，请计算两个没有相同字符的元素长度乘积的最大值
     *
     * eg: input :iwdvpbn,hk,iuop,iikd,kadgpf
     *     output : 14
     *
     * 数组中有5个元素。
     * iwdvpbn与hk无相同的字符，满足条件，iwdvpbn的长度为7，hk的长度为2，乘积为14（7*2）。
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] words = in.nextLine().split(",");
        int maxLength = 0;
        for (int i = 0; i < words.length - 1; i++) {
            for (int j = i + 1; j < words.length; j++) {
                char[] word1 = words[i].toCharArray();
                char[] word2 = words[j].toCharArray();
                boolean flag = true;
                for (int m = 0; m < word1.length; m++) {
                    for (int n = 0; n < word2.length; n++) {
                        if (word1[m] == word2[n]) {
                            flag = false;
                            break;
                        }
                    }
                }
                if (flag) {  // 两个字符串不包含相同字符
                    int Length = word1.length * word2.length;
                    maxLength = Math.max(maxLength, Length);
                }
            }
        }
        System.out.println(maxLength);
    }

}

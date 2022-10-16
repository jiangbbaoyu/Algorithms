package com.jby.algorithms.Q00_JianZhiOffer;

/**
 * 剑指 Offer 65. 不用加减乘除做加法
 * 写一个函数，求两个整数之和，要求在函数体内不得使用 “+”、“-”、“*”、“/” 四则运算符号
 *
 * 思路： 1）基于异或求没有进位的两个数之和
 *       2）基于 & 求两个数相加后产生的进位
 *       3）将 没有进位的两个数之和  与  进位 相加（重复进行1，2两步）
 */
public class Question65 {

    public int add(int a, int b) {
        while(b!=0){
            int sumWithoutCarry = a ^ b;
            int carry = (a & b) << 1;
            a = sumWithoutCarry;
            b = carry;
        }

        return a;

    }
}

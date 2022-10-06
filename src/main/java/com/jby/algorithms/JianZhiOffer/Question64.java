package com.jby.algorithms.JianZhiOffer;

/**
 * 剑指 Offer 64. 求1+2+…+n
 * 求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）
 *
 * 思路： 递归， 配合 boolean遍历求值的 短路特性
 */
public class Question64 {

    public int sumNums(int n) {
        int sum =n;
        boolean flag = n>0 && (sum += sumNums(n-1))>0;
        return sum;
    }

}

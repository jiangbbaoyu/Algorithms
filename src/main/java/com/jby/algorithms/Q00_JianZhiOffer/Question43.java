package com.jby.algorithms.Q00_JianZhiOffer;

import org.junit.Test;
/**
 * 剑指 Offer 43. 1～n 整数中 1 出现的次数
 * 输入一个整数 n ，求1～n这n个整数的十进制表示中1出现的次数。
 * 例如，输入12，1～12这些整数中包含1 的数字有1、10、11和12，1一共出现了5次
 * 思路：
 *     eg : 3 1 0 1 5 9 2
 *     /*
 *      *计算从1-n出现1的总次数
 *      *就是计算1-n的所有整数，这些数字中 个位+十位+百位+..为1的总和
 *
 *      *举个例子：把百位固定
 *      *3101 5 92  此时百位为5， 我们把百位设为1， 前面可以取0-3101，百位后面可以取0-99 （向百位借1，可以使得后面部分为0~99的任意数）
 *      *一共有（3101+1）*100（百位）种组合使百位为1
 *      *
 *      *再举个例子：把千位固定
 *      *310 1 592  此时千位为1，我们把千位设为1，这里可以分两种情况：
 *      *  1前面取0-309 此时千位后面可以取0-99
 *      *  1前面取310， 此时千位后面可以取0-592（不能向千位结尾，借位后千位不可能为1）
 *      *一共有（301+1）*1000（千位）+（592+1）种组合使千位为1
 *
 *      *再举最后一个例子：把万位固定
 *      *31 0 1592，此时千位为0，把万位设为1,此时只能向前面借位，前面只能取0-30,而不能取31
 *      万位后面可以取0-9999 （万位借位后是9，万位后面可以向万位借位使得后面部分为0~9999的任意数）
 *      *一共有（31）*10000（万位）种组合使万位为1
 *
 *      *我们可以分别计算一个数的个位百位...对应的组合数，最后相加
 *
 *      *基本情况就是上面的例子，分别对应的位元素 是大于1，等于1，等于0
 *     */
public class Question43 {
    public int countDigitOne(int n) {

        long base =1;
        long res =0;
        while(base<=n){
            long headAndCurDigit = (long)n/base;
            long curDigit = headAndCurDigit%10;
            long head = headAndCurDigit/10;

            long tail =(long)n%base;  // n %1 == 0

            if(curDigit>1){
                res += (head+1)* base;
            }

            if(curDigit==0){
                res += head * base;
            }

            if(curDigit==1){
                res += (head* base + 1 *(tail+1) );
            }

            base *=10;
        }
        return (int)res ;

    }

    @Test
    public void test(){
        int i = countDigitOne(10);
        System.out.println(i);
    }
}

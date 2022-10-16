package com.jby.algorithms.Q00_JianZhiOffer;

import org.junit.Test;

/**
 * 剑指 Offer 44. 数字序列中某一位的数字
 * 数字以0123456789101112131415…的格式序列化到一个字符序列中。在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，等等。
 * 请写一个函数，求任意第n位对应的数字。
 */
public class Question44 {

    public int findNthDigit(int n) {

        if(n<=0){
            throw new RuntimeException("invalid intput");
        }
        int digitNum =1;
        long numberCountOfDigit = numberCountOfDigit(digitNum);
        while(n>=digitNum*numberCountOfDigit){ // digitNum*numberOfDigit 的值可能会超出int 的表示范围 ， numberOfDigit 使用long 类型表示来避免
            n -= digitNum*numberCountOfDigit;
            digitNum ++;
            numberCountOfDigit = numberCountOfDigit(digitNum);
        }

        int locatedNum = startNumOfDigits(digitNum) + n/digitNum;
        int rightDigits = digitNum - n%digitNum;
        for(int i=1;i<rightDigits;i++){
            locatedNum = locatedNum/10;
        }

        return locatedNum%10;
    }

    private int numberCountOfDigit(int digitNum){
        if(digitNum==1){
            return 10;
        }
        return ((int)Math.pow(10,digitNum-1)) * 9;
    }

    private int startNumOfDigits(int digitNum){
        if(digitNum==1){
            return 0;
        }
        return (int)Math.pow(10,digitNum-1);
    }

    @Test
    public void test(){
        int nthDigit = findNthDigit(1000000000);
        System.out.println(nthDigit);
    }
}

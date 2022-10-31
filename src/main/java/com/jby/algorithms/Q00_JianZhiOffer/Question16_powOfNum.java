package com.jby.algorithms.Q00_JianZhiOffer;

/**
 * 剑指 Offer 16. 数值的整数次方
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。不得使用库函数，同时不需要考虑大数问题。
 */
public class Question16_powOfNum {
    public double myPow(double x, int n) {
        boolean isNagitive = n>=0? false : true;
        double res = pow(x,Math.abs((long)n));
        if(isNagitive){
            res = 1.0/res;
        }
        return res;

    }

    // O(logn) 求 x的n次方
    // a^n  =  a ^ n/2 *  a ^ n/2  , n为偶数
    // a^n  =  a^(n-1)/2  *  a^(n-1）/2  * a , n为奇数  (n-1)/2  == n/2
    private double pow(double x, long n){
        if(n==0){
            return 1;
        }
        if(n==1){
            return x;
        }

        double res = pow(x,n>>1);
        res *= res;
        if( (n & 1) ==1){
            res *= x; // n为奇数的情况
        }
        return res;
    }
}

package com.jby.algorithms.Q00_JianZhiOffer;
import java.math.BigInteger;

public class Question14 {
    /**
     * 剑指 Offer 14- I. 剪绳子
     * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），
     * 每段绳子的长度记为 k[0],k[1]...k[m-1] 。请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？
     * 例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18
     *
     * 思路： 动态规划， 从上到下分析问题，从下到上解决问题
     *                当绳子长度为1时，绳子只能分为一段，最大乘积为1
     *                当绳子长度为2时，绳子只能分为两个长度为1的段，最大乘积为1*1 =1 （绳子分段后的最大乘积小于绳子长度本身）
     *                当绳子长度为3时，绳子分为2，1，最大乘积为2 * 1 =2 （绳子分段后的最大乘积小于绳子长度本身）
     *                当绳子长度大于等于4时， f(i) = max(f(i-j)*f(j)), 0<j<i, （绳子分段后的最大乘积大于等于绳子长度本身）
     */
    public int cuttingRope(int n) {
        // 特殊的小问题
        if(n==2){
            return 1;
        }
        if(n==3){
            return 2;
        }

        int[] dp = new int[n+1];
        dp[1]=1;
        dp[2]=2;
        dp[3]=3;
        // 基于小问题来解决大问题
        for(int i =4;i<=n;i++){
            int max =0;
            for(int j=1;j<=i/2;j++){
                if(dp[j]*dp[i-j]>max){
                    max = dp[j]*dp[i-j];
                }
            }
            dp[i] =max;
        }

        return dp[n];
    }

    /**
     * 贪心解法 : n>4 ,尽可能多剪长度为3的绳子; n==4， 剪 2*2 ;  n<4为特殊情况
     */
    public int cuttingRope2(int n) {
        if(n==2){
            return 1;
        }
        if(n==3){
            return 2;
        }
        int res =1;
        while(n>=5){
            res *=3;
            n-=3;
        }

        return res*n;
    }

    /**
     * 剪绳子2 ： 答案需要取模 1e9+7（1000000007）
     */
    public int cuttingRope3(int n) {
        if(n==2){
            return 1;
        }
        if(n==3){
            return 2;
        }
        long res =1;  // n的值可能很大，因此要用long 来保存结果
        while(n>=5){
            res *=3;
            res = res%1000000007;
            n-=3;
        }

        return (int)(res*n%1000000007);
    }


    public int cuttingRope4(int n) {
        if(n==2){
            return 1;
        }
        if(n==3){
            return 2;
        }

        BigInteger[] dp =new BigInteger[n+1];// long 也可能溢出，使用BigInteger
        dp[1]=BigInteger.valueOf(1);
        dp[2]=BigInteger.valueOf(2);
        dp[3]=BigInteger.valueOf(3);

        for(int i= 4;i<=n;i++){
            BigInteger  max =BigInteger.valueOf(0);
            for(int j=1;j<=i/2;j++){
                max = dp[j].multiply(dp[i-j]).max(max);
            }
            dp[i] = max;
        }

        return dp[n].mod(BigInteger.valueOf(1000000007)).intValue();
    }

}

package com.jby.algorithms.Q00_JianZhiOffer;

/**
 * 剑指 Offer 49. 丑数
 * 我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。
 * 思路：
 *    丑数 * 2 ，丑数 * 3 ，丑数 * 5 仍然为丑数，第一个丑数为1
 *    从小到大依次计算出n个丑数， 返回第n个
 */
public class Question49 {
    public int nthUglyNumber(int n) {

        int[] dp = new int[n];
        dp[0] =1;
        int idx2 =0;
        int idx3 =0;
        int idx5 =0;
        int cur =1;

        while(cur<n){

            int next = Math.min(Math.min(dp[idx2]*2,dp[idx3]*3),dp[idx5]*5);
            dp[cur] = next;

            while(dp[idx2]*2<=next){
                idx2++;
            }

            while(dp[idx3]*3<=next){
                idx3++;
            }

            while(dp[idx5]*5<=next){
                idx5++;
            }

            cur++;
        }

        return dp[n-1];
    }
}

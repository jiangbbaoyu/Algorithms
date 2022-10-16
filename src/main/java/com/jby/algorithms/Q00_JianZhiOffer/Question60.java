package com.jby.algorithms.Q00_JianZhiOffer;

/**
 *
 *
 *
 * f(n,s)=f(n-1,s-1)+f(n-1,s-2)+f(n-1,s-3)+f(n-1,s-4)+f(n-1,s-5)+f(n-1,s-6)
 * 将f(n,s) 表示 n个骰子点数和为s 的排列总数
 * n个骰子点数和为s的种类数只与 n-1个骰子的和 以及第n个骰子的点数有关。
 * 因为一个骰子有六个点数，那么第n个骰子可能出现1到6的点数。所以第n个骰子点数为1的话，f(n,s)=f(n-1,s-1)，当第n个骰子点数为2的话，f(n,s)=f(n-1,s-2)，...
 * 那么有：f(n,s)=f(n-1,s-1)+f(n-1,s-2)+f(n-1,s-3)+f(n-1,s-4)+f(n-1,s-5)+f(n-1,s-6)
 * 已知初始阶段的解为：当n=1时, f(1,1)=f(1,2)=f(1,3)=f(1,4)=f(1,5)=f(1,6)=1
 */
public class Question60 {

    public double[] dicesProbability(int n) {

        // dp[n][6n]是有效空间： n个骰子， n个骰子点数的最大和为6n  dp[i][j] 表示i个骰子摇出的点数之和为j的情况的个数
        // 第 dp[0][] 行， 第 dp[][0] 列是为了适配边界情况
        int[][] dp=new int[n+1][6*n+1];
        double[] ans=new double[5*n+1];  // n个骰子点数之和 可能出现在 n ~ 6n 之间，总共 6n-n+1个
        double all=Math.pow(6,n); // n个骰子所能摇出的所有可能的结果

        for(int i=1;i<=6;i++){ // 一个骰子的情况， 和s 为1，2..6 均只有一种情况
            dp[1][i]=1;
        }

        for(int i=2;i<=n;i++){// 有两个骰子以上的情况
            for(int j=i;j<=6*i;j++){ // 有i个骰子，则点数之和 j  满足 i<=s <=6*i
                int sum =0;
                for(int k=1;k<=6;k++){
                    if(j>k){
                        // 只取 i个骰子可能出现的情况 eg 有3个骰子则不可能出现点数和为2的情况
                        sum+=dp[i-1][j-k];
                    }
                }
                dp[i][j] = sum;
            }
        }

        for(int i= 0;i< 5*n+1;i++){
            ans[i] = dp[n][n + i]/ all;
        }


        return ans;
    }

}

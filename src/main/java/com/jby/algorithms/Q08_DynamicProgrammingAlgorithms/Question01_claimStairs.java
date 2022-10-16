package com.jby.algorithms.Q08_DynamicProgrammingAlgorithms;

public class Question01_claimStairs {
    /**
     * leetcode 70. 爬楼梯
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     *
     * 思路：  f(n) = f(n-1) + f(n-2) , n>2
     *               2,               n=2;
     *               1,               n=1
     */
    public int climbStairs(int n) {
        int prev2 =1;
        int prev1 =2;
        if(n==1){
            return prev2;
        }

        if(n==2){
            return prev1;
        }
        int res =0;
        for(int i=3;i<=n ;i++){
            res = prev1 +prev2;
            prev2 =prev1;
            prev1 = res;
        }

        return res;
    }
}

package com.jby.algorithms.Q08_DynamicProgrammingAlgorithms;

public class Question04_coinChange {
    /**
     * leetcode 322. 零钱兑换
     * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
     * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
     * 你可以认为每种硬币的数量是无限的。
     *
     * 思路 ：
     * 动态规划问题。
     * 假设 f(n) 代表要凑齐金额为 n 所要用的最少硬币数量，那么有：
     * f(n) = min(f(n - c1), f(n - c2), ... f(n - cn)) + 1, 其中 c1 ~ cn 为硬币的所有面额
     * 例如这个示例：
     * 输入: coins = [1, 2, 5], amount = 11
     * 输出: 3
     * 解释: 11 = 5 + 5 + 1
     *
     * 题目求的值为 f(11)，第一次选择硬币时我们有三种选择。
     * 假设我们取面额为 1 的硬币，那么接下来需要凑齐的总金额变为 11 - 1 = 10，即 f(11) = f(10) + 1，这里的 +1 就是我们取出的面额为 1 的硬币。
     * 同理，如果取面额为 2 或面额为 5 的硬币可以得到：
     * f(11) = f(9) + 1
     * f(11) = f(6) + 1
     * 所以 f(11) = min(f(10), f(9), f(6)) + 1
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1]; // dp[0] ==0, 表示amount为0时，最少需要0个 coin
        // 依次计算 curAmount为 1,2,3,... 时，最少需要的 硬币数，并保存在dp[curAmount]中，
        // 如果 dp[curAmount] == Integer.MAX_VALUE-1, 则表示 使用 coins中的硬币无法凑出 curAmount的总金额
        for(int curAmount = 1;curAmount<=amount;curAmount ++){
            int coinCount = Integer.MAX_VALUE-1;  // 防止 coinCount +1 导致的 int类型越界
            for(int i=0;i<coins.length;i++){
                int conValue = coins[i];
                if(curAmount - conValue >=0 ){
                    coinCount = Math.min(coinCount,dp[curAmount-conValue]+1);
                }
            }
            dp[curAmount] = coinCount;
        }
        if(dp[amount]==Integer.MAX_VALUE-1){
            return -1;
        }
        return dp[amount];
    }
}

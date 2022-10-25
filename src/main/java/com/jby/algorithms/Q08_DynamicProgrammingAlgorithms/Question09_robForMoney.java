package com.jby.algorithms.Q08_DynamicProgrammingAlgorithms;

public class Question09_robForMoney {
    /**
     * leetcode 198. 打家劫舍
     * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
     * 示例 1：
     * 输入：[1,2,3,1]
     * 输出：4
     * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     *      偷窃到的最高金额 = 1 + 3 = 4
     *
     * 思路： dp[i] = max(dp[i-2]+nums[i], dp[i-1])
     *       如果抢劫第i个房屋，则第i-1个房屋不能抢劫，则结果为抢劫[:i-2]个房屋的总价值 +  第 i个房屋的总价值  dp[i-2]+nums[i]
     *       如果不抢劫第i个房屋，，则结果为抢劫[:i-1]个房屋的总价值   dp[i-1]
     *
     */
    public int rob(int[] nums) {

        int[] dp = new int[nums.length+1];
        dp[1] = nums[0];
        for(int i=2;i<=nums.length;i++){
            dp[i] =  Math.max(dp[i-1], dp[i-2]+ nums[i-1]);
        }

        return dp[nums.length];
    }

    /**
     * leetcode 213. 打家劫舍 II
     * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，
     * 这意味着第一个房屋和最后一个房屋是紧挨着的。
     * 同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
     *
     * 思路 :  考虑 第一个房子偷不偷
     */

    public int rob2(int[] nums) {
        if(nums.length==1){
            return nums[0];
        }
        // 1. 第一个房子 偷
        int[] dp = new int[nums.length];
        dp[1] = nums[0];
        for(int i=2;i<nums.length;i++){ // 此时只访问 nums[1:nums.len-2]
            dp[i] = Math.max(dp[i-1],dp[i-2]+ nums[i-1]);
        }
        int res1= dp[nums.length-1];
        // 2. 第一个房子 不偷
        dp[1] = nums[1];
        for(int i=2;i<nums.length;i++){  // 此时只访问 nums[1:nums.len-1]
            dp[i] = Math.max(dp[i-1],dp[i-2]+ nums[i]);
        }

        int res2= dp[nums.length-1];

        return Math.max(res1,res2);
    }
}

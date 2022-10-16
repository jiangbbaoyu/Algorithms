package com.jby.algorithms.Q03_arrayAlgorithms;

public class Question04_maxSubSeqSum {
    /**
     * leetcode 53. 最大子数组和 (剑指 Offer 42. 连续子数组的最大和)
     * 给你一个整数数组 nums ，请你找出一个 具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * 子数组 是数组中的一个连续部分
     *   思路: 求 max(f(i)) , 0<=i<=arr.length-1
     *   f(i) =  arr[i] , i=0 || f(i-1)<=0
     *   f(i) = arr[i]+f(i-1), i!=0 && f(i-1)>0
     */
    public int maxSubArray(int[] nums) {

        int maxSum =Integer.MIN_VALUE;
        int curSum =0;

        for(int i=0;i<nums.length;i++){
            // 如果当前的和小于0了，则curSum + nums 一定小于 nums,
            // 因此放弃curSum， maxSubArray从 nums中取
            if(curSum<0){
                curSum=0;
            }
            curSum += nums[i];

            maxSum = curSum>maxSum? curSum:maxSum;
        }

        return maxSum;



    }
}

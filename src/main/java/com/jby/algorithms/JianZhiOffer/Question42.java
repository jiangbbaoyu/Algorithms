package com.jby.algorithms.JianZhiOffer;

/**
 * 剑指 Offer 42. 连续子数组的最大和
 * 输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值
 * 要求时间复杂度为O(n)。
 *
 * 思路: 求 max(f(i)) , 0<=i<=arr.length-1
 *  f(i) =  arr[i] , i=0 || f(i-1)<=0
 *  f(i) = arr[i]+f(i-1), i!=0 && f(i-1)>0
 *
 */
public class Question42 {

    public int maxSubArray(int[] nums) {

        if(nums==null || nums.length==0){
            throw new RuntimeException("invalid input");
        }

        int maxSum =Integer.MIN_VALUE;
        int curSum =0;
        for(int i=0;i<nums.length;i++){
            if(curSum<=0){
                curSum = nums[i];
            }else{
                curSum +=nums[i];
            }

            if(curSum>maxSum){
                maxSum = curSum;
            }
        }

        return maxSum;
    }
}

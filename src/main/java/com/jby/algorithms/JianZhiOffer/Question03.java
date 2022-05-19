package com.jby.algorithms.JianZhiOffer;

/**
 * 剑指 Offer 03. 数组中重复的数字
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。
 * 数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。
 * 请找出数组中`任意一个`重复的数字。
 *
 * 思路： 从头到尾遍历每个元素，看第i个数组元素的值是否是i,
 *           如果不是则交换数组中第i个元素和第arr[i]个元素 （此时数组的第arr[i]个元素的值一定是arr[i]），交换的同时比较两个元素是否相等
 *           如果是，则继续遍历下一个元素
 */
public class Question03 {

    // 空间复杂度O(1), 时间复杂度O(N)
    public int findRepeatNumber(int[] nums) {
        for(int i =0;i<nums.length;i++){
            while(nums[i]!=i){
                if(nums[i]==nums[nums[i]]){
                    return nums[i];
                }
                swap(nums,i,nums[i]);
            }

        }

        return -1;
    }

    private void swap(int[] nums, int i,int j){
        if(i!=j){
            nums[i]= nums[i]+nums[j];
            nums[j]=nums[i]-nums[j];
            nums[i] =nums[i]-nums[j];
        }
    }
}

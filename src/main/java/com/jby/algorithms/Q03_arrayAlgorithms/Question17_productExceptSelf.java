package com.jby.algorithms.Q03_arrayAlgorithms;

public class Question17_productExceptSelf {
    /**
     * leetcode 238. 除自身以外数组的乘积
     * 给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积 。
     * 题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。
     * 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
     */

    // 空间复杂度 O(N) (除了res 数组)
    public int[] productExceptSelf(int[] nums) {

        int[] rightPart = new int[nums.length];
        rightPart[nums.length-1] =1;
        for(int i= nums.length-2;i>=0;i--){
            rightPart[i] = rightPart[i+1]* nums[i+1];
        }

        int[] res = new int[nums.length];

        int leftPartProduct =1;
        for(int i=0;i<nums.length;i++){
            res[i] =  leftPartProduct * rightPart[i];
            leftPartProduct *= nums[i];
        }

        return res;
    }

    // 记录每个元素的左右累乘 ，
    // 第i 次循环，计算res[i] 的左半部分 ;  计算res[nums.length-1-i] 的右半部分
    // 空间复杂度 O(1) (除了res 数组)
    public int[] productExceptSelf2(int[] nums) {

        int leftPartProduct =1;
        int rightPartProduct =1;
        int[] res = new int[nums.length];
        for(int i=0;i<nums.length;i++){
            res[i] =1;
        }

        for(int i=0;i<nums.length;i++){
            res[i] *= leftPartProduct;
            leftPartProduct *= nums[i];

            res[nums.length-1-i] *= rightPartProduct;
            rightPartProduct *= nums[nums.length-1-i];
        }

        return res;
    }
}

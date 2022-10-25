package com.jby.algorithms.Q03_arrayAlgorithms;

public class Question05_maxSubSeqProduct {
    /**
     * leetcode 152. 乘积最大子数组
     * 给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
     * 测试用例的答案是一个 32-位 整数。
     * 子数组 是数组的连续子序列。
     * 示例 1:
     * 输入: nums = [2,3,-2,4]
     * 输出: 6
     * 解释: 子数组 [2,3] 有最大乘积 6。
     *
     * 思路 ： 双向遍历数组
     *        求最大值，可以看成求 `被0分割的` 各个子数组的乘积最大值
     *        当一个数组中存在0时，0 将数组分为两个子数组， 分别求子数组的最大乘积
     *        当一个子数组中没有0存在时，则分为两种情况：
     *          1.负数为偶数个，则整个数组的各个值相乘为最大值；
     *          2.负数为奇数个，则从左到右，累乘到最后 得到有一个“最大值”，从右到左，累乘到最后 得到一个“最大值”，两者比较，得出最大值
     *
     *  eg :  nums = [1,-2,3,0,1,-2,3,4]
     *        子数组  [1,-2,3] [1,-2,3,4]
     *        左->右     1         1
     *        右->左     3         12
     *   maxProduct:              12
     */
    public int maxProduct(int[] nums) {

        int maxProduct =Integer.MIN_VALUE;
        int curProduct =1;
        for(int i=0;i<nums.length;i++){

            curProduct *= nums[i];
            maxProduct = Math.max(maxProduct,curProduct);
            if (nums[i]==0){
                curProduct =1;
            }
        }

        curProduct =1;
        for(int i=nums.length-1;i>=0;i--){

            curProduct *= nums[i];
            maxProduct = Math.max(maxProduct,curProduct);
            if(nums[i]==0){
                curProduct =1;
            }
        }

        return maxProduct;
    }
}

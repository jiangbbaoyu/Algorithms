package com.jby.algorithms.Q03_arrayAlgorithms;

public class Question14_firstMissingPositive {
    /**
     * leetcode  41. 缺失的第一个正数
     * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
     * 请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
     *
     * 示例 1：
     * 输入：nums = [1,2,0]
     * 输出：3
     * 示例 2：
     * 输入：nums = [3,4,-1,1]
     * 输出：2
     *
     * 思路：
     * 遍历一次数组把 大于等于1 且 小于等于数组大小的值 放到原数组对应位置，
     * 然后再遍历一次数组，判断 i+1 是否和 nums[i] 相等，如果不相等，i+1就是答案，
     * 否则遍历完都没出现那么答案就是数组长度加1
     *
     * 时间复杂度O(N) 空间复杂度O(!)
     *
     * eg
     * i==1  -1 4 2 1 9
     *       -1 1 2 4 9
     *       1 -1 2 4 9
     * i==2  1 2 -1 4 9
     * i==3  1 2 -1 4 9
     * i==4  1 2 -1 4 9
     *            *
     *          res = 2+1 =3
     */
    public int firstMissingPositive(int[] nums) {
        for(int i=0;i<nums.length;i++){
            // 如果 1） 当前第i个元素  是一个 大于等于1 且 小于等于数组大小的值，
            //     2)  同时 该值 还没有放到合适的位置上 ， 即  下标为 nums[i]-1 的值  不等于当前值 nums[i]
            while(nums[i]<=nums.length && nums[i]>0 && nums[i]!=nums[nums[i]-1]){
                swap(nums,nums[i]-1,i);
            }
        }

        for(int i=0;i<nums.length;i++){
            if(nums[i]!=i+1){
                return i+1;
            }
        }

        return nums.length+1;
    }
    private void swap(int[] nums,int i,int j){
        int  tmp = nums[i];
        nums[i] = nums[j];
        nums[j]= tmp;
    }
}

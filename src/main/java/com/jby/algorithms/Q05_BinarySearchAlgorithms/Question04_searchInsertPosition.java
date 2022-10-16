package com.jby.algorithms.Q05_BinarySearchAlgorithms;

public class Question04_searchInsertPosition {
    /**
     * leetcode 35. 搜索插入位置 (leetcode 300. 最长递增子序列 ,其中使用了该功能)
     * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。
     * 如果目标值不存在于数组中，返回它将会被按顺序插入的位置
     *
     * 输入: nums = [1,3,5,6], target = 5
     * 输出: 2
     * 输入: nums = [1,3,5,6], target = 2
     * 输出: 1
     */
    public int searchInsert(int[] nums, int target) {
        int left =0;
        int right = nums.length-1;

        while(left<=right){
            int mid= left + ((right-left)>>1);

            if(nums[mid]==target){
                return mid;
            }else if(nums[mid]>target){
                right= mid-1;
            }else if(nums[mid]<target){
                left = mid+1;
            }
        }
        // 此时没有找到，且left指向了 nums中大于target的第一个元素的位置，
        // right指向了 nums中小于target的第一个元素的位置
        return left;

    }
}

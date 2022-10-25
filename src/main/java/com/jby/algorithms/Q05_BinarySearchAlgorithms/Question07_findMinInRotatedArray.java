package com.jby.algorithms.Q05_BinarySearchAlgorithms;

public class Question07_findMinInRotatedArray {

    /**
     * leetcode 153. 寻找旋转排序数组中的最小值
     * 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,2,4,5,6,7] 在变化后可能得到：
     * 若旋转 4 次，则可以得到 [4,5,6,7,0,1,2]
     * 若旋转 7 次，则可以得到 [0,1,2,4,5,6,7]
     * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
     * 给你一个元素值 `互不相同` 的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
     * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
     * 示例 1：
     * 输入：nums = [3,4,5,1,2]
     * 输出：1
     */
    public int findMin(int[] nums) {
        int left =0;
        int right = nums.length-1;

        while(left < right){
            int mid = left +((right-left)>>1);
            //判断哪边是有序的
            if(nums[mid]>nums[right]){
                left = mid +1;   // [mid,right]是无序的，将范围缩小到无序的那一半，因为答案就在那一半。
                                 // 之所以要+1，是因为mid肯定不是最小的那个，至少nums[right]比nums[mid]更小
            }else{ //  [mid,right]是有序的，此时答案只可能在[left, mid]中，mid有可能直接是最小值，所以不能使得 right = mid -1
                right = mid;
            }
        }
        // 此时 left == right
        return nums[left];
    }

    /**
     * leetcode 154. 寻找旋转排序数组中的最小值 II
     * 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,4,4,5,6,7] 在变化后可能得到：
     * 若旋转 4 次，则可以得到 [4,5,6,7,0,1,4]
     * 若旋转 7 次，则可以得到 [0,1,4,4,5,6,7]
     * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
     * 给你一个``可能存在 重复 元素值`` 的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
     */

    public int findMin2(int[] nums) {
        int left =0;
        int right = nums.length-1;
        while(left < right){
            int mid = left +((right-left)>>1);

            if(nums[mid]>nums[right]){
                left = mid +1;
            }else if(nums[mid]==nums[right]){
                // 有重复值,如果此时mid 元素等于 right 元素，该将 right 减 1, 此时无法将搜索范围减半了，只能将搜索范围缩小1
                right =right-1;

            }else{
                right = mid;
            }
        }

        return nums[left];
    }




}

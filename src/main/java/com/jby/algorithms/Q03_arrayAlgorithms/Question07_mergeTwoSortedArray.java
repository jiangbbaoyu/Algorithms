package com.jby.algorithms.Q03_arrayAlgorithms;

public class Question07_mergeTwoSortedArray {

    /**
     * leetcode 88. 合并两个有序数组
     * 给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
     * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
     * 注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，
     *       其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n
     *
     * 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
     * 输出：[1,2,2,3,5,6]
     * 思路： 依次取两个数组中最大的值，放到nums1的最后面
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int tail = m+n-1;
        int tail1 = m-1;
        int tail2 = n-1;
        while(tail1>=0 && tail2>=0){
            if(nums1[tail1]>nums2[tail2]){
                nums1[tail--] = nums1[tail1--];
            }else{
                nums1[tail--] = nums2[tail2--];
            }
        }
        while(tail2>=0){
            nums1[tail--] = nums2[tail2--];
        }
        // 如果是tail2>=0, 此时可以不用移动，[0,tail1]的元素已经在nums1的最前面了
    }
}

package com.jby.algorithms.Q03_arrayAlgorithms;

/**
 * leetcode 283. 移动零
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 * 示例 1:
 * 输入: nums = [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 */
public class Question18_moveZeros {

    public void moveZeroes(int[] nums) {
        int boundIdx =0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]!=0){ // 将非 0 元素移动到 数组头部 boundIdx位置，然后 boundIdx 后移
                swap(nums,i,boundIdx);
                boundIdx++;
            }
            // 如果 元素是 0 ， 则直接后移 i 即可
        }
    }

    private void swap(int[] arr,int i,int j){
        if(i!=j){
            arr[i]=arr[i]^arr[j];
            arr[j]=arr[i]^arr[j];
            arr[i]=arr[i]^arr[j];
        }

    }
}

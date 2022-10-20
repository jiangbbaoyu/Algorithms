package com.jby.algorithms.Q03_arrayAlgorithms;

import org.junit.Test;

import java.util.Arrays;

public class Question13_nextPermutation {
    /**
     * leetcode 31. 下一个排列
     * 整数数组的 下一个排列 是指其整数的下一个字典序更大的排列。更正式地，如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，那么数组的 下一个排列 就是在这个有序容器中排在它后面的那个排列。如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列（即，其元素按升序排列）。
     * 例如，arr = [1,2,3] 的下一个排列是 [1,3,2] 。
     * 类似地，arr = [2,3,1] 的下一个排列是 [3,1,2] 。
     * 而 arr = [3,2,1] 的下一个排列是 [1,2,3] ，因为 [3,2,1] 不存在一个字典序更大的排列。
     */

    public void nextPermutation(int[] nums) {

        // 1. 前后指针 从后往前遍历数组，找到第一个数字（nums[smallValIdx]），该数字大于后面数字的
        int smallValIdx = -1;
        int next = nums.length-1;
        int prev = next-1;
        while(prev>=0){
            if(nums[prev]<nums[next]){
                smallValIdx = prev;
                break;
            }
            next --;
            prev --;
        }
        //2. 如果不存在 ，则说明该数组是从大到小排序的，此时将数组逆序，即可
        if(smallValIdx==-1){
            reverse(nums,0,nums.length-1);
            return;
        }

        // 3. 定位 nums[smallValIdx:]中，比nums[smallValIdx]大的最小的元素（nums[bigValIdx]）
        int bigValIdx= -1;
        for(int i=nums.length-1;i>smallValIdx;i--){
            bigValIdx = nums[i]>nums[smallValIdx]&& (bigValIdx==-1||nums[i]<nums[bigValIdx])?i:bigValIdx;
        }
        //4. 交换
        swap(nums,bigValIdx,smallValIdx);
        // 5, 翻转 nums[smallValIdx+1:]
        reverse(nums,smallValIdx+1,nums.length-1);
    }
    /**
     * eg  3 8 6 4 2   smallValIdx = 0 ,bigValIdx  = 3 , swap(nums,0,3)
     *     4 8 6 3 2   reverse(nums, 1,nums.length-1)
     *     4 2 3 6 8   得到结果
     */

    private void reverse(int[] nums,int i,int j){
        while(i<j){
            int tmp =nums[i];
            nums[i]= nums[j];
            nums[j] = tmp;

            i++;
            j--;
        }
    }

    private void swap(int[] nums,int i,int j){
        int tmp =nums[i];
        nums[i]= nums[j];
        nums[j] = tmp;
    }

    @Test
    public void test(){
        int[] nums = {1, 3, 2};
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));

    }
}

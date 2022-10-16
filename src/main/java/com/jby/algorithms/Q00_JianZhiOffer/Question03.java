package com.jby.algorithms.Q00_JianZhiOffer;

import org.junit.Test;

/**
 * 剑指 Offer 03. 数组中重复的数字
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。  （ n个元素从n个不同的数字中选择 ）
 * 数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。
 * 请找出数组中`任意一个`重复的数字。
 *
 * 思路： 从头到尾遍历每个元素，看第i个数组元素的值是否是i,
 *           如果不是则交换数组中第i个元素和第arr[i]个元素 （此时数组的第arr[i]个元素的值一定是arr[i]），交换的同时比较两个元素是否相等
 *           如果是，则继续遍历下一个元素
 */
public class Question03 {
    // test
    // test

    // 空间复杂度O(1), 时间复杂度O(N)
    // 可以修改 原始数组元素的值
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

    /**
     *
     * 变形： 对于n+1长度的数组， 元素分布在 [0~ n-1], 要求不修改原始数组，且要求空间复杂度O(1)， 求出任意一个重复数字
     * 思路：由于 ```共n+1个元素，而只有n个不同的数可供选择``` ，因此必定至少有一个是重复的（所有数字都出现的场景）。（
     *      且如果有某个数字没有出现，则至少有两个数字重复，或一个数字出现3次
     *      如果数组中的元素在[m,n] 区间内的个数大于n-m+1个，则一定有重复数字； 小于等于n-m+1个也有可能存在重复数字，eg: n-1出现两次，出现0次
     */

    public int findRepeatNumber2(int[] nums) {

        int left =0;
        int right = nums.length -1;

        while(left<=right){

            if (left==right){
                return left;
            }

            int mid = left + ((right-left)>>1);

            int rangeCount = countNum(nums,left,mid);

            if (rangeCount<=mid-left+1){
                left =mid+1;
            }else{
                right = mid;
            }
        }

        return -1;
    }
    private int countNum(int[] nums,int start,int end){
        int count =0;
        for(int i=0 ; i<nums.length;i++){
            if(nums[i]>=start && nums[i]<=end){
                count++;
            }
        }
        return count;
    }

    @Test
    public void test(){
//        int repeatNumber2 = findRepeatNumber2(new int[]{0, 1, 2, 0, 4, 5, 6, 7, 8, 8});
        int repeatNumber2 = findRepeatNumber2(new int[]{0,0,2,2});
        System.out.println(repeatNumber2);
    }


}

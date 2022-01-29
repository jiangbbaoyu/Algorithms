package com.jby.algorithms.SortAlgorithms;

import com.jby.algorithms.Util.NumberUtils;
import org.junit.Test;

import java.util.Arrays;

/**
 *
 * （leetcode 2224）剑指 Offer 21. 调整数组顺序使奇数位于偶数前面
 * 将数组中的奇数放在数组左边，偶数放在数组右边，不能使用额外数组空间，不要求值相同的元素原始的相对次序不变
 * 该问题与 快排1.0的partition过程 是等效的，稳定性是无法做到的
 */
public class QuickSortQuestion02 {

    public int[] exchange(int[] nums) {
        if(nums==null || nums.length<=1){
            return nums;
        }

        // 由于仅将数组分为两部分，因此只需要一个分界指针right,一个遍历指针i即可
        int right=nums.length-1; // (right,) 为偶数 ， [0,right] 为奇数
        int i=0;
        while(i<=right){

            if(nums[i]%2==1){
                i++;
            }else if(nums[i]%2==0){
                swap(nums,right,i);
                right--;
            }
        }
        return nums;
    }
    private void swap(int[] arr,int i,int j){
        if(i!=j){
            arr[i]=arr[i]^arr[j];
            arr[j]=arr[i]^arr[j];
            arr[i]=arr[i]^arr[j];
        }

    }

    @Test
    public void test(){
        int[] arr = NumberUtils.generateRandomIntArr();
        System.out.println(Arrays.toString(arr));
        arr=exchange(arr);
        System.out.println(Arrays.toString(arr));
    }
}

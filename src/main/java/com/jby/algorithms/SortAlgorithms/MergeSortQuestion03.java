package com.jby.algorithms.SortAlgorithms;

import com.jby.algorithms.Util.NumberUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * leetcode 2252 https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof/
 * 剑指 Offer 51. 数组中的逆序对
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对
 * 输入: [7,5,6,4]
 * 输出: 5
 */
public class MergeSortQuestion03 {

    public int reversePairs(int[] nums) {

        if(nums==null || nums.length<2){
            return 0;
        }
        int[] tempNums = new int[nums.length];
        return findReversePairs(nums,tempNums,0,nums.length-1);

    }

    private int findReversePairs(int[] nums,int[] tempNums, int left, int right){

        if(left==right){
            return 0;
        }

        int middle = left + ((right-left)>>1);
        return findReversePairs(nums,tempNums, left,middle)+
                findReversePairs(nums,tempNums, middle+1,right)+
                merge(nums,tempNums,left,middle,right);
    }

    private int merge(int[] nums,int[] tempNums, int left, int middle, int right){

        int res = 0;

        int i = left;
        int j= middle+1;
        int k = left;

        // 从大到小排序
        while(i<=middle && j<= right){
            if(nums[i]>nums[j]){
                res += (right-j+1);
                tempNums[k++] = nums[i++];
            }else{
                tempNums[k++] = nums[j++];
            }
        }

        // 从小到大排序
//        while(i<=middle && j<= right){
//            if(nums[i]>nums[j]){
//                res += (middle-i+1);
//                tempNums[k++] = nums[j++];
//            }else{
//                tempNums[k++] = nums[i++];
//            }
//        }

        while(i<=middle){
            tempNums[k++] = nums[i++];
        }

        while(j<=right){
            tempNums[k++] = nums[j++];
        }

        for(k=left;k<=right;k++){
            nums[k] = tempNums[k];
        }
        return res;
    }

    @Test
    public void test(){
        int[] arr = NumberUtils.generateRandomIntArr(1,9,7,8,5);
        System.out.println(Arrays.toString(arr));
        System.out.println(reversePairs(arr));
    }

}

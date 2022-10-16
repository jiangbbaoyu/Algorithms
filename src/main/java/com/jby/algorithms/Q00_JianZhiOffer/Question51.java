package com.jby.algorithms.Q00_JianZhiOffer;

/**
 * 剑指 Offer 51. 数组中的逆序对
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数
 *
 * 思路： 基于归并排序
 */
public class Question51 {
    public int reversePairs(int[] nums) {

        if(nums==null || nums.length==0){
            return 0;
        }

        int[] tmpNums = new int[nums.length];

        return mergeSort(nums,tmpNums,0,nums.length-1);
    }

    private int mergeSort(int[]nums,int[] tmpNums,int left ,int right){
        if(left>=right){
            return 0;
        }

        int mid = left + (right-left)/2;
        return mergeSort(nums,tmpNums,left,mid)+
                mergeSort(nums,tmpNums,mid+1,right)+
                merge(nums,tmpNums,left,mid,right);
    }

    private int merge(int[]nums,int[] tmpNums,int left ,int mid,int right){
        int count =0;
        int k= left;
        int i=left;
        int j=mid+1;
        // 从大到小排序
        while(i<=mid && j<=right){
            if(nums[i]>nums[j]){
                count += (right-j+1);
                tmpNums[k++] =nums[i++];
            }else{
                tmpNums[k++] =nums[j++];
            }
        }

        // 从小到大排序
//        while(i<=mid && j<=right){
//            if(nums[i]>nums[j]){
//                count += (mid-i+1);
//                tmpNums[k++] =nums[j++];
//            }else{
//                tmpNums[k++] =nums[i++];
//            }
//        }

        while(i<=mid){
            tmpNums[k++] =nums[i++];
        }
        while(j<=right){
            tmpNums[k++] =nums[j++];
        }

        for(k=left;k<=right;k++){
            nums[k] = tmpNums[k];
        }

        return count;

    }
}

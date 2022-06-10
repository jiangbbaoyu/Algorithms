package com.jby.algorithms.JianZhiOffer;

/**
 * 剑指 Offer 21. 调整数组顺序使奇数位于偶数前面
 * 思路： 双指针
 */
public class Question21 {
    public int[] exchange(int[] nums) {
        if(nums==null || nums.length==0){
            return nums;
        }

        int left = 0;
        int right =nums.length-1;
        while(left<right){
            if((nums[left]&1)!=0){
                left++; // 奇数
            }else{
                swap(nums,left,right); // left指针不动，因为交换过来的数字是偶数还是奇数还不清除
                right--;
            }
        }

        return nums;
    }

    private void swap(int[] nums,int i,int j){
        if(i!=j){
            nums[i]=nums[i]+nums[j];
            nums[j]=nums[i]-nums[j];
            nums[i]=nums[i]-nums[j];

        }
    }
}

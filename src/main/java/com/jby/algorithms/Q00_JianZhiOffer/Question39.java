package com.jby.algorithms.Q00_JianZhiOffer;

/**
 * 剑指 Offer 39. 数组中出现次数超过一半的数字
 * 数组中有一个数字出现的次数 超过数组长度的一半，请找出这个数字
 */
public class Question39 {
    /**
     * 遍历数组时保存两个值，一个是数组中的一个元素 res，一个是该元素出现次数 count
     * 当下一个元素 与res相同，则count++
     * 当下一个元素与res不同，则count--;
     * 如果count==0,则res替换为新的值，count重新赋值为1
     *
     * 当数组遍历结束时，如果数组中存在一个出现次数超过数组长度一半的元素，则res就是该值
     */
    public int majorityElement(int[] nums) {

        int res = nums[0];
        int count=0;
        for(int i=0;i<nums.length;i++){

            if(nums[i]==res){
                count++;
            }else{
                count--;
            }

            if(count==0){
                res = nums[i];
                count=1;
            }
        }

        return res;
    }

    /**
     * 基于 快速排序中partition函数的思路
     * 选择一个数，进行partition, 如果该元素是出现次数超过数组长度一半的元素，则经过partition后，返回的 bounds[0] <= mid <= bounds[1]
     */
    public int majorityElement2(int[] nums) {
        int start =0;
        int end=nums.length-1;
        int mid = nums.length/2;
        int targetIdx = start + (int)(Math.random()*(end-start+1));
        int[] bounds = partition(nums,start,end,targetIdx);

        while(mid>bounds[1]|| mid<bounds[0]){
            if(mid>bounds[1]){
                start= bounds[1]+1;
            }else if (mid<bounds[0]){
                end =bounds[0]-1;
            }
            targetIdx = start + (int)(Math.random()*(end-start+1));
            bounds = partition(nums,start,end,targetIdx);
        }

        return nums[mid];
    }

    private int[] partition(int[] nums, int start, int end,int targetIdx){
        int target = nums[targetIdx];
        int m= start;
        int n=end;
        int i=start;

        while(i<=n){
            if(nums[i]<target){
                swap(nums,i,m);
                m++;
                i++;
            }else if(nums[i]==target){
                i++;

            }else if (nums[i]>target){
                swap(nums,i,n);
                n--;
            }
        }

        return new int[]{m,n};
    }

    private void swap(int[] nums,int i,int j){
        int tmp =nums[i];
        nums[i]=nums[j];
        nums[j]=tmp;
    }
}

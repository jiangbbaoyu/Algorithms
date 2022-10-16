package com.jby.algorithms.Q00_JianZhiOffer;

/**
 * 剑指 Offer 53 - I. 统计一个数字在排序数组中出现的次数
 *
 * 思路： 使用二分查找，分别找出目标数字k在数据中第一次和最后一次出现的位置
 */
public class Question53 {

    public int search(int[] nums, int target) {
        if(nums==null || nums.length==0){
            return 0;
        }

        int firstIdx = getTargetIdx(nums,target,true);
        int lastIdx = getTargetIdx(nums,target,false);
        if(firstIdx!=-1){
            return lastIdx -firstIdx +1;
        }
        return 0;
    }

    private int getTargetIdx(int[] nums, int k, boolean findFirst){
        int left =0;
        int right=nums.length-1;
        while(left <= right){
            int mid = left + ((right-left)>>1);

            if(nums[mid] < k){
                left = mid+1;
            }else if (nums[mid]>k){
                right = mid - 1;
            }else{
                // nums[mid] ==k
                if(findFirst){
                    if(mid==0 ||(mid>0 && nums[mid-1]!=k)){ // 找到最左侧的k
                        return mid;
                    }
                    right = mid-1;
                }else{
                    if(mid==nums.length-1||(mid<nums.length-1 && nums[mid+1]!=k)){ // 找到最右侧的k
                        return mid;
                    }
                    left = mid+1;
                }
            }
        }

        return -1;
    }

    // 更优雅的解法
    private int getTargetIdx2(int[] nums, int target, boolean findFirst){
        int left =0;
        int right=nums.length-1;
        int boundary =-1; // 保存最后一次碰到等于target 的数组元素的下标

        while(left<=right){
            int middle = left + ((right-left)>>1);

            if(nums[middle]<target){
                left =middle+1;
            }else if(nums[middle]>target){
                right=middle-1;
            }else{
                // nums[middle]==target
                boundary = middle; // 记录最后一次碰到target时的 数组位置下标
                if(findFirst){
                    right =middle-1;
                }else {
                    left =middle+1;
                }
            }

        }
        return boundary;
    }

    /**
     * 剑指 Offer 53 - II. 0～n-1中缺失的数字
     * 一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。
     * 在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字
     * eg: n=5  [0,1,2,3] 4 不在数组中；   n=5  [1,2,3,4] 0 不在数组中
     *
     * 思路： 基于二分查找， 找出第一个 元素idx ！= nums[idx] (如果不存在的数字是n,则是一个特例，因为此时数组中所有的元素的值均等于元素所在的数组下标)
     */
    public int missingNumber(int[] nums) {
        if(nums==null || nums.length==0){
            return -1;
        }

        int left =0;
        int right=nums.length-1;
        int firstValueIdxMisMatch =-1;
        while(left <= right){

            int mid = left + ((right-left)>>1);

            if(nums[mid]==mid){
                left = mid+1;
            }else{

                // nums[mid]!=mid
                firstValueIdxMisMatch = mid;
                right = mid-1;
            }
        }

        // 特殊情况处理，不存在的数组为 0， 或 nums.length
        if(right==-1){
            return 0;
        }
        if(left==nums.length){
            return nums.length;
        }
        return firstValueIdxMisMatch;
    }



}

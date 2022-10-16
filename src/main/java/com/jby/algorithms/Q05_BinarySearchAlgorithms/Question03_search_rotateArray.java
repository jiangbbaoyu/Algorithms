package com.jby.algorithms.Q05_BinarySearchAlgorithms;

import org.junit.Test;

public class Question03_search_rotateArray {

    /**
     * 33. 搜索旋转排序数组
     * 整数数组 nums 按升序排列，数组中的值 互不相同 。
     * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，
     * 使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）
     * 。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
     * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1
     *
     * 思路：如果中间的数小于最右边的数，则右半段是有序的，
     *      若中间数大于最右边数，则左半段是有序的，
     *      我们只要在有序的半段里用首尾两个数组来判断目标值是否在这一区域内，这样就可以确定保留哪半边了
     *
     * * 数组从任意位置劈开后，至少有一半是有序的
     * * 先找到哪一段是有序的 (只要判断端点即可)，然后看 target 在不在有序的这一段里，
     *   如果在，那么就把另一半丢弃。如果不在，那么就把该有序段丢弃
     */
    public int search(int[] nums, int target) {
        int left =0;
        int right =nums.length-1;

        while(left<=right){
//            if(left==right && nums[left]!=target){
//                return -1;
//            }
            int mid = left + ((right-left)>>1);

            if(nums[mid] == target){
                return mid;
            }else if(nums[mid]>nums[right]){ // 左半段有序
                if(nums[mid]>target && nums[left]<=target){ // 且target在左半段范围内
                    right = mid-1;
                }else{
                    left = mid+1; // 在右半段
                }

            }else if(nums[mid]<=nums[right]){ // 右半段有序
                if(nums[right]>=target && nums[mid]<target){// 且target在右半段范围内
                    left = mid+1;
                }else{
                    right = mid-1; // 在左半段
                }
            }
        }

        return -1;
    }


    // todo 数组有重复元素的情况


    @Test
    public void test(){
        int search = search(new int[]{4, 5, 6, 7, 0, 1, 2}, 3);
    }
}

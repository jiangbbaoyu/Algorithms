package com.jby.algorithms.Q03_arrayAlgorithms;

import org.junit.Test;

public class Question09_longestIncSequence {
    /**
     * leetcode 300. 最长递增子序列
     * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
     * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。
     * 例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列
     *
     * 示例 1：
     * 输入：nums = [10,9,2,5,3,7,101,18]
     * 输出：4
     * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
     * 示例 2：
     * 输入：nums = [0,1,0,3,2,3] , 最长子序列为[0,1,2,3]
     * 输出：4
     *
     * 思路: 创建一个数组longestIncSequence，依次将nums中的元素添加进去，
     *       如果当前元素target大于longestIncSequence中最大的有效元素，则将target放入longestIncSequence有效元素末尾
     *       否则将当前元素插入到longestIncSequence有效元素部分的合适位置，并保持有效元素部分有序
     *
     * nums = [0,1,0,3,2,3]  ,longestIncSequence 中有效元素的变化：
     *                        add 0 :      0
     *                        add 1 :      0，1
     *                        add 0 :      0，1
     *                        add 3 :      0，1，3
     *                        add 2 :      0，1，2
     *                        add 3 :      0，1，2，3
     *
     */
    public int lengthOfLIS(int[] nums) {

        int[] longestIncSequence = new int[nums.length];
        longestIncSequence[0] = nums[0];

        int maxIdx =0;
        for(int i= 1;i<nums.length;i++){
            // 对数组进行迭代, 依次判断每个数 target ,将其插入longestIncSequence数组相应的位置:
            //   1. target > longestIncSequence[maxIdx], 表示target比所有已知递增序列的尾数都大,
            //      将maxIdx加1,并将target添加入longestIncSequence[maxIdx]
            //   2. longestIncSequence[i-1] < target <= longestIncSequence[i], 只更新相应的longestIncSequence[i]
            int target = nums[i];
            int left =0; // insertPosition
            int right=maxIdx;
            while(left<=right){  // leetcode 35. 搜索插入位置
                int mid = left +((right-left)>>1);
                if(longestIncSequence[mid]==target){
                    left = mid;
                    break;
                }else if(longestIncSequence[mid]>target){
                    right = mid - 1;
                }else if(longestIncSequence[mid]<target){
                    left = mid + 1;
                }
            }

            longestIncSequence[left] = target;
            if(left == maxIdx+1){ // target比所有已知递增序列的尾数都大
                maxIdx ++;
            }
        }

        return maxIdx+1;
    }

    public int lengthOfLIS2(int[] nums) {
        int[] longestIncSequence = new int[nums.length];

        int maxLen =-1;
        for(int i= 0;i<nums.length;i++){
            int target = nums[i];
            int left =0; // insertPosition
            int right=maxLen;
            while(left<=right){
                int mid = left +((right-left)>>1);
                if(longestIncSequence[mid]==target){
                    left = mid;
                    break;
                }else if(longestIncSequence[mid]>target){
                    right = mid - 1;
                }else if(longestIncSequence[mid]<target){
                    left = mid + 1;
                }
            }

            longestIncSequence[left] = target;
            if(left == maxLen+1){
                maxLen ++;
            }
        }

        return maxLen+1;
    }

    @Test
    public void test(){
        int i = lengthOfLIS(new int[]{0, 1, 0, 3, 2, 3});
        System.out.println(i);
    }
}

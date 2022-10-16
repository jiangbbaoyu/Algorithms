package com.jby.algorithms.Q05_BinarySearchAlgorithms;

import com.jby.algorithms.Util.NumberUtils;
import org.junit.Test;

import java.util.Arrays;

public class Question02 {

    /**
     * leetcode 162 : https://leetcode-cn.com/problems/find-peak-element 局部最大值，最小值问题
     * 峰值元素是指其值严格大于左右相邻值的元素
     * 给你一个整数数组 nums（相邻元素不相等），找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
     * 假设 nums[-1] = nums[n] = -∞
     * 时间复杂度要求O(log n)
     *
     * 解法1
     * O(logN)一般考虑二分搜索
     * 考虑一个具有极大值的连续函数，极大值点的左侧一定有一段区间是递增的，极大值点的右侧一定有一段区间是递减的
     * 当定位到一个数组元素时，可以看该元素与相邻元素构成的单调关系。
     * 如果nums[i] > nums[i+1]，则在i之前一定存在峰值元素； 如果nums[i] < nums[i+1]，则在i+1之后一定存在峰值元素；仅保留存在峰值的范围。 随着范围不断缩减，最终可以定位到一个峰值

     * @param nums
     * @return
     */
    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length-1;

        while(left<right){// 当left==right时，已经定位到了一个局部最大值，将该值返回即可，不用继续进入循环了 （此时没有必要）

//            int middle =(left+right)/2;
            int middle =left +((right-left)>>1);

            if (nums[middle]<nums[middle+1]){
                left =middle+1;  // 此时middle所在的元素不可能是局部最大值，因此直接将left赋值为middle+1
            }else{ // nums[middle]>nums[middle+1]
                right = middle; // 此时middle所在的元素可能是局部最大值，因此将right赋值为middle,而不能赋值为middle-1; 这也会导致right不可能比left小
            }
        }
        // 此时 right == left
        return right;
    }

    @Test
    public void test(){
        int[] arr = NumberUtils.generateRandomIntArr(7,10);
        System.out.println(Arrays.toString(arr));
        System.out.println(findPeakElement(arr));

    }

}

package com.jby.algorithms.JianZhiOffer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 剑指 Offer 57. 和为s的两个数字
 * 输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可
 *
 * 思路： 双指针， 一个指针从左向右，另一个从右向左 （如果数组不是递增的，则不能用该方法）
 */
public class Question57 {
    public int[] twoSum(int[] nums, int target) {
        if(nums==null || nums.length<2){
            throw new RuntimeException("invalid input");
        }

        int left =0;
        int right = nums.length-1;
        while(left<right){
            int sum = nums[left]+nums[right];
            if(sum==target){
                return new int[]{nums[left],nums[right]};
            }else if(sum<target){
                left++;
            }else if(sum>target){
                right--;
            }
        }

        return new int[]{-1,-1};
    }


    /**
     * 剑指 Offer 57 - II. 和为s的连续正数序列
     * 输入一个正整数 target ，输出所有和为 target 的 连续正整数 序列（至少含有两个数）。
     * 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列
     *
     * 思路： 双指针 构成的一个滑动窗口， ``两个指针都是只能向右滑动``
     */
    public int[][] findContinuousSequence(int target) {

        if(target<1){
            return new int[0][];
        }

        List<int[]> resTmp = new ArrayList<int[]>();  // int[] 为引用类型 , 可以作为泛型的参数

        // left 和right 指针均只能向右移动，不能向左移动
        int left =1;
        int right =2;
        int sum = left+right;  // sum 维护了 当前窗口包含的数字之和， 当窗口边界改变时动态改变该sum的值


        while(right<target){
            if (sum==target){
                int[] arr = new int[right-left+1];
                for(int i= left;i<=right;i++){
                    arr[i-left] = i;
                }
                resTmp.add(arr);

                right++;
                sum+=right;
            }else if (sum>target && left<right){
                sum -= left;
                left++;
            }else if (sum<target){
                right++;
                sum+=right;
            }else {
                break;
            }
        }

        int [][] res = new int[resTmp.size()][];  // 二维数组中第二维的数组的元素个数是可以不一样的
        for(int i=0;i<resTmp.size();i++){
            res[i]= resTmp.get(i);
        }

        return res;
    }

    @Test
    public void test(){
        int[] arr1 =new int[]{1,2};
        int[] arr2 =new int[]{1,2,3};

        int[][] res = new int[2][];
        res[0]= arr1;
        res[1]=arr2;

        System.out.println(Arrays.toString(res));
    }
}

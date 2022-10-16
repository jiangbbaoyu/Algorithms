package com.jby.algorithms.Q03_arrayAlgorithms;

import org.junit.Test;

public class Question10_warter_trap {

    /**
     * leetcode 42. 接雨水
     * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水
     * 思路：dp + 双指针 , 时间复杂度： O（n），空间复杂度： O（1）
     *
     */
    public int trap(int[] height) {

        if(height==null || height.length<3){ // 大于等于3个元素才可能接到雨水
            return 0;
        }
        int left = 1;  // 第一个元素，没有外墙肯定不保存水
        int right =height.length-2; // 最后一个元素，没有外墙肯定不保存水
        int sum =0;

        int maxLeft = height[0];// dp  保存left指针左侧的最高围墙
        int maxRight = height[height.length-1];// dp  保存right指针右侧的最高围墙

        while(left<=right){ // 共循环height.len-2次  , 每一次循环 计算一个列可以接的雨水量
            // 对于left,right两个指针，选择指针外侧围墙矮的一侧移动指针,
            // 这样的话，可以基于矮围墙和当前列的高度差 计算该列可存放的雨水量，
            // （基于高围墙和当前列的高度差 无法计算该列可存放的雨水量）
            if(maxLeft<maxRight){
                //  计算left指向的列 可存放的雨水量
                if(maxLeft>height[left]){
                    sum += maxLeft - height[left];
                }
                maxLeft = Math.max(maxLeft,height[left]); // 更新外部围墙最大高度
                left ++;
            }else{
                // 计算right指向的列 可存放的雨水量
                if(maxRight>height[right]){
                    sum += maxRight - height[right];
                }
                maxRight = Math.max(maxRight,height[right]); // 更新外部围墙最大高度
                right --;
            }
        }

        return sum;
    }

    @Test
    public void test(){
        int trap = trap(new int[]{4, 2, 0, 3, 2, 5});
    }
}

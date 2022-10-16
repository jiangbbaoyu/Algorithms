package com.jby.algorithms.Q00_JianZhiOffer;

import java.util.Arrays;

/**
 * 剑指 Offer 61. 扑克牌中的顺子
 * 从若干副扑克牌中随机抽 5 张牌，判断是不是一个顺子，即这5张牌是不是连续的。2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任意数字
 */
public class Question61 {

    public boolean isStraight(int[] nums) {
        if(nums==null || nums.length!=5){
            return false;
        }

        // 1. 排序
        Arrays.sort(nums);

        // 2. 计算排序后的数组中 0的个数， （0 此时全部在数组的头部）
        int zeroCount = 0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]==0){
                zeroCount ++;
            }else{
                break;
            }
        }

        // 3. 两个指针判断 数组中前后两个元素是否相邻，并计算元素之间空隙的大小
        int left =zeroCount;
        int right =left+1;
        int gaps = 0;
        while(right<nums.length){

            if(nums[left]==nums[right]){
                return false;
            }else if (nums[left]+1<nums[right]){
                gaps += nums[right] - nums[left] -1;
            }else{

            }

            left ++;
            right ++;
        }

        return gaps>zeroCount ? false: true;
    }
}

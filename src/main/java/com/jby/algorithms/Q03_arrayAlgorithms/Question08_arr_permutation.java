package com.jby.algorithms.Q03_arrayAlgorithms;

import java.util.ArrayList;
import java.util.List;

public class Question08_arr_permutation {

    /**
     * leetcode 46. 全排列 (剑指 Offer 38. 字符串的排列)
     * 给定一个 `不含重复数字` 的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案
     */
    public List<List<Integer>> permute(int[] nums) { // 剑指offer38
        List<List<Integer>>  res = new ArrayList<>();
        doPermute(res,nums,0);
        return res;
    }

    private void doPermute(List<List<Integer>>  res,int[] nums, int startIdx){

        if(startIdx==nums.length-1){
            List<Integer> permutation = new ArrayList<>();
            for(int i=0;i<nums.length;i++){
                permutation.add(nums[i]);
            }
            res.add(permutation);
        }

        for(int i=startIdx;i<nums.length;i++){
            swap(nums,i,startIdx);
            doPermute(res,nums,startIdx+1);
            swap(nums,i,startIdx);
        }
    }

    private void swap(int[] nums,int i,int j){
        int tmp = nums[i];
        nums[i]=nums[j];
        nums[j]= tmp;
    }

    public List<List<Integer>> permuteUnique2(int[] nums) {
        List<List<Integer>>  res = new ArrayList<>();
        doPermute2(res,nums,0);
        return res;
    }

    /**
     * leetcode 47. 全排列 II
     * 给定一个 `可包含重复数字`  的序列 nums ，按任意顺序 返回所有不重复的全排列
     */
    private void doPermute2(List<List<Integer>> res,int[] nums,int startIdx){

        if(startIdx==nums.length-1){
            List<Integer> permutation = new ArrayList<>();
            for(int i=0;i<nums.length;i++){
                permutation.add(nums[i]);
            }
            res.add(permutation);
        }
        // nums[:startIdx-1] 确定的情况下，nums[startIdx]不能出现重复的字符，
        // nums[startIdx]出现了2个重复字符， nums[:startIdx]为前缀的字符排列均会出现两次
        boolean[] visited = new boolean[21];
        for(int i= startIdx;i<nums.length;i++){
            if(visited[nums[i]+10]==true){
                continue;
            }
            visited[nums[i]+10] = true;
            swap(nums,i,startIdx);
            doPermute2(res,nums,startIdx+1);
            swap(nums,i,startIdx);
        }
    }

}

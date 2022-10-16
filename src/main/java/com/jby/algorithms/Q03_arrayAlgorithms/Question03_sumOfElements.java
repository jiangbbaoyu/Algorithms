package com.jby.algorithms.Q03_arrayAlgorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Question03_sumOfElements {
    /**
     * leetcode 1. 两数之和
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
     */
    public int[] twoSum(int[] nums, int target) {
        // 使用hash map ,以空间换时间
        HashMap<Integer,Integer> numToIdxMap = new HashMap<>();
        for(int i=0 ;i< nums.length;i++){
            int k = target - nums[i];
            if(numToIdxMap.containsKey(k)){
                return new int[]{numToIdxMap.get(k),i};
            }
            numToIdxMap.put(nums[i],i);
        }
        return new int[]{-1,-1};
    }

    /**
     * leetcode 15. 三数之和
     * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]]
     *    满足 i != j、i != k 且 j != k ，
     *    同时还满足 nums[i] + nums[j] + nums[k] == 0
     *
     * 你返回所有和为 0 且不重复的三元组
     * 思路 ： 排序 + 滑动窗口（left,right 双指针）
     *       1）对原数组进行排序，然后开始遍历排序后的数组，这里注意不是遍历到最后一个停止，而是到倒数第三个就可以了
     *       2）剪枝优化，就是当遍历到正数的时候就 break，因为数组现在是有序的了， 如果第一个要 fix 的数就是正数了，则后面的数字就都是正数，就永远不会出现和为0的情况了。
     *       3）跳过重复结果的处理，从第二个数开始，如果和前面的数字相等，就跳过。 如果把排序后相同的数字fix两次，会有重复问题
     *       4）对于遍历到的数，用0减去这个 fix 的数得到一个 target，然后使用left,right指针在排序数组中找和为target的两个数
     *          如果 target，则将这两个数和 fix 的数一起存入结果中。并跳过重复数字，
     *          两个指针都需要检测重复数字。
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for(int i=0;i<nums.length-2;i++){
            int fixedNum = nums[i];
            int target = 0-fixedNum;
            int left = i+1;
            int right = nums.length-1;

            if(nums[i]>0) break;
            if(i>0 && nums[i]==nums[i-1]) continue; // 排序后，相同的数字仅fix一次，防止重复

            while(left < right){
                if(target==nums[left]+nums[right]){
                    ArrayList<Integer> ans = new ArrayList<Integer>();
                    ans.add(fixedNum);
                    ans.add(nums[left]);
                    ans.add(nums[right]);
                    res.add(ans);
                    // 防止结果集三元组中，在第一个数字相同的情况下，第二个数字重复;
                    // 对于两个三元组，如果第一个，第二个数字均不同，则第三个数字肯定也不同
                    while(left<right && nums[left]==nums[left+1]) left ++;
                    left ++;
                    // 优化，更加快速的定义到下一组 等于target的两个数字
                    while(left<right && nums[right]==nums[right-1]) right --;
                    right --;
                }else if(target > nums[left]+nums[right]){
                    left ++ ;
                }else if(target < nums[left]+nums[right]){
                    right --;
                }
            }
        }

        return res;
    }


}

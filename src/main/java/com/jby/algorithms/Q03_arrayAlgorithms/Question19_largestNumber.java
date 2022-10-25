package com.jby.algorithms.Q03_arrayAlgorithms;

import java.util.Arrays;

public class Question19_largestNumber {

    /**
     * leetcode 179. 最大数
     * 给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。
     * 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
     * 示例 1：
     * 输入：nums = [10,2]
     * 输出："210"
     * 示例 2：
     * 输入：nums = [0,0]
     * 输出："0"
     *
     * 思路 ： 自定义字符串的排序规则
     */
    public String largestNumber(int[] nums) {

        String[] strs = new String[nums.length];
        for(int i=0;i<nums.length;i++){
            strs[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(strs,(str1,str2)->{
            return -(str1+str2).compareTo(str2+str1);  // 从大到小排序
        });

        String res = String.join("",strs);
        if(res.startsWith("0"))
            return "0";
        return res;
    }
}

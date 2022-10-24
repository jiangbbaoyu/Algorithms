package com.jby.algorithms.Q03_arrayAlgorithms;

import java.util.HashMap;

public class Question16_longestConsecutiveSeq {

    /**
     * leetcode 128. 最长连续序列
     * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
     * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
     * 示例 1：
     * 输入：nums = [100,4,200,1,3,2]
     * 输出：4
     * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
     * 示例 2：
     * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
     * 输出：9
     *
     * 思路 : 用哈希表存储 每个连续区间的端点值 对应连续区间的长度
     * 若数已在哈希表中：跳过不做处理
     * 若是新数加入：
     *      取出其 左右相邻数 已有的连续区间长度 left 和 right
     *      计算当前数的区间长度为：cur_length = left + right + 1
     *      根据 cur_length 更新最大长度 max_length 的值
     *      更新区间 `两端点` 的长度值
     * eg : nums = [0,3,7,2,5,8,4,6,0,1]
     *
     *     hashmap :  0->1 ,3->1 7->1
     *                0->1 ,3->2 7->1,2->2
     *                0->1 ,3->2 7->1,2->2,5->1
     *                0->1 ,3->2 7->2,2->2,5->1, 8->2
     *                0->1 ,3->2 7->2,2->4,5->4, 8->2 , 4->4 (2+1+1)
     *                0->1 ,3->2 7->2,2->7,5->4, 8->7 , 4->4, 6->7 (4+2+1)
     *                0->1 ,3->2 7->2,2->7,5->4, 8->7 , 4->4, 6->7 , (0已经存在了，不更新)
     *                0->9 ,3->2 7->2,2->7,5->4, 8->9 , 4->4, 6->7,1-> 9(1+7+1)
     *
     *                maxLen =9
     */
    public int longestConsecutive(int[] nums) {
        HashMap<Integer,Integer> numToSeqLen = new HashMap<>();
        int maxLen =0;

        for(int i=0;i<nums.length;i++){
            int curNum = nums[i];

            if(!numToSeqLen.containsKey(curNum)){
                int preLen = numToSeqLen.getOrDefault(curNum-1,0);
                int postLen = numToSeqLen.getOrDefault(curNum+1,0);

                int curLen = 1+preLen+postLen;
                maxLen = maxLen>curLen? maxLen:curLen;

                numToSeqLen.put(curNum,curLen);
                numToSeqLen.put(curNum-preLen,curLen);
                numToSeqLen.put(curNum+postLen,curLen);
            }
        }
        return maxLen;
    }
}

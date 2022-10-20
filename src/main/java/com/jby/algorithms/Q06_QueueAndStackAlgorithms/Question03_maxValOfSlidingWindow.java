package com.jby.algorithms.Q06_QueueAndStackAlgorithms;

import java.util.LinkedList;

public class Question03_maxValOfSlidingWindow {
    /**
     * leetcode 239. 滑动窗口最大值  (剑指offer 59)
     * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
     * 返回 滑动窗口中的最大值 。
     * 示例 1：
     * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
     * 输出：[3,3,5,5,6,7]
     * 解释：
     * 滑动窗口的位置                最大值
     * ---------------               -----
     * [1  3  -1] -3  5  3  6  7       3
     *  1 [3  -1  -3] 5  3  6  7       3
     *  1  3 [-1  -3  5] 3  6  7       5
     *  1  3  -1 [-3  5  3] 6  7       5
     *  1  3  -1  -3 [5  3  6] 7       6
     *  1  3  -1  -3  5 [3  6  7]      7
     *
     *   思路 ： 使用一个 size为k的队列 来维护可能成为滑动窗口最大值的数组元素的 `下标`，
     *      *        队列的最后一个元素始终是当前 滑动窗口最大值元素的数组下标，
     *               队列中前面的下标值对应的数组元素虽然较小，但有可能是后面滑动窗口的最大值
     *      *
     *
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        LinkedList<Integer> queue = new LinkedList<>();
        int[] res = new int[nums.length-k+1];
        int j=0;
        for(int i=0;i<nums.length;i++){

            if(!queue.isEmpty() && queue.getLast()<=i-k){
                queue.removeLast();
            }
            while(!queue.isEmpty() && nums[queue.getFirst()]<nums[i]){
                queue.removeFirst();
            }
            queue.addFirst(i);
            if(i+1>=k){
                res[j++] = nums[queue.getLast()];
            }
        }
        return res;
    }
}

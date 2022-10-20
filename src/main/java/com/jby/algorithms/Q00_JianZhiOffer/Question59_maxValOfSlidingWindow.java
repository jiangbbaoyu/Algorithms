package com.jby.algorithms.Q00_JianZhiOffer;

import java.util.LinkedList;

public class Question59_maxValOfSlidingWindow {
    /**
     * 剑指 Offer 59 - I. 滑动窗口的最大值   (leetcode 239. 滑动窗口最大值)
     * 给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。
     *
     * 示例:
     * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
     * 输出: [3,3,5,5,6,7]
     *   滑动窗口的位置                最大值
     * ---------------               -----
     * [1  3  -1] -3  5  3  6  7       3
     *  1 [3  -1  -3] 5  3  6  7       3
     *  1  3 [-1  -3  5] 3  6  7       5
     *  1  3  -1 [-3  5  3] 6  7       5
     *  1  3  -1  -3 [5  3  6] 7       6
     *  1  3  -1  -3  5 [3  6  7]      7
     *
     *  思路 ： 使用一个 size为k的队列 来维护可能成为滑动窗口最大值的数组元素的 `下标`，
     *        队列的最后一个元素始终是当前 滑动窗口最大值元素的数组下标， 队列中前面的下标值对应的数组元素虽然较小，但有可能是后面滑动窗口的最大值
     *
     */
    public int[] maxSlidingWindow(int[] nums, int k) {

        if(nums==null || k==0){
            return new int[0];
        }

        int[] res = new int[nums.length-k+1];
        LinkedList<Integer> queue = new LinkedList<Integer>();

        int j =0;
        for(int i=0;i<nums.length;i++){

            if(!queue.isEmpty()&& i>=queue.getLast()+k){ // 队列最后一个元素已经不在当前滑动窗口中了
                queue.removeLast();
            }

            // 向队列头部插入元素前， 首次判断当前加入的元素值与队列头部值的大小关系
            // 如果当前加入的值大于 队列头部值， 则说明队列头部值不可能是当前滑动窗口的最大值了，将该值从队列中移除
            while(!queue.isEmpty() && nums[queue.getFirst()]<nums[i]){
                queue.removeFirst();
            }

            queue.addFirst(i);

            if(i+1>=k){
                res[j++] = nums[queue.getLast()]; // 保存当前滑动窗口的最大值保存到返回结果中
            }
        }

        return res;
    }


    /**
     * 剑指 Offer 59 - II. 队列的最大值
     * 请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。
     * 若队列为空，pop_front 和 max_value 需要返回 -1
     *
     * 思路：与 带有getMin函数的栈问题类似，  使用一个辅助队列来存放最大值， 在push_bak, pop_front时同时更新该辅助队列
     */
    class MaxQueue {
        LinkedList<Integer>  dataQ;
        LinkedList<Integer>  maxQ;
        public MaxQueue() {
            dataQ = new LinkedList<Integer>();
            maxQ  =  new  LinkedList<Integer>();
        }

        public int max_value() {
            if (maxQ.isEmpty()){
                return -1;
            }
            return maxQ.getLast();
        }

        public void push_back(int value) {
            dataQ.addFirst(value);

            while(!maxQ.isEmpty() && value > maxQ.getFirst()){
                maxQ.removeLast();

            }
            maxQ.addFirst(value);
        }

        public int pop_front() {
            if (dataQ.isEmpty()){
                return -1;
            }

            int num = dataQ.removeLast();

            if(num== maxQ.getLast()){
                maxQ.removeLast();
            }

            return num;
        }
    }

}

package com.jby.algorithms.heapAlgorithms;

import org.junit.Test;

import java.util.PriorityQueue;

/**
 * 703. 数据流中的第 K 大元素
 * 设计一个找到数据流中第 k 大元素的类（class）。注意是排序后的第 k 大元素，不是第 k 个不同的元素。
 *
 * 请实现 KthLargest 类：
 * KthLargest(int k, int[] nums) 使用整数 k 和整数流 nums 初始化对象。
 * int add(int val) 将 val 插入数据流 nums 后，返回当前数据流中第 k 大的元素。
 */
class KthLargest {
    PriorityQueue<Integer> queue;  // 使用一个`小根堆`维护数据流中的`最大k个数字`
    int k;
    public KthLargest(int k, int[] nums) {
        this.k =k;
        queue = new PriorityQueue<Integer>(k,(a,b)->a-b); // k 为 initCapacity, 后序如果持续加入元素，会扩容
        for(int i=0;i<nums.length;i++){
            add(nums[i]);
        }
    }

    public int add(int val) {
        if(queue.size()<k){
            queue.add(val); // 堆中元素不足k个，直接加入
        }else if(queue.peek()<val){ // 堆中已有k个元素，且堆顶元素小于当前val, 则将当前堆顶元素取出，然后将val加入到堆中
            queue.poll();
            queue.add(val);
        }
        return queue.peek();
    }
}


public class Question01_kTh_ele_in_datastream {

    @Test
    public void test(){
        KthLargest kthLargest = new KthLargest(3, new int[]{4, 5, 8, 2});
        kthLargest.add(3);
        kthLargest.add(5);
        kthLargest.add(10);
        kthLargest.add(9);
        kthLargest.add(4);

    }
}

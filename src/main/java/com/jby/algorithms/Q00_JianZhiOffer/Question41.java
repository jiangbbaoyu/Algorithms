package com.jby.algorithms.Q00_JianZhiOffer;

import java.util.PriorityQueue;

/**
 * 剑指 Offer 41. 数据流中的中位数
 * 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。
 *                         如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值
 *
 *
 *
 *  思路： 使用一个大根堆存放较小的一半数据，使用一个小根堆存放较大的一半数据
 *        中位数 为：  (大根堆 堆顶元素 + 小根堆堆顶元素）/2 （共偶数个元素）, 或 元素较多的堆的堆顶元素  （共奇数个元素）
 */
public class Question41 {

    class MedianFinder {

        PriorityQueue<Integer> leftMaxHeap;
        PriorityQueue<Integer> rightMinHeap;
        /** initialize your data structure here. */
        public MedianFinder() {

            leftMaxHeap = new PriorityQueue<Integer>((a,b)->b-a);// 大根堆
            rightMinHeap = new PriorityQueue<Integer>((a,b)->a-b); // 小根堆

        }

        public void addNum(int num) {  // O（logN）

            if(leftMaxHeap.isEmpty()&&rightMinHeap.isEmpty()){
                leftMaxHeap.add(num);
                return;
            }

            if(num>leftMaxHeap.peek()){
                rightMinHeap.add(num);
            }else{
                leftMaxHeap.add(num);
            }

            // 保证两个堆的元素个数之差不超过 1

            if(rightMinHeap.size()>leftMaxHeap.size()+1){
                leftMaxHeap.add(rightMinHeap.poll());
            }

            if(leftMaxHeap.size()>rightMinHeap.size()+1){
                rightMinHeap.add(leftMaxHeap.poll());
            }
        }

        public double findMedian() {// O（1）
            if((leftMaxHeap.size()+rightMinHeap.size())%2==0){
                return (leftMaxHeap.peek()+rightMinHeap.peek())/2.0;
            }else{
                return leftMaxHeap.size()>rightMinHeap.size()?leftMaxHeap.peek():rightMinHeap.peek();
            }

        }
    }
}

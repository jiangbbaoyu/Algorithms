package com.jby.algorithms.Q03_arrayAlgorithms;

import java.util.PriorityQueue;

public class Question01_KthLargest {
    /**
     * 215. 数组中的第K个最大元素
     * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
     */
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
        for(int i=0;i<k;i++){
            queue.add(nums[i]);
        }

        for(int j=k;j<nums.length;j++){

            if(nums[j]>queue.peek()){
                queue.poll();
                queue.add(nums[j]);
            }
        }
        return queue.peek();
    }

    // 自己构建 小根堆，不使用库函数
    public int findKthLargest2(int[] nums, int k) {
        int[] heap = new int[k];
        for(int i=0;i<k;i++){
            heap[i]=nums[i];
        }
        buildHeap(heap);

        for(int j=k;j<nums.length;j++){
            if(nums[j]>heap[0]){
                heap[0] = nums[j];
                heapify(heap,0);
            }
        }
        return heap[0];
    }

    private void buildHeap(int[] heap){
        // for(int i= heap.length-1;i>=0;i--){
        for(int i= (heap.length-1-1)/2;i>=0;i--){ // 从非叶子节点开始 heapify的操作
            heapify(heap,i);
        }
    }

    private void heapify(int[] heap, int parentIdx) {
        int leftChildIdx = parentIdx*2+1;
        while(leftChildIdx < heap.length){
            int minChildIdx = leftChildIdx+1>= heap.length? leftChildIdx:
                    heap[leftChildIdx]<heap[leftChildIdx+1]?leftChildIdx:leftChildIdx+1;
            if(heap[minChildIdx]<heap[parentIdx]){
                swap(heap, minChildIdx,parentIdx);
                parentIdx = minChildIdx;
                leftChildIdx = parentIdx*2+1;
            }else{
                break;
            }
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] =arr[j];
        arr[j]=temp;
    }
}

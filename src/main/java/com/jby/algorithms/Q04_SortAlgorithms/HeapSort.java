package com.jby.algorithms.Q04_SortAlgorithms;

import com.jby.algorithms.Util.NumberUtils;
import org.junit.Test;

import java.util.Arrays;


/**
 *
 * leetcode 912. 排序数组
 *
 *  堆： 本质上是一个完全二叉树， 数据可以存放在数组中
 *  不具有稳定性 时间复杂度O(N*logN) ,空间复杂度 O(1)
 *  leftChild = parent*2 +1;
 *  rightChild = parent*2+2;
 *  parent = (child-1)/2;
 *
 *  1. 构建堆的过程 (heapInsert or heapify )
 *     heapify的使用条件：    已知 arr[i+1:n]已经是一个大根堆， 通过heapify操作将第i个元素加入到该大根堆中 使之仍为一个大根堆
 *     heapInsert的使用条件： 已知 arr[0:i-1]已经是一个大根堆， 通过heapify操作将第i个元素加入到该大根堆中 使之仍为一个大根堆
 *
 *    heapInsert
 *    3  2  1
 *    |  |  |      O          |
 *    |  |        /\          |
 *    |  |       O O       |  |
 *    |         /\ /\      |  |
 *    |        O O O O  |  |  |
 *                      1  2  3
 *                       heapify
 *
 *  2. 调整堆的过程  heapify
 *
 */
public class HeapSort {

    public void heapSort(int[] arr){

        if (arr==null || arr.length<=1){
            return;
        }

//        for (int i = 0; i <arr.length ; i++) {
//            heapInsert(arr,i); // 构建大根堆：从头结点开始遍历
//        }

        for (int i = 0; i <arr.length ; i++) {
            heapify(arr,arr.length-i-1,arr.length);// 构建大根堆：从叶子结点开始遍历
        }

//        for(int i= (arr.length-1-1)/2;i>=0;i--){ // 从非叶子节点开始 heapify的操作
//            heapify(arr,i,arr.length);
//        }

        // 交换堆顶的元素到数组的尾部位置（该元素在排好序的数组中的最终位置），并重建大根堆
        for (int i = 0; i < arr.length; i++) {
            NumberUtils.swap(arr,0,arr.length-i-1);
            heapify(arr,0,arr.length-i-1);
        }
    }

    /**
     * 从 parent开始，调整以parent为root的堆为大根堆 ，O(N*logN)
     * @param arr
     * @param parentIdx
     * @param heapSizeExclusive  exclusive ：之前已经将heapSize位置的元素swap到了heap[0]，因此heapSize位置不作为堆的一部分了
     */
    private void heapify(int[] arr, int parentIdx, int heapSizeExclusive) {

        int leftChildIdx = parentIdx*2 +1;

        while (leftChildIdx<heapSizeExclusive){

            int maxChildIdx = leftChildIdx+1<heapSizeExclusive && arr[leftChildIdx]<arr[leftChildIdx+1]? leftChildIdx+1:leftChildIdx;
            if (arr[parentIdx]<arr[maxChildIdx]){
                NumberUtils.swap(arr,parentIdx,maxChildIdx);
                parentIdx = maxChildIdx;
                leftChildIdx = parentIdx*2 + 1;
            }else{
                break;
            }
        }
    }

    private void heapInsert(int[] arr, int child) { // O(logN)
        int parent = (child-1)/2;   // 主要此处parent不会越界， 当i为0时， -1/2 等于0 ， 但 >> 位移操作会出现越界的情况
        while (arr[child]>arr[parent]){
            NumberUtils.swap(arr,child,parent);
            child = parent;
            parent =(child-1)/2;
        }
    }

    @Test
    public void test(){
        int[] arr = NumberUtils.generateRandomIntArr();
        System.out.println(Arrays.toString(arr));
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

}

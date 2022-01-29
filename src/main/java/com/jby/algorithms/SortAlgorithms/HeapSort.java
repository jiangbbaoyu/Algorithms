package com.jby.algorithms.SortAlgorithms;

import com.jby.algorithms.Util.NumberUtils;
import org.junit.Test;

import java.util.Arrays;


/**
 *  堆： 本质上是一个完全二叉树， 数据可以存放在数组中
 *  不具有稳定性 时间复杂度O(N*logN) ,空间复杂度 O(1)
 *  leftChild = parent*2 +1;
 *  rightChild = parent*2+2;
 *  parent = (child-1)/2;
 */
public class HeapSort {

    public void heapSort(int[] arr){

        if (arr==null || arr.length<=1){
            return;
        }

        // 数组元素未知，当元素插入到堆底部时通过heapinsert的方式构建大根堆  O(N*logN)
//        for (int i = 0; i <arr.length ; i++) {
//            heapInsert(arr,i);
//        }

        // 数组元素已知， 从低到顶通过heapify的方式构建大根堆  O(N)
        for (int i = 0; i <arr.length ; i++) {
            heapify(arr,arr.length-i-1,arr.length);
        }

        // 交换堆顶的元素到数组的尾部位置（该元素在排好序的数组中的最终位置），并重建大根堆
        for (int i = 0; i < arr.length; i++) {
            NumberUtils.swap(arr,0,arr.length-i-1);
            heapify(arr,0,arr.length-i-1);
        }
    }

    /**
     * 从 parent开始，调整以parent为root的堆为大根堆 ，O(N*logN)
     * @param arr
     * @param parent
     * @param heapSize  exclusive ：之前已经将heapSize位置的元素swap到了heap[0]，因此heapSize位置不作为堆的一部分了
     */
    private void heapify(int[] arr, int parent, int heapSize) {

        int leftChild = parent*2 +1;

        while (leftChild<heapSize){

            int maxChild = leftChild+1<heapSize && arr[leftChild]<arr[leftChild+1]? leftChild+1:leftChild;
            if (arr[parent]<arr[maxChild]){
                NumberUtils.swap(arr,parent,maxChild);
                parent = maxChild;
                leftChild = parent*2 + 1;
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

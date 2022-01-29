package com.jby.algorithms.SortAlgorithms;

import com.jby.algorithms.Util.NumberUtils;
import org.junit.Test;

import java.util.Arrays;

/**
 * 已知一个几乎有序的数组，几乎有序是指，如果把数组排好顺序的话，每个元素移动的距离可以不超过k，并且k相对于数组来说比较小
 * 对这个几乎有序的数组进行 从小到大排序
 */
public class HeapSortQuestion01 {

    /**
     *  使用一个k大小的小根堆进行堆排序， 时间复杂度 O(N*logK)
     * @param arr
     * @param k
     */
    public void sort(int[] arr,int k){

        if (arr==null||arr.length<=1){
            return;
        }

        int heapSize = Math.min(arr.length, k); // 开辟一块额外的数组作为堆
        int[] heap = new int[heapSize];

        int i =0; // add arr element to heap cursor
        for (; i < heapSize; i++) { // 将数组的前k个元素入堆，并构建一个小根堆
            heapInsert(arr,i,heap);
        }


        int j=0; // pull element from heap to arr cursor
        for (; i < arr.length; i++) {
            arr[j++]=heap[0];  //取出小跟堆的一个元素写回到数组的指定位置
            heap[0]=arr[i];   // 将数组的后序元素入堆
            heapify(heap,0,heapSize);  // 调整小根堆
        }

        for (; j < arr.length; j++) {// 当数组没有元素入堆时，将堆中剩余的元素按从小到大的顺序取出，放回数组中
            arr[j] = heap[0]; //
            NumberUtils.swap(heap,0,arr.length-j-1);
            //之前已经将heapSize位置的元素swap到了heap[0]，因此heapSize位置不作为堆的一部分了
            heapify(heap,0,arr.length-j-1);
        }
    }

    private void heapify(int[] heap, int parent, int heapSize) {

        int leftChild =2*parent+1;

        while(leftChild<heapSize){
            int minChild = leftChild+1<heapSize && heap[leftChild+1]<heap[leftChild]? leftChild+1:leftChild;
            if (heap[minChild]<heap[parent]){
                NumberUtils.swap(heap,minChild,parent);
                parent=minChild;
                leftChild = 2*parent+1;
            }else{
                break;
            }
        }
    }

    private void heapInsert(int[] arr, int child,int[] heap) {
        heap[child]= arr[child];
        int parent = (child-1)/2;
        while(heap[parent]>heap[child]){
            NumberUtils.swap(heap,parent,child);
            child=parent;
            parent= (child-1)/2;
        }
    }
    @Test
    public void test(){
//        int[] arr = NumberUtils.generateRandomIntArr(3,1,2,5,4,8,6,7);
        int[] arr = NumberUtils.generateRandomIntArr(3,1,2);
        System.out.println(Arrays.toString(arr));
        sort(arr,4);
        System.out.println(Arrays.toString(arr));
    }
}

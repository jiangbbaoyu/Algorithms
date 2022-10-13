package com.jby.algorithms.JianZhiOffer;

import java.util.PriorityQueue;

/**
 * 剑指 Offer 40. 数组中最小的k个数 (最小的第k个数，思路类似)
 * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4
 */
public class Question40 {

    // 基于 快排的 partition 函数， 循环直到随机选择的元素 在排序后，是位于数组的第k-1个为止
    // 此时 0~k-1个为 最小的k个元素
    public int[] getLeastNumbers(int[] arr, int k) {

        if(arr==null|| arr.length==0 || k==0){
            return new int[0];
        }

        int left =0;
        int right=arr.length-1;
        int randIdx = left + (int)(Math.random()*(right-left+1));

        int[] bounds = partition(arr,left,right,randIdx);

        while (k-1>bounds[1] || k-1<bounds[0]){

            if(k-1>bounds[1]){
                left = bounds[1]+1;
            }

            if(k-1<bounds[0]){
                right = bounds[0]-1;
            }
            randIdx = left + (int)(Math.random()*(right-left+1));
            bounds = partition(arr,left,right,randIdx);
        }

        int[] res = new int[k];
        for(int i=0;i<k;i++){
            res[i] = arr[i];
        }
        return res;

    }

    private int[] partition(int[] arr, int left, int right, int randIdx){

        int target = arr[randIdx];
        int i= left;

        int m=left;
        int n=right;

        while(i<=n){

            if(arr[i]==target){
                i++;
            }else if(arr[i]>target){
                swap(arr,i,n);
                n--;
            }else if(arr[i]<target){
                swap(arr,i,m);
                i++;
                m++;
            }
        }

        return new int[]{m,n};
    }
    private void swap(int[] nums,int i,int j){
        int tmp =nums[i];
        nums[i]=nums[j];
        nums[j]=tmp;
    }

    // 基于 size=k的堆 来实现
    public int[] getLeastNumbers2(int[] arr, int k) {

        if(arr==null|| arr.length==0 || k==0){
            return new int[0];
        }
        // `用大根堆` 保存数组中 `最小的k个数`
        PriorityQueue<Integer>  heap = new PriorityQueue<Integer>((a, b)->b-a);

        for(int i=0;i<arr.length;i++){

            if(heap.size()<k){
                heap.add(arr[i]);
            }else{

                if (heap.peek()>arr[i]){
                    heap.poll();
                    heap.add(arr[i]);
                }
            }
        }
        int[] res =new int[k];
        for(int i=0;i<k;i++){
            res[i]=heap.poll();
        }

        return res;
    }

}

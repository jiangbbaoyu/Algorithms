package com.jby.algorithms.SortAlgorithms;

import com.jby.algorithms.Util.NumberUtils;
import org.junit.Test;

import java.util.Arrays;

public class MergeSort {


    /**
     * 归并排序，时间复杂度 O(N*logN) ;根据master公式，满足 a*logb ==d的场景  ；  空间复杂度O(N)
     * 其核心思想是首先将数组两个连续的局部变为有序，然后将这两个局部有序部分merge为一个整体有序的部分
     *
     * @param arr
     */
    public void mergeSort(int[] arr){

        if (arr==null|| arr.length==1){
            return;
        }

        int left = 0;
        int right = arr.length-1;
        int[] tempArr =new int[arr.length];// 单线程排序，可以保证tempArr的安全使用

        sortPartial(arr,tempArr,left,right);

    }

    private void sortPartial(int[] arr, int[] tempArr,int left, int right) {

        if (left==right){ // 这一部分数组中只有一个元素，因此是有序的
            return;
        }

        int middle = left+ ((right-left)>>1);
        sortPartial(arr,tempArr,left,middle);
        sortPartial(arr,tempArr,middle+1,right);

        // 当右侧和左侧都有序后，将这两个有序的部分合并为一个整体有序的部分
        merge(arr,tempArr,left,middle,right);
    }

    private void merge(int[] arr,int[] tempArr, int left, int middle, int right) {  // O(N)

        int i= left;      // left part cursor
        int j = middle+1; // right part cursor
        int k=left;  // temp arr cursor

        while(i<=middle && j<=right){
            tempArr[k++] = arr[i]<arr[j]?arr[i++]:arr[j++];
        }

        while(i<=middle){
            tempArr[k++] = arr[i++];
        }
        while(j<=right){
            tempArr[k++]=arr[j++];
        }

        for (k = left; k <= right; k++) {  // copy sorted temp array's elements to origin arr
            arr[k] =tempArr[k];
        }
    }

    @Test
    public void test(){
        int[] arr = NumberUtils.generateRandomIntArr();
        System.out.println(Arrays.toString(arr));
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }




}

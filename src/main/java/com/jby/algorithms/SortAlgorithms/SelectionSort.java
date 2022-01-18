package com.jby.algorithms.SortAlgorithms;

import com.jby.algorithms.Util.NumberUtils;
import org.junit.Test;

import java.util.Arrays;

/**
 * 选择排序  O(N*N)
 */
public class SelectionSort {

    public void selectSort(int[] arr){

        if (arr==null || arr.length<=1){
            return;
        }

        for (int i = 0; i < arr.length-1; i++) { // len-1个元素在最终位置后，剩下的最后一个元素也在最终位置了
            int minIndex = i;
            for (int j = i+1; j < arr.length; j++) {// 每一次循环，找出一个最小值，放到index 为i的位置
                if (arr[minIndex]>arr[j]){
                    minIndex = j;
                }
            }
            NumberUtils.swap(arr,i,minIndex);
        }

    }




    @Test
    public void test(){
        int[] arr=NumberUtils.generateRandomIntArr(30);
        selectSort(arr);
        System.out.println(Arrays.toString(arr));
    }

}

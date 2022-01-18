package com.jby.algorithms.SortAlgorithms;

import com.jby.algorithms.Util.NumberUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

/**
 * 冒泡排序 O(N*N)
 */
public class BubbleSort {

    public void bubbleSort(int[] arr){

        if (arr==null || arr.length<2){
            return;
        }

        for (int i = 0; i <arr.length-1; i++) { // 总共循环len-1次
            for (int j = 0; j <arr.length-i-1 ; j++) { //每次从第0个元素开始， 每次循环后确定数组最后一个元素的值,因此 本次内循环 比前一次内循环 元素比较的次数减一
                if (arr[j]>arr[j+1]){
                    NumberUtils.swap(arr,j,j+1);
                }
            }
        }
    }

    @Test
    public void test(){
        int[] arr = NumberUtils.generateRandomIntArr(20);
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}

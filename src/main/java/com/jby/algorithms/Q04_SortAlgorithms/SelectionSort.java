package com.jby.algorithms.Q04_SortAlgorithms;

import com.jby.algorithms.Util.NumberUtils;
import org.junit.Test;

import java.util.Arrays;

/**
 * 选择排序  O(N*N) :
 * 每次选择一个最小值交换到数组的头部； 排除该最小值后，从剩余的数组元素中再次选择一个最小值，交换到头部第二个位置，以此类推
 * 不具有稳定性，在交换时，数组头部的元素 可能会交换到值相同的元素的后面
 *
 *  i   4 3 2 1
 *
 *  0   1 3 2 4
 *  1   1 2 3 4
 *  2   1 2 3 4
 *
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

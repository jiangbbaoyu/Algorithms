package com.jby.algorithms.Q04_SortAlgorithms;


import com.jby.algorithms.Util.NumberUtils;
import org.junit.Test;

import java.util.Arrays;

/**
 *
 * 选择排序根据原始数组中数据分布的不同，性能也不一样，最坏 O(N*N) ,最好 O(N) 。 选择排序总体(最坏)时间复杂度还是O(N*N)
 * 第i次循环，确定0~i 位置的数组元素为有序的；
 * 在第i次循环中，由于 0 ~ i-1 位置的数组元素已经有序了，因此只需要拿第i个位置的数，分别和i-1， i-2 , ...0 位置的数进行比较并交换，如果找到第i个元素的位置则停止该过程
 *  i   4 3 2 1
 *
 *  0   4 3 2 1
 *  1   3 4 2 1
 *  2   2 3 4 1
 *  3   1 2 3 4
 *
 *  具有稳定性，在寻找插入位置时，一个元素不会插入到值等于该元素位置的前面
 */
public class InsertionSort {

    public void insertionSort(int[] arr){

        if (arr==null || arr.length<2){
            return ;
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j > 0 && arr[j-1]>arr[j] ; j--) {// 如果第j个元素前面还有元素 且  第j-1个元素（前面的元素）比第j个元素大，则交换j-1个元素与j个元素的位置； 同时继续向前比较
                NumberUtils.swap(arr,j-1,j);
            }
        }
    }

    @Test
    public void test(){
        int[] arr = NumberUtils.generateRandomIntArr(20);
        insertionSort(arr);
        System.out.println(Arrays.toString(arr));
    }

}

package com.jby.algorithms.Q05_BinarySearchAlgorithms;

import com.jby.algorithms.Util.NumberUtils;
import org.junit.Test;

import java.util.Arrays;

/**
 * 二分查找， O(logN)
 * 二分法能解决四种问题：
 *  （1）有序数组查找某个值是否存在
 *  （2）有序数组查找大于等于某个值的最左侧
 *  （3）有序数组查找小于等于某个值的最右侧
 *  （4）局部最值问题
 *
 */
public class BinarySearch {

    public int binarySearch(int[] arr,int target){
        int left =0;
        int right = arr.length-1;

        // 考虑到二分到数组只剩一个元素的情况，此处要加上等于号。
        // 否则如果此时left+1==right, 且数组中right位置的值正好是target的话。
        // 本轮计算出的middle值为left, 而arr[middle]<target; 下一轮中left就赋值为middle+1 ,与right值一致。如果没有等于号的话，就会导致错过target在right位置的情况
        while (left<=right){
//            int middle = (left + right) / 2; // 当数组元素很大时，left+right可能会导致int类型数据溢出
            int middle = left + ((right-left)>>1);  //防止溢出
            if (arr[middle]==target){
                return middle;
            }
            if (arr[middle]>target){
                right=middle-1;
            }
            if (arr[middle]<target){
                left =middle+1;
            }
        }
        return -1;
    }


    @Test
    public void test(){
//        int[] arr = NumberUtils.generateRandomIntArr(7,10);
        int[] arr = NumberUtils.generateRandomIntArr(1, 1, 2, 3, 4, 4, 5, 5, 6, 7, 7, 7, 8, 8, 8, 8, 8, 9, 9, 9);
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println(binarySearch(arr,3));

    }


}

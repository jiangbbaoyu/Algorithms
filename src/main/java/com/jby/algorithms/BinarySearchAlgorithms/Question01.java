package com.jby.algorithms.BinarySearchAlgorithms;

import com.jby.algorithms.Util.NumberUtils;
import org.junit.Test;

import java.util.Arrays;


public class Question01 {

    /**
     * 在一个有序数组中，给定一个数target , 定位数组中大于等于target 的最左侧的元素的下标; 要求时间复杂度O(logN ) ,只要时间复杂度为O(logN),要联想到二分查找
     * 不是找到大于等于target的值就停止，因为此时仍然无法确定找到的值其左侧是否仍然有满足条件的其他值。 此时应该一直二分，直到left,right两个下标重合； arr[middle]==target不再是终止条件
     */
    public int binarySearchMostLeft(int[] arr,int target){
        int left =0;
        int right = arr.length-1;

        int mostLeft =-1;

        while (left<=right){// 考虑到二分到数组只剩一个元素的情况，此处要加上等于号。
            int middle = (left+right)/2;
            if (arr[middle]>=target){ // 当找到等于target的值后，继续向左找
                System.out.println("mostLeft:"+mostLeft);
                mostLeft=middle; // 记录当前找到的大于等于target的值，
                right=middle-1;
            }
            if (arr[middle]<target){
                left=middle+1;
            }
        }

        return mostLeft;
    }

    /**
     * 在一个有序数组中，给定一个数target , 定位数组中小于等于target 的最右侧的元素的下标
     */
    public int binarySearchMostRight(int[] arr,int target){
        int left =0;
        int right = arr.length-1;

        int mostRight =-1;

        while (left<=right){// 考虑到二分到数组只剩一个元素的情况，此处要加上等于号。
            int middle = (left+right)/2;
            if (arr[middle]>target){
                right=middle-1;
            }
            if (arr[middle]<=target){ // 当找到等于target的值后，继续向右找
                mostRight=middle; // 记录当前找到的大于等于target的值，
                left=middle+1;
            }
        }

        return mostRight;
    }


    @Test
    public void test(){
//        int[] arr = NumberUtils.generateRandomIntArr(20,10);
//        int[] arr = NumberUtils.generateRandomIntArr(1, 1, 2, 3, 4, 4, 5, 5, 6, 7, 7, 7, 8, 8, 8, 8, 8, 9, 9, 9);
        int[] arr = NumberUtils.generateRandomIntArr(3,3,3,3,3);
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println(binarySearchMostLeft(arr,3));
    }

    @Test
    public void test2(){
        int[] arr = NumberUtils.generateRandomIntArr(20,10);
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println(binarySearchMostRight(arr,3));
    }
}

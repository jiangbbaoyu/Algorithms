package com.jby.algorithms.Util;

import java.util.Random;

public class NumberUtils {
    /**
     * 三种方式交换数组不同位置的两个元素
     * @param arr
     * @param i
     * @param j
     */
    public static void swap(int[] arr, int i, int j) {

//        int temp = arr[i];
//        arr[i] =arr[j];
//        arr[j]=temp;

        if (i!=j){ // 不使用额外的存储空间交换数组两个不同位置的元素 （i,j 不能指向同一个数组元素）
//            arr[i] = arr[i]+arr[j];
//            arr[j]=arr[i]-arr[j];
//            arr[i]=arr[i]-arr[j];

            // 使用异或
            arr[i]=arr[i]^arr[j];
            arr[j]=arr[i]^arr[j];
            arr[i]=arr[i]^arr[j];
        }

    }



    public static int[] generateRandomIntArr(){
        int randomLen = new Random().nextInt(20);
        return generateRandomIntArr(randomLen);
    }

    public static int[] generateRandomIntArr(int len){
        return generateRandomIntArr(len,100);
    }
    public static int[] generateRandomIntArr(int len,int limit){
        Random random = new Random();
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = random.nextInt(limit);
        }
        return arr;
    }
    public static int[] generateRandomIntArr(Integer ... nums){
        int[] arr = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            arr[i]=nums[i];
        }
        return arr;
    }


}

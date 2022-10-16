package com.jby.algorithms.Q04_SortAlgorithms;

import com.jby.algorithms.Util.NumberUtils;
import org.junit.Test;

import java.util.Arrays;

public class NoneCompareSort {

    /**
     * 计数排序： 对于数值范围不大的场景，例如年龄。 可以统计数值范围中每个数字出现的次数，然后根据每个数字出现的次数计算最终的排序数组
     * 时间复杂度 O(N)
     * @param arr
     */
    public void countSort(int[] arr,int limit){

        int[] counts = new int[limit];
        for (int i = 0; i <arr.length ; i++) {
            counts[arr[i]]+=1; // 统计arr中每个数出现的次数
        }

        int current=0;
        for (int i = 0; i <counts.length ; i++) {
            int count = counts[i];
            while (count>0){
                arr[current++]=i;
                count--;
            }
        }
    }


    /**
     * 基数排序： 取出数组中最大的数并计算其位数.对于其他数,高位补零
     * 然后按个位数排序，十位数排序，依次进行... (优先级高位的放在后面)
     * 时间复杂度 O(N*Digits)  Digits为数组元素的最大位数 ，空间复杂度 O(N)
     * 具有稳定性
     * @param arr
     */
    public void radixSort(int[] arr){

        if (arr==null || arr.length<=1){
            return;
        }


        int digits = maxNumDigits(arr);
        int radix =10;
        int[] counts =new int[radix];
        int[] temp = new int[arr.length];

        for (int d = 0; d <digits ; d++) {// 从低位开始，对数组中的每个数 按该位的数的大小进行排序,d=0表示个位

            for (int i = 0; i <arr.length ; i++) {
                int digitNum = getSpecifiedDigitNum(arr[i], d); // 对于特定的位， 统计数组中该位为digitNum的元素的次数
                counts[digitNum]+=1;
            }

            for (int i = 1; i <= counts.length-1; i++) {
                counts[i]+=counts[i-1];  // 转换为 前缀和数组， 此时counts[i]表示，对于特定的位， 该位小于等于i的有counts[i]个元素
            }

            for (int i = 0; i <arr.length ; i++) { //根据第d位的值对数组排序，并将排序结果写到temp中
                // 此处要从后往前遍历 **
                int digitNum = getSpecifiedDigitNum(arr[arr.length-i-1], d);
                int idx = counts[digitNum]-1; // 可以根据包含前缀和的counts直接计算出一个元素在temp中的下标
                temp[idx]=arr[arr.length-i-1];
                /**
                 * 更新counts中的下标，下一个该位的digitNum与该数一致的元素，在temp中会写入到该元素的前面
                 * eg:          8 12 15 14
                 *  个位排序结果  12 14 15 8
                 *  十位排序结果  8  15 14 12  （从前到后遍历，如果十位一样，不能保持个位的排序结果）
                 *  十位排序结果  8  12 14 15  （从后到前遍历，如果十位一样，可以保持个位的排序结果）
                 */
                counts[digitNum]-=1;
            }

//            System.out.println(Arrays.toString(res));

            for (int i = 0; i <temp.length ; i++) {
                arr[i] = temp[i]; // 将按特定位进行排序的结果写回数组
            }

            for (int i = 1; i <= counts.length-1; i++) {
                counts[i] = 0;  // reset counts
            }
        }
    }

    private int getSpecifiedDigitNum(int i, int d) {
        while (d>0){
            i/=10;
            d--;
        }
        return i%10;
    }

    private int maxNumDigits(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i <arr.length ; i++) {
            if (arr[i]>max){
                max = arr[i];
            }
        }

        int digits =0;
        while (max>0){
            digits+=1;
            max/=10;
        }
        return digits;
    }


    @Test
    public void test(){
        int[] arr = NumberUtils.generateRandomIntArr(10,10);
        System.out.println(Arrays.toString(arr));
        countSort(arr,10);
        System.out.println(Arrays.toString(arr));
    }


    @Test
    public void test2(){
        int[] arr = NumberUtils.generateRandomIntArr(10,1100);
//        int[] arr = NumberUtils.generateRandomIntArr(87, 106, 10, 55, 107, 29, 53, 40, 80, 105);
        System.out.println(Arrays.toString(arr));
        radixSort(arr);
        System.out.println(Arrays.toString(arr));
    }


}

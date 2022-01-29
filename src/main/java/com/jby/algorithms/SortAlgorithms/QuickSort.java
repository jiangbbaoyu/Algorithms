package com.jby.algorithms.SortAlgorithms;


import com.jby.algorithms.Util.NumberUtils;
import org.junit.Test;

import java.util.Arrays;

import static com.jby.algorithms.Util.NumberUtils.swap;

/**
 * V1,V2 最坏时间复杂度 O(N*N) ,eg: 数组是从小到大的， 要求从大到小排序
 * V3 没有最坏时间复杂度， 整体是一个O(N*logN)的实际复杂度 ; 递归使用的额外空间复杂度 O(logN)
 * 不具有稳定性
 */
public class QuickSort {

    public void quickSort(int[] arr){
        if (arr==null || arr.length<=1){
            return;
        }

        doQuickSort(arr,0,arr.length-1);
    }

    // 每次函数调用确定target 在排好序的数组中的最终位置
    private void doQuickSort(int[] arr, int left, int right) {
        if (right>left){ // 当该部分大于1个元素时才可以继续对该部分排序，只有一个元素时，该部分已经是排好序的了
            // 从left~right中随机抽取一个数作为target,避免最坏情况的O(N*N) 时间复杂度
            int randIdx = (int)(left + Math.random()*(right-left+1));  // Math.random() generate a double located in [0,1)

            int[] bounds = partition(arr, left, right, randIdx);
            doQuickSort(arr,left,bounds[0]-1);
            doQuickSort(arr,bounds[1]+1,right);
        }
    }

    /**
     * 该M方法将 left~ right分为三部分，[left,m） : 小于target的部分； [m,n] : 等于target的部分； [n,right] :  大于target的部分
     * 返回 等于target部分的左边界和右边界
     * @param arr
     * @param left
     * @param right
     * @param randIdx
     * @return
     */
    private int[] partition(int[] arr, int left, int right, int randIdx) {

        int target = arr[randIdx];

        int i=left;
        int m =left;
        int n= right;

        while(i<=n){
            if (arr[i]==target){
                i++;
            }else if (arr[i]<target){
                swap(arr,i,m);
                m++;
                i++;
            }else if (arr[i]>target){
                swap(arr,i,n);
                n--;
            }
        }
        return new int[]{m,n};
    }


    @Test
    public void test(){
        int[] arr = NumberUtils.generateRandomIntArr();
        System.out.println(Arrays.toString(arr));
        quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}

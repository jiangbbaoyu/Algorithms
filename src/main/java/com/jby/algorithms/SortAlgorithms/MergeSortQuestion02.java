package com.jby.algorithms.SortAlgorithms;

import com.jby.algorithms.Util.NumberUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * leetcode 315 https://leetcode-cn.com/problems/count-of-smaller-numbers-after-self/   (hard)
 * 计算右侧小于当前元素的数组元素个数： 给你一个整数数组 nums ，按要求返回一个新数组 counts 。数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量
 */
public class MergeSortQuestion02 {

    /**
     * 解法1
     * 采用归并排序，从大排到小，在合并左右两部分有序的元素时，遇到左侧元素大于右侧元素可以直接计算右侧中小于当前左侧值的元素数量。
     * 在归并排序的时候数组中的元素位置会发生变化，同一个数组位置中的值，在排序过程中会变更多次
     * 可以用一个 int[] pos数组：来记录当前数组中元素在原来初始数组中的位置，eg pos[i]=j表示当前数组中的第i个数一开始在数组的第j个位置； 当数组中某个元素位置发生变化时，会同时更新pos数组
     * pos数组的更新与nums数组一致，也要再用一个临时数组
     * @param nums
     * @return
     */
    public List<Integer> countSmaller(int[] nums) {

        int[] res = new int[nums.length];
        // 定义记录 当前数组中每个元素的位置 与该元素 在原来初始数组中的位置 的对应关系的pos数组； 并初始化
        int[] pos = new int[nums.length];
        int[] tempPos = new int[nums.length];
        for (int i = 0; i <nums.length ; i++) {
            pos[i]=i;
            tempPos[i]=i;
        }


        int[] tempArr =new int[nums.length];
        countRightSmallerNum(nums,tempArr,pos,tempPos,res,0,nums.length-1);

        List<Integer> result = new ArrayList<Integer>();
        for(int i=0; i<nums.length;i++){
            result.add(res[i]);
        }

        return result;
    }

    private void countRightSmallerNum(int[] nums, int[] tempArr,int[] pos, int[] tempPos,int[] res,int left, int right){
        if(left ==right){
            return;
        }

        int middle = left +((right - left)>>1);

        countRightSmallerNum(nums,tempArr,pos,tempPos,res,left,middle);
        countRightSmallerNum(nums,tempArr,pos,tempPos,res,middle+1,right);

        merge(nums,tempArr,pos,tempPos,res,left,middle,right);
    }

    private void merge(int[] nums, int[] tempArr,int[] pos, int[] tempPos,int[] res,int left, int middle,int right){

        int i =left;
        int j= middle+1;
        int k= left;

        while(i<=middle && j<=right){
            if(nums[i]>nums[j]){
                res[pos[i]]+=(right-j+1); // 根据当前元素在初始数组中的位置，更新找到的右侧较小的数的数量

                tempPos[k]=pos[i]; // 记录元素在当前数组中的位置的变化
                tempArr[k++]=nums[i++];
            }else{
                tempPos[k]=pos[j];
                tempArr[k++]=nums[j++];
            }
        }

        while(i<=middle){
            tempPos[k]=pos[i];
            tempArr[k++]=nums[i++];
        }

        while(j<=right){
            tempPos[k]=pos[j];
            tempArr[k++]=nums[j++];
        }

        for(k=left;k<=right;k++){
            nums[k]=tempArr[k];
            pos[k]=tempPos[k];
        }

    }


    /**
     * 解法2； 插入排序 ，由于时间复杂度为O(N*N)，因此会超出时间限制，即使使用了二分查找优化后的插入排序
     */
    public List<Integer> countSmaller2(int[] nums) {
        int[] res = new int[nums.length];

        reverse(nums);

        // for(int i=0;i<=nums.length-1;i++){
        //     int count =0;
        //     for(int j=i;j>0 && nums[j] > nums[j-1];j--){
        //         swap(nums,j,j-1);
        //         count++;
        //     }
        //     res[i] = count;
        // }

        for(int i=0;i<=nums.length-1;i++){
            int left =0;
            int right =i-1;
            int target = nums[i];

            int targetIndex =i;
            while (left<=right){

                int middle =left+((right-left)>>1);

                if(nums[middle]<target){
                    targetIndex=middle; // 插入位置为  最左侧的小于target的元素的位置
                    right=middle-1;
                }
                if(nums[middle]>=target){
                    left = middle+1;
                }
            }

            res[i]=i-targetIndex;

            for(int k=i;k>targetIndex;k--){
                nums[k]=nums[k-1];
            }
            nums[targetIndex]=target;

        }

        reverse(res);

        List<Integer> result =new ArrayList<Integer>(res.length);
        for(int i=0;i<res.length;i++){
            result.add(res[i]);
        }
        return result;
    }

    private void reverse(int[] arr){
        for(int i=0 ;i<arr.length/2;i++){
            swap(arr,i,arr.length-i-1);
        }

    }

    private void swap(int[] arr,int i,int j){
        arr[i]=arr[i]^arr[j];
        arr[j]=arr[i]^arr[j];
        arr[i]=arr[i]^arr[j];

    }

    /**
     *  TODO 其他解法： 树状数组， 二叉搜索数， 线段数
     */


    @Test
    public void test(){

//        int[] arr = NumberUtils.generateRandomIntArr(5, 2, 6, 1);
        int[] arr = NumberUtils.generateRandomIntArr(1,9,7,8,5);
        System.out.println(Arrays.toString(arr));
        List<Integer> res = countSmaller(arr);
        System.out.println(res);
    }
    @Test
    public void test2(){

        int[] arr = NumberUtils.generateRandomIntArr(5, 2, 6, 1);
//        int[] arr = NumberUtils.generateRandomIntArr(1,9,7,8,5);
        System.out.println(Arrays.toString(arr));
        List<Integer> res = countSmaller2(arr);
        System.out.println(res);
    }
}

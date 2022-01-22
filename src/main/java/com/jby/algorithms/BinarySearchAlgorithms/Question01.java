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
//            int middle = (left+right)/2;
            int middle = left + ((right-left)>>1);  //防止溢出
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


    /**
     * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值 在数组中的开始位置和结束位置。如果数组中不存在目标值 target，返回 [-1, -1]
     * leetcode 34 : https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array  (leetcode 2246与之一个思路 )
     */

    /**
     *  解法1 ，时间复杂度最坏O(N) ，数组元素全部相同的情况下
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {

        int[] res=new int[]{-1,-1};
        if(nums==null|| nums.length==0){
            return res;
        }

        int left =0;
        int right=nums.length-1;
        while(left<=right){// 注意等于号
//            int middle=(left+right)/2;
            int middle = left + ((right-left)>>1);  //防止溢出
            if (nums[middle]==target){ //定位到target后，开始确定该元素第一次出现和最后一次出现的位置 (左右滑动指针)
                res[0]=middle;
                res[1]=middle;
                while(res[0]-1>=0&&nums[res[0]-1]==target){
                    res[0]=res[0]-1;
                }
                while(res[1]+1<=nums.length-1&&nums[res[1]+1]==target){
                    res[1]=res[1]+1;
                }
                return res;

            }else if(nums[middle]<target){
                left=middle+1;
            }else if(nums[middle]>target){
                right=middle-1;
            }

        }
        return res;
    }

    /**
     *  解法2， 时间复杂度 O(logN)
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange2(int[] nums, int target) {

        int[] res=new int[]{-1,-1};
        if(nums==null|| nums.length==0){
            return res;
        }

        res[0]=searchBoundary(nums,target,0);
        res[1]=searchBoundary(nums,target,1);

        return res;

    }
    private int searchBoundary(int[] nums, int target,int side){

        int left =0;
        int right=nums.length-1;
        int boundary =-1;

        while(left<=right){// 注意等于号
//            int middle=(left+right)/2;
            int middle = left + ((right-left)>>1);  //防止溢出

            if(nums[middle]<target){
                left =middle+1;
            }else if(nums[middle]>target){
                right=middle-1;
            }else{ // nums[middle]==target
                boundary = middle;
                if(side == 0){  // find most left
                    right =middle-1;
                }else if (side==1){// find most right
                    left =middle+1;
                }
            }

        }
        return boundary;
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

    @Test
    public void test3(){
        int[] arr = NumberUtils.generateRandomIntArr(20,10);
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(searchRange2(arr,3)));
    }
}

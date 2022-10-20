package com.jby.algorithms.Q05_BinarySearchAlgorithms;

import org.junit.Test;

public class Question05_sqrt {
    /**
     * leetcode 69. x 的平方根
     * 给你一个非负整数 x ，计算并返回 x 的 算术平方根 。
     * 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
     * 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5
     *
     * 思路： 二分查找 mid,使得mid*mid = x
     */
    public int mySqrt(int x) {
        if(x==0 || x==1){
            return x;
        }
        int left =1;
        int right =x;
        while(left<=right){
            int mid = left+ (right-left)/2;
//            long product = mid*mid;
            long divide = x/mid;  // 防止溢出
            if(divide==mid){
                return mid;
            }else if(divide>mid){
                left = mid+1;
            }else if(divide<mid){
                right = mid-1;
            }
        }
        return right;  // 此时 left> right, right指向的元素的平方 最接近x

    }

    @Test
    public void test(){
        int res = mySqrt(2147395599);
//        int res = mySqrt(199);
        System.out.println(res);
    }
}

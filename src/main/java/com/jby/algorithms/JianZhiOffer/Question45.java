package com.jby.algorithms.JianZhiOffer;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 剑指 Offer 45. 把数组排成最小的数
 * 输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个
 *
 */
public class Question45 {
    public String minNumber(int[] nums) {

        if(nums==null || nums.length==0){
            return "";
        }

        String[] strs = new String[nums.length];
        for(int i=0;i<nums.length;i++){
            strs[i]= String.valueOf(nums[i]);
        }

        // int[],long[] 等基本类型数组不能 使用Comparator， 对应的包装类可以
        Arrays.sort(strs,new Comparator<String>(){
            public int compare(String a,String b){  // Comparable interface
                return (a+b).compareTo(b+a);
            }
        });


        StringBuilder sb= new StringBuilder();
        for(int i=0;i<strs.length;i++){
            sb.append(strs[i]);
        }
        return sb.toString();
    }

}

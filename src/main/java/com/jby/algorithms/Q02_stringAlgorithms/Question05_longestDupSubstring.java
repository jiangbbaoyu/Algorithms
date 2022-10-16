package com.jby.algorithms.Q02_stringAlgorithms;

import org.junit.Test;

public class Question05_longestDupSubstring {

    /**
     * leetcode 1044. 最长重复子串 (hard)
     * 给你一个字符串 s ，考虑其所有 重复子串 ：即 s 的（连续）子串，在 s 中出现 2 次或更多次。这些出现之间可能存在重叠。
     * 返回 任意一个 可能具有最长长度的重复子串。如果 s 不含重复子串，那么答案为 ""
     *
     * 输入：s = "banana"  // 前一个ana 和后一个ana存在重叠
     * 输出："ana"
     *
     * 输入：s = "abcdefghiabcd"
     * 输出："abcd"
     *
     */

    // 法1:   双指针表示 子字符串 第一次出现的位置，然后到 (left,s.length-1]中判断该子字符串是否再次出现，
    //       如果出现在记录当前的子字符串（s[left,right]）
    //       时间复杂度 O(N^3) 超时
    public String longestDupSubstring(String s) {

        String res = "";
        int maxLen =0;

        int left =0;
        int right=0;
        while(right<s.length()-1){
            String subStr = s.substring(left,right+1);
            if(findRepeat(s,subStr,left+1)){
                if(right-left+1 > maxLen){
                    maxLen = right-left+1;
                    res = subStr;
                }
                right ++;
            }else{
                left ++;
                right =left;
            }
        }

        return res;
    }

    private boolean findRepeat(String s,String subStr,int startIdx){
        return s.substring(startIdx).contains(subStr);
    }

}

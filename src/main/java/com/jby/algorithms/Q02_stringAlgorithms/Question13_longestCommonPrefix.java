package com.jby.algorithms.Q02_stringAlgorithms;

public class Question13_longestCommonPrefix {
    /**
     * leetcode 14. 最长公共前缀
     * 编写一个函数来查找字符串数组中的最长公共前缀。
     * 如果不存在公共前缀，返回空字符串 ""。
     * 示例 1：
     * 输入：strs = ["flower","flow","flight"]
     * 输出："fl"
     */
    public String longestCommonPrefix(String[] strs) {
        if(strs==null|| strs.length==0){ return ""; }

        for(int i=0;i<strs[0].length();i++){
            char c=strs[0].charAt(i);
            for(int j=1;j<strs.length;j++){
                //针对str0中的每个字符
                //遍历除了str0之外的所有str,
                //如果发现比str0短的字符串，or 如果存在str的一个字符，与str0中相同位置的字符不一致
                //则从str0中返回 该字符之前的字符串作为公共前缀
                if(i>=strs[j].length()||strs[j].charAt(i)!=c){ //这两个条件的位置不能变
                    return strs[0].substring(0,i); //i is exclusive
                }
            }
        }
        return strs[0];
    }
}

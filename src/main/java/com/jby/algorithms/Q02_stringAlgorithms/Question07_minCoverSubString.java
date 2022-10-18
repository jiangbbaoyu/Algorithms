package com.jby.algorithms.Q02_stringAlgorithms;

import java.util.HashMap;

public class Question07_minCoverSubString {
    /**
     * leetcode 76. 最小覆盖子串
     * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。
     * 如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
     * 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须 `不少于`  t中该字符数量
     * 如果 s 中存在这样的子串，我们保证它是唯一的答案
     *
     * 思路： 使用滑动窗口 + hashMap
     */
    public String minWindow(String s, String t) {
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();

        HashMap<Character,Integer> tMap = new HashMap<>();// 统计字符串t中每个字符出现的频率
        for(int i=0;i<tChars.length;i++){
            tMap.put(tChars[i],tMap.getOrDefault(tChars[i],0)+1);
        }
        // 当前发现的最小覆盖子串 的索引范围
        int minLeft=0;
        int minRight =sChars.length-1;
        boolean found = false;

        HashMap<Character,Integer> sMap = new HashMap<>(); // 统计当前 滑动窗口s[left,right]中每个字符出现的频率
        int left =0;
        int count=0; // 统计当前滑动窗口中的字符，匹配字符串t中的字符个数
        for(int right=0;right<sChars.length;right++){
            sMap.put(sChars[right],sMap.getOrDefault(sChars[right],0)+1); // 更新 右边界字符 在sMap中的频率

            if(sMap.get(sChars[right])<=tMap.getOrDefault(sChars[right],-1)) count++; // 右边界字符 包含在 t中

            // 如果 左边界字符是 不需要的，窗口的左边界右移
            while(left<=right && sMap.get(sChars[left]) > tMap.getOrDefault(sChars[left],0)){
                sMap.put(sChars[left],sMap.get(sChars[left])-1);
                left ++;
            }

            if(count == tChars.length){ // 当前滑动窗口包含了t中的所有字符
                found =true;
                if(right-left<minRight-minLeft){ // 如果当前滑动窗口表示的字符串更短，则更新 当前发现的最小覆盖子串
                    minRight = right;
                    minLeft = left;
                }
            }
        }
        if(!found) return "";
        return s.substring(minLeft,minRight+1);
    }
}

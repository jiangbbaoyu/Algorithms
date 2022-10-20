package com.jby.algorithms.Q02_stringAlgorithms;

import java.util.HashMap;

public class Question02_longestUniqueSubstr {
    /**
     * leetcode 3. 无重复字符的最长子串
     *    给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度
     *    思路 ： 滑动窗口 + hash map
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        // 使用 map 保存 每个字符到 该字符在string中的下标位置
        HashMap<Character,Integer> charToIdxMap = new HashMap<>();
        int maxLen =0;
        char[] chars = s.toCharArray();
        int left =0,right = 0; // 滑动窗口，  left, right 只能向右走
        while(right < chars.length){
            // 如果当前字符 chars[right] 已经在 [left,right)间存在了，则更新 left的值为 oldCharIdx+1;
            if(charToIdxMap.containsKey(chars[right])){
                int oldCharIdx = charToIdxMap.get(chars[right]);
                if(oldCharIdx >= left){
                    left = oldCharIdx+1;
                }
            }
            // 将当前字符加入到map中（或更新该字符的 idx）
            charToIdxMap.put(chars[right],right);
            maxLen = Math.max((right-left+1),maxLen); // 更新 maxLen

            right++; // 移动窗口右边界
        }
        return maxLen;
    }
}

package com.jby.algorithms.Q00_JianZhiOffer;

import org.junit.Test;

import java.util.HashMap;

/**
 * 剑指 Offer 48. 最长不含重复字符的子字符串
 * 请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
 * 思路：
 *       f(i) =f(i-1) , 第i个字符第一次出现
 *          f(i) = f(i-1) , 第i个字符不出现在 当前包含非重复字符的字符串中
 *          f(i) = i- lastOccurPosOf(s[i]), 第i个字符出现在 当前包含非重复字符的字符串中
 */
public class Question48 {

    public int lengthOfLongestSubstring(String s) {
        if(s == null || s.length()==0){
            return 0;
        }

        int maxLen =0;
        int curLen =0;
        int[] occurPosition = new int[26];  // 输入字符中只有 'a'~'z' ,使用一个数组记录 一个字符在s中出现的位置
        for(int i=0;i<occurPosition.length;i++){
            occurPosition[i] = -1;
        }

        for(int i=0;i<s.length();i++){

            char curCh = s.charAt(i);
            int prevPos = occurPosition[curCh-'a'];
            if(prevPos<0){
                curLen++;  // 1. curCh第一次遇到
            }else if(prevPos<i-curLen){
                curLen++;  // 2.2  curCh之前出现过，且 当前包含非重复字符的字符串中  不包含curCh
            }else{ // prevPos>=i-curLen
                curLen = i-prevPos; // 2.1 curCh之前出现过，且 当前包含非重复字符的字符串中  包含curCh
            }
            if(maxLen<curLen){
                maxLen = curLen;
            }

            occurPosition[curCh-'a']=i;
        }

        return maxLen;
    }

    // 输入除了 'a'~'z', 还有其他字符的场景
    /**
     * leetcode 3. 无重复字符的最长子串  （leetcode 3 ）
     *    给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度
     *    思路 ： 滑动窗口 + hash map
     * @return
     */
    public int lengthOfLongestSubstring2(String s) {
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

    @Test
    public void test(){
        System.out.println((char)0);
    }
}

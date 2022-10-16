package com.jby.algorithms.Q02_stringAlgorithms;

import org.junit.Test;

public class Question03 {
    /**
     * leetcode 5. 最长回文子串
     * 给你一个字符串 s，找到 s 中最长的回文子串。
     * 输入：s = "babad"
     * 输出："bab". "aba" 同样是符合题意的答案
     *
     * 思路： 回文串指的是 正读和反读是一样的 的字符串
     *       中心扩展法 ：回文串一定是对称的，所以我们可以每次循环选择一个中心，进行左右扩展，判断左右字符是否相等即可
     *       由于存在奇数的回文字符串和偶数的回文字符串，所以我们需要`从一个字符`开始扩展，或者`从两个字符之间`开始扩展，
     *       所以总共有 n + n - 1 个中心， 时间复杂度：O（n²）；空间复杂度：O（1）
     */
    public String longestPalindrome(String s) {

        String res ="";
        // 记录目前最长回文字符串的 起始字符位置 和 终止字符位置
        int left =0;
        int right =0;
        char[] chars = s.toCharArray();
        for(int i=0;i<chars.length;i++){
            // 计算以 chars[i]为中心点向两边扩散 的回文字符串
            int[] bounds1 = expand(chars,i,i);
            // 计算以 chars[i]，chars[i+1]为中心 点向两边扩散 的回文字符串
            int[] bounds2 = expand(chars,i,i+1);

            // 更新目前最长回文字符串的 起始字符位置 和 终止字符位置
            if(bounds1[1]-bounds1[0] > right-left){
                left = bounds1[0];
                right = bounds1[1];
            }
            if(bounds2[1]-bounds2[0] > right-left){
                left = bounds2[0];
                right = bounds2[1];
            }
        }

        return s.substring(left,right+1);
    }

    private int[] expand(char[] chars, int left, int right){
        while(left>=0 && right<=chars.length-1){
            if(chars[left] == chars[right]){ // chars[left,right]是回文串时，继续向两边扩散
                left--;
                right++;
            }else{
                break;
            }
        }
        // 循环结束时的条件是 chars[left]!= chars[right],
        // 所以正确的区间为 [left + 1, right-1]
        return new int[]{left+1,right-1};
    }

    @Test
    public void test(){
        String aa = longestPalindrome("aa");
    }
}

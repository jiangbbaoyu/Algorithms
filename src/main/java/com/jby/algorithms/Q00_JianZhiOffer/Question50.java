package com.jby.algorithms.Q00_JianZhiOffer;

/**
 * 剑指 Offer 50. 第一个只出现一次的字符
 * 在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s中可能包含ASIIC 编码的256个字符
 *
 * 思路： 使用数组模拟简单的hashTable ； 第二次遍历字符串时 ，从ht中找到 第一个出现次数为1的字符
 */
public class Question50 {
    public char firstUniqChar(String s) {
        char res = ' ';
        if(s==null || s.length()==0){
            return res;
        }

        int[] hashTable = new int[256];

        for(int i=0;i<s.length();i++){
            char cur = s.charAt(i);
            hashTable[(int)cur]+=1;
        }

        for(int i=0;i<s.length();i++){
            char cur = s.charAt(i);
            if(hashTable[(int)cur]==1){
                return cur;
            }
        }

        return res;
    }
}

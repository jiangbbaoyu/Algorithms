package com.jby.algorithms.JianZhiOffer;

/**
 * 剑指 Offer 05. 替换空格
 * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"
 * 思路： 首先遍历字符串，计算有几个空格， 然后根据空格数分配一个更大的字符数组
 *       从后往前遍历原来的字符串，遇到非空格，则原样放入到新字符数组中；遇到空格则向新数组中添加 %20 三个字符  （双指针）
 */
public class Question05 {

    public String replaceSpace(String s) {

        char[] chars = s.toCharArray();
        int spaceNum=0;
        for(int i=0;i<chars.length;i++){
            if (' '==chars[i]){
                spaceNum++;
            }
        }

        char[] newChars = new char[chars.length+2*spaceNum];// 注意剑指offer题目中要求的在原来字符数组空间中进行替换， 与leetcode 要求不同
        int idx1 =chars.length-1;
        int idx2 = newChars.length-1;

        while(idx1>=0){
            if(chars[idx1]!=' '){
                newChars[idx2--]=chars[idx1--];
            }else{
                newChars[idx2--]='0';
                newChars[idx2--]='2';
                newChars[idx2--]='%';
                idx1--;
            }
        }

        return new String(newChars);
    }
}

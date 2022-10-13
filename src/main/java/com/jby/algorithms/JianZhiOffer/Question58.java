package com.jby.algorithms.JianZhiOffer;

import org.junit.Test;

/**
 * 剑指 Offer 58 - I. 翻转单词顺序
 * 输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。为简单起见，标点符号和普通字母一样处理。
 * 例如输入字符串"I am a student. "，则输出"student. a am I"。
 * 输入字符串可以在前面或者后面包含多个多余的空格，但是反转后的字符不能包括； 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个
 */
public class Question58 {
    public String reverseWords(String s) {
        if(s==null) {
            return null;
        }
        s = s.trim();
        if(s.length()==0){
            return s;
        }

        char[] chars = s.toCharArray();

        // 1. 除去多余的空格
        StringBuffer sb = new StringBuffer();
        boolean prevSpace = true;
        for(int i=0;i<chars.length;i++){
            if ((chars[i]==' ' &&  !prevSpace)  ||chars[i]!=' '){
                sb.append(chars[i]);
            }
            prevSpace = chars[i]==' '? true:false;
        }

        // 2. 整个字符串反转
        chars = sb.toString().toCharArray();
        reverse(chars,0,chars.length-1);

        // 3. 每个单词反转 （滑动窗口）
        int left=0;
        int right=0;
        while (right <= chars.length) {
            // reverse last word
            if (right == chars.length) {
                reverse(chars, left, right - 1);
                break;
            }

            if (chars[right] != ' ') {
                right++;
            } else if (chars[right] == ' ') {
                reverse(chars, left, right - 1);
                right = right + 1;
                left = right;
            }
        }

        return new String(chars);
    }

    private void reverse(char[] chars ,int left,int right){
        while(left<right){
            char tmp= chars[left];
            chars[left] = chars[right];
            chars[right] =tmp;

            left++;
            right--;
        }

    }


    /**
     * 剑指 Offer 58 - II. 左旋转字符串
     * 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。
     * 比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"
     *
     * 思路：基于字符串反转的思想，将字符串的前面部分和后面部分分别反转
     */
    public String reverseLeftWords(String s, int n) {

        if(s==null || n<0 || s.length()<n){
            throw new RuntimeException("invalid params");
        }

        char[] chars = s.toCharArray();

        // 先分别反转前后两部分，再反转整个字符串
        reverse(chars,0,n-1);
        reverse(chars,n,s.length()-1);
        reverse(chars,0,s.length()-1);


        // 先反转整个字符串，再分别反转前后两部分
        // reverse(chars,0,s.length()-1);
        // reverse(chars,0,s.length()-1-n);
        // reverse(chars,s.length()-n,s.length()-1);


        return new String(chars);
    }


    @Test
    public void test(){
        String s = reverseWords("  a  good exampel  ");
        System.out.println(s);
    }
}

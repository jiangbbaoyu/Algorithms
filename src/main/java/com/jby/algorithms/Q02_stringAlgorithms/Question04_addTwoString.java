package com.jby.algorithms.Q02_stringAlgorithms;

import org.junit.Test;

public class Question04_addTwoString {
    /**
     * leetcode 415. 字符串相加 (leetcode 2.两个链表表示的数相加)
     * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和并同样以字符串形式返回。
     */
    public String addStrings(String num1, String num2) {

        char[] chars1 = num1.toCharArray();
        char[] chars2 = num2.toCharArray();

        StringBuilder sb = new StringBuilder();
        int carry =0;
        int tail1= chars1.length-1;
        int tail2 =chars2.length-1;
        while(tail1>=0 && tail2>=0){
            int n1 = chars1[tail1--]-'0';
            int n2 = chars2[tail2--]-'0';
            sb.append((n1+n2+carry)%10);
            carry = (n1+n2+carry)/10;
        }

        while(tail1>=0){
            int n1 = chars1[tail1--]-'0';
            sb.append((n1+carry)%10);
            carry = (n1+carry)/10;
        }
        while(tail2>=0){
            int n2 = chars2[tail2--]-'0';
            sb.append((n2+carry)%10);
            carry = (n2+carry)/10;
        }

        if(carry>0){
            sb.append(carry);
        }

        return sb.reverse().toString();
    }
    // 法2
    public String addStrings2(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int carry = 0, i = num1.length()-1, j = num2.length()-1;
        while(i >= 0 || j >= 0 || carry != 0){
            if(i>=0) carry += num1.charAt(i--)-'0';
            if(j>=0) carry += num2.charAt(j--)-'0';
            sb.append(carry%10);
            carry /= 10;
        }
        return sb.reverse().toString();
    }

    @Test
    public void test(){
        String s = addStrings("456", "77");
        System.out.println(s);
    }
}

package com.jby.algorithms.Q02_stringAlgorithms;

import org.junit.Test;

import java.util.Stack;

public class Question12_decodeString {
    /**
     * leetcode 394. 字符串解码
     * 给定一个经过编码的字符串，返回它解码后的字符串。
     * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
     * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
     * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
     * 示例 1：
     * 输入：s = "3[a]2[bc]"
     * 输出："aaabcbc"
     * 示例 2：
     * 输入：s = "3[a2[c]]"
     * 输出："accaccacc"
     * 示例 3：
     * 输入：s = "2[abc]3[cd]ef"
     * 输出："abcabccdcdcdef"
     *
     */
    public String decodeString(String s) {
        StringBuilder res = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();
        for(int i=0;i<chars.length;i++){
            char c = chars[i];
            if((c>='0' && c<='9') || c=='[' || (c>='a' && c<='z')){ // 数字，左括号，字符 直接入栈
                stack.push(c);
            }else if(c==']'){ // 遇到右括号，
                // 1. 将该右括号与最近的一个左括号间的字符 弹出
                StringBuilder sb = new StringBuilder();
                while(stack.peek()>='a' && stack.peek()<='z'){
                    sb.append(stack.pop());
                }
                // 2. 跳过 '['
                if(stack.peek()=='['){
                    stack.pop();
                }
                // 3. 弹出字符串的重复次数
                StringBuilder countSb = new StringBuilder();
                while(!stack.isEmpty()&&stack.peek()>='0' && stack.peek()<='9'){
                    countSb.append(stack.pop());
                }
                int count = Integer.parseInt(countSb.reverse().toString());

                // 4. 将字符重复 count次，并翻转
                String str = sb.toString();
                for(int j=0;j<count-1;j++){
                    sb.append(str);
                }
                str = sb.reverse().toString();

                // 5.1 如果此时栈为空，则当前重复count次的字符是返回结果的一部分
                if(stack.isEmpty()){
                    res.append(str);
                }else{
                    // 5.2 如果此时栈不为空，则当前重复count次的字符可能还会重复若干次，此时需要将其重新压入栈中
                    for(int j=0;j<str.length();j++){
                        stack.push(str.charAt(j));
                    }
                }
            }
        }
        // 6. 将栈中遗留的字符翻转后，append 到res中
        StringBuilder rest = new StringBuilder();
        while(!stack.isEmpty()){
            rest.append(stack.pop());
        }
        res.append(rest.reverse());

        return res.toString();
    }
    @Test
    public void test(){
//        String s = decodeString("3[a]2[bc]");
//        String s = decodeString("100[leetcode]");
//        String s = decodeString("2[abc]3[cd]ef");
        String s = decodeString("ef2[abc]3[cd]ef");

        System.out.println(s);
    }
}

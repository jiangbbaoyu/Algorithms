package com.jby.algorithms.Q06_QueueAndStackAlgorithms;

import org.junit.Test;

import java.util.Stack;

public class Question04_Calculator {


    /**
     * leetcode 227. 基本计算器 II
     * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
     * 整数除法仅保留整数部分。
     * 你可以假设给定的表达式总是有效的。所有中间结果将在 [-2^31, 2^31 - 1] 的范围内。
     * s 由整数和算符 ('+', '-', '*', '/') 组成，中间由一些空格隔开 ,表达式中不包含 括号
     *
     * 思路：
     *  使用一个数据栈。一个变量LastSign存储上一个符号，主要思想如下:
     *     将减法转化为加法（取相反数）
     *     由于乘除法优先级高，直接计算
     */
    public int calculate(String s) {
        Stack<Integer> numStack = new Stack<>();
        int lastSign = '+';
        int curNum = 0;

        int idx =0;
        while(idx<s.length()){
            char c = s.charAt(idx);
            // 解析当前 数字 curNum
            if(c>='0' && c<='9'){
                curNum = curNum*10 + (c-'0');
            }

            if(c=='+'||c=='-'||c=='*'||c=='/' || idx== s.length()-1){
                switch(lastSign){
                    case '+': numStack.push(curNum); break; // 如果curNum 前面的 lastSign为 + ， 则将curNum入栈
                    case '-': numStack.push(-1 * curNum); break;// 如果curNum 前面的 lastSign为 - ， 则将 -curNum 入栈
                    case '*': numStack.push( numStack.pop() * curNum); break; // 如果curNum 前面的 lastSign为 * ， 则将 numStack.pop() * curNum 入栈
                    case '/': numStack.push( numStack.pop() / curNum); break; // 如果curNum 前面的 lastSign为 / ， 则将 numStack.pop() / curNum 入栈
                }
                lastSign = c; // 更新 lastSign
                curNum = 0;  // curNum 重新置为 0
            }

            idx++;
        }

        // 将栈内剩余数字累加，即为结果
        int res =0;
        while(!numStack.isEmpty()){
            res += numStack.pop();
        }

        return res;
    }

    @Test
    public void test(){
//        int calculate = calculate(" 3/2 ");
        int calculate = calculate(" 42");
        System.out.println(calculate);
    }
}

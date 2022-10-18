package com.jby.algorithms.Q06_QueueAndStackAlgorithms;

import java.util.Stack;

public class Question02_validBracketsSeq {

    /**
     * leetcode 20. 有效的括号
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
     * 有效字符串需满足：
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * 每个右括号都有一个对应的相同类型的左括号。
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        char[] chars = s.toCharArray();
        for(int i=0;i<chars.length;i++){
            char c = chars[i];
            if(c=='('|| c=='{' || c=='['){
                stack.push(c);
            }else{
                if(stack.isEmpty()|| !isPair(stack.peek(),c)){
                    return false;
                }
                // 右括号匹配到了栈顶的左括号，将栈顶的左括号出栈
                stack.pop();
            }
        }

        if(stack.isEmpty()){
            return true;
        }
        return false;
    }

    private boolean isPair(char a,char b){
        if ((a=='(' && b==')')|| (a=='{' && b=='}') || (a=='[' && b==']')){
            return true;
        }
        return false;
    }

    /**
     * leetcode 1614. 括号的最大嵌套深度
     *
     * depth("") = 0
     * depth(C) = 0，其中 C 是单个字符的字符串，且该字符不是 "(" 或者 ")"
     * depth(A + B) = max(depth(A), depth(B))，其中 A 和 B 都是 有效括号字符串
     * depth("(" + A + ")") = 1 + depth(A)，其中 A 是一个 有效括号字符串
     * 例如：""、"()()"、"()(()())" 都是 有效括号字符串（嵌套深度分别为 0、1、2），而 ")(" 、"(()" 都不是 有效括号字符串 。
     *
     * 给你一个 有效括号字符串 s，返回该字符串的 s 嵌套深度 。
     * 输入：s = "(1+(2*3)+((8)/4))+1"
     * 输出：3
     * 解释：数字 8 在嵌套的 3 层括号中。
     */
    public int maxDepth(String s) {
        Stack<Character> stack = new Stack<Character>();
        int maxDepth=0;
        char[] chars = s.toCharArray();
        for(int i=0;i<chars.length;i++){
            char c = chars[i];
            if(c=='('){
                stack.push(c);
                if(stack.size()>maxDepth){
                    maxDepth = stack.size();
                }
            }else if(c==')'){
                stack.pop();
            }
        }

        return maxDepth;
    }
}

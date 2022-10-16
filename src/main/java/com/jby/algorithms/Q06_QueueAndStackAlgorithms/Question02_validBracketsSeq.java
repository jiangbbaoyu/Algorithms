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


}

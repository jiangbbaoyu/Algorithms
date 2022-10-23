package com.jby.algorithms.Q06_QueueAndStackAlgorithms;

import org.junit.Test;

import java.util.Stack;

public class Question02_BracketsSeq {

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

    /**
     *
     * leetcode 32. 最长有效括号
     * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
     * 示例 1：
     * 输入：s = "(()"
     * 输出：2
     * 解释：最长有效括号子串是 "()"
     * 示例 2：
     * 输入：s = ")()())"
     * 输出：4
     * 解释：最长有效括号子串是 "()()"
     *
     * 思路 ：
     *   用栈记录无法匹配的括号的索引位置，
     *   然后遍历栈，两个无法匹配括号的索引之间的最大间隔 就是能够匹配括号的字串 最大长度
     * eg:
     * s        = ")()())"
     * 遍历后
     * stack     = [0,5]
     * unMatchedCharPositions   = [-1,0,5,6]
     * 最大间隔为  5-0-1  = 4 , 即对应  “()()” 这段
     */
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        // 1. 遍历字符列表， 统计非法字符的索引下标
        char[] chars = s.toCharArray();
        for(int i=0;i<chars.length;i++){
            char c = chars[i];
            if(c=='('){
                stack.push(i);
            }else {
                // c ==')'
                if(!stack.isEmpty() && chars[stack.peek()]=='('){
                    stack.pop();
                }else{ // 如果是 ‘）’ ，且栈顶没有与之匹配的‘（’，也要将该字符压栈
                    stack.push(i);
                }
            }
        }

        // 2. 构造 unMatchedCharPositions 数组
        int unMatchedCharNum = stack.size();
        int[] unMatchedCharPositions = new int[unMatchedCharNum+2];
        unMatchedCharPositions[0]=-1;
        unMatchedCharPositions[unMatchedCharNum+1] = chars.length;
        for(int i=unMatchedCharNum;i>=1;i--){
            unMatchedCharPositions[i]= stack.pop();
        }
        // 3. 统计非法字符的最大间隔
        int maxLen =0;
        int curLen =0;
        for(int i=1;i<unMatchedCharPositions.length;i++){
            curLen = unMatchedCharPositions[i] -unMatchedCharPositions[i-1]-1;
            maxLen = maxLen>curLen? maxLen:curLen;
        }
        return maxLen;
    }

    @Test
    public void test(){
        int i = longestValidParentheses(")()())");
        System.out.println(i);
    }
}

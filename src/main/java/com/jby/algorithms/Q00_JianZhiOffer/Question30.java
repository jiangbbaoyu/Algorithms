package com.jby.algorithms.Q00_JianZhiOffer;

import java.util.LinkedList;

/**
 * 剑指 Offer 30. 包含min函数的栈
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，
 * 调用 min、push 及 pop 的时间复杂度都是 O(1)
 * 思路 : 使用一个存放min值的辅助栈
 */
public class Question30 {
    class MinStack {
        LinkedList<Integer> stack ;
        LinkedList<Integer>  minStack;
        public MinStack() {
            stack = new LinkedList<Integer>();
            minStack = new LinkedList<Integer>();
        }

        public void push(int x) {
            stack.addFirst(x);
            if(minStack.isEmpty()|| x< minStack.getFirst()){
                minStack.addFirst(x);
            }else{
                minStack.addFirst(minStack.getFirst());
            }
        }

        public void pop() {
            if(!stack.isEmpty()){
                stack.removeFirst();
                minStack.removeFirst();
            }
        }

        public int top() {
            if(stack.isEmpty() || minStack.isEmpty()){
                throw new RuntimeException("stack is empty");
            }
            return stack.getFirst();
        }

        public int min() {
            if(stack.isEmpty() || minStack.isEmpty()){
                throw new RuntimeException("stack is empty");
            }
            return minStack.getFirst();
        }
    }
}

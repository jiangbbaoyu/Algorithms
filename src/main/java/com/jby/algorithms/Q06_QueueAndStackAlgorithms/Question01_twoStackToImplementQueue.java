package com.jby.algorithms.Q06_QueueAndStackAlgorithms;

import java.util.Stack;

public class Question01_twoStackToImplementQueue {
    Stack<Integer> addStack ;
    Stack<Integer> removeStack;
    public Question01_twoStackToImplementQueue() {
        addStack = new Stack<>();
        removeStack = new Stack<>();
    }

    public void push(int x) {
        addStack.push(x);
    }
    public int pop() {
        if(removeStack.isEmpty()){
            while(!addStack.isEmpty()){
                removeStack.push(addStack.pop());
            }
        }
        if(removeStack.isEmpty()){
            return -1;
        }
        return removeStack.pop();
    }

    public int peek() {
        if(removeStack.isEmpty()){
            while(!addStack.isEmpty()){
                removeStack.push(addStack.pop());
            }
        }
        if(removeStack.isEmpty()){
            return -1;
        }
        return removeStack.peek();
    }

    public boolean empty() {
        return addStack.isEmpty() && removeStack.isEmpty();
    }

}

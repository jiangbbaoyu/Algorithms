package com.jby.algorithms.Q00_JianZhiOffer;

import java.util.LinkedList;

/**
 * 剑指 Offer 31. 栈的压入、弹出序列
 * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。
 * 假设压入栈的所有数字均不相等。
 * 例如，序列 {1,2,3,4,5} 是某栈的压栈序列，序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。
 *
 * 思路：遍历弹出序列，判断辅助栈的 栈顶元素 是否和当前元素相等
 *    如果相等则弹出栈顶元素，继续遍历下一个弹出序列元素；
 *    如果不相等则继续将压栈序列中的元素压栈，直到栈顶元素与当前弹出序列元素相等
 */
public class Question31_validateStackSequences {

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        LinkedList<Integer> stack = new LinkedList<Integer>();
        int pushIdx =0;
        int poppedIdx =0;
        while(poppedIdx<popped.length){
            while(stack.isEmpty() || stack.getFirst()!=popped[poppedIdx]){
                if(pushIdx<pushed.length){
                    stack.addFirst(pushed[pushIdx++]);
                }else{
                    break;
                }
            }

            if(stack.getFirst()!=popped[poppedIdx]){
                break;
            }else{
                stack.removeFirst();
                poppedIdx++;
            }

        }

        if(poppedIdx<popped.length || !stack.isEmpty()){
            return false;
        }
        return true;
    }
}

package com.jby.algorithms.Q00_JianZhiOffer;

import java.util.LinkedList;

/**
 * 剑指 Offer 09. 用两个栈实现队列
 * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。
 * 若队列中没有元素，deleteHead 操作返回 -1
 * 思路：
 *       appendTail 向stackToAdd中添加元素
 *       deleteHead 从stackDeleteFrom中删除；
 *       stackDeleteFrom中如果没有元素，则将stackToAdd中的全部元素pop,然后push到stackToRemove中，
 *       此时stackToRemove中的元素是与push顺序相反的，，因此从stackToRemove中pop元素时满足队列元素出队的特点
 */
public class Question09 {

    class CQueue {
        LinkedList<Integer> stackToAdd;
        LinkedList<Integer> stackDeleteFrom;
        public CQueue() {
            stackToAdd = new LinkedList<Integer>();
            stackDeleteFrom = new LinkedList<Integer>();
        }

        public void appendTail(int value) {
            stackToAdd.addFirst(value);
        }

        public int deleteHead() {
            if (stackDeleteFrom.isEmpty()){
                while(!stackToAdd.isEmpty()){
                    stackDeleteFrom.addFirst(stackToAdd.removeFirst());
                }
            }

            if (stackDeleteFrom.isEmpty()){
                return -1;
            }
            return stackDeleteFrom.removeFirst();
        }
    }

    // 用两个队列实现一个栈
    // 思路：
    //       插入元素时，将元素写入到一个已经存在元素的队列中   （任意时刻，两个队列至少有一个是空的）
    //       删除元素时，将有元素的队列中的前n-1个元素重新放入到另一个队列中，然后将最后一个元素返回。
    class Stack {
        LinkedList<Integer> queue1;
        LinkedList<Integer> queue2;
        public Stack() {
            queue1 = new LinkedList<Integer>();
            queue2 = new LinkedList<Integer>();
        }

        public void push(int value) {

            if(!queue1.isEmpty()){
                queue1.addFirst(value);
            }else{
                queue2.addFirst(value);
            }

        }

        public int pop() {
            if(queue1.isEmpty() && queue2.isEmpty()){
                return -1;
            }

            LinkedList<Integer> queueHasElements = queue1.isEmpty()? queue2 : queue1;
            LinkedList<Integer> queueEmpty = queue1.isEmpty()? queue1 : queue2;

            while(!queueHasElements.isEmpty()){

                Integer value = queueHasElements.removeLast();

                if(queueHasElements.isEmpty()){
                    return value;
                }else{
                    queueEmpty.addFirst(value);
                }
            }
            return -1;
        }
    }
}

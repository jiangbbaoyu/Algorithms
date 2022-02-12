package com.jby.algorithms.LinkedListAlgorithms;

import org.junit.Test;

/**
 * 环形链表相关问题
 */
public class Question05 {

    /**
     * leetcode 141 环形链表
     * 使用快慢指针
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        ListNode slow=head;
        ListNode fast=head;

        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if(slow==fast){
                return true;
            }
        }
        return false;
    }
    /**
     * leetcode 142 链表中环的入口节点
     * 使用快慢指针
     * 注意快慢指针的初始化：  slow=head; fast=head.next.next; 这种初始化是错误的, 此时会导致slow和fast指向的节点下标如下
     *                     slow 0 1 2 3 4
     *                     fast 2 4 6 8 10
     *                     slow,fast正确的下标的对应关系应该是
     *                     slow 0 1 2 3 4
     *                     fast 0 2 4 6 8
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                fast = head;
                while (fast != slow) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return fast;
            }
        }
        return null;
    }

    /**
     * leetcode 160 链表相交（整个链式结构中不存在环）
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNodeWithoutLoop(ListNode headA, ListNode headB) {

        // 1 分别遍历两个链表，计算两个链表的长度
        int lenA=0;
        int lenB=0;
        ListNode currA=headA;
        while(currA!=null){
            lenA++;
            currA=currA.next;
        }
        ListNode currB=headB;
        while(currB!=null){
            lenB++;
            currB=currB.next;
        }

        // 2. 将较长的链表前进delta步，使得两个链表长度一致
        currB=headB;
        currA=headA;
        int delta=0;
        if(lenA>lenB){
            delta=lenA-lenB;
            while(delta>0){
                currA=currA.next;
                delta--;
            }
        }else{
            delta=lenB-lenA;
            while(delta>0){
                currB=currB.next;
                delta--;
            }
        }

        // 3 同时遍历两个链表，直到碰到相交节点，如果没有相交节点，currA,currB均会遍历到末尾指向null
        while(currA!=currB){
            currA=currA.next;
            currB=currB.next;
        }
        return currA;
    }

    /**
     * 拓展： 链表相交（整个链式结构中可能存在环）
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNodeMayWithLoop(ListNode headA, ListNode headB) {
        ListNode intersectionNodeA = detectCycle(headA);
        ListNode intersectionNodeB = detectCycle(headB);

        if (intersectionNodeA==null && intersectionNodeB==null){
            // 1 两个链表均没有环的情况
            return getIntersectionNodeWithoutLoop(headA,headB);
        }else if(intersectionNodeA!=null && intersectionNodeB!=null){
            // 2 两个链表均有环的情况
            if(intersectionNodeA==intersectionNodeB){
                //2.1 两个链表中的环是同一个，且入环节点是同一个

                int lenA=0;
                int lenB=0;
                ListNode currA=headA;
                while(currA!=intersectionNodeA){ //链表长度只统计到入环节点即可
                    lenA++;
                    currA=currA.next;
                }
                ListNode currB=headB;
                while(currB!=intersectionNodeB){//链表长度只统计到入环节点即可
                    lenB++;
                    currB=currB.next;
                }

                currB=headB;
                currA=headA;
                int delta=0;
                if(lenA>lenB){
                    delta=lenA-lenB;
                    while(delta>0){
                        currA=currA.next;
                        delta--;
                    }
                }else{
                    delta=lenB-lenA;
                    while(delta>0){
                        currB=currB.next;
                        delta--;
                    }
                }

                while(currA!=currB){
                    currA=currA.next;
                    currB=currB.next;
                }
                return currA;
            }else{
                //2.2 两个链表中的环是同一个，且入环节点不是同一个
                ListNode curr =intersectionNodeA.next;
                while(curr!=intersectionNodeA){
                    if (curr==intersectionNodeB){
                        return curr;
                    }
                }// 从intersectionNodeA开始，环遍历了一遍，没有发现intersectionNodeB节点
                //2.3 两个链表分别有一个环, 没有交集
                return null;
            }
        }else{
            // 3 一个链表存在环，一个链表不存在环： 该情况两个链表肯定不存在交集 （链表中一个节点只有一个next指针决定的）
            return null;
        }
    }


    @Test
    public void test2(){

        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(2);
        node1.next=node2;
        node2.next=node3;
        node3.next=node4;
        node4.next=node2;

        ListNode listNode = detectCycle(node1);
        System.out.println(listNode);

    }
}

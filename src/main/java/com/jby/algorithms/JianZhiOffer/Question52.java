package com.jby.algorithms.JianZhiOffer;

import com.jby.algorithms.LinkedListAlgorithms.ListNode;

/**
 * 剑指 Offer 52. 两个链表的第一个公共节点 (无环链表)
 */
public class Question52 {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        if(headA==null || headA==null){
            return null;
        }

        int lenA =getLen(headA);
        int lenB =getLen(headB);

        ListNode curA = headA;
        ListNode curB = headB;


        if(lenA>lenB){
            int delta = lenA-lenB;
            while(delta>0){
                curA =curA.next;
                delta--;
            }
        }else{
            int delta = lenB-lenA;
            while(delta>0){
                curB =curB.next;
                delta--;
            }
        }

        while(curA!=null&& curA!=curB){
            curA = curA.next;
            curB = curB.next;
        }

        return curA;
    }

    private int getLen(ListNode head){
        ListNode cur =head;
        int len=0;
        while(cur!=null){
            len++;
            cur=cur.next;
        }
        return len;
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
            return getIntersectionNode(headA,headB);
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
}

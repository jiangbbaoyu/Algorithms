package com.jby.algorithms.JianZhiOffer;

import com.jby.algorithms.Q01_LinkedListAlgorithms.ListNode;

/**
 * 剑指offer23 链表中环的入口节点
 */
public class Question23 {
    public ListNode detectCycle(ListNode head) {

        ListNode slow = head;
        ListNode fast = head;

        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if(slow==fast){
                fast=head;
                while(fast!=slow){
                    fast=fast.next;
                    slow=slow.next;
                }
                return fast;
            }
        }
        return null;

    }
}

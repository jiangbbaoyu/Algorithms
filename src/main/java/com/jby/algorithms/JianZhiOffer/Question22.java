package com.jby.algorithms.JianZhiOffer;

import com.jby.algorithms.Q01_LinkedListAlgorithms.ListNode;

/**
 * 剑指 Offer 22. 链表中倒数第k个节点
 * 输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点
 *
 * 思路：快慢指针
 * 拓展：求链表的中间节点， 快指针一次走两步，慢指针一次走一步，遍历一次即可
 */
public class Question22 {

    public ListNode getKthFromEnd(ListNode head, int k) {
        if(k<=0){
            return null;
        }

        int i=0;
        ListNode fast =head;
        while(fast!=null && i<k){
            fast =fast.next;
            i++;
        }
        if(i<k) return null; // 防止出现k 大于 链表节点个数的情况

        ListNode slow = head;
        while(fast!=null){
            fast =fast.next;
            slow =slow.next;
        }

        return slow;
    }
}

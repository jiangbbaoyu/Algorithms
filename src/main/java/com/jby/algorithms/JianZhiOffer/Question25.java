package com.jby.algorithms.JianZhiOffer;

import com.jby.algorithms.LinkedListAlgorithms.ListNode;

/**
 * 剑指 Offer 25. 合并两个排序的链表
 * 输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。
 */
public class Question25 {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1==null){
            return l2;
        }
        if(l2==null){
            return l1;
        }

        ListNode newHead = null;
        if(l1.val<l2.val){
            newHead =l1;
            newHead.next= mergeTwoLists(l1.next,l2);
        }else{
            newHead =l2;
            newHead.next= mergeTwoLists(l2.next,l1);
        }
        return newHead;
    }
}

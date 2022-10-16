package com.jby.algorithms.Q00_JianZhiOffer;

import com.jby.algorithms.Q01_LinkedListAlgorithms.ListNode;

/**
 * 剑指 Offer 25. 合并两个排序的链表 (leetcode 21)
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

    // 非递归
    public ListNode mergeTwoLists2(ListNode list1, ListNode list2) {
        ListNode head = new ListNode();
        ListNode tail = head;

        while(list1!=null && list2!=null){
            if(list1.val< list2.val){
                tail.next = list1;
                tail = list1;
                list1= list1.next;
            }else{
                tail.next = list2;
                tail = list2;
                list2 = list2.next;
            }
        }

        if(list1!=null)  tail.next= list1;
        if(list2!=null)  tail.next= list2;

        return head.next;
    }


}

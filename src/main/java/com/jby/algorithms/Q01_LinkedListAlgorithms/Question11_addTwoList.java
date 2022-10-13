package com.jby.algorithms.Q01_LinkedListAlgorithms;

public class Question11_addTwoList {
    /**
     * leetcode 2. 两数相加
     * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，（如果不是逆序，则可以先将链表 reverse）
     * 并且每个节点只能存储 一位 数字。
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode dummyNode = new ListNode();
        ListNode tail = dummyNode;
        int carry =0;
        while(l1!=null && l2!=null){
            ListNode node = new ListNode((l1.val+l2.val+carry)%10);
            tail.next = node;
            tail = node;

            carry = (l1.val+l2.val+carry)/10;

            l1 = l1.next;
            l2 = l2.next;
        }

        ListNode unfinished = l1!= null ? l1:l2; // 位数较多的链表
        while(unfinished!=null){
            ListNode node = new ListNode((unfinished.val+carry)%10);
            tail.next = node;
            tail = node;
            carry = (unfinished.val+carry)/10;
            unfinished = unfinished.next;
        }

        if(carry>0){  // 处理最后的进位
            ListNode node = new ListNode(carry);
            tail.next = node;
            tail = node;
        }

        return dummyNode.next;
    }
}

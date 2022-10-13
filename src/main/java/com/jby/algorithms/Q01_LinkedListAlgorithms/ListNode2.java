package com.jby.algorithms.Q01_LinkedListAlgorithms;

//Definition for double-linked list.
public class ListNode2 {
    int val;
    ListNode2 next;
    ListNode2 before;
    ListNode2() {}
    ListNode2(int val) { this.val = val; }
    ListNode2(int val, ListNode2 next,ListNode2 before) { this.val = val; this.next = next; this.before=before;}
}
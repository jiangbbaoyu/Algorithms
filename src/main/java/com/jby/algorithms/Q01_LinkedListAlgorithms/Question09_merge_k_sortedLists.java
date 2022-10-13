package com.jby.algorithms.Q01_LinkedListAlgorithms;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * leetcode 23. 合并K个升序链表
 * 给你一个链表数组，每个链表都已经按升序排列。
 *
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 */
public class Question09_merge_k_sortedLists {

    public ListNode mergeKLists(ListNode[] lists) {
        if(lists==null || lists.length==0){
            return null;
        }
        PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(lists.length,new Comparator<ListNode>(){
            public int compare(ListNode node1,ListNode node2){
                return node1.val - node2.val;
            }
        });

        for(int i=0;i<lists.length;i++){
            if(lists[i]!=null)  queue.add(lists[i]);
        }

        ListNode dummyHead = new ListNode();
        ListNode tail = dummyHead;

        while(!queue.isEmpty()){
            ListNode node = queue.poll();
            tail.next = node;
            tail = node;

            if(node.next!=null){
                queue.add(node.next);
            }
        }

        return  dummyHead.next;
    }
}

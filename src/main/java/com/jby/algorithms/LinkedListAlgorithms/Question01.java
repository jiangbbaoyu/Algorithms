package com.jby.algorithms.LinkedListAlgorithms;

import org.junit.Test;

/**
 * 反转链表
 */
public class Question01 {

    /**
     * 反转单向链表 leetcode 206 https://leetcode-cn.com/problems/reverse-linked-list/submissions/
     * 定义三个指针，分别指向当前node,前一个node,后一个node
     * 由于在反转的时候，需要将当前node的next指向before,因此需要一个after指针提前记录node的next
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if(head==null||head.next==null){
            return head;
        }

        ListNode before =null;
        ListNode curr = head; // 当前元素从head开始
        ListNode after = curr.next;
        while(after!=null){
            curr.next=before;
            before =curr;
            curr= after;
            after=after.next;
        }
        curr.next=before;
        return curr; // 返回新的头结点
    }


    /**
     * 反转双向链表
     */
    public ListNode2 reverseList2(ListNode2 head) {
        if(head==null||head.next==null){
            return head;
        }

        ListNode2 before =null;
        ListNode2 curr =head;
        ListNode2 after =curr.next;

        while (after!=null){
            curr.next=before;
            curr.before=after;
            before=curr;
            curr=after;
            after=after.next;
        }
        curr.next=before;
        curr.before=after;
        return curr;
    }


    @Test
    public void test2(){

        ListNode2 node1 = new ListNode2(1);
        ListNode2 node2 = new ListNode2(2);
        ListNode2 node3 = new ListNode2(3);
        ListNode2 node4 = new ListNode2(4);
        node1.before=null;
        node1.next=node2;

        node2.before=node1;
        node2.next=node3;

        node3.before=node2;
        node3.next=node4;

        node4.before=node3;
        node4.next=null;

        ListNode2 newHead = reverseList2(node1);

        while (newHead!=null){
            System.out.println(newHead.val);
            newHead=newHead.next;
        }

    }
}

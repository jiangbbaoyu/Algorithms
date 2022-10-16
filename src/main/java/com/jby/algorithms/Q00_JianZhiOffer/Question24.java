package com.jby.algorithms.Q00_JianZhiOffer;

import com.jby.algorithms.Q01_LinkedListAlgorithms.ListNode;

/**
 * 剑指 Offer 24. 反转链表
 * 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点
 */
public class Question24 {

    // 使用 dummy 节点， 兼容head为空的情况
    public ListNode reverseList(ListNode head) {

        // if(head==null || head.next==null){
        //     return head;
        // }

        ListNode dummy = new ListNode();
        dummy.next= null;
        ListNode cur =head;
        while(cur!=null){

            ListNode nextNode =cur.next;
            //头插法：每次都将新节点插入到 dummy节点的下一个节点位置
            cur.next=dummy.next;
            dummy.next=cur;

            cur=nextNode;
        }

        return dummy.next;

    }

    // 递归解法
    public ListNode reverseList2(ListNode head) {
        if(head==null || head.next ==null){
            return head;
        }

        ListNode newHead = reverseList2(head.next);

        // head.next 此时指向了新链表中最后一个节点
        // 将head 尾插 到新链表的最后一个节点
        head.next.next=head;
        head.next=null;

        return newHead;
    }
}

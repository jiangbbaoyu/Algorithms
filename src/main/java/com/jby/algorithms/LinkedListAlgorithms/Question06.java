package com.jby.algorithms.LinkedListAlgorithms;

/**
 * 链表节点删除
 */
public class Question06 {

    /**
     * leetcode 237. 删除链表中的节点
     * 思路 ： 从要删除的节点开始，赋值后序节点的值到前一个节点，然后将最后一个节点删除掉
     * @param node 要删除的节点， 程序中不能访问到链表的head
     */
    public void deleteNode(ListNode node) {
        ListNode cur =node;
        while(cur.next!=null){
            ListNode next = cur.next;
            cur.val=next.val; // 值拷贝

            if(next.next!=null){
                cur=next;
                next=next.next;
            }else{ // next为最后一个节点，删除掉next
                cur.next=null;
                break;
            }

        }
    }

    public ListNode deleteNode(ListNode head, int val) {

        if(head==null){
            return null;
        }
        if(head.val==val){
            return head.next;
        }

        ListNode pre = head;
        ListNode cur = head.next;

        while(cur!=null){
            if(cur.val==val){
                pre.next=cur.next;
                break;
            }
            pre=cur;
            cur=cur.next;
        }

        return head;

    }
}

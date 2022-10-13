package com.jby.algorithms.Q01_LinkedListAlgorithms;

public class Question12_rotateRight {
    /**
     * 61. 旋转链表
     * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置
     *
     */
    public ListNode rotateRight(ListNode head, int k) {
        if(head==null){
            return head;
        }
        // 1. 统计链表长度
        int len =0;
        ListNode cur = head;
        while(cur!=null){
            len++;
            cur = cur.next;
        }
        // 2. 计算链表中节点移动的次数
        k = k%len;
        if(k==0){
            return head;
        }
        // 3. 快慢指针， slow 定位到 倒数第k个节点的前一个节点； fast定位到最后一个节点
        ListNode slow = head;
        ListNode fast = head;
        while(k>0){
            fast = fast.next;
            k--;
        }
        while(fast.next!=null){
            slow = slow.next;
            fast = fast.next;
        }

        // 4. 将 [slow, fast] 部分与原链表断开， 然后将[slow, fast]部分添加到原链表的头部
        ListNode newHead = slow.next;
        slow.next = null;
        fast.next = head;

        return newHead;
    }
}

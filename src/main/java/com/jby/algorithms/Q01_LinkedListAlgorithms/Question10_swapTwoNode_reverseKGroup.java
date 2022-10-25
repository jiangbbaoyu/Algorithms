package com.jby.algorithms.Q01_LinkedListAlgorithms;

import org.junit.Test;

public class Question10_swapTwoNode_reverseKGroup {
    /**
     * 24. 两两交换链表中的节点
     * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。
     * 你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）
     * 思路：使用三个指针 ：pre为两个要交换的节点的前驱； node1, node2 为要交换的节点
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        ListNode dummyNode = new ListNode();
        dummyNode.next = head;

        ListNode pre = dummyNode;
        while(pre.next!=null && pre.next.next!=null){ // pre后面有两个要交换的节点时才进行交互流程
            ListNode node1 = pre.next;
            ListNode node2 = pre.next.next;

            node1.next = node2.next;  // 首先保证node2 后面的链表不丢失
            node2.next = node1;
            pre.next = node2;

            pre = node1;  // pre指向下一次 要两两交换节点的 前一个节点
        }
        return dummyNode.next;
    }

    public ListNode swapPairs2(ListNode head) {

        ListNode dummyNode = new ListNode();
        dummyNode.next = head;
        ListNode pre = dummyNode;
        ListNode cur = pre.next;

        while(cur!=null && cur.next!=null){
            ListNode next = cur.next;
            cur.next = next.next;
            next.next = cur;
            pre.next = next;

            pre = cur;
            cur = cur.next;
        }

        return dummyNode.next;
    }

    /**
     * 25. K 个一组翻转链表
     * 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
     * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
     * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
     */
    public ListNode reverseKGroup(ListNode head, int k) {

        // 1. 将 groupTailNode 指向 本组 节点的最后一个
        ListNode groupTailNode = head; int offset =k-1;
        while(groupTailNode!=null && offset>0){
            groupTailNode =groupTailNode.next;
            offset--;
        }
        if(groupTailNode==null){ // 如果从head开始的链表长度不足 k个，则不用反转了，直接返回head
            return head;
        }
        // 2. 获取下一组节点的头结点， 并将本组节点与下一组节点断开
        ListNode newHead = groupTailNode.next;
        groupTailNode.next =null;
        // 3. 将本组节点 反转
        ListNode res = reverse(head); //head指向反转后group的最后一个节点
        // 4. 递归将剩下的group 反转， 并将剩下的group反转后的头结点 赋值到本组链表反转后的尾结点上
        head.next = reverseKGroup(newHead,k);
        return res;
    }
    private ListNode reverse(ListNode head){
        ListNode dummyNode = new ListNode();
        ListNode cur = head;
        while(cur!=null){
            ListNode next = cur.next;
            cur.next= dummyNode.next;
            dummyNode.next = cur;
            cur = next;
        }
        return dummyNode.next;
    }

    @Test
    public void test(){
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next=node2;
        node2.next=node3;
        node3.next=node4;
        node4.next=node5;
        node5.next=null;

        System.out.println(reverseKGroup(node1,2));
    }

}

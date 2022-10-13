package com.jby.algorithms.Q01_LinkedListAlgorithms;

/**
 * 剑指offer 18 : O(1)时间内删除链表中的一个节点 (给定的节点 node 可能是链表中的最后一个节点)
 */

public class Question06_deleteNode {

    // 删除链表中给定值的第一个出现的节点
    public ListNode deleteNode(ListNode head, int val) {
        ListNode dummyNode = new ListNode();
        dummyNode.next = head;

        ListNode pre = dummyNode;
        ListNode cur = pre.next;
        while(cur!=null){
            if(cur.val==val){
                pre.next=cur.next;
                break;
            }
            pre=cur;
            cur=cur.next;
        }

        return dummyNode.next;
    }

    /**
     * leetcode 83. 删除 排序链表 中的重复元素
     * 给定一个已排序的链表的头 head ， 删除所有重复的元素，使每个元素 `只出现一次` 。返回 已排序的链表 。
     */
    public ListNode deleteDuplicates(ListNode head) {

        ListNode dummyNode = new ListNode();
        dummyNode.next= head;

        ListNode pre = dummyNode;
        ListNode cur = pre.next;
        while(cur!=null){

            ListNode next = cur.next;

            if(next!=null && next.val==cur.val){
                pre.next = next;
            }else{
                pre =cur;
            }

            cur=next;
        }

        return dummyNode.next;
    }

    /**
     * leetcode 82. 删除排序链表中的重复元素 II
     * 给定一个已排序的链表的头 head ， 删除原始链表中 `所有重复数字的节点`，`只留下不同的数字` 。返回 已排序的链表 。
     * @param head
     * @return
     */
    public ListNode deleteDuplicates2(ListNode head) {

        ListNode dummyNode = new ListNode();
        dummyNode.next= head;

        ListNode pre = dummyNode;
        ListNode cur = pre.next;
        while(cur!=null){
            ListNode next = cur.next;

            if(next!=null && cur.val==next.val){
                // 如果cur和cur.next的value一致，此时有节点要删除
                while(next!=null && cur.val==next.val){ // 定位next指针，使其指向重复节点的后一个节点
                    cur = next;
                    next =next.next;
                }
                pre.next= next; // 删除所有重复节点
                // 此时不移动 pre指针
            }else{
                // 如果cur和cur.next的value不一致，则cur不需要删除，将pre,cur指针后移
                pre = cur;
            }
            cur =next;
        }
        return dummyNode.next;
    }
    // 形式2
    public ListNode deleteDuplicates22(ListNode head){
        ListNode dummy = new ListNode();
        dummy.next =head;

        ListNode pre =dummy;
        ListNode cur =head;
        while(cur!=null){
            boolean needDelete =false;
            if(cur.next!=null &&cur.val == cur.next.val){
                needDelete =true;
            }

            if(!needDelete){
                // 如果cur和cur.next的value不一致，则cur不需要删除，将pre,cur指针后移
                pre=cur;
                cur=cur.next;
            }else{
                // cur和cur.next的value不致，cur需要删除，此时循环遍历与cur值一样的后序节点，直到遇到与cur值不一样的节点（或空节点），
                // 并删除所有与cur值一样的节点
                int val =cur.val;
                while(cur!=null && cur.val==val){
                    cur=cur.next;
                }
                pre.next=cur; // 删除所有与cur值一样的节点
                // 此时，pre节点不会向后移动，
                // 因为cur此时的值与val不一样了，但是 cur后面的节点的值可能又与cur的值一致，因此还无法判断cur的值在后序是否存在重复
            }
        }

        return dummy.next;
    }

    /**
     * leetcode 19. 删除链表的倒数第 N 个结点
     * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummyNode = new ListNode();
        dummyNode.next=head;

        ListNode fast =dummyNode;
        ListNode slow =dummyNode;

        while(n>0 && fast!=null){// fast 向前移动n步
            fast= fast.next;
            n--;
        }

        while(fast!=null && fast.next!=null){// 定位slow 到待删除节点的 `前一个节点`
            slow=slow.next;
            fast=fast.next;
        }

        slow.next= slow.next.next; // 节点删除
        return dummyNode.next;
    }
}

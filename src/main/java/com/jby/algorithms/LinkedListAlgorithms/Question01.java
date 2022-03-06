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

    // 头插法反转链表
    public ListNode reverseList2(ListNode head) {

        if(head==null||head.next==null){
            return head;
        }
        ListNode dummy =new ListNode(0,head); // 定义一个临时头结点
        ListNode pre = dummy;
        ListNode cur = pre.next;

        while(cur!=null){
            ListNode next =cur.next;

            cur.next=pre.next; // 将cur头插到pre的后面
            pre.next=cur;

            cur=next;
        }
        head.next=cur;  // cur 此时为null

        return dummy.next;
    }


    /**
     * 反转双向链表
     */
    public ListNode2 reverseList3(ListNode2 head) {
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

    /**
     * leetcode 92. 反转链表II
     * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
     * 思路： 整体采用头插法来反转指定区间的链表
     * @param head
     * @param left
     * @param right
     * @return
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {

        if(head==null || head.next==null || left == right){
            return head;
        }

        ListNode dummy =new ListNode(0,head); //创建一个临时头结点，以应对从链表第一个位置就开始反转的场景
        ListNode pre=dummy;
        ListNode cur=pre.next;

        ListNode reverseRear=null;
        for(int i=1;i<=right;i++){

            if(i==left){
                reverseRear=cur; // 记录反转区间的第left个节点，区间反转后，该节点的next需要和right+1个节点相连
            }

            if(i<left){ // 定位到left位置， 此时pre指向第left-1个node，cur指向第left个node
                pre=cur;
                cur=cur.next;
            }else {
                ListNode next = cur.next;

                cur.next=pre.next; // 将cur头插到pre的后面
                pre.next=cur;

                cur=next;
            }
        }

        reverseRear.next=cur;// 此时cur指向right+1个node

        return dummy.next; // 此处不能返回head,从链表第一个位置就开始reverse的场景中，head在反转后早就不是链表头节点了
    }
    public ListNode reverseBetween2(ListNode head, int left, int right) {
        ListNode dummy =new ListNode(0);
        dummy.next=head;
        ListNode pre =dummy;
        ListNode cur =pre.next;

        int i=1;
        for(;i<left;i++){
            pre=pre.next;
            cur=cur.next;
        }
        // 此时cur指向第left个节点
        ListNode firstReverse=cur;
        for(;i<=right;i++){
            ListNode next = cur.next;

            cur.next=pre.next;
            pre.next=cur;
            cur=next;

        }
        firstReverse.next=cur; // left个节点的next指向right+1个节点

        return dummy.next;
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

        ListNode2 newHead = reverseList3(node1);

        while (newHead!=null){
            System.out.println(newHead.val);
            newHead=newHead.next;
        }

    }

    @Test
    public void test3(){

//        ListNode node1 = new ListNode(1);
//        ListNode node2 = new ListNode(2);
//        ListNode node3 = new ListNode(3);
//        ListNode node4 = new ListNode(4);
//        ListNode node5 = new ListNode(5);
//        node1.next=node2;
//        node2.next=node3;
//        node3.next=node4;
//        node4.next=node5;
//        node5.next=null;
//        ListNode head = reverseBetween(node1, 2, 4);

        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        node1.next=node2;
        ListNode head = reverseBetween(node1, 1, 2);

        while (head!=null){
            System.out.println(head.val);
            head=head.next;
        }

    }
}

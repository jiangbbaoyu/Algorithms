package com.jby.algorithms.Q01_LinkedListAlgorithms;

import org.junit.Test;

import java.util.LinkedList;

/**
 * leetcode 234 回文链表问题  （可不可以修改源链表的值）
 */
public class Question02_Palindrome_list {

    /**
     * 解法1 ：使用栈存放右半段的元素，空间复杂度O(N)
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while(fast.next!=null && fast.next.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        slow =slow.next;

        LinkedList<Integer> rightParts = new LinkedList<Integer>();
        while(slow!=null){
            rightParts.push(slow.val);
            slow=slow.next;
        }

        while (!rightParts.isEmpty()){
            Integer num = rightParts.pop();
            if (head.val!=num){
                return false;
            }
            head=head.next;
        }
        return true;
    }

    /**
     * 解法2: 双指针定位到链表右半段，反转右半段链表， 空间复杂度O(1)
     * @param head
     * @return
     */
    public boolean isPalindrome3(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        // 1. 使用快慢指针找到链表右半段的前1个节点
        while(fast != null && fast.next!=null && fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        reverseRightPart(slow); // 2. 基于头插法反转 右侧部分
        ListNode firstRightPartNode = slow.next;

        while(head!=null && firstRightPartNode!=null){  //3. 左半段的链表与反转后的右半段的链表逐个比较元素值
            if(head.val!= firstRightPartNode.val){
                return false;
            }
            head =head.next;
            firstRightPartNode=firstRightPartNode.next;
        }
        return true;
    }

    private void reverseRightPart(ListNode pre){
        ListNode cur = pre.next;
        pre.next =null; //pre node 与后面的节点断开

        while(cur!=null){
            ListNode next = cur.next;
            cur.next = pre.next;
            pre.next = cur;
            cur = next;
        }
    }

    @Test
    public void test(){

//        ListNode node1 = new ListNode(1);
//        ListNode node2 = new ListNode(2);
//        ListNode node3 = new ListNode(2);
//        ListNode node4 = new ListNode(1);
//        node1.next=node2;
//        node2.next=node3;
//        node3.next=node4;
//        node4.next=null;

        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(2);
        ListNode node5 = new ListNode(1);
        node1.next=node2;
//        node2.next=node3;
//        node3.next=node4;
//        node4.next=node5;
//        node5.next=null;

        System.out.println(isPalindrome3(node1));
    }

    @Test
    public void test2(){

//        ListNode node1 = new ListNode(1);
//        ListNode node2 = new ListNode(2);
//        ListNode node3 = new ListNode(2);
//        ListNode node4 = new ListNode(1);
//        node1.next=node2;
//        node2.next=node3;
//        node3.next=node4;
//        node4.next=null;

        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(2);
        ListNode node5 = new ListNode(1);
        node1.next=node2;
        node2.next=node3;
        node3.next=node4;
        node4.next=node5;
        node5.next=null;

        System.out.println(isPalindrome3(node1));
    }
}

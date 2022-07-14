package com.jby.algorithms.LinkedListAlgorithms;

import org.junit.Test;

import java.util.LinkedList;

/**
 * leetcode 234 回文链表问题  （可不可以修改源链表的值）
 */
public class Question02 {

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
    public boolean isPalindrome2(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        // 1. 使用快慢指针找到链表右半段的第一个节点
        while(fast.next!=null && fast.next.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        slow =slow.next; //指向链表右半段（如果链表有奇数个元素，不包含中间元素）的第一个节点

        // 2. 反转右半段的链表
        slow=reverse(slow);

        //3. 左半段的链表与反转后的右半段的链表逐个比较元素值
        while (slow!=null && head.next!=null){
            if (slow.val!=head.val){
                return false;
            }
            slow=slow.next;
            head=head.next;
        }
        return true;
    }

    private ListNode reverse(ListNode slow) {

        if(slow==null|| slow.next==null){
            return slow;
        }
        ListNode pre = null;
        ListNode cur = slow;
        ListNode next = cur.next;

        while (next!=null){
            cur.next=pre;
            pre=cur;
            cur=next;
            next=next.next;
        }
        cur.next=pre;

        return cur;
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
        node2.next=node3;
        node3.next=node4;
        node4.next=node5;
        node5.next=null;

        System.out.println(isPalindrome(node1));
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

        System.out.println(isPalindrome2(node1));
    }
}

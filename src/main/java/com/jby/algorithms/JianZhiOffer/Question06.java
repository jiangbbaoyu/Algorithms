package com.jby.algorithms.JianZhiOffer;

import com.jby.algorithms.LinkedListAlgorithms.ListNode;

/**
 * 剑指 Offer 06. 从尾到头打印链表
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回)
 * 思路： 先遍历一次确定元素个数；第二次遍历的过程中从后向前给数组赋值
 */
public class Question06 {

    public int[] reversePrint(ListNode head) {
        ListNode node = head;
        int count = 0;
        while (node != null) {
            ++count;
            node = node.next;
        }
        int[] nums = new int[count];
        node = head;
        for (int i = count - 1; i >= 0; --i) {
            nums[i] = node.val;
            node = node.next;
        }
        return nums;
    }

}

package com.jby.algorithms.Q01_LinkedListAlgorithms;

public class Question13_reorder_list {
    /**
     * leetcode 143. 重排链表
     * 给定一个单链表 L 的头节点 head ，单链表 L 表示为：
     * L0 → L1 → … → Ln - 1 → Ln
     * 请将其重新排列后变为：
     * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
     *
     * 输入：head = [1,2,3,4]
     * 输出：[1,4,2,3]
     * 输入：head = [1,2,3,4,5]
     * 输出：[1,5,2,4,3]
     */
    public void reorderList(ListNode head) {

        ListNode slow = head;
        ListNode fast = head;
        // 1,快慢指针定位到 链表右半部分的前一个节点；
        while(fast!=null && fast.next!=null && fast.next.next!=null){
            slow =slow.next;
            fast =fast.next.next;
        }
        // 2. 保存有半部分的第一个节点，并将左半部分与右半部分断开
        ListNode rightPartHead = slow.next;
        slow.next =null;
        // 3. 反转右半部分
        rightPartHead = reverse(rightPartHead);

        //4. 使用尾插法，交替将左右两部分 连接为一个新链表
        ListNode dummyHead = new ListNode();
        ListNode tail = dummyHead;
        boolean addLeft =true;
        while(head!=null || rightPartHead!=null){
            if(addLeft){
                tail.next= head;
                tail=head;
                head =head.next;
            }else{
                tail.next= rightPartHead;
                tail=rightPartHead;
                rightPartHead =rightPartHead.next;
            }
            addLeft =!addLeft;
        }

        // if(head!=null){
        //      tail.next= head;
        //      tail=head;
        // }
        // if(rightPartHead!=null){
        //      tail.next= rightPartHead;
        //      tail=rightPartHead;
        // }

        head = dummyHead.next;
    }

    private ListNode reverse(ListNode head){
        ListNode dummyHead = new ListNode();

        while(head!=null){
            ListNode next = head.next;
            head.next = dummyHead.next;
            dummyHead.next = head;

            head =next;
        }

        return dummyHead.next;
    }
}

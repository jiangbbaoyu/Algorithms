package com.jby.algorithms.Q01_LinkedListAlgorithms;

/**
 * leetcode 148. 排序链表
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 */
public class Question14_sortList {

    /**
     * 归并排序 时间复杂度O(NlogN), 空间复杂度O(1)
     * 利用归并的思想，递归地将当前链表分为两段，然后merge，
     */
    public ListNode sortList2(ListNode head) {
        return mergeSort(head);
    }

    private ListNode mergeSort(ListNode head){
        if(head==null || head.next==null){
            return head;
        }
        // 1. slow 定位到中间节点的前一个节点
        ListNode slow = head;
        ListNode fast =head;
        while(fast.next!=null && fast.next.next!=null){
            slow = slow.next;
            fast =fast.next.next;
        }
        // 2. 获取到右半部分的头结点，并将左半部分 与右半部分断开
        ListNode rightPartHead = slow.next;
        slow.next =null;
        //3. 递归对两半部分进行排序 （终止条件是该部分只有一个节点，此时该部分是有序的）
        ListNode newLeftPartHead = mergeSort(head);
        ListNode newRightPartHead = mergeSort(rightPartHead);
        // 4. 对两个有序的链表进行合并
        return merge(newLeftPartHead,newRightPartHead);
    }

    private ListNode merge(ListNode head1, ListNode head2){

        ListNode dummyNode= new ListNode();
        ListNode tail = dummyNode;

        while(head1!=null && head2!=null){
            if(head1.val<=head2.val){
                tail.next = head1;
                tail = head1;
                head1= head1.next;
            }else{
                tail.next = head2;
                tail = head2;
                head2= head2.next;
            }
        }

        ListNode unfinishedList = head1!=null?head1:head2;
        tail.next = unfinishedList;

        return dummyNode.next;
    }




    // 插入排序 (leetcode 147)
    public ListNode sortList(ListNode head) {
        ListNode dummyNode = new ListNode();
        dummyNode.next = head;

        ListNode cur = head;
        while(cur!=null && cur.next!=null){

            if(cur.val<=cur.next.val){
                cur = cur.next;
                continue;
            }
            ListNode nodeToInsert = cur.next;

            ListNode pre = dummyNode;
            while(pre!=null && pre.next!=null){
                if(pre.next.val<nodeToInsert.val){
                    pre = pre.next;
                    continue;
                }
                cur.next = nodeToInsert.next;
                nodeToInsert.next = pre.next;
                pre.next = nodeToInsert;
                break;
            }
        }
        return dummyNode.next;
    }


}

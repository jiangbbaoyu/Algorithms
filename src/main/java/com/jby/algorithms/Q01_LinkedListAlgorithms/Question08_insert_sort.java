package com.jby.algorithms.Q01_LinkedListAlgorithms;

import org.junit.Test;

/**
 * leetcode 147. 对链表进行插入排序
 */
public class Question08_insert_sort {

    public ListNode insertionSortList(ListNode head) {
        if(head==null){
            return head;
        }

        ListNode newDummyNode = new ListNode();

        ListNode dummyNode = new ListNode();
        dummyNode.next= head;
        ListNode pre = dummyNode;

        ListNode curr = head;
        while(curr!=null){ // 对于每个节点都要进行比较，并插入，性能差
            ListNode next = curr.next;
            pre.next =next;
            curr.next =null;
            insert(newDummyNode,curr);

            curr = next;
        }

        return newDummyNode.next;
    }

    private void insert(ListNode head, ListNode node){

        ListNode pre = head;
        ListNode cur = head.next;

        while(cur!=null){
            if(cur.val>node.val){
                pre.next=node;
                node.next=cur;
                break;
            }
            pre =cur;
            cur = cur.next;
        }

        if(cur==null){
            pre.next = node;
        }
    }

    /**
     * 更优解
     * 需要一个指针指向当前已排序的最后一个位置，这里用的是cur指针
     * 需要另外一个指针pre,每次从表头循环
     *
     * 每次拿出未排序的节点，先和前驱比较，如果大于或者等于前驱，该节点就不用插入了，直接进入下一次循环
     * 如果前驱小，则进入内层循环，依次和pre指针比较，插入对应位置即可。
     * @param head
     * @return
     */
    public ListNode insertionSortList2(ListNode head) {
        ListNode dummyNode = new ListNode();
        dummyNode.next = head;
        ListNode pre = null;

        ListNode cur = head;
        while(cur!=null && cur.next!=null){
            if(cur.val<=cur.next.val){
                cur =cur.next;
                continue;
            }

            ListNode nodeToInsert = cur.next;
            pre = dummyNode;

            while(pre.next!=null && pre.next.val<=cur.next.val){
                pre =pre.next;
            }

            cur.next = nodeToInsert.next;
            nodeToInsert.next= pre.next;
            pre.next = nodeToInsert;
        }
        return dummyNode.next;
    }

    @Test
    public void test(){
        ListNode node1 = new ListNode(-1);
        ListNode node2 = new ListNode(5);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(0);
        node1.next=node2;
//        node2.next=node3;
//        node3.next=node4;
//        node4.next=node5;
//        node5.next=null;

        System.out.println(insertionSortList(node1));
    }
}

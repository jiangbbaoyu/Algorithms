package com.jby.algorithms.Q01_LinkedListAlgorithms;

import org.junit.Test;

/**
 * leetcode 86 分隔链表
 * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
 */
public class Question03_partition_list {

    /**
     * 使用四个指针维护小于x元素、大于等于x元素构成的两个链表的首尾指针， 构造两个链表后，将这两个链表连接起来即可
     * @param head
     * @param x
     * @return
     */
    public ListNode partition(ListNode head, int x) {

        ListNode lHead =null;
        ListNode lTail= null;

        ListNode geHead =null;
        ListNode geTail= null;

        ListNode curr=head;
        while(curr!=null){
            if (curr.val<x){
                if(lTail == null){
                   lHead=curr;
                   lTail=curr;
                }else{
                    lTail.next=curr;
                    lTail=curr;
                }
            }else{
                if(geTail == null){
                    geHead=curr;
                    geTail=curr;
                }else{
                    geTail.next=curr;
                    geTail=curr;
                }
            }
            curr=curr.next;
        }

        if(lHead!=null){
            lTail.next=geHead;
        }else{
            lHead=geHead;
        }

        if(geTail!=null){
            geTail.next=null;
        }
        return lHead;
    }

    /**
     * 进阶问题
     * 给你一个链表的头节点 head 和一个特定值 x ，
     * 对链表进行分隔，使得所有 小于 x 的节点都出现在左侧，等于x的节点出现在中间，大于x 的节点出现在右侧
     */
    public ListNode partition2(ListNode head, int x) {

        ListNode lHead =null;
        ListNode lTail= null;

        ListNode eHead =null;
        ListNode eTail= null;

        ListNode gHead =null;
        ListNode gTail= null;

        ListNode curr=head;

        while(curr!=null){
            if (curr.val<x){
                if(lTail == null){
                    lHead=curr;
                    lTail=curr;
                }else{
                    lTail.next=curr;
                    lTail=curr;
                }
            }else if(curr.val==x){
                if(eTail == null){
                    eHead=curr;
                    eTail=curr;
                }else{
                    eTail.next=curr;
                    eTail=curr;
                }
            } else{
                if(gTail == null){
                    gHead=curr;
                    gTail=curr;
                }else{
                    gTail.next=curr;
                    gTail=curr;
                }
            }
            curr=curr.next;
        }

        ListNode newHead=null;
        if(lHead!=null){
            lTail.next=eHead;
            newHead =lHead;
        }else{
            newHead=eHead;
        }

        if (newHead!=null){
            eTail.next=gHead;
        }else {
            newHead=gHead;
        }


        if(gTail!=null){
            gTail.next=null;
        }
        return newHead;
    }

    /**
     * leetcode 328 奇偶链表问题
     * 给定单链表的头节点 head ，将所有索引为奇数的节点和索引为偶数的节点分别组合在一起，然后返回重新排序的列表。
     * @param head
     * @return
     */
    public ListNode oddEvenList(ListNode head) {
        int idx =1;
        ListNode oddHead=null;
        ListNode oddTail=null;
        ListNode evenHead=null;
        ListNode evenTail=null;

        ListNode cur=head;
        while(cur!=null){

            if(idx%2==1){
                if(oddHead==null){
                    oddHead=cur;
                    oddTail=cur;
                }else{
                    oddTail.next=cur;
                    oddTail=cur;
                }
            }else{
                if(evenHead==null){
                    evenHead=cur;
                    evenTail=cur;
                }else{
                    evenTail.next=cur;
                    evenTail=cur;
                }
            }
            idx++;
            cur=cur.next;
        }

        if(oddHead!=null){
            oddTail.next=evenHead;  //连接奇数链表和偶数链表
        }
        if(evenTail!=null){
            evenTail.next=null;  // break the cycle
        }
        return oddHead;
    }



    @Test
    public void test1(){

        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(4);
        ListNode node4 = new ListNode(3);
        ListNode node5 = new ListNode(1);
        node1.next=node2;
        node2.next=node3;
        node3.next=node4;
        node4.next=node5;
        node5.next=null;

        ListNode res = partition(node1, 3);
        while (res!=null){
            System.out.println(res.val);
            res=res.next;
        }
    }

    @Test
    public void test2(){

        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(4);
        ListNode node4 = new ListNode(3);
        ListNode node5 = new ListNode(1);
        ListNode node6 = new ListNode(3);
        node1.next=node2;
        node2.next=node3;
        node3.next=node4;
        node4.next=node5;
        node5.next=node6;
        node6.next=null;

        ListNode res = partition2(node1, 3);
        while (res!=null){
            System.out.println(res.val);
            res=res.next;
        }
    }
}

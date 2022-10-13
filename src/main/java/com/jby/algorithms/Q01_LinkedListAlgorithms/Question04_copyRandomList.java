package com.jby.algorithms.Q01_LinkedListAlgorithms;

import java.util.HashMap;

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

/**
 * leetcode 138. 复制带随机指针的链表
 * 给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，该指针可以指向链表中的任何节点或空节点。构造这个链表的 深拷贝。
 */
public class Question04_copyRandomList {

    /**
     * 解法1： 使用额外空间 空间复杂度O(N),时间复杂度O(N)
     * @param head
     * @return
     */
    public Node copyRandomList(Node head) {

        HashMap<Node,Node> nodes = new HashMap<Node,Node>();
        Node curr=head;
        while(curr!=null){ // 首先根据提供的链表构建新的节点。并将新旧节点分别作为k,v放入到Map中,使得可以通过旧节点找到对应的新节点
            nodes.put(curr,new Node(curr.val));
            curr=curr.next;
        }

        curr=head;
        while(curr!=null){// 根据旧节点的next,random指针，给对相应的新节点的next,random指针赋值
            Node currNew = nodes.get(curr);
            currNew.next=curr.next!=null?nodes.get(curr.next):null;
            currNew.random=curr.random!=null?nodes.get(curr.random):null;
            curr=curr.next;
        }
        return nodes.get(head);//返回克隆后节点的head
    }

    /**
     * 解法2： 空间复杂度优化到O(1)
     * 1.首先在原来链表的每个节点后面复制一个新的节点
     * 2.遍历添加新节点后的链表，根据前一个旧节点random指针的值给新节点的random指针赋值
     * 3.将包含新旧节点的链表拆分为旧节点链表和新节点链表
     * 4.返回新节点链表的head
     */
    public Node copyRandomList2(Node head) {
        if(head==null){
            return head;
        }

        Node curr=head;
        while(curr!=null){
            Node next = curr.next;
            Node currCopy = new Node(curr.val);// 链表节点扩充
            curr.next=currCopy;
            currCopy.next=next;
            curr=next;
        }

        curr=head;
        while(curr!=null){
            Node currCopy = curr.next;
            currCopy.random=curr.random!=null?curr.random.next:null;//维护新添加节点的random指针, random指针可能为null

            curr=currCopy.next;
        }

        curr=head;
        Node copyHead=curr.next;
        while(curr!=null&&curr.next!=null){
            Node next = curr.next;
            curr.next=curr.next.next; // 链表拆分
            curr=next;
        }
        return copyHead;
    }
}

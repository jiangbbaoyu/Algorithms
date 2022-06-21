package com.jby.algorithms.JianZhiOffer;

/**
 * 剑指 Offer 35. 复杂链表的复制
 * 请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指向链表中的任意节点或者 null。
 */
public class Question35 {

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

    public Node copyRandomList(Node head) {

        if(head==null){
            return null;
        }
        Node node = head;
        while(node!=null){
            Node nodeCopied = new Node(node.val);
            nodeCopied.next = node.next;
            node.next= nodeCopied;

            node=node.next.next;
        }

        node =head;
        while(node!=null){
            if(node.random!=null){
                node.next.random = node.random.next;
            }
            node =node.next.next;
        }

        Node headCopied = head.next;

        // 拆分链表
        // node =head;
        // Node nodeCopied = head.next;
        // while(node!=null){
        //     node.next = node.next.next;
        //     node = node.next;
        //     if(nodeCopied.next!=null){
        //         nodeCopied.next = nodeCopied.next.next;
        //         nodeCopied = nodeCopied.next;
        //     }
        // }

        // 链表拆分
        node =head;
        while(node!=null && node.next!=null){
            Node next = node.next;
            node.next = node.next.next;

            node =next;
        }
        return headCopied;
    }
}

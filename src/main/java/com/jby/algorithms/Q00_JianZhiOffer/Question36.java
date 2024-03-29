package com.jby.algorithms.Q00_JianZhiOffer;

import java.util.LinkedList;

/**
 * 剑指 Offer 36. 二叉搜索树与双向链表
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的 循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。
 */
public class Question36 {
    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }


    // 基于二叉排序树的迭代写法
    public Node treeToDoublyList(Node root) {
        if(root==null){
            return null;
        }

        Node head =null;
        Node tail =null;

        LinkedList<Node> stack = new LinkedList<Node>();
        Node cur =root;
        while(!stack.isEmpty() || cur!=null){
            while(cur!=null){
                stack.addFirst(cur);
                cur=cur.left;
            }

            cur = stack.removeFirst();

            if(head==null){
                head = cur;
                tail =cur;
            }else{
                tail.right=cur;
                cur.left =tail;  // 尾插法构造双向链表
                tail =cur;
            }

            cur =cur.right;
        }

        tail.right =head;  // 连接双向链表的首尾
        head.left=tail;

        return head;
    }


    Node head =null;
    Node tail =null;

    // 基于二叉搜索树的递归写法
    public Node treeToDoublyList2(Node root) {
        if(root==null){
            return root;
        }

        convert(root);
        head.left=tail;
        tail.right=head;

        return head;
    }

    private void convert(Node cur){
        if(cur==null){
            return;
        }

        convert(cur.left);

        if(head==null){
            head=cur;
            tail=cur;
        }else{
            tail.right=cur;
            cur.left=tail;
            tail =cur;
        }

        convert(cur.right);
    }
}

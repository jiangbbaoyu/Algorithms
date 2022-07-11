package com.jby.algorithms.JianZhiOffer;

import java.util.LinkedList;

/**
 * 剑指 Offer 54. 二叉搜索树的第k大节点
 * 给定一棵二叉搜索树，请找出其中第 k 大的节点的值
 *
 *  思路： 中序遍历的变形 ： 右 -> 中 -> 左
 */
public class Question54 {
    public int kthLargest(TreeNode root, int k) {

        if(root==null || k<1){
            throw new RuntimeException("input invalid ");
        }

        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();


        TreeNode cur = root;
        while(!stack.isEmpty() || cur!=null){
            while(cur!=null){
                stack.addFirst(cur);
                cur=cur.right;
            }

            cur = stack.removeFirst();

            if(k==1){
                return cur.val;
            }else{
                k--;
                cur = cur.left;
            }
        }
        return -1;
    }
}

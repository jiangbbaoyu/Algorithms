package com.jby.algorithms.Q00_JianZhiOffer;

/**
 * 剑指 Offer 28. 对称的二叉树
 * 请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
 */
public class Question28 {

    public boolean isSymmetric(TreeNode root) {
        if(root==null){
            return true;
        }

        return isSymmetric(root.left,root.right);
    }

    public boolean isSymmetric(TreeNode node1, TreeNode node2) {
        if(node1==null && node2==null){
            return true;
        }
        if(node1==null || node2==null){
            return false;
        }

//        if(node1.val!=node2.val){
//            return false;
//        }
//        return isSymmetric(node1.left,node2.right) && isSymmetric(node1.right,node2.left);

        return node1.val==node2.val && isSymmetric(node1.left,node2.right) && isSymmetric(node1.right,node2.left);
    }
}

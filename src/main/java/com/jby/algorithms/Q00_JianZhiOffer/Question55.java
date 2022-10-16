package com.jby.algorithms.Q00_JianZhiOffer;

public class Question55 {

    /**
     * 剑指 Offer 55 - I. 二叉树的深度
     * 输入一棵二叉树的根节点，求该树的深度。从根节点到叶节点依次经过的节点（含根、叶节点）形成树的一条路径，最长路径的长度为树的深度
     * 思路： 基于后序遍历； 空节点 depth为0； 叶节点depth为1；
     */
    public int maxDepth(TreeNode root) {
        if(root==null){
            return 0;
        }

        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);

        return Math.max(leftDepth,rightDepth)+1;
    }

    public boolean isBalanced(TreeNode root) {
        return isBalancedTree(root)>=0;
    }

    /**
     * 剑指 Offer 55 - II. 平衡二叉树
     * 输入一棵二叉树的根节点，判断该树是不是平衡二叉树。如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树
     *
     * 思路：  基于后序遍历；逐个节点判断 左右子树的高度差
     */
    public int isBalancedTree(TreeNode node) {

        if(node==null){
            return 0;
        }

        int leftDepth = isBalancedTree(node.left);
        int rightDepth = isBalancedTree(node.right);

        if(leftDepth<0 || rightDepth<0){
            return -1;  // not balanced
        }
        if(Math.abs(leftDepth-rightDepth)>1){
            return -1; // not balanced
        }

        return Math.max(leftDepth,rightDepth)+1;
    }


}

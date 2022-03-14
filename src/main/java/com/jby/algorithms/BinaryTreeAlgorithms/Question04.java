package com.jby.algorithms.BinaryTreeAlgorithms;


/**
 * TODO 动态规划
 * 树形 DP : 根据左子树的信息和右子树的信息得到本棵树的信息
 *           leetcode 104 二叉树的最大深度
 *
 */
public class Question04 {

    /**
     * leetcode 110 : 给定一个二叉树，判断它是否是高度平衡的二叉树 (一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 )
     * 思路： 对于左子树和右子树，分别返回左右子树的高度和是否为平衡二叉树，
     *       如果左右子树均为平衡二叉树，且左右子树的高度差不超过1，则该二叉树为平衡二叉树
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        return isBalancedTree(root)!=-1;
    }
    private int isBalancedTree(TreeNode node) {

        if(node==null){
            return 0;
        }

        int lh = isBalancedTree(node.left);
        int rh = isBalancedTree(node.right);

        // 后序遍历
        if(lh==-1 || rh==-1){
            return -1;
        }
        if(lh-rh>1 || lh-rh<-1){
            return -1;
        }
        return  lh>rh?lh+1:rh+1;
    }









}

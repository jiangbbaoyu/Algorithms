package com.jby.algorithms.Q07_BinaryTreeAlgorithms;

import org.junit.Test;

public class Question07_maxPathSum {

    /**
     * leetcode 124. 二叉树中的最大路径和 （思路类似 leetcode 53. 最大子数组和 ）
     * 路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。
     * 同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
     * 路径和 是路径中各节点值的总和。
     * 给你一个二叉树的根节点 root ，返回其 最大路径和
     * 实例：
     * 输入：root = [-10,9,20,null,null,15,7]
     * 输出：42
     * 解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
     *
     * 思路： 后序遍历，
     *       如果 max(左子树路径和，右子树路径和） + 本节点的值 <0 ，则以本节点为根的子树的路径和返回0
     *       最大路径 不可能包含该
     */
    private int maxSum = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {

        doMaxPathSum(root);
        return maxSum;

    }
    private int doMaxPathSum(TreeNode root){
        if(root==null){
            return 0;
        }
        int leftVal = doMaxPathSum(root.left);
        int rightVal = doMaxPathSum(root.right);

        int sumTmp = leftVal+rightVal+root.val;

        maxSum = sumTmp > maxSum? sumTmp : maxSum;

        int childMax = leftVal>rightVal? leftVal:rightVal;

        return childMax+root.val >0 ? childMax+root.val:0;
    }

    @Test
    public void test(){

        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        treeNode1.left=treeNode2;
        treeNode1.right=treeNode3;

        int i = maxPathSum(treeNode1);
        System.out.println(i);


    }
}

package com.jby.algorithms.Q07_BinaryTreeAlgorithms;


public class Question06_lowestCommonAncestor {

    /**
     * leetcode 236. 二叉树的最近公共祖先
     * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先 (常规的二叉树，非二叉搜索树)
     * 所有节点的值都是唯一的。
     * p、q 为不同节点且均存在于给定的二叉树中。
     *
     * 思路：
     *       后序遍历二叉树， 当碰到 p或q节时，就将该节点值向上返回
     *       如果一个节点的左右子树返回的结果均不为空，则说明 p,q分别在其左右子树中，则将该节点返回
     *       如果一个节点的左右子树返回的结果均为空，则说明 p,q不存在于该节点的子树中，返回null
     *       如果一个节点的左右子树返回的结果有一个不为空，则说明该节点的这个子树中只包含一个p,q 节点，将该子树的返回值作为该节点的返回值
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null)  return null;
        if(root==p || root ==q) return root;

        TreeNode leftNode = lowestCommonAncestor(root.left,p,q);
        TreeNode rightNode = lowestCommonAncestor(root.right,p,q);

        // 后序遍历
        if(leftNode!=null && rightNode!=null){
            return root;
        }

        return leftNode!=null ? leftNode:rightNode;
    }
}

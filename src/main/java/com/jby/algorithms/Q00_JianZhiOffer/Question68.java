package com.jby.algorithms.Q00_JianZhiOffer;


/**
 * 剑指 Offer 68 - I. 二叉搜索树的最近公共祖先
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 */
public class Question68 {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(p.val>q.val){
            return findLowestCommonAncestor(root, p,q);
        }else{
            return findLowestCommonAncestor(root, q,p);
        }
    }

    private TreeNode findLowestCommonAncestor(TreeNode root, TreeNode bigNode, TreeNode smallNode){
        if(root==null){
            return null;
        }

        if(root.val >= smallNode.val && root.val <= bigNode.val){
            return root;
        }

        if(root.val>bigNode.val){
            return findLowestCommonAncestor(root.left,bigNode,smallNode);
        }

        if(root.val<smallNode.val){
            return findLowestCommonAncestor(root.right,bigNode,smallNode);
        }

        return null;
    }

    /**
     * 剑指 Offer 68 - II. 二叉树的最近公共祖先
     * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先 (常规的二叉树，非二叉搜索树)
     * 所有节点的值都是唯一的。
     * p、q 为不同节点且均存在于给定的二叉树中。
     *
     * 思路： 更加通用的解法
     *       后序遍历二叉树， 当碰到 p或q节时，就将该节点值向上返回
     *       如果一个节点的左右子树返回的结果均不为空，则说明 p,q分别在其左右子树中，则将该节点返回
     *       如果一个节点的左右子树返回的结果均为空，则说明 p,q不存在于该节点的子树中，返回null
     *       如果一个节点的左右子树返回的结果有一个不为空，则说明该节点的这个子树中只包含一个p,q 节点，将该子树的返回值作为该节点的返回值
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null){
            return null;
        }

        if(root==p || root ==q){
            return root;
        }

        TreeNode leftNode = lowestCommonAncestor2(root.left,p,q);
        TreeNode rightNode = lowestCommonAncestor2(root.right,p,q);

        if(leftNode!=null && rightNode!=null){
            return root;
        }

        return leftNode!=null ? leftNode:rightNode;
    }

}

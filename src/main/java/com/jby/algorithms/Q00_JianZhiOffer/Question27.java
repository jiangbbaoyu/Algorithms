package com.jby.algorithms.Q00_JianZhiOffer;

import java.util.LinkedList;

/**
 * 剑指 Offer 27. 二叉树的镜像
 * 请完成一个函数，输入一个二叉树，该函数输出它的镜像。
 */
public class Question27 {

    // 递归解法
    public TreeNode mirrorTree(TreeNode root) {
        if(root==null){
            return root;
        }

        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        mirrorTree(root.left);
        mirrorTree(root.right);
        return root;
    }


    // 基于先序遍历的迭代写法
    public TreeNode mirrorTree2(TreeNode root) {
        if(root==null){
            return root;
        }
        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();

            TreeNode tmp = node.left;
            node.left=node.right;
            node.right=tmp;

            if(node.left!=null){
                stack.push(node.left);
            }
            if(node.right!=null){
                stack.push(node.right);
            }
        }
        return root;
    }
}

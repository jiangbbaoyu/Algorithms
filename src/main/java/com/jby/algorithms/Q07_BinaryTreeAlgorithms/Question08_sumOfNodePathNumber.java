package com.jby.algorithms.Q07_BinaryTreeAlgorithms;

/**
 * leetcode 129. 求根节点到叶节点数字之和
 * 给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
 * 每条从根节点到叶节点的路径都代表一个数字：
 * 例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
 * 计算从根节点到叶节点生成的 所有数字之和
 * eg:
 * 输入：root = [1,2,3]
 * 输出：25
 * 从根到叶子节点路径 1->2 代表数字 12
 * 从根到叶子节点路径 1->3 代表数字 13
 * 因此，数字总和 = 12 + 13 = 25
 */
public class Question08_sumOfNodePathNumber {

    int sum =0;
    public int sumNumbers(TreeNode root) {
        dfs(root,0);
        return sum;
    }

    private void dfs(TreeNode node ,int cur){
        if(node==null){
            return;
        }
        cur = cur*10 + node.val;
        if(node.left==null && node.right==null){
            sum += cur;
            return;
        }
        dfs(node.left,cur);
        dfs(node.right,cur);
    }
}

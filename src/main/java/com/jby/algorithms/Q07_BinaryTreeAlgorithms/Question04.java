package com.jby.algorithms.Q07_BinaryTreeAlgorithms;


import org.junit.Test;

/**
 * TODO 动态规划
 * 树形 DP : 根据左子树的信息和右子树的信息得到本棵树的信息
 *           leetcode 104 二叉树的最大深度
 *           leetcode 543 二叉树的最大直径
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

    /**
     * leetcode 543 二叉树的直径
     * 一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
     * 思路： 后续遍历，
     *       如果 节点为空，则返回0;
     *       否则递归计算 左子树和右子树的直径
     *       使用 左子树和右子树的直径 之和 更新最大直径
     *       然后返回 左子树和右子树的直径中最大的 +1， 给上层节点, 此时本节点的左子树和右子树只有一个会存在路径上，因此要返回最大的
     */
    private int maxLen =0;
    public int diameterOfBinaryTree(TreeNode root) {
        getDiameter(root);
        return maxLen;
    }
    private int getDiameter(TreeNode root){
        if(root==null){
            return 0;
        }
        int leftLen =getDiameter(root.left);
        int rightLen = getDiameter(root.right);
        maxLen = maxLen > leftLen + rightLen ? maxLen : leftLen + rightLen;
        return  Math.max(leftLen,rightLen)+1;
    }

    @Test
    public void test2(){
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        node1.left=node2;
        int i = diameterOfBinaryTree(node1);
        System.out.println(i);
    }



    /**
     * leetcode 687. 最长同值路径
     *          给定一个二叉树的 root ，返回 最长的路径的长度 ，这个路径中的 每个节点具有相同值
     *
     *  思路：
     *        如果子树为空，该子树的同值最长路径一定为0；
     *        后序遍历， 分别拿到左右子树的同值最长路径后，
     *            如果该节点与左子节点值不一样，则经过该节点向左的同值最长路径长度为0，否则同值最长路径长度在左子树基础上+1
     *            如果该节点与右子节点值不一样，则经过该节点向右的同值最长路径长度为0，否则同值最长路径长度在右子树基础上+1
     */

    private int maxCount=0;
    public int longestUnivaluePath(TreeNode root) {
        if(root==null){
            return 0;
        }
        dfs3(root);
        return maxCount;
    }

    private int dfs3(TreeNode node){
        if(node.left==null&&node.right==null){
            return 0;
        }

        int left= node.left==null?0:dfs3(node.left);
        int right= node.right==null?0:dfs3(node.right);

        if(node.left!=null){
            if(node.val==node.left.val){
                left++;
            }else{
                left=0;
            }

        }

        if(node.right!=null){
            if(node.val==node.right.val){
                right++;
            }else{
                right=0;
            }
        }

        maxCount =maxCount>left+right? maxCount:left+right;
        return right>left?right:left;

    }

    @Test
    public void test(){
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        node1.left=node2;
        node1.right=node3;
    }









}

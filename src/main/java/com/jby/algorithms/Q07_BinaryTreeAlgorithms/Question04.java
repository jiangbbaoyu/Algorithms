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
     * leetcode 543 二叉树的直径  一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
     * 思路：二叉树的最小深度一致， 对于子树为空的情况，不在对该子树进行递归，直接给出该子树的信息
     */
    private int max =0;
    public int diameterOfBinaryTree(TreeNode root) {
        dfs2(root);
        return max;
    }
    private int dfs2(TreeNode node){
        if(node.left==null && node.right==null){
            return 0;
        }
        int left=node.left==null?0:dfs2(node.left)+1;// 如果左子树为空，该节点+左子树的最大直径就是0
        int right=node.right==null?0:dfs2(node.right)+1;// 如果右子树为空，该节点+右子树的最大直径就是0
        max=max>left+right?max:left+right;
        return left>right?left:right;
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

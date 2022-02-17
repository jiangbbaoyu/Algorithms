package com.jby.algorithms.BinaryTreeAlgorithms;

import org.junit.Test;

import java.util.LinkedList;

/**
 * 二叉搜索树问题
 */
public class Question02 {

    /**
     * leetcode 98. 验证二叉搜索树
     *
     * 有效 二叉搜索树定义如下：
     *  节点的左子树只包含 小于 当前节点的数。
     *  节点的右子树只包含 大于 当前节点的数。
     *  所有左子树和右子树自身必须也是二叉搜索树。
     *
     * 思路：  二叉搜索树的中序遍历序列是递增的  (不能使用前序遍历的思路去解决)
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        if (root==null){
            return true;
        }

        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
        Integer preValue =null;
        TreeNode cur=root;
        while(!stack.isEmpty()||cur!=null){

            while(cur!=null){
                stack.push(cur);
                cur=cur.left;
            }
            cur=stack.pop();

            if(preValue!=null && preValue>=cur.val){
                return false;
            }
            preValue=cur.val;

            cur=cur.right;
        }
        return true;
    }

    // 中序遍历的递归解法
    private Integer preValue; // Integer类型的 preValue变量不能作为递归函数的参数来传递，因为在方法内部的改变无法传递到方法外部 （值类型）
    public boolean isValidBST2(TreeNode root) {
        return validate(root);
    }

    private boolean validate(TreeNode node){
        if(node==null){
            return true;
        }

        if(!validate(node.left)){
            return false;
        }

        if(preValue!=null && preValue>=node.val){
            return false;
        }
        preValue=node.val;

        if(!validate(node.right)){
            return false;
        }

        return true;
    }

    /**
     * leetcode 897 (剑指 Offer II 052)  展平二叉搜索树
     * 给你一棵二叉搜索树，请 按中序遍历 将其重新排列为一棵递增顺序搜索树，使树中最左边的节点成为树的根节点，并且每个节点没有左子节点，只有一个右子节点。
     * @param root
     * @return
     */
    public TreeNode increasingBST(TreeNode root) {
        if (root==null){
            return null;
        }

        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
        TreeNode newRoot =null;
        TreeNode newCur =null;
        TreeNode cur=root;
        while(!stack.isEmpty()||cur!=null){

            while(cur!=null){
                stack.push(cur);
                cur=cur.left;
            }
            cur=stack.pop();

            if(newRoot==null){
                newRoot=cur;
                newCur=cur;
            }else{
                cur.left=null;
                // cur.right=null;// 后面还要用到 cur.right， 因此不能将其在此处置为null

                newCur.right=cur;
                newCur=cur;
            }

            cur=cur.right;
        }
        return newRoot;
    }

    // 递归解法
    private TreeNode newRoot;
    private TreeNode newCur;
    public TreeNode increasingBST2(TreeNode root) {
        increasing(root);
        return newRoot;
    }

    private void increasing(TreeNode cur){
        if(cur==null){
            return;
        }

        increasing(cur.left);

        cur.left=null;
        if(newRoot==null){
            newRoot=cur;
            newCur=cur;
        }else{

            newCur.right=cur;
            newCur=cur;
        }

        increasing(cur.right);
    }


    /**
     * leetcode 99. 恢复二叉搜索树
     * 给你二叉搜索树的根节点 root ，该树中的 恰好 两个节点的值被错误地交换。请在不改变其结构的情况下，恢复这棵树
     * 思路:
     * 有两种情况
     * 如果值被错误交换的两个节点在中序遍历序列中是相邻的，此时中序遍历序列中只有一个降序对， 只需交换降序对的这两个node的值即可 eg 1,3,2,4  交换，3，2即可
     * 如果值被错误交换的两个节点在中序遍历序列中不是相邻的，此时中序遍历序列中有两个降序对， 需将第一个降序对的前一个node值，与第二个降序对的后一个node值进行交换 eg 3,2,1 需要将3，2中的3 与2，1中的1交换
     * @param root
     */
    public void recoverTree(TreeNode root) {

        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();

        TreeNode cur= root;
        TreeNode pre=null;

        TreeNode node1=null;
        TreeNode node2=null;
        while(!stack.isEmpty() || cur!=null){
            while(cur!=null){
                stack.push(cur);
                cur=cur.left;
            }

            cur=stack.pop();

            if(pre!=null && pre.val>cur.val){
                if(node1==null){
                    // 找到第一个降序对
                    node1=pre;
                    node2=cur;
                }else {
                    // 找到第二个降序对
                    node2=cur;
                }
            }
            pre=cur;
            cur=cur.right;
        }
        // 交换逆序对节点值
        int tmp=node1.val;
        node1.val=node2.val;
        node2.val=tmp;
    }


    @Test
    public void test1(){

        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(1);
        node1.left=node2;
        boolean isvalid = isValidBST2(node1);
        System.out.println(isvalid);
    }

    @Test
    public void test11(){
        Integer i=123;
        setMethod(i);
        System.out.println(i);
    }

    private void setMethod(Integer i) {
        i=i+1;
        System.out.println(i);
    }

    @Test
    public void test2(){

        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(1);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(2);
        node1.left=node2;
        node1.right=node3;
        node3.left=node4;
        recoverTree(node1);

        System.out.println(node1);
    }



}

package com.jby.algorithms.BinaryTreeAlgorithms;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.*;

/**
 * 二叉树的其他经典问题
 */
public class Question05 {

    /**
     * leetcode 236. 二叉树的最近公共祖先 （也可以解二叉搜索树的最近公共祖先）
     * 思路： 后序递归遍历，总共有两种情况：
     *      1）  p或q本身为最近公共最先，该情况在找到p或q后作为返回值即可
     *      2）  p或q在某个节点的不同子树上，此时该节点为pq的最近公共祖先
     *
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null){
            return null;
        }

        if( root==p || root == q){
            return root; //  发现了p或q直接返回
        }

        TreeNode node1 = lowestCommonAncestor(root.left,p,q);
        TreeNode node2 = lowestCommonAncestor(root.right,p,q);

        if(node1!=null && node2!=null){
            return root; //在 root的左右子树中分别发现了p,q, 此时root为pq的最近公共祖先
        }else if(node1!=null){
            return node1;// 仅在左子树中发现了p或q，返回p或q
        }else if(node2!=null){
            return node2;// 仅在右子树中发现了p或q，返回p或q
        }
        return null;
    }


    /**
     * nowcoder JZ8 二叉树的下一个结点
     * https://www.nowcoder.com/practice/9023a0c988684a53960365b889ceaf5e?tpId=13&tqId=11210&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
     * 给定一个二叉树其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。注意，树中的结点不仅包含左右子结点，同时包含指向父结点的next指针
     * 思路： 两种情况，pNode的右子树是否为空
     *
     * 拓展：对于该二叉树的前一个节点也分两种情况：
     *      1）如果该节点左子树不为null,前驱节点为左子树的最右侧节点
     *      2）如果该节点左子树为null,前驱节点为该节点的祖先，该祖先节点的右子树中包含了该节点
     *
     * @param pNode
     * @return
     */
    public TreeLinkNode GetNext(TreeLinkNode pNode) {

        TreeLinkNode next =null;

        if(pNode.right!=null){
            // pNode 的右子树不为null， 后继节点为右子树中最左侧节点
            next= pNode.right;
            while(next.left!=null){
                next=next.left;
            }
        }else{
            // pNode 的右子树为null， 后继节点为pNode的祖先节点，pNode包含在该祖先节点的左子树中
            TreeLinkNode parent = pNode.next;
            TreeLinkNode child = pNode;

            while(parent!=null&&parent.left!=child){
                child=parent;
                parent=parent.next;
            }
            next =parent; // parent 不为null的话，此时parent的左子树中包含了pNode
        }
        return next;
    }


    /**
     * leetcode 297. 二叉树的序列化与反序列化  (先序， TODO 层序)
     * @param root
     * @return
     */
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuffer sb=new StringBuffer();
        serialize(root,sb);
        String data =sb.toString();
        return data.substring(0,data.length()-1);

    }

    private void serialize(TreeNode node, StringBuffer sb){
        if(node==null){
            sb.append("#_");
            return;
        }

        sb.append(node.val+"_");  // 先序遍历
        serialize(node.left,sb);
        serialize(node.right,sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {

        String[] nodesVal = data.split("_");
        return deserialize(nodesVal);
    }

    private int idx =0;
    private TreeNode deserialize(String[] nodesVal){

        String nodeVal = nodesVal[idx++];
        if(nodeVal.equals("#")){
            return null;
        }

        TreeNode node = new TreeNode(Integer.parseInt(nodeVal)); // 先序遍历
        node.left=deserialize(nodesVal);
        node.right=deserialize(nodesVal);
        return node;
    }

    /**
     * leetcode 101 对称二叉树  (相似： leetcode 100. 相同的树)
     * 思路： 同时后序遍历root的左右子树，
     *       对于左右子树中对应位置的两个节点，只有当一个节点的所有子节点与另一个节点对应位置的子节点值相同，且这两个节点的值也相同时。这两个节点构成的两个子树才为对称的
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if(root==null){
            return true;
        }
        return isSymmetric(root.left,root.right);
    }
    private boolean isSymmetric(TreeNode left,TreeNode right) {
        if(left==null && right==null){
            return true;
        }
        if(left==null || right==null){
            return false;
        }
        if (isSymmetric(left.left,right.right) && isSymmetric(left.right,right.left)
                && left.val==right.val){ // 后序遍历，
            return true;
        }
        return false;
    }

    /**
     * 二叉树的镜像
     * @param root
     * @return
     */
    public TreeNode mirrorTree(TreeNode root) {
        if(root==null){
            return null;
        }
        TreeNode tmp=root.left;
        root.left=root.right;
        root.right=tmp;

        mirrorTree(root.left);
        mirrorTree(root.right);
        return root;
    }

    /**
     * leetcode 111. 二叉树的最小深度 最小深度是从根节点到最近叶子节点的最短路径上的节点数量
     * 思路：由于是从根节点到最近叶子节点的最短路径，因此不去null的节点统计信息
     */
    public int minDepth(TreeNode root) {
        if(root==null){
            return 0;
        }
        return findMinDepth(root);
    }

    private int findMinDepth(TreeNode node) {
        if(node.left==null && node.right==null){
            return 1;
        }
        if(node.left==null){
            return minDepth(node.right)+1;
        }else if(node.right==null){
            return minDepth(node.left)+1;
        }else{
            return Math.min(minDepth(node.left),minDepth(node.right))+1;
        }
    }


    /**
     * leetcode 513 二叉树最底层最左边的值
     * 思路：1）基于先序遍历的深度优先遍历，由于是先序遍历，因此每次深度遍历到下面一层时，一定是先访问到下面一层最左边的节点
     *      2）基于层序遍历来做
     *
     *      拓展，如果是访问二叉树最底层最右边的值 ，只需基于 头->右->左的先序遍历进行深度遍历即可
     */
    private int maxLevel=0;
    private int res;
    public int findBottomLeftValue(TreeNode root) {
        dfs(root,1);
        return res;
    }
    private void dfs(TreeNode node,int currLevel){
        if(node==null){
            return;
        }
        if(currLevel>maxLevel){  //先序遍历
            maxLevel=currLevel;
            res=node.val;
        }

        dfs(node.left,currLevel+1);
        dfs(node.right,currLevel+1);
    }


    /**
     * leetcode 105  前序遍历和中序遍历构建二叉树  (leetcode 106 后序+中序构建二叉树的逻辑与之类似)
     *
     * 思路: 对于先序遍历的第一个元素，在中序遍历中找到其位置，
     *      该节点会将中序遍历分为两部分，左部分用来构建左子树；右部分用来构建右子树；
     *      然后根据左部分的元素个数，从先序遍历数组拿出相应个数的元素来构建左子树；剩余元素用来构建右子树
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(preorder,inorder,0,preorder.length-1,0,inorder.length-1);
    }
    private TreeNode buildTree(int[] preorder, int[] inorder,int preStart,int preEnd,int inStart,int inEnd ) {

        if(preStart>preEnd){
            return null;
        }

        int val = preorder[preStart];
        TreeNode node = new TreeNode(val);
        int idx = findValInOrder(inorder, val,inStart,inEnd);       // 前序

        // idx-inStart ： 左子树的元素个数
        // preStart + idx-inStart ；先序数组中用来构建左子树的最后一个元素的索引下标

        node.left=buildTree(preorder,inorder,preStart+1,preStart+idx-inStart,inStart,idx-1);
        node.right=buildTree(preorder,inorder,preStart+idx-inStart+1,preEnd,idx+1,inEnd);


        return node;
    }
    private int findValInOrder(int[] inorder,int val,int left ,int right){

        for(int i=left;i<=right;i++){
            if(inorder[i]==val){
                return i;
            }
        }
        return -1;
    }




    @Test
    public void test(){

        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        node1.left=node2;
        node1.right=node3;
        node3.left=node4;
        node3.right=node5;

        String serialize = serialize(node1);
        System.out.println(serialize);
        TreeNode root = deserialize(serialize);
        System.out.println(root);
    }


}
class TreeLinkNode {
    int val;
    TreeLinkNode left = null;
    TreeLinkNode right = null;
    TreeLinkNode next = null;

    TreeLinkNode(int val) {
        this.val = val;
    }
}

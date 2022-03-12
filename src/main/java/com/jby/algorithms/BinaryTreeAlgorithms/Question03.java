package com.jby.algorithms.BinaryTreeAlgorithms;

import org.junit.Test;

import java.util.LinkedList;

/**
 * 完全二叉树
 */
public class Question03 {

    /**
     * leetcode 958 完全二叉树判定
     * @param root
     * @return
     */
    public boolean isCompleteTree(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        boolean findRightChildIsNull=false;
        queue.addFirst(root);


        while(!queue.isEmpty()){

            int levelSize=queue.size();

            while(levelSize>0){
                TreeNode node = queue.removeLast();

                if(node.right!=null&&node.left==null){ // 一个节点有右孩子而没有左孩子，不是完全二叉树
                    return false;
                }
                // 发现一个节点右孩子为空后，层序遍历中该节点后序的节点必须都为叶子节点； 否则不是完全二叉树
                if(findRightChildIsNull && (node.left!=null || node.right!=null)){
                    return false;
                }

                if(node.left!=null){
                    queue.addFirst(node.left);
                }
                if(node.right!=null){
                    queue.addFirst(node.right);
                }else{
                    findRightChildIsNull=true;
                }
                levelSize--;
            }
        }
        return true;
    }

    /**
     * leetcode 222  给定一棵 完全二叉树 的根节点 root ，求出该树的节点个数
     * 思路： 完全二叉树的高度可以直接通过不断地访问左孩子就可以获取
     *       对于一个完全二叉树，
     *       如果左右子树的高度一致(最后一层最后出现的节点必然在右子树中)，说明左子树是满二叉树, 此时根据左子树高度直接求出左子树节点个数。然后进一步判断右子树的节点数
     *       如果左子树高于右子树  (最后一层最后出现的节点必然在左子树中)，则说明右子树是深度小于左子树的满二叉树, 此时根据右子树高度直接求出右子树节点个数，然后进一步判断左子树的节点数
     *
     * @param root
     * @return
     */
    public int countNodes(TreeNode root) {

        if(root==null){
            return 0;
        }
        int lh=getDepth(root.left);
        int rh=getDepth(root.right);

        if(lh==rh){
//            return (1<<lh) +countNodes(root.right);
            return 1 + ((1<<lh)-1) + countNodes(root.right); // root节点 + 左子树节点（满）+ 右子树节点
        }else{  // lh>rh
//            return (1<<rh) + countNodes(root.left);
            return 1 + ((1<<rh)-1) + countNodes(root.left);// root节点 + 右子树节点（满）+ 左子树节点
        }
    }

    private int getDepth(TreeNode node){
        int depth =0;
        while(node!=null){
            depth++;
            node=node.left;
        }
        return depth;
    }


    @Test
    public void test(){
        System.out.println((1<<2)-1);

    }
}


/**
 *  leetcode 919. 完全二叉树插入器  (层序遍历)
 */
class CBTInserter{

    private TreeNode root;
    private LinkedList<TreeNode> queue= new LinkedList<TreeNode>();
    private TreeNode cur;
    public CBTInserter(TreeNode root) {
        this.root=root;
        queue.addFirst(root);

        while(!queue.isEmpty()){
            TreeNode node = queue.removeLast();
            if(node.left!=null){
                queue.addFirst(node.left);
            }
            if(node.right!=null){
                queue.addFirst(node.right);
            }
            if(node.left==null || node.right==null){
                cur = node;  //定位到第一个存在子节点为空的node
                break;
            }
        }
    }
    // 将节点插入到cur,如果cur左右子节点均不为空则从队列中取出下一个叶子节点
    public int insert(int val) {

        if(cur.left!=null && cur.right!=null){
            cur=queue.removeLast();
        }

        if(cur.left==null){
            cur.left=new TreeNode(val);
            queue.addFirst(cur.left);
        }else if(cur.right==null){
            cur.right=new TreeNode(val);
            queue.addFirst(cur.right);
        }
        return cur.val;
    }

    public TreeNode get_root() {
        return this.root;

    }
}

package com.jby.algorithms.JianZhiOffer;



public class Question08 {

    /**
     * nowcoder : NC 279 , JZ8 二叉树的下一个结点
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

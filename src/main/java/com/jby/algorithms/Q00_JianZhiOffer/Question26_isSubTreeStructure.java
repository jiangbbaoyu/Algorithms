package com.jby.algorithms.Q00_JianZhiOffer;

/**
 * 剑指 Offer 26. 树的子结构
 * 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
 * B是A的子结构， 即 A中有出现和B相同的结构和节点值
 *
 * 思路： 先在A中定位到与B根节点值相同的节点， 然后比较以该节点为根的子树是否包含B
 */
public class Question26_isSubTreeStructure {
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if(A==null || B==null){
            return false;
        }

        if(A.val==B.val){ // 先序遍历
            if(check(A,B)){
                return true;
            }
        }

        return isSubStructure(A.left,B) || isSubStructure(A.right,B);
    }

    public boolean check(TreeNode A, TreeNode B) {
        if(B==null){
            return true;
        }
        if(A==null){ // B树节点不为空，但是A节点为空
            return false;
        }
        // A ,B 节点均不为空
        if(A.val==B.val){
            return check(A.left,B.left) && check(A.right,B.right);
        }else{
            return false;
        }

    }
}

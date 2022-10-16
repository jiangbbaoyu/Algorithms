package com.jby.algorithms.Q00_JianZhiOffer;


import org.junit.Test;

import java.util.HashMap;

/**
 * 剑指 Offer 07. 重建二叉树
 * 输入某二叉树的前序遍历和中序遍历的结果，请构建该二叉树并返回其根节点。 假设输入的前序遍历和中序遍历的结果中都 ```不含重复的数字```。
 */
public class Question07 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {

        HashMap<Integer,Integer> valueToIdx =  new HashMap<Integer,Integer>(inorder.length-1);
        for(int i=0;i<inorder.length;i++){
            valueToIdx.put(inorder[i],i);
        }

        return build(preorder,0,preorder.length-1,inorder,0,inorder.length-1,valueToIdx);
    }

    public TreeNode build(int[] preorder,int preStart,int preEnd, int[] inorder,int inStart, int inEnd,HashMap<Integer,Integer> valueToIdx) {

        if(preStart>preEnd){
            return null;
        }

        int rootValue = preorder[preStart];
        TreeNode node = new TreeNode(rootValue);

        // 使用 hashmap 加速根据值在中序序列中查找idx的操作
//        int idx = valueToIdx.get(rootValue);
        int idx = findRootValueAtInOrder(inorder,rootValue);
        // 左子树元素有idx-inStart 个
        node.left = build(preorder,preStart+1,preStart+(idx-inStart),inorder,inStart,idx-1,valueToIdx);

        node .right = build(preorder,preStart+(idx-inStart)+1,preEnd,inorder,idx+1,inEnd,valueToIdx);

        return node;
    }

    private int findRootValueAtInOrder(int[] inOrder,int target){
        for(int i=0;i<inOrder.length;i++){
            if (inOrder[i]==target){
                return i;
            }
        }
        return -1;
    }

    @Test
    public void test(){
        TreeNode treeNode = buildTree(new int[]{1, 2}, new int[]{2, 1});
        System.out.println(treeNode);

    }
}

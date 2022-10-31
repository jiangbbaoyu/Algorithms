package com.jby.algorithms.Q00_JianZhiOffer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 剑指 Offer 32 - III. 从上到下打印二叉树
 */
public class Question32 {

    // 按层打印
    public List<List<Integer>> levelOrder2(TreeNode root) {
        if(root==null){
            return new ArrayList();
        }
        ArrayList<List<Integer>> res= new ArrayList<List<Integer>>();
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.addFirst(root);

        ArrayList curLevelData = new ArrayList<Integer>();
        int toPrint =1; // 当前层要打印的节点数
        int nextLevelNum =0;  // 下一层要打印的节点数
        while(!queue.isEmpty()){
            TreeNode node = queue.removeLast();
            curLevelData.add(node.val);
            toPrint--; // 每打印一个，当前层要打印的节点数减一

            if(node.left!=null){
                queue.addFirst(node.left);
                nextLevelNum++; // 每添加一个节点到队列，下一层要打印的节点数加一
            }
            if(node.right!=null){
                queue.addFirst(node.right);
                nextLevelNum++;// 每添加一个节点到队列，下一层要打印的节点数加一
            }

            if(toPrint==0){// 当前层要打印的节点数为0了，切换到下一层
                toPrint = nextLevelNum;
                nextLevelNum =0;
                res.add(curLevelData);
                curLevelData=new ArrayList<Integer>();
            }
        }

        return res;
    }


    // 按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推
    // 使用三个变量
    public List<List<Integer>> levelOrder3(TreeNode root) {

        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(root==null){
            return res;
        }
        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
        stack.addFirst(root);

        int curLevelToPrint =1; // 当前层要打印的节点数
        boolean oddLevel =true;  // 奇数层还是偶数层
        int nextLevelToPrint =0; // 下一层要打印的节点数
        LinkedList<Integer> rowNodes = new LinkedList<Integer>();
        while(!stack.isEmpty()){
            TreeNode node =  stack.removeFirst();
            curLevelToPrint--;
            if(oddLevel){
                rowNodes.addLast(node.val);
            }else{
                rowNodes.addFirst(node.val);
            }


            if(node.left!=null){
                stack.add(node.left);
                nextLevelToPrint ++;
            }
            if(node.right!=null){
                stack.add(node.right);
                nextLevelToPrint ++;
            }

            if(curLevelToPrint==0){

                oddLevel= !oddLevel;
                curLevelToPrint = nextLevelToPrint;
                nextLevelToPrint =0;
                res.add(rowNodes);
                rowNodes = new LinkedList<Integer>();
            }
        }

        return res;
    }

    /**
     * leetcode 103. 二叉树的锯齿形层序遍历
     * 思路： 基于层序遍历，使用一个变量控制 从左向右还是右向左
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        List<List<Integer>> res = new ArrayList();
        if(root==null){
            return res;
        }

        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.addFirst(root);
        boolean leftToRight=true; // 使用一个变量控制 从左向右还是右向左
        while(!queue.isEmpty()){
            int levelNodesNum = queue.size();
            int[] levelNodesarr= new int[levelNodesNum];
            int count=levelNodesNum;
            while(count>0){

                TreeNode node = queue.removeLast();
                if(leftToRight){
                    levelNodesarr[levelNodesNum-count]=node.val; // 数组由前向后写入数据
                }else{
                    levelNodesarr[count-1]=node.val;// 数组由后向前写入数据
                }

                if(node.left!=null){
                    queue.addFirst(node.left);
                }
                if(node.right!=null){
                    queue.addFirst(node.right);
                }
                count--;
            }

            ArrayList<Integer> levelData = new ArrayList<Integer>();
            for (int i = 0; i < levelNodesarr.length; i++) {
                levelData.add(levelNodesarr[i]);
            }
            res.add(levelData);

            leftToRight = !leftToRight; // 改变方向
        }

        return res;
    }


}

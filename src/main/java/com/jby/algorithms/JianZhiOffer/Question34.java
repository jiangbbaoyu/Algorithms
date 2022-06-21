package com.jby.algorithms.JianZhiOffer;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer 34. 二叉树中和为某一值的路径
 * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
 */
public class Question34 {
    public List<List<Integer>> pathSum(TreeNode root, int target) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        dfs(root,res,new ArrayList<Integer>(),0,target);
        return res;
    }

    private void dfs(TreeNode node,List<List<Integer>> res, List<Integer> tmpRes, int currSum ,int target){
        if(node==null){
            return;
        }

        currSum += node.val;
        tmpRes.add(node.val);

        if(node.left==null && node.right==null && currSum==target){
            List<Integer> path = new ArrayList<Integer>();
            path.addAll(tmpRes);
            res.add(path);
        }

        dfs(node.left,res,tmpRes,currSum,target);
        dfs(node.right,res,tmpRes,currSum,target);
        tmpRes.remove(tmpRes.size()-1);

    }
}

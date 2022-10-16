package com.jby.algorithms.Q00_JianZhiOffer;

/**
 * 剑指 Offer 37. 序列化二叉树
 * 请实现两个函数，分别用来序列化和反序列化二叉树。
 * 你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构
 *
 * 思路: 基于前序遍历
 */
public class Question37 {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {

        if(root==null){
            return "$_";
        }
        String data = root.val+"_";
        data += serialize(root.left);
        data += serialize(root.right);
        return data;
    }

    // Decodes your encoded data to tree.
    int idx =0;
    public TreeNode deserialize(String data) {

        String[] nodeVals = data.split("_");
        idx =0;
        return doDeserialize(nodeVals);
    }

    private TreeNode doDeserialize(String[] nodeVals){
        // 由于 最后一个叶子节点 后面一定有两个 $ , 因此 idx 一定不会越界
        // if(idx>=nodeVals.length){
        //     return null;
        // }

        if(nodeVals[idx].equals("$")){
            return null;
        }

        TreeNode node = new TreeNode(Integer.parseInt(nodeVals[idx]));
        ++idx;
        node.left = doDeserialize(nodeVals);
        ++idx;
        node.right= doDeserialize(nodeVals);
        return node;
    }
}

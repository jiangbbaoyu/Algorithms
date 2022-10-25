package com.jby.algorithms.Q07_BinaryTreeAlgorithms;

/**
 * leetcode 297. 二叉树的序列化与反序列化
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 * 提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 */
public class Question09_seDeseTree {

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
}

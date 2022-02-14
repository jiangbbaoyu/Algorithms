package com.jby.algorithms.BinaryTreeAlgorithms;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树遍历相关 （前序，中序，后序，层序）
 */
public class Question01 {


    /**
     * leetcode 144. 二叉树的前序遍历
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {

        List<Integer> res=new ArrayList<Integer>();
        doPreorderTraversal(root,res);
        return res;
    }

    // 递归写法
    private void doPreorderTraversal(TreeNode node,List<Integer> res){
        if(node==null){
            return;
        }
        res.add(node.val);
        doPreorderTraversal(node.left,res);
        doPreorderTraversal(node.right,res);
    }


    // 非递归写法
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> res=new ArrayList<Integer>();
        LinkedList<TreeNode> stack =new LinkedList<TreeNode>();
        if(root!=null){
//            stack.push(root);
            stack.addFirst(root);
        }

        while(!stack.isEmpty()){
//            TreeNode node=stack.pop();
            TreeNode node=stack.removeFirst();
            res.add(node.val);

            //先push right,后 push left ，因此stack中访问的顺序为 node -> left -> right ;
            if(node.right!=null){
                stack.push(node.right);
            }
            if(node.left!=null){
                stack.push(node.left);
            }
        }
        return res;
    }

    /**
     * leetcode 94. 二叉树的中序遍历
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res=new ArrayList<Integer>();
        doInorderTraversal(root,res);
        return res;
    }
    // 递归写法
    private void doInorderTraversal(TreeNode node,List<Integer> res){
        if(node==null){
            return;
        }
        doInorderTraversal(node.left,res);
        res.add(node.val);
        doInorderTraversal(node.right,res);
    }


    // 非递归写法
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> res=new ArrayList<Integer>();
        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
        TreeNode cur=root;

        while(!stack.isEmpty()||cur!=null){ // 每次循环要么添加节点进栈，要么弹出一个节点
            if (cur!=null){
                while(cur!=null){  // 对于以cur为根的子树，整棵子树的左边界上所有节点进栈
                    stack.push(cur);
                    cur=cur.left;
                }

            }else{
                TreeNode node = stack.pop();// 弹出栈顶节点并访问
                res.add(node.val);
                if(node.right!=null){// 若弹出的栈顶节点右孩子为空，则下一轮继续弹出栈顶节点
                    cur=node.right; //  若弹出的栈顶节点右孩子不为空，将cur赋值为栈顶弹出节点的右节点
                }
            }
        }

        // 更加精要的写法
//        while(!stack.isEmpty()||cur!=null){
//
//            while(cur!=null){
//                stack.push(cur);
//                cur=cur.left;
//            }

//            cur=stack.pop();
//            res.add(cur.val);
//            cur=node.right;
//        }

        return res;
    }


    /**
     * leetcode 154. 二叉树的后序遍历
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res=new ArrayList<Integer>();
        doPostorderTraversal(root,res);
        return res;
    }

    // 递归写法
    private void doPostorderTraversal(TreeNode node,List<Integer> res){
        if(node==null){
            return;
        }
        doPostorderTraversal(node.left,res);
        doPostorderTraversal(node.right,res);
        res.add(node.val);
    }

    // 非递归写法
    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> res=new ArrayList<Integer>();
        LinkedList<Integer> tmpRes = new LinkedList<Integer>();
        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();

        if(root!=null){
            stack.push(root);
        }

        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            tmpRes.push(node.val);

            // 与先序遍历不同，先push left,后 push right ，因此stack中访问的顺序为 node -> right -> left ;
            // tmpRes栈中入栈的顺序也为node -> right -> left,因此从tmpRes中pop出的元素顺序为 left -> right -> node ,即后序遍历的顺序
            if(node.left!=null){
                stack.push(node.left);
            }
            if(node.right!=null){
                stack.push(node.right);
            }

        }

        while(!tmpRes.isEmpty()){
            res.add(tmpRes.pop());
        }

        return res;
    }


    @Test
    public void test(){

        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        node1.right=node2;
        node2.left=node3;

        List<Integer> integers = inorderTraversal2(node1);
        System.out.println(integers);


    }




}

package com.jby.algorithms.BinaryTreeAlgorithms;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树遍历相关 （ 前序/深度，中序，后序，层序/宽度 ,）
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


    /**
     * leetcode 102. 二叉树的层序遍历
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root==null){
            return new ArrayList();
        }
        ArrayList<List<Integer>> res= new ArrayList<List<Integer>>();
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.addFirst(root);

        while(!queue.isEmpty()){
            int curLevelCount = queue.size();
            ArrayList curLevelData = new ArrayList<Integer>(curLevelCount);
            while(curLevelCount>0){ // 内层while将一层的全部节点添加到一个List中，遍历过程中将下一层的所有节点添加到queue中
                TreeNode node = queue.removeLast();
                curLevelData.add(node.val);

                if(node.left!=null){
                    queue.addFirst(node.left);
                }
                if(node.right!=null){
                    queue.addFirst(node.right);
                }
                curLevelCount--;
            }
            res.add(curLevelData);
        }
        return res;
    }

    // 方法2
    public List<List<Integer>> levelOrder2(TreeNode root) {
        if(root==null){
            return new ArrayList();
        }
        ArrayList<List<Integer>> res= new ArrayList<List<Integer>>();
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.addFirst(root);
        HashMap<TreeNode,Integer> nodeLevelMap = new HashMap<TreeNode,Integer>();// 记录节点与其对应的level
        nodeLevelMap.put(root,1);


        ArrayList curLevelData = new ArrayList<Integer>();
        int curLevel =1;
        while(!queue.isEmpty()){
            TreeNode node = queue.removeLast();
            int level = nodeLevelMap.get(node);
            if(level>=curLevel){
                curLevel++;
                res.add(curLevelData);
                curLevelData=new ArrayList<Integer>();
            }
            curLevelData.add(node.val);

            if(node.left!=null){
                queue.addFirst(node.left);
                nodeLevelMap.put(node.left,level+1);
            }
            if(node.right!=null){
                queue.addFirst(node.right);
                nodeLevelMap.put(node.right,level+1);
            }
        }

        res.add(curLevelData); //添加最后一层

        return res;
    }


    /**
     * leetcode 662. 二叉树最大宽度
     *      每一层的宽度被定义为两个端点（该层最左和最右的非空节点，两端点间的null节点也计入长度）之间的长度
     * 思路：每个节点原本的值是没有用处的，所以我们可以用其来保存节点的位置信息：
     *      对于一颗完全二插树，如果按照从上至下，从左往右对所有节点从零开始顺序编号，父节点的左孩子节点的序号：2*i+1   父节点的左孩子节点的序号：2*i+2;
     *      所以每层的宽度就可以使用：每层最后一个节点的值 - 第一个节点的值 + 1  （对于同一层中最左和最右侧的两个节点，用其在满二叉树中位置的距离来衡量这一层的宽度）
     * @param root
     * @return
     */
    public int widthOfBinaryTree(TreeNode root) {
        if(root==null){
            return 0;
        }
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        int maxWidth = 0;
        root.val=0;
        queue.addFirst(root);

        while(!queue.isEmpty()){
            int curLevelCount=queue.size();
            int width = queue.getFirst().val-queue.getLast().val+1; // 每层最后一个节点的值 - 第一个节点的值 + 1
            while(curLevelCount>0){
                TreeNode node = queue.removeLast();

                if(node.left!=null){
                    node.left.val=2*node.val+1; // 添加进queue前，将该节点的值更新为该node的位置
                    queue.addFirst(node.left);
                }
                if(node.right!=null){
                    node.right.val=2*node.val+2;
                    queue.addFirst(node.right);
                }
                curLevelCount--;
            }
            maxWidth = width>maxWidth? width:maxWidth;
        }
        return maxWidth;
    }


    // 笨方法，超时了 ，对于空的节点，向队列中添加null充数（模拟一棵满二叉树）。 对于每一层，统计两个非空节点的距离
    public int widthOfBinaryTree2(TreeNode root) {
        if(root==null){
            return 0;
        }
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        int maxWidth = 0;
        queue.addFirst(root);

        while(!queue.isEmpty()){

            int curLevelCount=queue.size();
            int start =0;
            int end =0;

            for(int i=1;i<=curLevelCount;i++){
                TreeNode node = queue.removeLast();
                if(node!=null){
                    if(start==0){
                        start =i;
                        end=i;
                    }else{
                        end=i;
                    }
                    queue.addFirst(node.left);
                    queue.addFirst(node.right);
                }else{
                    // 二叉树中空节点添加null,以构造一个满二叉树
                    queue.addFirst(null);
                    queue.addFirst(null);
                }
            }

            if (start==0){
                break;
            }
            int max = end-start+1;

            maxWidth = max>maxWidth? max:maxWidth;
        }
        return maxWidth;
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

    @Test
    public void test2(){

        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        node1.left=node2;
        node1.right=node3;
        node2.left=node4;
        node2.right=node5;
        node3.right=node6;

        int i = widthOfBinaryTree(node1);
        System.out.println(i);
    }


}

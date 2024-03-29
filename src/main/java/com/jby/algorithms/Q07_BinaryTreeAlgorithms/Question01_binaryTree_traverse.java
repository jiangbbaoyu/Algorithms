package com.jby.algorithms.Q07_BinaryTreeAlgorithms;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树遍历相关 （ 前序/深度，中序，后序，层序/宽度 ,）
 */
public class Question01_binaryTree_traverse {


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
     * leetcode 114. 二叉树展开为链表
     * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
     * 展开后的单链表应该与二叉树 先序遍历 顺序相同
     * 思路： 1） 基于二叉树先序遍历的非递归写法，在节点出栈时，使用尾插法插入到 node构成的链表上
     */
    public void flatten(TreeNode root) {
        if(root==null){
            return ;
        }
        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
        stack.push(root);

        TreeNode head =null;
        TreeNode cur=null;
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();

            if(head==null){
                head=node;
                cur=node;
            }else{
                cur.left=null;
                cur.right=node;
                cur=node;
            }

            if(node.right!=null){
                stack.push(node.right);
            }
            if(node.left!=null){
                stack.push(node.left);
            }
        }
    }

    /**
     * 思路2 ： 基于后序遍历的头插法。 后序遍历与先序遍历的顺序相反， 因此在后序遍历访问一个节点时，将该节点插入到链表的头部
     *         后序遍历访问一个节点时，该节点的右子树和左子树一定被访问过了，因此可以将该节点的right指针指向 链表头节点；将left指针置空
     */
    TreeNode last =null;
    public void flatten2(TreeNode root) {
        if(root==null){
            return ;
        }

        flatten2(root.right);
        flatten2(root.left);

        // 后序遍历
        root.right=last;
        root.left=null;

        last=root;
    }


    /**
     * leetcode 199. 二叉树的右视图
     * 思路: 使用基于 头->右->左 的顺序来先序遍历， 此时可以保证每一层上最右边的节点最先被访问到
     *      也可以使用层序遍历
     */
    public List<Integer> rightSideView(TreeNode root) {

        List<Integer> res = new ArrayList<Integer>();
        dfs(res,root,1);
        return res;
    }

    private void dfs(List<Integer> res, TreeNode node, int depth){
        if(node==null){
            return;
        }
        if(depth>res.size()){
            res.add(node.val);
        }
        dfs(res,node.right,depth+1);
        dfs(res,node.left,depth+1);

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
     * leetcode 145. 二叉树的后序遍历
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

    // 方法2 ， 效率高
    public List<List<Integer>> levelOrder2(TreeNode root) {
        if(root==null){
            return new ArrayList();
        }
        ArrayList<List<Integer>> res= new ArrayList<List<Integer>>();
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.addFirst(root);

        ArrayList curLevelData = new ArrayList<Integer>();
        int currLevelToPrint =1; // 当前层要打印的节点数
        int nextLevelToPrint =0;  // 下一层要打印的节点数
        while(!queue.isEmpty()){
            TreeNode node = queue.removeLast();
            curLevelData.add(node.val);
            currLevelToPrint--; // 每打印一个，当前层要打印的节点数减一

            if(node.left!=null){
                queue.addFirst(node.left);
                nextLevelToPrint++; // 每添加一个节点到队列，下一层要打印的节点数加一
            }
            if(node.right!=null){
                queue.addFirst(node.right);
                nextLevelToPrint++;// 每添加一个节点到队列，下一层要打印的节点数加一
            }

            if(currLevelToPrint==0){// 当前层要打印的节点数为0了，切换到下一层
                currLevelToPrint = nextLevelToPrint;
                nextLevelToPrint =0;
                res.add(curLevelData);
                curLevelData=new ArrayList<Integer>();
            }
        }

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
            int[] currLevelData= new int[levelNodesNum];
            int count=levelNodesNum;
            while(count>0){

                TreeNode node = queue.removeLast();
                if(leftToRight){
                    currLevelData[levelNodesNum-count]=node.val; // 数组由前向后写入数据
                }else{
                    currLevelData[count-1]=node.val;// 数组由后向前写入数据
                }
                // TODO 此处也可以使用LinkedList 来存放levelNodes , 根据 leftToRight 决定使用addFirst 还是addLast 方法写入数据

                if(node.left!=null){
                    queue.addFirst(node.left);
                }
                if(node.right!=null){
                    queue.addFirst(node.right);
                }
                count--;
            }

            ArrayList<Integer> levelData = new ArrayList<Integer>();
            for (int i = 0; i < currLevelData.length; i++) {
                levelData.add(currLevelData[i]);
            }
            res.add(levelData);

            leftToRight = !leftToRight; // 改变方向
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

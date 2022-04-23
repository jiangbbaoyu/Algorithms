package com.jby.algorithms.BinaryTreeAlgorithms;

import com.jby.algorithms.LinkedListAlgorithms.ListNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 二叉搜索树问题 ：
 * 主要是根据二叉搜索树的中序遍历序列是递增的
 * 二叉搜索树的左子树所有节点值均小于 当前节点值；二叉搜索树的右子树所有节点值均大于 当前节点值。 这两个特性来做题的
 */
public class Question02 {

    /**
     * leetcode 98. 验证二叉搜索树
     *
     * 有效 二叉搜索树定义如下：
     *  节点的左子树只包含 小于 当前节点的数。
     *  节点的右子树只包含 大于 当前节点的数。
     *  所有左子树和右子树自身必须也是二叉搜索树。
     *
     * 思路：  二叉搜索树的中序遍历序列是递增的  (不能使用前序遍历的思路去解决)
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        if (root==null){
            return true;
        }

        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
        Integer preValue =null;
        TreeNode cur=root;
        while(!stack.isEmpty()||cur!=null){

            while(cur!=null){
                stack.push(cur);
                cur=cur.left;
            }
            cur=stack.pop();

            if(preValue!=null && preValue>=cur.val){
                return false;
            }
            preValue=cur.val;

            cur=cur.right;
        }
        return true;
    }

    // 中序遍历的递归解法
    private Integer preValue; // Integer类型的 preValue变量不能作为递归函数的参数来传递，因为在方法内部的改变无法传递到方法外部 （值类型）
    public boolean isValidBST2(TreeNode root) {
        return validate(root);
    }

    private boolean validate(TreeNode node){
        if(node==null){
            return true;
        }

        if(!validate(node.left)){
            return false;
        }

        if(preValue!=null && preValue>=node.val){
            return false;
        }
        preValue=node.val;

        if(!validate(node.right)){
            return false;
        }

        return true;
    }

    /**
     * leetcode 897 (剑指 Offer II 052)  展平二叉搜索树
     * 给你一棵二叉搜索树，请 按中序遍历 将其重新排列为一棵递增顺序搜索树，使树中最左边的节点成为树的根节点，并且每个节点没有左子节点，只有一个右子节点。
     * @param root
     * @return
     */
    public TreeNode increasingBST(TreeNode root) {
        if (root==null){
            return null;
        }

        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
        TreeNode newRoot =null;
        TreeNode newCur =null;
        TreeNode cur=root;
        while(!stack.isEmpty()||cur!=null){

            while(cur!=null){
                stack.push(cur);
                cur=cur.left;
            }
            cur=stack.pop();

            if(newRoot==null){
                newRoot=cur;
                newCur=cur;
            }else{
                cur.left=null;
                // cur.right=null;// 后面还要用到 cur.right， 因此不能将其在此处置为null

                newCur.right=cur;
                newCur=cur;
            }

            cur=cur.right;
        }
        return newRoot;
    }

    // 递归解法
    private TreeNode newRoot;
    private TreeNode newCur;
    public TreeNode increasingBST2(TreeNode root) {
        increasing(root);
        return newRoot;
    }

    private void increasing(TreeNode cur){
        if(cur==null){
            return;
        }

        increasing(cur.left);

        cur.left=null;
        if(newRoot==null){
            newRoot=cur;
            newCur=cur;
        }else{

            newCur.right=cur;
            newCur=cur;
        }

        increasing(cur.right);
    }


    /**
     * leetcode 99. 恢复二叉搜索树
     * 给你二叉搜索树的根节点 root ，该树中的 恰好 两个节点的值被错误地交换。请在不改变其结构的情况下，恢复这棵树
     * 思路:
     * 有两种情况
     * 如果值被错误交换的两个节点在中序遍历序列中是相邻的，此时中序遍历序列中只有一个降序对， 只需交换降序对的这两个node的值即可 eg 1,3,2,4  交换，3，2即可
     * 如果值被错误交换的两个节点在中序遍历序列中不是相邻的，此时中序遍历序列中有两个降序对， 需将第一个降序对的前一个node值，与第二个降序对的后一个node值进行交换 eg 3,2,1 需要将3，2中的3 与2，1中的1交换
     * @param root
     */
    public void recoverTree(TreeNode root) {

        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();

        TreeNode cur= root;
        TreeNode pre=null;

        TreeNode node1=null;
        TreeNode node2=null;
        while(!stack.isEmpty() || cur!=null){
            while(cur!=null){
                stack.push(cur);
                cur=cur.left;
            }

            cur=stack.pop();

            if(pre!=null && pre.val>cur.val){
                if(node1==null){
                    // 找到第一个降序对
                    node1=pre;
                    node2=cur;
                }else {
                    // 找到第二个降序对
                    node2=cur;
                }
            }
            pre=cur;
            cur=cur.right;
        }
        // 交换逆序对节点值
        int tmp=node1.val;
        node1.val=node2.val;
        node2.val=tmp;
    }


    /**
     * leetcode 530. 二叉搜索树的最小绝对差 : 给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值
     * 思路： 中序遍历， 记录前一个节点与当前遍历节点的绝对值，并更新全局最小绝对值 （任意两不同节点值之间的最小差值，一定存在中序遍历中相邻的两个节点间）
     * @param root
     * @return
     */
    public int getMinimumDifference(TreeNode root) {

        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();

        TreeNode pre= null;
        TreeNode cur=root;
        int minDelta=Integer.MAX_VALUE;

        while(!stack.isEmpty() || cur!=null){

            while(cur!=null){
                stack.push(cur);
                cur=cur.left;
            }

            cur=stack.pop();

            if(pre!=null && cur.val-pre.val<minDelta){
                minDelta = cur.val-pre.val ;
            }
            pre=cur;

            cur=cur.right;
        }
        return minDelta;
    }

    /**
     * leetcode 108. 将有序数组转换为二叉搜索树
     * 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树
     * 思路：：BST的中序遍历是升序的，因此本题等同于根据中序遍历的序列 恢复 二叉搜索树。
     *      因此我们可以以升序序列中的任一个元素作为根节点，以该元素左边的升序序列构建左子树，以该元素右边的升序序列构建右子树，这样得到的树就是一棵二叉搜索树。
     *      又因为本题要求高度平衡，因此在根据升序序列构建平衡二叉树时，需要选择升序序列的中间元素作为二叉搜索数的根节点。这样可以保证二叉搜索树左右子树包含的节点个数最多相差1，即可以保证二叉搜索树的左右子树高度差不超过1
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {

        TreeNode root = sortedArrayToBST(nums,0,nums.length-1);
        return root;
    }

    private TreeNode sortedArrayToBST(int[] nums,int left,int right) {
        if(left>right){
            return null;
        }
        int mid =left+(right-left)/2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left=sortedArrayToBST(nums,left,mid-1);
        node.right=sortedArrayToBST(nums,mid+1,right);
        return node;
    }


    /**
     * leetcode 109. 有序链表转换二叉搜索树 : 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树
     * 思路： 使用快慢指针定位到链表的中间节点，同时基于中间节点将链表切为两部分，左边部分链表元素构建左子树，右边部分链表元素构建右子树
     * @param head
     * @return
     */
    public TreeNode sortedListToBST(ListNode head) {

        if(head==null) {
            return null;
        }
        if(head.next==null){ // 对于本段链表只有一个节点的场景，直接创建一个TreeNode并返回即可
            return new TreeNode(head.val);
        }

        ListNode mid = findMidNode(head);// 由于排除了head.next==null的场景，因此找到mid节点后，preMid一定是不为null的
        TreeNode node = new TreeNode(mid.val);

        node.left=sortedListToBST(head);
        node.right=sortedListToBST(mid.next);
        return node;
    }

    private ListNode findMidNode(ListNode head){

        ListNode midPre = null; // 记录中间节点的前一个节点
        ListNode slow = head;
        ListNode fast = head;
        while(fast!=null && fast.next!=null){
            midPre=slow;
            slow=slow.next;
            fast=fast.next.next;
        }

        midPre.next=null;// 将链表以mid节点切断
        return slow;
    }


    /**
     * leetcode 501. 二叉搜索树中的众数： 给你一个含重复值的二叉搜索树（BST）的根节点 root ，找出并返回 BST 中出现频率最高的元素的集合
     * 思路：
     * @param root
     * @return
     */
    public int[] findMode(TreeNode root) {

        ArrayList<Integer> nums = new ArrayList<Integer>();
        int maxFreq =0; // 元素值最大的出现频率
        int freq =0; // 当前遍历节点元素的值出现频率
        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();

        TreeNode pre= null;
        TreeNode cur=root;
        while(!stack.isEmpty() || cur!=null){

            while(cur!=null){
                stack.push(cur);
                cur=cur.left;
            }

            cur=stack.pop();

            if(pre!=null && pre.val==cur.val ){
                freq++; // 当前遍历节点元素值的出现频率 +1
            }else{
                freq=1; // 当前遍历节点的值与上一个节点的值不一致，将 当前遍历节点元素值的出现频率置为1
            }
            // 更新全局元素值最大的出现频率，并更新临时保存出新频率最大值的ArrayList
            if(freq>maxFreq){
                nums.clear();
                nums.add(cur.val);
                maxFreq=freq;
            }else if(freq==maxFreq){
                nums.add(cur.val);
            }
            pre=cur;

            cur=cur.right;
        }

        int[] res = new int[nums.size()];
        for(int i=0;i<nums.size();i++){
            res[i]=nums.get(i);
        }
        return res;
    }


    /**
     * leetcode 235. 二叉搜索树的最近公共祖先： 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先
     * 思路：二叉搜索树的左子树节点值均小于当前节点值；二叉搜索树的右子树节点值均大于当前节点值。 因此对一个节点，如果p,q中一个大于等于当前值，一个小于等于当前节点值，则该节点是p,q的最近公共祖先
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if(p.val>q.val){
            TreeNode tmp =p;
            p=q;
            q=tmp;
        }
        return findAncestor(root,p,q);
    }
    public TreeNode findAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if(root.val>=p.val && root.val<=q.val){
            return root;
        }else if(root.val > q.val){
            return findAncestor(root.left,p,q);
        }else{
            return findAncestor(root.right,p,q);
        }
    }

    /**
     * 剑指 Offer 33. 二叉搜索树的后序遍历序列是否合法 ：输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果
     * 思路： 后序遍历数组中的最后一个元素为二叉树的根节点，后序遍历数组 [0,len-2]个元素为根节点的左子树和右子树元素。且前面部分为左子树，后面部分为右子树；
     *       如果是二叉搜索树，则前部分元素值均小于后部分元素值。
     *       eg:   1,3,2     6      5
     *             ----     ---    ---
     *             左子树    右子树  根节点
     *       然后递归判断左右子树即可
     *       TODO 单调栈解法
     *
     * @param postorder
     * @return
     */
    public boolean verifyPostorder(int[] postorder) {
        return verify(postorder,0,postorder.length-1);
    }

    private boolean verify(int[] arr, int left, int right){

        if(left>=right){// 子树只有一个元素，此时认为该子树是合法的二叉搜索树
            return true;
        }

        int pivot =arr[right];
        int i=left;
        int j=right-1;

        while(i<=j){
            if(arr[i]<pivot){
                i++;
            }else if(arr[j]>pivot){
                j--;
            }else{
                return false;  // 不满足前部分元素值均小于后部分元素值
            }

        }

        // 此时满足前部分元素值均小于后部分元素值 ，j指向左子树元素的最后一个， i指向右子树元素的第一个

        if(!verify(arr,left,j)){
            return false;
        }

        if(!verify(arr,i,right-1)){
            return false;
        }
        return true;
    }

    /**
     * 剑指offer 36
     * leetcode 426  输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向
     * @param root
     * @return
     */
    public Node treeToDoublyList(Node root) {

        LinkedList<Node> stack = new LinkedList<Node>();
        Node cur = root;
        Node head=null;
        Node nodeTail=null;

        while(!stack.isEmpty()||cur!=null){
            while(cur!=null){
                stack.push(cur);
                cur=cur.left;
            }

            cur = stack.pop();

            if(head==null){
                head=cur;
                nodeTail=cur;
            }else{
                cur.left=nodeTail;
                nodeTail.right=cur;
                nodeTail=cur;  // tail insert
            }

            if(stack.isEmpty()&& cur.right==null){
                // last node in inorder traverse
                cur.right=head;
                head.left=cur;
                break;
            }else{
                cur=cur.right;
            }

        }
        return head;

    }

    /**
     * leetcode 538. 把二叉搜索树转换为累加树
     * 思路： 基于右-头-左的中序遍历， 递归 or 迭代 ; 遍历过程中维护一个遍历过的节点的累加和
     * @param root
     * @return
     */
    public TreeNode convertBST(TreeNode root) {
        convert(root,0);
        return root;
    }

    public int convert(TreeNode node,int sum) {
        if(node==null){
            return sum;
        }

        int right = convert(node.right,sum);

        node.val=right+node.val;
        sum =node.val;

        int left =convert(node.left,sum);

        return left;
    }


    @Test
    public void test1(){

        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(1);
        node1.left=node2;
        boolean isvalid = isValidBST2(node1);
        System.out.println(isvalid);
    }

    @Test
    public void test11(){
        Integer i=123;
        setMethod(i);
        System.out.println(i);
    }

    private void setMethod(Integer i) {
        i=i+1;
        System.out.println(i);
    }

    @Test
    public void test2(){

        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(1);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(2);
        node1.left=node2;
        node1.right=node3;
        node3.left=node4;
        recoverTree(node1);

        System.out.println(node1);
    }


    @Test
    public void test3(){

        ListNode node1 = new ListNode(-10);
        ListNode node2 = new ListNode(-3);
        ListNode node3 = new ListNode(0);
        ListNode node4 = new ListNode(5);
        ListNode node5 = new ListNode(9);
        node1.next=node2;
        node2.next=node3;
        node3.next=node4;
        node4.next=node5;
        TreeNode treeNode = sortedListToBST(node1);
        System.out.println(treeNode);
    }



}

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};

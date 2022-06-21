package com.jby.algorithms.JianZhiOffer;

/**
 * 剑指 Offer 33. 二叉搜索树的后序遍历序列
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。
 * 假设输入的数组的任意两个数字都互不相同
 */
public class Question33 {
    public boolean verifyPostorder(int[] postorder) {
        if(postorder==null){
            return false;
        }
        return verify(postorder,0,postorder.length-1);
    }

    private boolean verify(int[] postorder,int start,int end) {
        if(start>=end){
            return true;
        }
        int rootVal = postorder[end];
        int i = start;
        int j= end-1;
        while(i<=j){
            if(postorder[i]<rootVal){
                i++;
            }else if(postorder[j]>rootVal){
                j--;
            }else{
                return false; //不满足前部分元素值均小于后部分元素值
            }
        }
        // 此时满足前部分元素值均小于后部分元素值 ，j指向前半部分的最后一个， i指向右半部分的第一个

//        int pivot =start;
//        boolean findGreater =false;
//        for(int i=start;i<=end-1;i++){
//            if(postorder[i]>rootVal && !findGreater){
//                findGreater=true;
//                pivot = i;
//            }
//            if (findGreater && postorder[i]<rootVal){
//                return false;
//            }
//        }

        return  verify(postorder,start,j) && verify(postorder,i,end-1);
    }
}

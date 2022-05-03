package com.jby.algorithms.backTraceAlgorithms;

import java.util.ArrayList;
import java.util.List;

public class Question01 {

    /**
     * leetcode 51 N 皇后问题
     * 思路: 逐行尝试， 对于每一行，依次尝试每个列的位置，如果该当前行的某一列的位置与之前的摆放不冲突，则占据该位置，继续尝试下一行
     *      时间复杂度 O(N^N)
     */

    public List<List<String>> solveNQueens(int n) {
        int[] records = new int[n]; // records[i]=j 表示棋盘中第i行中，在第j列放置了皇后
        List<List<String>> res = new ArrayList<List<String>>();
        traverse(records,0,n,res);
        return res;
    }

    private void traverse(int[] records,int curRow, int size,List<List<String>> res){
        if(curRow==size){
            // find a solution
            List<String> aSolution = new ArrayList<String>();
            for(int k=0;k<size;k++){
                int col = records[k];
                StringBuffer sb = new StringBuffer();
                int l=0;
                for(l=0;l<col;l++){
                    sb.append(".");
                }
                sb.append("Q");

                for(l=col+1;l<size;l++){
                    sb.append(".");
                }
                aSolution.add(sb.toString());
            }
            res.add(aSolution);
            return ;
        }

        for(int col=0;col<size;col++){
            if (isValidPos(records,curRow,col)){
                records[curRow]=col;// 记录当前位置
                traverse(records,curRow+1,size,res);//继续下一行的遍历
                // 当traverse方法调用结束后不需要对records中失效的数据进行清理，这些失效的数据在后序的流程中会被覆盖
            }
        }
    }

    private boolean isValidPos(int[] records,int curRow, int col){
        for(int i=0;i<curRow;i++){ // 对于curRow的 col列是否合法， 只需考虑 [0,curRow)行的元素的影响即可，
            // (当前尝试的列在之前的 第i行中已经被使用了) || （ curRow,col） 这个点与（i，records[i]）点 在对角线或斜对角线上
            if( records[i]==col || Math.abs(records[i]-col) ==  Math.abs(curRow-i)){
                return false;
            }
        }
        return true;
    }

    /**
     *  方法2， 使用位运算来加速 ，整体时间复杂度还是 O(N^N)
     *  核心思想是 使用三个整数来表示 方法1中的 records数组，来记录哪些列已经占用了，下一行的流程中哪些列的位置是冲突的，哪些是合法的
     *
     *
     *  负数使用补码表示，即 原码取反加一 ， -1 ：   0000...1  ->  1111...0 -> 1111...1  （计算机使用补码表示负数才可以正确运行减法）
     *  >>> 表示有符号右移，根据高位是0则补0； 高位是1则补1
     *  >> 表示无符号右移， 高位补0
     */

    private int size;
    private List<List<String>> res;
    private String template;
    public List<List<String>> solveNQueens2(int n) {
        res = new ArrayList<List<String>>();
        size=n;
        StringBuffer sb =new StringBuffer();
        for(int k=0;k<size;k++){
            sb.append(".");
        }
        template =sb.toString();

        List<String> curRes = new ArrayList<String>();
        int limit = (1<<n)-1;  // limit的二进制表示中 低n位全为1  // int 32位表示，只能处理小于等于32的 n皇后问题
        traverse2(limit,0,0,0,curRes);
        return res;
    }

    /**
     *
     * @param limit  限定合法的bit范围在低n位，忽略其他bit位
     * @param colLimit 当前哪些列被占用了（bit为1表示不可用）
     * @param leftDiaLimit 记录了 下一行的过程中，因和已经占用的列处在左对角线位置的无法使用的列 （bit为1表示不可用）
     * @param rightDiaLimit 记录了 下一行的过程中，因和已经占用的列处在右对角线位置的无法使用的列（bit为1表示不可用）
     * @param curRes        当前遍历过程的部分结果
     */
    private void traverse2(int limit, int colLimit, int leftDiaLimit,int rightDiaLimit, List<String> curRes){

        if(colLimit==limit){// n个col全都安排好了Q
            ArrayList<String> tmp=new ArrayList<String>();
            tmp.addAll(curRes);
            res.add(tmp);
        }

        int avaliablePos = limit & (~(colLimit | leftDiaLimit | rightDiaLimit));
        while(avaliablePos>0){
            int mostRightPos = avaliablePos & (~avaliablePos+1);
            recordPos(mostRightPos,curRes); // 根据mostRightPos的值，计算那一列放置了Q， 记录当前行的字符串表示
            traverse2(limit,
                    colLimit|mostRightPos,
                    (leftDiaLimit|mostRightPos)<<1,
                    (rightDiaLimit|mostRightPos)>>>1,curRes);

            curRes.remove(curRes.size()-1);
            avaliablePos -= mostRightPos;
        }
    }
    private void recordPos(int mostRightPos,List<String> tmpRes){
//        int idx=1;
//        while(mostRightPos!=1){
//            mostRightPos=mostRightPos>>1;
//            idx++;
//        }
//        char[] chars =template.toCharArray();
//        chars[size-idx]='Q';
//        tmpRes.add(new String(chars));


        int idx=Integer.bitCount(mostRightPos-1); // bitCount 统计一个int 的二进制表示中bit位为1的个数
        char[] chars =template.toCharArray();
        chars[size-idx-1]='Q';
        tmpRes.add(new String(chars));

    }

}

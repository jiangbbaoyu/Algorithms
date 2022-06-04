package com.jby.algorithms.JianZhiOffer;

import org.junit.Test;

/**
 * 剑指 Offer 12. 矩阵中的路径
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 */
public class Question12 {

    public boolean exist(char[][] board, String word) {
        int cur =0;
        boolean[][] visited = new boolean[board.length][board[0].length]; // 使用额外空间标记 当前遍历board寻找word path过程中哪些位置的元素被占用了，这些元素不允许被重复占用
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if (exist(board,word,cur,i,j,board.length,board[0].length,visited)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean exist(char[][] board, String word,int cur,int row,int col,int rows,int cols,boolean[][] visited) {
        if(cur==word.length()){
            return true ;
        }

        boolean exist =false;
        if( col>=0 && col<cols && row>=0 && row<rows
                && word.charAt(cur)==board[row][col]
                && !visited[row][col] ){  // 当前col, row不越界， board当前位置的字符等于 字符串 cur位置的字符， 当前col, row位置可用

            visited[row][col] =true; // 占据当前位置
            cur ++;

            exist =  exist(board,word,cur,row-1,col,rows,cols,visited) ||
                     exist(board,word,cur,row,col-1,rows,cols,visited) ||
                     exist(board,word,cur,row,col+1,rows,cols,visited) ||
                     exist(board,word,cur,row+1,col,rows,cols,visited)  ;

            visited[row][col] =false;  // 通过该位置的后序所有路径已经遍历过，释放该位置，便于其他扫描路径方式占用
        }

        return exist;

    }

    // 复用原始board 来标记当前正在遍历的路径上的元素，防止这些元素被重复使用
    public boolean exist2(char[][] board, String word) {

        for(int i=0;i<board.length;i++){
            for(int j =0;j<board[0].length;j++){
                if (exist2(board,word,0,i,j,board.length-1,board[0].length-1)){
                    return true;
                }
            }
        }
        return false;

    }

    private boolean exist2(char[][] board, String word,int cur,int row,int col,int rows,int cols) {

        if(cur==word.length()){
            return true;
        }

        boolean exist =false;
        if(row<=rows && row>=0 && col <= cols && col>=0
                &&  word.charAt(cur) == board[row][col]
        ){

            board[row][col] =  (char)(board[row][col] + 200);
            cur++;

            exist = exist2(board,word,cur,row+1,col,rows,cols) ||
                    exist2(board,word,cur,row-1,col,rows,cols) ||
                    exist2(board,word,cur,row,col+1,rows,cols) ||
                    exist2(board,word,cur,row,col-1,rows,cols) ;

            board[row][col] -=  200;

        }
        return exist;
    }

    @Test
    public void test(){
//        System.out.println('a'-0);
//        System.out.println('z'-0);
//        System.out.println('A'-0);
//        System.out.println('Z'-0);

        System.out.println(Integer.MAX_VALUE);
    }
}

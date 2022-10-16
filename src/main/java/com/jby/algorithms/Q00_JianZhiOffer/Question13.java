package com.jby.algorithms.Q00_JianZhiOffer;

/**
 * 剑指 Offer 13. 机器人的运动范围
 * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1]
 * 一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。
 * 例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19
 * 请问该机器人能够到达多少个格子？
 *
 * 思路： 深度优先遍历， 并使用visited数组记录访问过的位置
 */
public class Question13 {

    public int movingCount(int m, int n, int k) {

        boolean[][] visited = new boolean[m][n];

        return moving(0,0,m,n,k,visited);

    }

    private int moving(int row,int col ,int rows, int cols, int k,boolean[][] visited) {
        int count =0;
        if (row>=0 && row<rows &&col >=0 && col<cols &&
                getDigital(row)+getDigital(col)<=k &&
                !visited[row][col]){

            visited[row][col] =true;
            count ++;

            count += moving(row+1,col,rows,cols,k,visited);
            count += moving(row-1,col,rows,cols,k,visited);
            count += moving(row,col+1,rows,cols,k,visited);
            count += moving(row,col-1,rows,cols,k,visited);
        }

        return count;

    }

    private int getDigital(int num){

        int res =0;

        while(num>0){
            res += num%10;
            num = num/10;
        }
        return res;
    }
}

package com.jby.algorithms.JianZhiOffer;

import org.junit.Test;

/**
 * 剑指 Offer 47. 礼物的最大价值
 * 在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。
 * 你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。
 * 给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
 *
 * 思路： 动态规划
 *       f(i,j) 表示从左上角到第i行j列时可以获取的最大价值，则f(i,j) = max(f(i-1,j),f(i,j-1)) + grid[i][j] ,i>0; j>0
 */
public class Question47 {

    public int maxValue(int[][] grid) {
        if(grid==null || grid.length==0|| grid[0].length==0){
            return 0;
        }
        int rows = grid.length;
        int cols =grid[0].length;
        int[][] dp = new int[rows][cols];
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){

                int upMax= 0;
                int leftMax=0;
                if(i>0){
                    upMax= dp[i-1][j];
                }
                if(j>0){
                    leftMax = dp[i][j-1];
                }

                dp[i][j]= Math.max(upMax,leftMax)+ grid[i][j];
            }
        }

        return dp[rows-1][cols-1];
    }

    // 优化动态规划的空间复杂度 O(N*N) --> O(N)
    // 当遍历到第i行第j列时， dp[0~j-1]    中保存的是到达第grid[i][0~j-1]     个元素时的最大价值
    //                    dp[j~cols-1] 中保存的是到达第grid[i-1][j~cols-1]个元素时的最大价值
    public int maxValue2(int[][] grid) {
        if(grid==null || grid.length==0|| grid[0].length==0){
            return 0;
        }
        int rows = grid.length;
        int cols =grid[0].length;
        int[] dp = new int[cols];
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                int upMax= 0;
                int leftMax=0;
                if(i>0){
                    upMax= dp[j];
                }
                if(j>0){
                    leftMax = dp[j-1];
                }

                dp[j]= Math.max(upMax,leftMax)+ grid[i][j];
            }
        }

        return dp[cols-1];
    }

    @Test
    public void test(){
        int i = maxValue(new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}});
        System.out.println(i);
    }
}
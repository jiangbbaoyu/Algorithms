package com.jby.algorithms.Q08_DynamicProgrammingAlgorithms;

public class Question08_maximalSequare {
    /**
     * leetcode 221. 最大正方形
     * 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积
     *
     * 思路：动态规划
     *     dp[i][j]表示以第i行第j列为右下角所能构成的最大正方形边长, 则递推式为:
     *     dp[i][j] = 1 + min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]) , matrix[i-1][j-1] =='1'
     *                0                                             , matrix[i-1][j-1] =='0'
     */
    public int maximalSquare(char[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;

        int maxLen =0;
        int[][] dp  = new int[row+1][col+1];
        // dp[0][j], dp[i][0] 均为0 因此不用初始化

        for(int i=1;i<row+1;i++){
            for(int j=1;j<col+1;j++){
                if(matrix[i-1][j-1]=='1'){
                    dp[i][j] = Math.min(Math.min(dp[i-1][j],dp[i][j-1]),dp[i-1][j-1]) +1;
                    maxLen = maxLen > dp[i][j]? maxLen:dp[i][j];
                }else{
                    dp[i][j] =0;
                }
            }
        }

        return maxLen * maxLen;
    }
}

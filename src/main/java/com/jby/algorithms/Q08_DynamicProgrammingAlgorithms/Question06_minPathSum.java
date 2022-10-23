package com.jby.algorithms.Q08_DynamicProgrammingAlgorithms;

/**
 * leetcode 64. 最小路径和
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 *
 * 输入：grid = [[1,3,1],
 *             [1,5,1],
 *             [4,2,1]]
 * 输出：7
 * 解释：因为路径 1→3→1→1→1 的总和最小
 *
 * 思路： 动态规划， 与leetcode 63. 不同路径 类似
 */
public class Question06_minPathSum {
    public int minPathSum(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;

        int[][] dp = new int[row][col];
        // 1. 从下到上初始化 最后一列的dp数组
        dp[row-1][col-1]= grid[row-1][col-1];
        for(int i = row-2;i>=0;i--){
            dp[i][col-1]=  grid[i][col-1] + dp[i+1][col-1];
        }
        // 2. 从右到左初始化 最后一行的dp数组
        for(int j = col-2;j>=0;j--){
            dp[row-1][j] = grid[row-1][j] +dp[row-1][j+1];
        }
        // 3. dp[i][j] = min{dp[i][j+1], dp[i+1][j]} + grid[i][j]
        for(int i=row-2;i>=0;i--){
            for(int j= col-2;j>=0;j--){
                dp[i][j] = Math.min(dp[i][j+1], dp[i+1][j]) + grid[i][j];
            }
        }

        return dp[0][0];
    }
}

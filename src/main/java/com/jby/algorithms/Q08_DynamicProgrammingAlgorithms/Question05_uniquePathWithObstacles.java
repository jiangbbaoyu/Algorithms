package com.jby.algorithms.Q08_DynamicProgrammingAlgorithms;

public class Question05_uniquePathWithObstacles {
    /**
     * leetcode 63. 不同路径 II
     * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
     * 机器人每次只能 `向下或者向右`  移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish”）。
     * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
     * 网格中的障碍物和空位置分别用 1 和 0 来表示。
     *
     * 思路：
     *    dp[i][j]  =    0                       ,   obstacleGrid[i][j] == 1
     *                  dp[i+1][j] + dp[i][j+1]  ,   obstacleGrid[i][j] != 1
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;
        int[][] dp = new int[row][col];
        // 1. 从下到上初始化 最后一列的dp数组
        for(int i= row-1;i>=0;i--){
            if(obstacleGrid[i][col-1]==1) break;
            dp[i][col-1] =1;
        }
        // 2. 从右到左初始化 最后一行的dp数组
        for(int j= col-1;j>=0;j--){
            if(obstacleGrid[row-1][j]==1) break;
            dp[row-1][j] =1;
        }
        // 3. dp[i][j] = dp[i][j+1], dp[i+1][j] ,obstacleGrid[i][j] ==0
        //                0                     ,obstacleGrid[i][j] ==1
        for(int i= row-2;i>=0;i--){
            for(int j=col-2;j>=0;j--){
                if(obstacleGrid[i][j]==1){
                    dp[i][j]= 0;
                }else{
                    dp[i][j]= dp[i+1][j] + dp[i][j+1];
                }
            }
        }

        return dp[0][0];
    }

    /**
     * leetcode 62. 不同路径
     * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
     * 问总共有多少条不同的路径？
     * 输入：m = 3, n = 2
     * 输出：3
     * 解释：
     * 从左上角开始，总共有 3 条路径可以到达右下角。
     * 1. 向右 -> 向下 -> 向下
     * 2. 向下 -> 向下 -> 向右
     * 3. 向下 -> 向右 -> 向下
     */
    public int uniquePaths(int m, int n) {

        int[][] dp = new int[m][n];
        // 1. 从下到上初始化 最后一列的dp数组
        for(int i=m-1;i>=0;i--){
            dp[i][n-1] =1;
        }
        // 2. 从右到左初始化 最后一行的dp数组
        for(int j=n-1;j>=0;j--){
            dp[m-1][j] =1;
        }
        // 3. dp[i][j] = dp[i][j+1] + dp[i+1][j]}
        for(int i = m-2;i>=0;i--){
            for(int j=n-2;j>=0;j--){
                dp[i][j] = dp[i+1][j] + dp[i][j+1];
            }
        }

        return dp[0][0];
    }

}

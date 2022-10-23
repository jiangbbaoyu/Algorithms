package com.jby.algorithms.Q03_arrayAlgorithms;

public class Question06_islandsNum {
    /**
     * leetcode 200. 岛屿数量
     * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
     * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
     * 此外，你可以假设该网格的四条边均被水包围
     * 输入：grid = [
     *   ["1","1","0","0","0"],
     *   ["1","1","0","0","0"],
     *   ["0","0","1","0","0"],
     *   ["0","0","0","1","1"]
     * ]
     * 输出：3
     *
     * 思路 ： 遍历grid中的每个元素， 如果是 '1'，则通过dfs将所有相连的 '1' 都标记为 '*'， 岛屿数量加1；
     *        直到将grid中的全部 '1' 都标记为'*'
     */
    public int numIslands(char[][] grid) {
        int num =0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]=='1'){
                    expand(grid,i,j);
                    num++;
                }
            }
        }
        return num;
    }

    private void expand(char[][] grid, int i,int j){
        if(i<0 || i>=grid.length || j<0 || j>=grid[0].length
                || grid[i][j]=='*' || grid[i][j]=='0'){
            return;
        }
        grid[i][j]='*'; // already accessed
        expand(grid,i-1,j);
        expand(grid,i+1,j);
        expand(grid,i,j-1);
        expand(grid,i,j+1);
    }


    /**
     * leetcode 695. 岛屿的最大面积
     * 给你一个大小为 m x n 的二进制矩阵 grid 。
     * 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在 水平或者竖直的四个方向上 相邻。你可以假设 grid 的四个边缘都被 0（代表水）包围着。
     * 岛屿的面积是岛上值为 1 的单元格的数目。
     * 计算并返回 grid 中最大的岛屿面积。如果没有岛屿，则返回面积为 0 。
     */
    public int maxAreaOfIsland(int[][] grid) {
        int maxArea =0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]==1){
                    int area = expand(grid,i,j);
                    maxArea = maxArea>area? maxArea:area;
                }
            }
        }
        return maxArea;
    }
    private int expand(int[][] grid, int i,int j){
        if(i<0 || i>=grid.length || j<0 || j>=grid[0].length
                || grid[i][j]==2 || grid[i][j]==0){
            return 0;
        }
        grid[i][j]=2; // already accessed
        return  expand(grid,i-1,j) +
                expand(grid,i+1,j) +
                expand(grid,i,j-1) +
                expand(grid,i,j+1) + 1 ;
    }
}

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
}

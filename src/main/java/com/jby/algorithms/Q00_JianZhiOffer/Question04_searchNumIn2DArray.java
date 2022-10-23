package com.jby.algorithms.Q00_JianZhiOffer;

/**
 * 剑指 Offer 04. 二维数组中的查找  （leetcode 240. 搜索二维矩阵 II）
 * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数
 *
 *  思路： 从数组的 右上角 或 左下角 开始遍历，根据当前matrix[row][col]的值，每次排除一行或一列   ,时间复杂度 O(row+col)
 */
public class Question04_searchNumIn2DArray {

    public boolean findNumberIn2DArray(int[][] matrix, int target) {

        if(matrix==null||matrix.length==0 || matrix[0].length==0){
            return false;
        }

        int col =matrix[0].length-1;
        int row = 0;

        while(col>=0&& row<matrix.length){

            if(matrix[row][col]>target){
                col--;
            }else if(matrix[row][col]<target){
                row++;
            }else{
                return true;
            }
        }
        return false;
    }

    /**
     * leetcode 74. 搜索二维矩阵
     * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
     * 每行中的整数从左到右按升序排列。
     * 每行的第一个整数大于前一行的最后一个整数
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int row =0;
        int col = matrix[0].length-1;

        while(row<= matrix.length-1 && col >=0){
            if(matrix[row][col]==target){
                return true;
            }else if (matrix[row][col]<target){
                row++;
            }else if(matrix[row][col]>target){
                col--;
            }
        }

        return false;
    }
}

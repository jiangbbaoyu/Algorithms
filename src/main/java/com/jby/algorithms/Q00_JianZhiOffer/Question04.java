package com.jby.algorithms.Q00_JianZhiOffer;

/**
 * 剑指 Offer 04. 二维数组中的查找
 * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数
 *
 *  思路： 从数组的 右上角 或 左下角 开始遍历，根据当前matrix[row][col]的值，每次排除一行或一列   ,时间复杂度 O(row+col)
 */
public class Question04 {

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
}

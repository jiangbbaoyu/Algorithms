package com.jby.algorithms.Q03_arrayAlgorithms;

public class Question15_rotateMatrix {
    /**
     * leetcode 48. 旋转图像
     * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
     * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像
     *
     * 思路：
     * 顺时针旋转90°
     *   转置矩阵
     *   水平翻转
     * 逆时针旋转90°
     *   转置矩阵
     *   垂直翻转
     * 旋转180°
     *    水平翻转
     *    垂直翻转
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // 矩阵转置
        for(int i = 1;i<n;i++){
            for(int j=0;j<i;j++){
                swap(matrix,i,j);
            }
        }
        // 水平翻转
        for(int i=0;i<n;i++){
            reverse(matrix[i]);
        }
    }
    private void reverse(int[] row){
        int left =0;
        int right = row.length-1;
        while(left<right){
            int tmp = row[left];
            row[left]= row[right];
            row[right] = tmp;

            left ++;
            right --;
        }
    }
    private void swap(int[][] matrix, int i, int j){
        int tmp = matrix[i][j];
        matrix[i][j]= matrix[j][i];
        matrix[j][i]= tmp;
    }
}

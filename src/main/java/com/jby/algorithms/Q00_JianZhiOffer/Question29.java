package com.jby.algorithms.Q00_JianZhiOffer;

/**
 * 剑指 Offer 29. 顺时针打印矩阵 （螺旋矩阵）
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
 */
public class Question29 {
    public int[] spiralOrder(int[][] matrix) {
        if(matrix==null || matrix.length==0){
            return new int[0];
        }

        int rows = matrix.length;
        int cols =matrix[0].length;
        int[] res =new int[rows*cols];
        int idx =0;

        int circle =  (Math.min(rows,cols)+1)/2;// 外层循环的次数

        for(int start =0;start<circle;start++){
            // 每次循环打印外层的一圈
            // 左上角为（start,start）, 右下角为（rowEnd,colEnd）
            int rowEnd = rows-start-1;
            int colEnd = cols-start-1;

            for(int i=start;i<=colEnd;i++){// 左->右
                res[idx++]=matrix[start][i];
            }

            if(start<rowEnd){ // 大于1行
                for(int i=start+1;i<=rowEnd;i++){ //上 -> 下
                    res[idx++]=matrix[i][colEnd];
                }
            }

            if(start<rowEnd && start<colEnd){// 大于1行 && 大于1列
                for(int i=colEnd-1;i>=start;i--){ // 右-> 左
                    res[idx++] = matrix[rowEnd][i];
                }
            }

            if(start<rowEnd+1 && start<colEnd){ // 大于2行 && 大于1列
                for(int i=rowEnd-1;i>start;i--){  // 下 -> 上
                    res[idx++] = matrix[i][start];
                }
            }
        }
        return res;
    }

    // todo  螺旋矩阵 II
}

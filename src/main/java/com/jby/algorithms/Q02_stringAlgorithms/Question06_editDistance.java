package com.jby.algorithms.Q02_stringAlgorithms;

import org.junit.Test;

public class Question06_editDistance {
    /**
     * leetcode 72. 编辑距离
     * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。
     * 你可以对一个单词进行如下三种操作：
     * 插入一个字符
     * 删除一个字符
     * 替换一个字符
     *
     *
     *
     * dp[i][j]  =  min{
 *                   dp[i][j-1] +1;  //left
 *                   dp[i-1][j] +1;  // up
 *                   dp[i-1][j-1]+1; word1[i] ！= word1[j]  // diagonal
 *                   dp[i-1][j-1];  word1[i] == word1[j]   // diagonal
 *                 }
     */
    public int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2= word2.length();

        int[][] dp = new int[len1+1][len2+1];
        for(int i=0;i<len1+1;i++){
            dp[i][0] = i;
        }

        for(int j=0;j<len2+1;j++){
            dp[0][j] =j;
        }

        for(int i=1;i<=len1;i++){
            for(int j=1;j<=len2;j++){
                int upOrLeftDistance = Math.min(dp[i-1][j],dp[i][j-1])+1;
                int diagonalDistance = dp[i-1][j-1];
                if(word1.charAt(i-1)!=word2.charAt(j-1)){
                    diagonalDistance ++;
                }
                dp[i][j] = Math.min(upOrLeftDistance,diagonalDistance);
            }
        }

        return dp[len1][len2];
    }

    @Test
    public void test(){
        int i = minDistance("horse", "ros");
        System.out.println(i);
    }
}

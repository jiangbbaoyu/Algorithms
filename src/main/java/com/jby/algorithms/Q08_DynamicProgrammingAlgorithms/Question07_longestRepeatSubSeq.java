package com.jby.algorithms.Q08_DynamicProgrammingAlgorithms;

public class Question07_longestRepeatSubSeq {
    /**
     * leetcode 718. 最长重复子数组
     * 给两个整数数组 nums1 和 nums2 ，返回 两个数组中 公共的 、长度最长的子数组的长度 。
     *
     *  输入：nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]
     * 输出：3
     * 解释：长度最长的公共子数组是 [3,2,1] 。
     *
     * 示例 2：
     * 输入：nums1 = [0,0,0,0,0], nums2 = [0,0,0,0,0]
     * 输出：5
     *
     * 思路 :
     * dp[i][j]代表nums1,nums2中， 以nums1[i-1] 与 nums2[j-1] `结尾`  的公共字串的最大长度,
     * 公共字串必须以A[i-1]，B[j-1]结束，即
     *   当nums1[i-1] == nums2[j-1]时，dp[i][j] = dp[i-1][j-1] + 1;
     *   当nums1[i-1] != nums2[j-1]时，以nums1[i-1]和nums2[j-1]结尾的公共字串长度为0,dp[i][j] = 0。
     *   输出最大的公共字串的长度即为最长重复字串。 打个表会更直观一点
     *          3 2 1 4 7
     *         ----------
     *      1 | 0 0 1 0 0
     *      2 | 0 1 0 0 0
     *      3 | 1 0 0 0 0
     *      2 | 0 2 0 0 0
     *      1 | 0 0 3 0 0
     */
    public int findLength(int[] nums1, int[] nums2) {
        int[][] dp = new int[nums1.length+1][nums2.length+1];
        // 填表
        for(int i=1;i<nums1.length+1;i++){
            for(int j=1;j<nums2.length+1;j++){
                if(nums1[i-1]==nums2[j-1]){
                    dp[i][j]=  dp[i-1][j-1]+1;
                }
            }
        }
        // 寻找最大值
        int maxLen =0;
        for(int i=1;i<nums1.length+1;i++){
            int rowMaxLen =0;
            for(int j=1;j<nums2.length+1;j++){
                rowMaxLen = rowMaxLen>dp[i][j]? rowMaxLen:dp[i][j];
            }
            maxLen = maxLen>rowMaxLen? maxLen:rowMaxLen;
        }
        return maxLen;
    }

    public int findLength2(int[] nums1, int[] nums2) {

        int[][] dp = new int[nums1.length+1][nums2.length+1];
        int maxLen =0;
        for(int i=1;i<nums1.length+1;i++){
            for(int j=1;j<nums2.length+1;j++){
                if(nums1[i-1]==nums2[j-1]){
                    dp[i][j]=  dp[i-1][j-1]+1;
                    maxLen = maxLen> dp[i][j]? maxLen:dp[i][j];
                }
            }
        }
        return maxLen;
    }
}

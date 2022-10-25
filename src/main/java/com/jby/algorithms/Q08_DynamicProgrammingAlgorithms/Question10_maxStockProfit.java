package com.jby.algorithms.Q08_DynamicProgrammingAlgorithms;

import org.junit.Test;

public class Question10_maxStockProfit {

    /**
     * leetcode 121. 买卖股票的最佳时机 (剑指offer63)  (交易1次)
     * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
     * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票  （只能买卖一次）。设计一个算法来计算你所能获取的最大利润。
     *
     * 思路：遍历数组时    `保存当前遍历过的数组元素的最小值`， 每次遍历一个元素时就用当前值和最小值计算利润，并保存最大利润
     */
    public int maxProfit(int[] prices) {

        int maxProfit =0;
        int minPrice =prices[0];
        for(int i=1;i<prices.length;i++){
            if(minPrice > prices[i]){
                minPrice = prices[i];
            }

            int profit = prices[i] - minPrice;

            maxProfit = maxProfit<profit? profit:maxProfit;
        }
        return maxProfit;
    }

    /**
     * leetcode 122. 买卖股票的最佳时机 II (交易多次)
     * 给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
     * 在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
     * 返回 你能获得的 最大 利润 。
     * 示例 1：
     * 输入：prices = [7,1,5,3,6,4]
     * 输出：7
     * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4 。
     *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6 - 3 = 3 。
     *      总利润为 4 + 3 = 7
     *
     * 思路：dp[i] =  dp[i-1] + prices[i] - prices[i-1] , prices[i]>prices[i-1]
     *               dp[i-1]                           , prices[i]<=prices[i-1]
     *
     * eg:  prices = [7,1,5,3,6,4]
     *       dp    = [0,0,4,4,7,7]
     */
    public int maxProfit2(int[] prices) {

        int[] dp = new int[prices.length];
        for(int i=1;i<prices.length;i++){
            if(prices[i]>prices[i-1]){
                dp[i] =dp[i-1] + prices[i] - prices[i-1];
            }else{
                dp[i] =dp[i-1];
            }
        }

        return dp[prices.length-1];
    }

    /**
     * leetcode 123. 买卖股票的最佳时机 III  (0~2次交易)
     * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
     * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * 示例 1:
     * 输入：prices = [1,2,4,2,5,7,2,4,9,0]
     * 输出：13
     * 解释：在第 1 天（股票价格 = 1）的时候买入，在第 6 天（股票价格 = 7）的时候卖出，这笔交易所能获得利润 = 7-1 = 6 。
     *      随后，在第 7 天（股票价格 = 2）的时候买入，在第 9 天 （股票价格 = 9）的时候卖出，这笔交易所能获得利润 = 9-2 = 7
     *
     *
     *  思路：
     *      dp1[i] = max(dp[i-1], prices[i] - minval) 从前往后遍历，表示如果在第i天`卖出`的话，第1天到第i天之间的最大利润；dp1从前往后递增
     *      dp2[i] = max(dp[i+1], maxval - prices[i]) 从后往前遍历，表示如果在第i天`买入`的话,表示第i天到最后一天之间的最大利润；dp2从后往前递增
     *      res = max(dp1[i] + dp2[i])， dp1[i] + dp2[i] 表示从第1天到最后一天 `经过两次交易`(第一次在1~i天，第二次在 i~len-1天)的最大利润，
     *                                   我们的目标是找到令总利润最大的i。
     *
     *  eg
     *    prices = [1,2,4,2,5,7,2,4,9,0]
     *    dp1    = [0,1,3,3,4,6,6,6,8,8]
     *    dp2    = [8,7,7,7,7,7,7,5,0,0]
     *     i                  * *
     */
    public int maxProfit3(int[] prices) {

        if(prices==null || prices.length==1){
            return 0;
        }

        int[] dp1= new int[prices.length];
        int minPrice =prices[0];
        for(int i=1;i<prices.length;i++){
            dp1[i] = Math.max(dp1[i-1],prices[i] - minPrice);
            minPrice = Math.min(minPrice,prices[i]);
        }

        int[] dp2= new int[prices.length];
        int maxPrice = prices[prices.length-1];
        for(int j=prices.length-2;j>=0;j--){
            dp2[j] = Math.max(dp2[j+1],maxPrice - prices[j]);
            maxPrice = Math.max(maxPrice, prices[j]);
        }

        int maxProfit =0;
        for(int i=0;i<prices.length;i++){
            maxProfit = Math.max(maxProfit,dp1[i]+dp2[i]);
        }

        return maxProfit;
    }

    @Test
    public void test(){
        int i = maxProfit3(new int[]{2, 1, 2, 0, 1});
        System.out.println(i);
    }
}

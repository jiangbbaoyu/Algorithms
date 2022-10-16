package com.jby.algorithms.Q03_arrayAlgorithms;

public class Question05_stockMaxProfit {

    /**
     * leetcode 121. 买卖股票的最佳时机 (剑指offer63)
     * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
     * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
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
}

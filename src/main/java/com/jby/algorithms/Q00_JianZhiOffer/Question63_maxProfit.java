package com.jby.algorithms.Q00_JianZhiOffer;

/**
 * 剑指 Offer 63. 股票的最大利润
 * 假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？
 *
 * 思路：遍历数组时保存当前遍历过的数组元素的最小值， 每次遍历一个元素时就用当前值和最小值计算利润，并保存最大利润
 */
public class Question63_maxProfit {
    public int maxProfit(int[] prices) {
        if(prices==null || prices.length<=1){
            return 0;
        }

        int previousMin =prices[0];
        int maxProfit =0;
        for(int i=1;i<prices.length;i++){

            if (prices[i]-previousMin> maxProfit){
                maxProfit =  prices[i]-previousMin;
            }

            if(prices[i]<previousMin){
                previousMin = prices[i];
            }
        }

        return maxProfit;
    }
}

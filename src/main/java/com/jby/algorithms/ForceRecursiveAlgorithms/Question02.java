package com.jby.algorithms.ForceRecursiveAlgorithms;

/**
 * 背包问题
 */
public class Question02 {

    /**
     * 给定两个长度都为N的数组weights和values，weights[i]和values[i]分别代表i号物品的重量和价值。
     * 给定一个正数bag，表示一个载重bag的袋子，你装的物品不能超过这个重量。
     * 返回你能装下最多的价值是多少?
     * todo 动态规划
     */


    // 递归加法，从左到右遍历每个物品，每个物品都尝试放或不放入bag中
    public int mostValueOfTheBag(int[] weights,int[] values,int bag,int idx,int alreadyWeight){

        if(idx==weights.length){
            return 0;
        }
        if(alreadyWeight>bag){
            return 0;
        }
        return Math.max(
          mostValueOfTheBag(weights,values,bag,idx+1,alreadyWeight),
          values[idx]+mostValueOfTheBag(weights,values,bag,idx+1,alreadyWeight+weights[idx])
        );
    }

}

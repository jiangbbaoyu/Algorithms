package com.jby.algorithms.Q03_arrayAlgorithms;

import java.util.Arrays;

public class Question12_mergeRanges {
    /**
     * leetcode 56. 合并区间
     * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
     * 请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间
     *
     * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
     * 输出：[[1,6],[8,10],[15,18]]
     * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
     *
     * 思路：
     *      先根据interval的 begin排序，
     *      然后便利开始排序 : 如果后一个区间的begin <= 前一个区间的end, 则将后一个区间与前一个区间合并
     *                      否则前一个区间作为一个返回结果， 继续合并 后一个区间 以及之后的区间
     */
    public int[][] merge(int[][] intervals) {

        Arrays.sort(intervals,(i1, i2)-> i1[0]-i2[0]);

        int mergedIdx = 0;
        for(int i=1;i<intervals.length;i++){

            if(intervals[i][0]<= intervals[mergedIdx][1]){
                intervals[mergedIdx][1] = Math.max(intervals[mergedIdx][1], intervals[i][1]);
            }else{
                mergedIdx ++;
                intervals[mergedIdx] = intervals[i];
            }
        }
        // 复用了intervals数组， 合并完成后 ，intervals[0,mergedIdx]保存了合并后的结果
        int[][] res = new int[mergedIdx+1][2];
        for(int i=0;i<= mergedIdx;i++){
            res[i]= intervals[i];
        }
        return res;
    }
}

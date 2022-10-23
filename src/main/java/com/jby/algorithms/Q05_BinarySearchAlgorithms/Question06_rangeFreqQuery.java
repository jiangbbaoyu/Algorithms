package com.jby.algorithms.Q05_BinarySearchAlgorithms;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * leetcode 2080. 区间内查询数字的频率
 * 请你设计一个数据结构，它能求出给定子数组内一个给定值的 频率 。
 * 子数组中一个值的 频率 指的是这个子数组中这个值的出现次数。
 * 请你实现 RangeFreqQuery 类：
 * RangeFreqQuery(int[] arr) 用下标从 0 开始的整数数组 arr 构造一个类的实例。
 * int query(int left, int right, int value) 返回子数组 arr[left...right] 中 value 的 频率 。
 * 一个 子数组 指的是数组中一段连续的元素。arr[left...right] 指的是 nums 中包含下标 left 和 right 在内 的中间一段连续元素。
 *
 * 示例 1：
 * 输入：
 * ["RangeFreqQuery", "query", "query"]
 * [[[12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56]], [1, 2, 4], [0, 11, 33]]
 * 输出：
 * [null, 1, 2]
 * 解释：
 * RangeFreqQuery rangeFreqQuery = new RangeFreqQuery([12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56]);
 * rangeFreqQuery.query(1, 2, 4); // 返回 1 。4 在子数组 [33, 4] 中出现 1 次。
 * rangeFreqQuery.query(0, 11, 33); // 返回 2 。33 在整个子数组中出现 2 次
 *
 */
public class Question06_rangeFreqQuery {
    HashMap<Integer,List<Integer>> valToPositionMap = new HashMap<>();
    public Question06_rangeFreqQuery(int[] arr) {
        for(int i=0;i<arr.length;i++){
            if(!valToPositionMap.containsKey(arr[i])){
                valToPositionMap.put(arr[i],new ArrayList<Integer>());
            }
            List<Integer> positions = valToPositionMap.get(arr[i]);
            positions.add(i);
        }
    }

    /**
     * 思路：
     *  O(N) 时间复杂度超时，因此想到了 O(logN)复杂度，此时要基于 二分查找
     *  使用一个 hashmap 存放 arr各个元素 -> 元素在arr中的下标位置集合（下标位置按升序排列，可使用二分 ） 的映射关系
     *  对于一个给定的value, 和arr的 索引下标范围[left,right]，
     *  使用二分查找分别找到 left在 positions中的下标，或第一个大于left的值在positions中的下标 (leftBound)
     *                   right在 positions中的下标，或第一个小于right的值在positions中的下标(rightBound)
     *
     *  rightBound - leftBound +1 即为 vale 在 arr[left, right]中出现的频次
     */
    public int query(int left, int right, int value) {
        if(!valToPositionMap.containsKey(value)){
            return 0;
        }
        List<Integer> positions = valToPositionMap.get(value);

        int l =0;
        int r = positions.size()-1;

        while(l<=r){
            int mid = l + (r-l)/2;
            if(positions.get(mid)==left){
                l= mid;   // leetcode 300. 最长递增子序列，  循环过后，l 定位到 等于left 或第一个大于left的位置
                break;
            }else if (left<positions.get(mid)){
                r = mid-1;
            }else if(left>positions.get(mid)){
                l = mid+1;
            }
        }
        int leftBound =l;

        l =0;
        r = positions.size()-1;

        while(l<=r){
            int mid = l + (r-l)/2;
            if(positions.get(mid)==right){
                r= mid;   // 循环过后，r定位到 等于right 或第一个小于right的位置
                break;
            }else if (right<positions.get(mid)){
                r = mid-1;
            }else if(right>positions.get(mid)){
                l = mid+1;
            }
        }
        int rightBound = r;

        return rightBound -leftBound +1;
    }

    public static void main(String[] args) {
        Question06_rangeFreqQuery question06_rangeFreqQuery = new Question06_rangeFreqQuery(new int[]{12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56});
        int query = question06_rangeFreqQuery.query(0, 11, 33);
        System.out.println(query);
    }

}

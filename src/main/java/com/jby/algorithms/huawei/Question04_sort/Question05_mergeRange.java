package com.jby.algorithms.huawei.Question04_sort;

import java.util.Arrays;
import java.util.Scanner;

/**
 * NC37 合并区间
 * 描述
 * 给出一组区间，请合并所有重叠的区间。
 * 请保证合并后的区间按区间起点升序排列。
 */
public class Question05_mergeRange {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            // [[10,30],[20,60],[80,100],[150,180]]
            String line = in.nextLine();
            String[] rangeStrs = line.substring(2, line.length() - 2).split("],\\[");

            int[][] ranges = new int[rangeStrs.length][2];
            for (int i = 0; i < rangeStrs.length; i++) {
                String[] startAndEnd = rangeStrs[i].split(",");
                ranges[i]= new int[]{ Integer.parseInt(startAndEnd[0]),Integer.parseInt(startAndEnd[1])};
            }

            Arrays.sort(ranges,(r1,r2)->r1[0]-r2[0]);

            int mergedIdx =0;
            for (int i = 1; i < ranges.length; i++) {
                if(ranges[i][0]<=ranges[mergedIdx][1]){
                    ranges[mergedIdx][1] = Math.max( ranges[mergedIdx][1],  ranges[i][1]);
                }else{
                    ranges[++mergedIdx] = ranges[i];
                }
            }

            StringBuffer res = new StringBuffer("[");
            for (int i = 0; i <= mergedIdx; i++) {
                res.append("[");
                res.append(ranges[i][0]);
                res.append(",");
                res.append(ranges[i][1]);
                res.append("]");

                if(i!=mergedIdx){
                    res.append(",");
                }
            }
            res.append("]");
            System.out.println(res.toString());
        }
    }
}

package com.jby.algorithms.huawei.Question04_sort;

import java.util.Arrays;
import java.util.Scanner;

public class Question01_mergeRecord {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num=0;
        if (in.hasNextLine()) {
            num = Integer.parseInt(in.nextLine().trim());
        }
        int[][] data= new int[num][2];
        for (int i = 0; i < num; i++) {
            String[] fields = in.nextLine().trim().split(" ");
            data[i] = new int[]{Integer.parseInt(fields[0]),Integer.parseInt(fields[1])};
        }
        Arrays.sort(data,(arr1,arr2)->arr1[0]-arr2[0]);
        int mergedIdx =0;
        for (int i = 1; i < num; i++) {
            if(data[i][0]==data[mergedIdx][0]){
                data[mergedIdx][1] += data[i][1];
            }else{
                data[++mergedIdx] = data[i];
            }
        }

        for (int i = 0; i <= mergedIdx; i++) {
            System.out.println(data[i][0]+" "+data[i][1]);
        }
    }
}

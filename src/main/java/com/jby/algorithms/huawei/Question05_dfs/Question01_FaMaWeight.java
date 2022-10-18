package com.jby.algorithms.huawei.Question05_dfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Question01_FaMaWeight {

    /**
     * HJ41 称砝码
     * 描述
     * 现有n种砝码，重量互不相等，分别为 m1,m2,m3…mn ；
     * 每种砝码对应的数量为 x1,x2,x3...xn 。现在要用这些砝码去称物体的重量，问能称出多少种不同的重量。
     *
     * 称重重量包括 0
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            HashSet<Integer> set = new HashSet<>();//存放所有可能的结果，不用担心重复问题
            set.add(0);//初始化为0
            int n = in.nextInt();//个数
            int[] w = new int[n];
            int[] nums = new int[n];
            for(int i=0;i<n;i++){
                w[i] = in.nextInt();//砝码的重量
            }
            for(int i=0;i<n;i++){
                nums[i] = in.nextInt();//砝码个数
            }
            for(int i=0;i<n;i++){//遍历砝码
                ArrayList<Integer> list = new ArrayList<>(set);//取当前所有的结果
                for(int j=1;j<=nums[i];j++){//遍历个数
                    for(int k=0;k<list.size();k++){
                        set.add(list.get(k) + w[i] * j);
                    }
                }
            }
            System.out.println(set.size());
        }
    }

    // 基于组合(combination)的方式，超时
    public static void main2(String[] args) {
        Scanner in = new Scanner(System.in);
        int num=0;
        if(in.hasNextLine()) {
            num = Integer.parseInt(in.nextLine().trim());
        }
        String[] weights = in.nextLine().split(" ");
        String[] counts = in.nextLine().split(" ");

        ArrayList<Integer> faMaList = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            int count =Integer.parseInt(counts[i]);
            int weight =Integer.parseInt(weights[i]);
            for (int i1 = 0; i1 < count; i1++) {
                faMaList.add(weight);
            }
        }

        int res = calculate(faMaList);
        System.out.println(res);

    }

    private static int calculate(ArrayList<Integer> faMaList) {
        HashSet<Integer> weightsCanMeasure = new HashSet<>();
        doCalculate(faMaList,weightsCanMeasure,0,0);
        return weightsCanMeasure.size();
    }

    private static void doCalculate(ArrayList<Integer> faMaList, HashSet<Integer> weightsCanMeasure,
                                    int curWeight, int startIdx) {
        if(startIdx==faMaList.size()){
            weightsCanMeasure.add(curWeight);
            return;
        }
        doCalculate(faMaList,weightsCanMeasure,curWeight+faMaList.get(startIdx),startIdx+1);
        curWeight -= faMaList.get(startIdx);
        doCalculate(faMaList,weightsCanMeasure,curWeight,startIdx+1);
    }
}

package com.jby.algorithms.huawei.Question04_sort;

import java.util.Arrays;
import java.util.Scanner;

public class Question02_sortString {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num=0;
        if (in.hasNextLine()) {
            num = Integer.parseInt(in.nextLine().trim());
        }
        String[] strings = new String[num];
        for (int i = 0; i < num; i++) {
            strings[i] = in.nextLine().trim();
        }
        Arrays.sort(strings);
        for (int i = 0; i < num; i++) {
            System.out.println(strings[i]);
        }
    }
}

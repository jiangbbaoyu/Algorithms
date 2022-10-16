package com.jby.algorithms.huawei.Question01_string;

import java.util.Scanner;

public class Question06_reverseStr {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String line = in.nextLine();
            System.out.println(reverse(line));
        }
    }

    private static String reverse(String line) {
        int left =0;
        int right =line.length()-1;
        char[] chars = line.toCharArray();
        while(left<right){

            char tmp = chars[left];
            chars[left]=chars[right];
            chars[right]=tmp;

            left++;
            right--;
        }
        return new String(chars);
    }
}

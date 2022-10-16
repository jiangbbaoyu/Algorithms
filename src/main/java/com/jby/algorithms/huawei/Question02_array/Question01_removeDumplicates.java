package com.jby.algorithms.huawei.Question02_array;

import java.util.*;

/**
 * HJ3 明明的随机数
 * 明明生成了NN个1到500之间的随机整数。请你删去其中重复的数字，
 * 即相同的数字只保留一个，把其余相同的数去掉，然后再把这些数从小到大排序，按照排好的顺序输出
 */
public class Question01_removeDumplicates {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int size =0;
        if (in.hasNextLine()) {
            size = Integer.parseInt(in.nextLine().trim());
        }
        int[] nums = new int[size];
        for(int i=0;i<nums.length;i++){
            nums[i] = Integer.parseInt(in.nextLine().trim());
        }
        Arrays.sort(nums);
        int uniqueIdx =0;

        int left=0;
        int right=0;
        while(right <= nums.length) {
            if(right==nums.length){
                nums[uniqueIdx++] = nums[left];
                break;
            }

            if (nums[left] == nums[right]) {
                right++;
            } else if (nums[left] != nums[right]) {
                nums[uniqueIdx++] = nums[left];
                left = right;
                right++;
            }
        }

        for (int i = 0; i < uniqueIdx; i++) {
            System.out.println(nums[i]);
        }
    }
}

package com.jby.algorithms.huawei.Question03_hash;

import java.util.*;

public class Question01_twoNumSum {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String line = in.nextLine().trim();
            String[] strs = line.substring(1).split("]");
            int target = Integer.parseInt(strs[1].substring(1));
            strs = strs[0].split(",");

            HashMap<Integer, Integer> charToIdxMap =  new HashMap<>();
            int idx1 =-1;
            int idx2 =-1;
            for (int i = 0; i < strs.length; i++) {
                int num = Integer.parseInt(strs[i]);
                int value = target -num;
                if (charToIdxMap.containsKey(value)) {
                    idx1=charToIdxMap.get(value)+1;
                    idx2 = i+1;
                    break;
                }

                charToIdxMap.put(num, i);
            }
            System.out.println("["+idx1+","+idx2+"]");
        }
    }
}

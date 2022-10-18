package com.jby.algorithms.huawei.Question04_sort;

import java.util.*;

/**
 * HJ27 查找兄弟单词
 * 描述
 * 定义一个单词的“兄弟单词”为：交换该单词字母顺序（注：可以交换任意次），而不添加、删除、修改原有的字母就能生成的单词。
 * 兄弟单词要求和原来的单词不同。例如： ab 和 ba 是兄弟单词。 ab 和 ab 则不是兄弟单词。
 * 现在给定你 n 个单词，另外再给你一个单词 x ，让你寻找 x 的兄弟单词里，按字典序排列后的第 k 个单词是什么？
 * 注意：字典中可能有重复单词。
 */
public class Question03_brotherString {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String[] fields = in.nextLine().trim().split(" ");
            int candidatesNum = Integer.parseInt(fields[0]);
            int k = Integer.parseInt(fields[fields.length-1]);
            String target = fields[fields.length-2];
            String[] candidates = new String[candidatesNum];
            for (int i = 1; i <= candidatesNum; i++) {
                candidates[i-1] = fields[i];
            }
            ArrayList<String> res = new ArrayList<>();
            for (String candidate : candidates) {
                if(isBrother(target,candidate)){
                    res.add(candidate);
                }
            }
            Collections.sort(res);
            System.out.println(res.size());
            if(res.size()>=k){
                System.out.println(res.get(k-1));
            }

        }
    }

    private static boolean isBrother(String target, String candidate) {
        if(target.equals(candidate) ||target.length()!=candidate.length()){
            return false;
        }
        char[] chars1 = target.toCharArray();
        char[] chars2 = candidate.toCharArray();
        Arrays.sort(chars1);
        Arrays.sort(chars2);

        for (int i = 0; i < chars1.length; i++) {
            if(chars1[i]!=chars2[i]){
                return false;
            }
        }
        return true;
    }
}

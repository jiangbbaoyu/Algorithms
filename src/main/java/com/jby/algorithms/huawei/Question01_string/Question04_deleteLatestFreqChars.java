package com.jby.algorithms.huawei.Question01_string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

/**
 * HJ23 删除字符串中出现次数最少的字符
 * 实现删除字符串中出现次数最少的字符，若出现次数最少的字符有多个，则把出现次数最少的字符都删除。
 * 输出删除这些单词后的字符串，字符串中其它字符保持原来的顺序。
 */
public class Question04_deleteLatestFreqChars {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String line = in.nextLine();
            //1. 统计 字符频率
            HashMap<Character, Integer> charToFreq = new HashMap<>();
            for (int i = 0; i <line.length() ; i++) {
                char c = line.charAt(i);
                if(charToFreq.containsKey(c)){
                    charToFreq.put(c,charToFreq.get(c)+1);
                }else{
                    charToFreq.put(c,1);
                }
            }
            // 2. 获取最小频率
            int minFreq =Integer.MAX_VALUE;
            for (Map.Entry<Character, Integer> e : charToFreq.entrySet()) {
                if(minFreq>e.getValue()){
                    minFreq =e.getValue();
                }
            }
            //3. 获取最小频率的 字符set
            HashSet<Character> latestFreqChars = new HashSet<>();
            for (Map.Entry<Character, Integer> e : charToFreq.entrySet()) {
                if(minFreq==e.getValue()){
                   latestFreqChars.add(e.getKey());
                }
            }
            //4.过滤最小频率的字符
            StringBuffer res = new StringBuffer();
            for (int i = 0; i <line.length() ; i++) {
                if(!latestFreqChars.contains(line.charAt(i))){
                    res.append(line.charAt(i));
                }
            }

            System.out.println(res.toString());

        }
    }
}

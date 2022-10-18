package com.jby.algorithms.huawei.Question01_string;

import java.util.ArrayList;

public class Question07_permutation {
    public String[] permutation(String S) {
        ArrayList<String> resTmp = new ArrayList<>();
        char[] chars = S.toCharArray();
        doPermutate(resTmp,chars,0);
        String[] res = new String[resTmp.size()];
        for (int i = 0; i < resTmp.size(); i++) {
            res[i]= resTmp.get(i);
        }
        return  res;

    }

    private void doPermutate(ArrayList<String> resTmp, char[] chars, int startIdx) {
        if(startIdx==chars.length-1){
            resTmp.add(new String(chars));
            return;
        }
        boolean[] visited = new boolean[256];
        for (int i = startIdx; i <chars.length ; i++) {
            if(visited[chars[i]]){
                continue;
            }
            visited[chars[i]]= true;

            swap(chars,i,startIdx);
            doPermutate(resTmp,chars,startIdx+1);
            swap(chars,i,startIdx);
        }
    }
    private void swap(char[] chars, int i, int j) {
        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;
    }
}

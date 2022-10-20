package com.jby.algorithms.Q02_stringAlgorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Question08_generateParenthesis {
    /**
     * leetcode 22. 括号生成
     * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
     * 示例 1：
     * 输入：n = 3
     * 输出：["((()))","(()())","(())()","()(())","()()()"]
     *
     * 思路： dfs
     */
    public List<String> generateParenthesis(int n) {

        List<String> res = new ArrayList<>();
        dfs(res,n,n,"");
        return res;
    }

    private void dfs( List<String> res, int left,int right,String curStr){
        if(left==0 && right==0){  // 左右括号都不剩余了，递归终止
            res.add(curStr);
            return;
        }

        if(left>0){  // 如果左括号还剩余的话，优先拼接左括号
            dfs(res,left-1,right,curStr+"(");
        }

        if(left<right){ // 如果 当前字符串curStr中 ``左括号个数多于右括号个数`` 的话，可以拼接右括号  (防止非法括号序列)
            dfs(res,left,right-1,curStr+")");
        }
    }
}

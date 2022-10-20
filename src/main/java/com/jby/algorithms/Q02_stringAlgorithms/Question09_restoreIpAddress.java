package com.jby.algorithms.Q02_stringAlgorithms;

import java.util.ArrayList;
import java.util.List;

public class Question09_restoreIpAddress {
    /**
     * leetcode 93. 复原 IP 地址
     * 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
     * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
     * 给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过在 s 中插入 '.' 来形成。你 不能 重新排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。
     * 示例 1：
     * 输入：s = "25525511135"
     * 输出：["255.255.11.135","255.255.111.35"]
     *
     * 思路 ： 回溯法
     */
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        dfs(res,s,0,new ArrayList<String>());
        return res;
    }

    private void dfs(List<String> res,String s,int startIdx, List<String> curIp){
        if(startIdx==s.length()){  // 遍历到了字符串的末尾，如果此时curIP中正好包含了四个字符串，则拼接为一个IP地址，放入到结果集中
            if(curIp.size()==4){
                StringBuffer sb = new StringBuffer();
                for(int i=0;i<curIp.size();i++){
                    sb.append(curIp.get(i)+".");
                }
                res.add(sb.toString().substring(0,sb.length()-1));
            }
            return;
        }

        if(s.charAt(startIdx)=='0'){ // 本subNet的第一个字符为0的情况，此时 本段IP只能是 “0”， 其他以“0”开头的字符串都是非法的
            curIp.add("0");
            dfs(res,s,startIdx+1,curIp);
            curIp.remove(curIp.size()-1);
            return;
        }

        int sub =0;
        for(int i= startIdx,count=1;i<s.length();i++,count++){// 任何 (0,255]内的值都可以作为本Ip段的值
            char c = s.charAt(i);
            sub = sub*10 + (c-'0');
            if (sub>255){
                break;
            }
            curIp.add(String.valueOf(sub));
            dfs(res,s,startIdx+count,curIp);
            curIp.remove(curIp.size()-1);
        }
    }
}

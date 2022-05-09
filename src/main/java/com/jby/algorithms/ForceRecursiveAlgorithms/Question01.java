package com.jby.algorithms.ForceRecursiveAlgorithms;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Stack;

public class Question01 {

    /**
     * 打印字符串的所有子序列
     * @param str
     */
    public void printAllSub(String str) {
        printAllSub(str.toCharArray(), 0, "");
    }
    private void printAllSub(char[] str, int i, String res) {
        if (i == str.length) {
            System.out.println(res);
            return ;
        } else {
            // str中的每个字符都可以要或者不要
            printAllSub(str, i + 1, res); // 结果中不要下标为i的字符
            printAllSub(str, i + 1, res+str[i]); // 结果中要第i个字符
        }
    }

    /**
     * 字符串的全排列
     * todo 动态规划解法
     */

    public String[] permutation(String s) {

        ArrayList<String> resTmp = new ArrayList<String>();
        permutation(s.toCharArray(),0,resTmp,"");

        String[] res =new String[resTmp.size()];
        for(int i=0;i<resTmp.size();i++){
            res[i]=resTmp.get(i);
        }
        return res;
    }
    private  void permutation(char[] str,int idx, ArrayList<String> resTmp,String cur) {
        if(idx==str.length){
            resTmp.add(cur);
            return;
        }
        // cur的第[:idx-1]个字符已经确定的情况下（即cur的第[:idx-1]个字符相同的情况下），
        // 对于 a~z ,每个字符在cur[idx]只能出现一次 (分支限界)
        boolean[] visited =new boolean[26];
        for(int i=idx;i<str.length;i++){
            if(visited[str[i]-'a']){
                continue;
            }
            visited[str[i]-'a']=true;

            swap(str,i,idx);
            permutation(str,idx+1,resTmp,cur+str[idx]);
            swap(str,i,idx);
        }
    }

    private void swap(char[] str,int i,int j){
        char tmp = str[i];
        str[i]=str[j];
        str[j]=tmp;
    }

    /**
     * leetcode 486. 预测赢家
     * 给你一个整数数组 nums 。玩家 1 和玩家 2 基于这个数组设计了一个游戏。
     * 玩家 1 和玩家 2 轮流进行自己的回合，玩家 1 先手。
     * 开始时，两个玩家的初始分值都是 0 。每一回合，玩家从数组的任意一端取一个数字（即，nums[0] 或 nums[nums.length - 1]），
     * 取到的数字将会从数组中移除。玩家选中的数字将会加到他的得分上。当数组中没有剩余数字可取时，游戏结束。
     * 如果玩家 1 能成为赢家，返回 true 。如果两个玩家得分相等，同样认为玩家 1 是游戏的赢家，也返回 true 。
     *
     *
     * 思路： 考虑只有两个元素的特殊场景
     * todo 重复
     * todo 动态规划解法
     *
     **/


    public boolean PredictTheWinner(int[] nums) {
        return f(nums,0,nums.length-1) >= s(nums,0,nums.length-1);
    }

    // 对于一个特定的区间，玩家为先手时的逻辑
    public int f(int[] nums,int start,int end){
        if(start==end){
            return nums[start];
        }
        // 先手玩家在选择一个元素后，在排除该元素后的新区间中变为了后手玩家
        return Math.max(nums[start]+s(nums,start+1,end),nums[end]+s(nums,start,end-1));
    }
    // 对于一个特定的区间，玩家为后手时的逻辑
    public int s(int[] nums,int start,int end){
        if(start==end){
            return 0;
        }
        // 在[start:end]区间上后手玩家不会挑选元素
        // 后手玩家会在先手玩家挑选完最佳元素的区间上成为先手玩家
        return Math.min(f(nums,start+1,end),f(nums,start,end-1));
    }

    /**
     * 使用递归函数逆序一个栈
     */
    public void reverse(Stack<Integer> stack){
        if(stack.isEmpty()){
            return;
        }
        int lastEle = removeBottom(stack);//拿到栈中最底下的一个元素
        reverse(stack);
        stack.push(lastEle);

    }

    private int removeBottom(Stack<Integer> stack) {

        Integer ele = stack.pop();
        if(stack.isEmpty()){
            return ele;
        }else{
            Integer res = removeBottom(stack);
            stack.push(ele);
            return res;
        }
    }

    /**
     * 剑指 Offer 46. 把数字翻译成字符串
     * 给定一个数字，我们按照如下规则把它翻译为字符串：
     *  0 翻译成 “a” ，
     *  1 翻译成 “b”，……，
     *  11 翻译成 “l”，……，
     *  25 翻译成 “z”。
     *  一个数字可能有多个翻译
     *  todo 动态规划
     * @param num
     * @return
     */

    public int translateNum(int num) {
        char[] chars =String.valueOf(num).toCharArray();
        return translate(chars,0);
    }
    // 从左到右递归尝试
    private int translate(char[] chars,int idx){
        if(idx>=chars.length){
            return 1;
        }
        int res=0;
        if(chars[idx]=='1'){
            //  翻译为b,
            res += translate(chars,idx+1);
            if (idx+1<chars.length){
                // chars[idx],chars[idx+1] 一块进行翻译
                res += translate(chars,idx+2);
            }
        }else if (chars[idx]=='2'){
            // 将 chars[idx] 翻译为c,
            res += translate(chars,idx+1);
            if (idx+1<chars.length && chars[idx+1]<='5'){
                // chars[idx],chars[idx+1] 一块进行翻译
                res += translate(chars,idx+2);
            }
        }else{
            // chars[idx] 为 '0' ， '3' ~'9' 的情况 ，只能将 将 chars[idx] 进行单独翻译
            res += translate(chars,idx+1);
        }
        return res;
    }

    @Test
    public void test(){
        Stack<Integer> integers = new Stack<>();
        integers.push(1);
        integers.push(2);
        integers.push(3);
        integers.push(3);
        reverse(integers);
        System.out.println(integers);
    }

}

package com.jby.algorithms.Q00_JianZhiOffer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 剑指 Offer 38. 字符串的排列
 * 输入一个字符串，打印出该字符串中字符的所有排列。 (注意考虑输入字符串是否包含重复字符,字符的范围 a-z ? A-Z ? 特殊字符？  )
 * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
 */
public class Question38_arrangement_combination {
    public String[] permutation(String s) {
        if(s==null || s.length()==0){
            return new String[0];
        }

        ArrayList<String> resTmp = new ArrayList<String>();
        char[] chars = s.toCharArray();

        doPermutation(chars,0,resTmp);

        String[] res = new String[resTmp.size()];
        for(int i=0;i<res.length;i++){
            res[i]=resTmp.get(i);
        }
        return res;
    }

    private void doPermutation(char[] chars,int startIdx,ArrayList<String> resTmp){
        if(startIdx==chars.length){
            resTmp.add(new String(chars));
        }
        // 在chars[:startIdx-1] 确定的情况下，char[startIdx]不能出现重复的字符，
        // 如果char[startIdx]出现了2个重复字符， 则以chars[:startIdx]为前缀的字符排列均会出现两次
        boolean[] visited =new boolean[26];
        for(int i=startIdx;i<chars.length;i++){
            if(visited[chars[i]-'a']){
                continue;
            }
            visited[chars[i]-'a']=true;
            swap(chars,i,startIdx);
            doPermutation(chars,startIdx+1,resTmp);
            swap(chars,startIdx,i);
        }
    }

    private void swap(char[] chars,int x,int y){
        char tmp =chars[x];
        chars[x]=chars[y];
        chars[y]=tmp;
    }


    /**
     * 字符串的组合问题 (是否有重复字符的场景)
     * 思路： 从M个不同字符中任取N个字符的所有组合
     *       假设我们要求abc字符中任意两个字符的组合。也就是输入3个字符，求3个字符长度为2的组合。
     *       从第一个字符开始(也就是a)，第一种情况，这个组合有a，那么剩下就2-1=1个字符和a组合了，要么是b，要么是c，分别组合成ab，ac
     *                             第二种情况，这个组合没有a，那么就剩下bc，由于规定的是2个字符的组合，也就只有bc
     */
    public String[] combination(String s){
        if(s==null || s.length()==0){
            return new String[0];
        }

        ArrayList<String> resTmp = new ArrayList<String>();
//        HashSet<String> resTmp = new HashSet<String>(); // 如果输入字符串中有重复字符，可以考虑使用HashSet来去重
        char[] chars = s.toCharArray();

        for(int i=1;i<=s.length();i++){
            doCombination(chars,i,0,new StringBuffer(),resTmp);
        }


        String[] res = new String[resTmp.size()];
        for(int i=0;i<res.length;i++){
            res[i]=resTmp.get(i);
        }
        return res;
    }

    private void doCombination(char[] chars, int length, int startIdx,StringBuffer sb,ArrayList<String> resTmp) {

        if(length==0){
            resTmp.add(sb.toString());
            return;
        }

        if(startIdx>=chars.length){
            return;
        }

        // 1. 长度为length的一个组合中包含 char[startIdx]
        sb.append(chars[startIdx]);
        doCombination(chars,length-1,startIdx+1,sb,resTmp);
        sb.deleteCharAt(sb.length()-1);
        // 2. 长度为length的一个组合中不包含 char[startIdx]
        doCombination(chars,length,startIdx+1,sb,resTmp);
    }

    /**
     * leetcode 77. 组合
     * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
     * 你可以按 任何顺序 返回答案。
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        doCombine(res,new ArrayList<Integer>(),1,n,k);
        return res;
    }
    private void doCombine(List<List<Integer>> res, List<Integer> curRes, int start,int n,int k){
        if(curRes.size()==k){
            ArrayList<Integer> ans = new ArrayList<Integer>();
            ans.addAll(curRes);
            res.add(ans);
            return ;
        }
        if(start>n){
            return;
        }

        curRes.add(start);
        doCombine(res,curRes,start+1,n,k);
        curRes.remove(curRes.size()-1);

        doCombine(res,curRes,start+1,n,k);
    }

    /**
     * leetcode 78. 子集
     * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
     * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
     * 示例 1：
     * 输入：nums = [1,2,3]
     * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>>  res  = new ArrayList<>();
        getSubSets(res, nums,0, new ArrayList<Integer>());
        return res;
    }
    private void getSubSets(List<List<Integer>>  res, int[] nums, int startIdx,List<Integer> cur){
        if(startIdx==nums.length){
            List<Integer> ans = new ArrayList<Integer>();
            ans.addAll(cur);
            res.add(ans);
            return;
        }
        // 结果中不包含 nums[startIdx]
        getSubSets(res,nums,startIdx+1,cur);

        // 结果中包含 nums[startIdx]
        cur.add(nums[startIdx]);
        getSubSets(res,nums,startIdx+1,cur);
        cur.remove(cur.size()-1);
    }

    /**
     * leetcode 39. 组合总和
     *  给你一个 `无重复元素` 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合
     *  并以列表形式返回。你可以按 任意顺序 返回这些组合。
     *  candidates 中的 `同一个数字可以 无限制重复被选取` 。如果至少一个数字的被选数量不同，则两种组合是不同的。
     *
     * 输入：candidates = [2,3,6,7], target = 7
     * 输出：[[2,2,3],[7]]
     * 解释：
     * 2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
     * 7 也是一个候选， 7 = 7 。
     *
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>>  res = new ArrayList<>();
        combine(res,new ArrayList<Integer>(),0,0, candidates,target);

        return res;
    }
    private void combine(List<List<Integer>> res,List<Integer> cur,int curSum,int startIdx, int[] candidates, int target){
        if(curSum==target){
            res.add(new ArrayList<Integer>(cur));
            return;
        }
        if(curSum>target){
            return;
        }
        if(startIdx==candidates.length){
            return ;
        }

        combine(res,cur,curSum,startIdx+1,candidates,target); // 不选择当前元素

        cur.add(candidates[startIdx]);
        curSum += candidates[startIdx];
        combine(res,cur,curSum,startIdx,candidates,target); // 选择当前元素，由于 candidate中的一个数可以包含多次，因此 startIdx 没有加1
        curSum -= candidates[startIdx];
        cur.remove(cur.size()-1);
    }


    /**
     * leetcode 40. 组合总和 II
     * 给定一个候选人编号的集合 candidates（`可能存在重复元素`） 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     * candidates 中的每个数字在每个组合中只能使用 一次 。
     * 注意：解集不能包含重复的组合
     *
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates); // 先对 含有重复数字的 candidates进行排序
        List<List<Integer>>  res = new ArrayList<>();
        combine2(res,new ArrayList<Integer>(),0,0, candidates,target);
        return res;
    }
    private void combine2(List<List<Integer>> res,List<Integer> cur,int curSum,int startIdx, int[] candidates, int target){
        if(curSum==target){
            res.add(new ArrayList<Integer>(cur));
            return;
        }
        if(curSum>target) return;
        if(startIdx==candidates.length) return ;

        int i =1;
        while(startIdx+i<candidates.length && candidates[startIdx+i]==candidates[startIdx]) i++;
        combine2(res,cur,curSum,startIdx+i,candidates,target); // 对于不包含当前元素的情况，跳过重复元素，防止重复

        cur.add(candidates[startIdx]);
        curSum += candidates[startIdx];
        combine2(res,cur,curSum,startIdx+1,candidates,target); // 由于 candidate中每个元素只能使用1次，因此要 startIdx+1
        curSum -= candidates[startIdx];
        cur.remove(cur.size()-1);
    }

    @Test
    public void test(){
        String[] abcs = combination("abcc");
        System.out.println(Arrays.toString(abcs));
//        String[] abcs = combination("abc");
//        System.out.println(Arrays.toString(abcs));
    }

    @Test
    public void test2(){
        for (int i = 0; i <26 ; i++) {
            System.out.println('A'+i);
        }
    }


}

package com.jby.algorithms.JianZhiOffer;

/**
 * 剑指 Offer 19. 正则表达式匹配
 * 请实现一个函数用来匹配包含'. '和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（含0次）
 * 在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但与"aa.a"和"ab*a"均不匹配
 */
public class Question19 {
    public boolean isMatch(String s, String p) {
        if(s==null || p==null){
            return false;
        }

        return match(s.toCharArray(),0,p.toCharArray(),0);
    }

    private boolean match(char[] str,int idx1,char[] pattern ,int idx2){
        if(idx1==str.length && idx2==pattern.length){
            return true;
        }
        if(idx1!=str.length  && idx2==pattern.length){
            return false;
        }

        // 当 字符串匹配到末尾，模式没有到末尾时（idx1==str.length  && idx2!=pattern.length） 仍有可能匹配成功
        // eg ： "a"
        //       "ab*"

        if(idx2+1 < pattern.length && pattern[idx2+1]=='*'){ // pattern 匹配位置的下一个字符是 *
            if(idx1<str.length && (str[idx1]==pattern[idx2] || pattern[idx2]=='.')){

                return  match(str,idx1+1,pattern,idx2) ||  // 仅字符串后移  ，* 表示 1 + n
                        match(str,idx1+1,pattern,idx2+2) ||  // 字符串和 pattern同时后移  ， * 表示1
                        match(str,idx1,pattern,idx2+2);           // 仅pattern后移 ，* 表示0
            }else{
                return match(str,idx1,pattern,idx2+2);
            }
        }else{// pattern 匹配位置的下一个字符不是 *
            if((idx1<str.length && str[idx1]==pattern[idx2]) || pattern[idx2]=='.'){
                return  match(str,idx1+1,pattern,idx2+1);
            }else{
                return false;
            }

        }
    }

    //todo 使用dp加速


}

package com.jby.algorithms.Q00_JianZhiOffer;

import org.junit.Test;

/**
 * 剑指 Offer 20. 表示数值的字符串,给定一个字符串，可能包含一个数字的底数和指数部分， 底数可以是整数和小数
 *
 */
public class Question20 {

    public boolean isNumber(String s) {
        if(s==null || s.length()==0){
            return false;
        }
        s =s.trim();

        char[] str = s.toCharArray();
        boolean isValid =true;
        int idx =0;
        int[] idxAndFlag = scanInt(str,idx); //整数部分
        if(idxAndFlag[1]==-1){
            isValid =false;
        }
        idx = idxAndFlag[0];

        if(idx<str.length && str[idx]=='.'){
            idx++;
            idxAndFlag = scanUnsignedInt(str,idx); // 小数部分
            isValid = isValid || idxAndFlag[1]==1; // . 前面的整数部分合法 或者 . 后面的小数部分合法，则newIdx前的部分字符串合法  （eg 12.  .12 都合法）
            idx =idxAndFlag[0];
        }

        if(idx<str.length && (str[idx]=='e'||str[idx]=='E')){
            idx++;
            idxAndFlag = scanInt(str,idx); // 指数部分
            isValid = isValid && idxAndFlag[1]==1;  // e 前面的底数部分合法 并且 e 后面的指数部分合法，则idxAndFlag[0]前的部分字符串合法 （12e .e12 均不合法）
            idx =idxAndFlag[0];
        }

        return isValid && str.length ==idx; // idx 以前的字符串是一个合法的数字，且 idx到了字符串的末尾， 则整个字符串为一个合法的字符串
    }

    private int[] scanInt(char[] str,int from){
        return scan(str,from,false);
    }

    private int[] scanUnsignedInt(char[] str,int from){
        return scan(str,from,true);
    }

    private int[] scan(char[] str,int from,boolean skipSign){
        int idx = from;
        boolean hasNum =false;
        while(idx<str.length){
            if(!skipSign && idx==from && (str[idx]=='+' || str[idx]=='-')){
                idx++;
            }else if (str[idx]>='0' && str[idx]<='9'){
                idx++;
                hasNum =true;
            }else{
                break;
            }
        }
        return  hasNum? new int[]{idx,1}:new int[]{idx,-1};
    }


    @Test
    public void test(){
        boolean number = isNumber(".");
        System.out.println(number);
    }
}

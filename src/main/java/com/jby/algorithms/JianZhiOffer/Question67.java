package com.jby.algorithms.JianZhiOffer;

/**
 * 剑指 Offer 67. 把字符串转换成整数
 *
 * 写一个函数 StrToInt，实现把字符串转换成整数这个功能。不能使用 atoi 或者其他类似的库函数。
 * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。
 * 当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
 * 该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。
 * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。
 * 在任何情况下，若函数不能进行有效的转换时，请返回 0。
 */
public class Question67 {
    public int strToInt(String str) {
        char[] chars = str.toCharArray();
        boolean isNegative =false;
        int idx=0;
        while(idx<chars.length && chars[idx]==' '){
            idx++;
        }
        if(idx==chars.length){
            return 0;
        }

        if(chars[idx]=='-'){
            isNegative=true;
            idx++;
        }else if(chars[idx]=='+'){
            idx++;
        }
        int res =0;
        while(idx<chars.length && (chars[idx]>='0' && chars[idx]<='9')){

            int digit = chars[idx]-'0';

            if(res>(Integer.MAX_VALUE-digit)/10){ // 防止 res*10 + digit 导致的溢出问题
                return isNegative? Integer.MIN_VALUE:Integer.MAX_VALUE;
            }
            res = res*10+digit;
            idx++;
        }

        return isNegative?-res:res;
    }
}

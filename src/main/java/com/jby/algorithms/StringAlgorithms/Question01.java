package com.jby.algorithms.StringAlgorithms;

import org.junit.Test;

public class Question01 {

    /**
     * leetcode 8. 字符串转换整数 (atoi)
     * 思路 ： 整个字符串其实可以分为三部分： 第一部分为空字符串， 第二部分为 正负号， 第三部分为数字和字母
     * @param str
     * @return
     */
    public int myAtoi(String str) {
        char[] chars = str.toCharArray();
        int n = chars.length;
        int idx = 0;
        while (idx < n && chars[idx] == ' ') {
            // 去掉前导空格
            idx++;
        }
        if (idx == n) {
            //去掉前导空格以后到了末尾了
            return 0;
        }
        // 判断空格后面的 第一个字符 是否为 正负号
        boolean negative = false;
        if (chars[idx] == '-') {
            //遇到负号
            negative = true;
            idx++;
        } else if (chars[idx] == '+') {
            // 遇到正号
            idx++;
        }

        // 开始处理第三部分 （处理完空格部分后，紧挨着的第一或第二个字符开始）
        // 只解析第三部分中为数字的 `前缀部分`
        int ans = 0;
        // chars[idx]>='0' && chars[idx]<='9'  即  Character.isDigit(char)
        while (idx < n && chars[idx]>='0' && chars[idx]<='9') {
            int digit = chars[idx] - '0';
            // ans >2147483647 时， 如果是正数则结果为2147483647 ；如果是负数则结果为 -2147483648
            if (ans > (Integer.MAX_VALUE - digit) / 10) {
                // 本来应该是 ans * 10 + digit > Integer.MAX_VALUE
                // 但是 *10 和 + digit 都有可能越界，所有都移动到右边去就可以了。
                return negative? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            ans = ans * 10 + digit;
            idx++;
        }
        return negative? -ans : ans;
    }

    @Test
    public void test(){
        System.out.println(Integer.MIN_VALUE); // -(1<<31)
        System.out.println(Integer.MAX_VALUE); // (1<<31)-1
    }
    @Test
    public void test1(){
        String str ="-000000000000001";
        System.out.println(myAtoi(str));

    }
}

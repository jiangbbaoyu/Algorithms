package com.jby.algorithms.Q02_stringAlgorithms;

public class Question11_stringMultiply {

    /**
     * leetcode 43. 字符串相乘
     * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
     * 注意：不能使用任何内置的 BigInteger 库或直接将输入转换为整数。
     * 输入: num1 = "2", num2 = "3"
     * 输出: "6"
     * 输入: num1 = "123", num2 = "456"
     * 输出: "56088"
     *
     *  思路
     * 两个数相乘之后的结果的位数肯定不会超过两个数 位数之和，
     * 定义一个长度为两数位数之和的字符串，然后从尾部开始迭代，通过取余和整除的操作依次计算各位结果，
     * 最后从不为零的位置返回字符串。
     */
    public String multiply(String num1, String num2) {
        int len1 = num1.length();
        int len2 = num2.length();
        int[] res = new int[len1+len2];
        for(int i=len1-1;i>=0;i--){
            for(int j =len2-1;j>=0;j--){
                int tmp = (num1.charAt(i)-'0') * (num2.charAt(j)-'0') + res[i+j+1]; //
                res[i+j+1]  =  tmp%10;  // 由于此时 个位上的 数字已经加到了 tmp中， 此时 需要使用tmp%10的值 `重置` res[i+j+1]位
                res[i+j] += tmp/10;     // tmp的计算没有涉及到 高位  res[i+j]，  因此 需要将 tmp进位到res[i+j]位的值 `累加`到  res[i+j]位中
            }
        }

        StringBuilder sb = new StringBuilder();
        boolean prefixZeroSkipped = false;
        for(int i=0;i<res.length;i++){
            if(!prefixZeroSkipped && res[i]!=0){
                prefixZeroSkipped = true;
            }
            if(prefixZeroSkipped){
                sb.append(res[i]);
            }
        }
        return  sb.length()==0?"0":sb.toString();
    }
}

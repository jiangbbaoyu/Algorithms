package com.jby.algorithms.JianZhiOffer;

import org.junit.Test;

import java.util.ArrayList;

/**
 * 剑指 Offer 17. 打印从1到最大的n位数
 * 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999
 */
public class Question17 {
    public int[] printNumbers(int n) {
        ArrayList<Integer> resTmp =new ArrayList<Integer>();
        char[] nums = new char[n];
        for(int i=0;i<n;i++){
            nums[i] = '0';
        }

        while(!increment(nums)){
            resTmp.add(parseNum(nums));
        }
        int[] res = new int[resTmp.size()];
        for(int i=0;i<resTmp.size();i++){
            res[i] = resTmp.get(i);
        }
        return res;

    }

    // 基于字符数组保存num的值，并完成加一操作
    private boolean increment(char[] nums){
        int takeOver =0;
        int i = nums.length-1;
        boolean reachMax =false;
        while(i>=0){
            int num = nums[i]-'0' + takeOver; // 第i位的数值
            if(i==nums.length-1){
                num += 1;  // 个位加一
            }

            if(num >= 10){ // 有进位
                if( i==0){ // 最高位有进位，表示到达了nums字符数组所能表示的最大数字
                    reachMax =true;
                }
                takeOver = num/10;
                num =  num% 10;
                nums[i] =(char)('0'+num);
            }else{ // 无进位
                nums[i] = (char)('0'+num);
                break;
            }

            i--;
        }

        return reachMax;
    }

    private int parseNum(char[] nums){
        int res =0;
        boolean start =false;
        for(int i=0;i<nums.length;i++){
            if(!start && nums[i]!='0'){ // 忽略掉前置的 为0的高位
                start =true;
            }
            if(start){
                res = res*10 + (nums[i]-'0');
            }
        }
        return res;

    }

    // 方法2 ： 全排列的方式 在字符数组nums中 列出所有可能的字符组合
    public int[] printNumbers2(int n) {
        ArrayList<Integer> resTmp =new ArrayList<Integer>();
        char[] nums = new char[n];

        generateNum(nums,resTmp,-1);

        int[] res = new int[resTmp.size()];
        for(int i=0;i<resTmp.size();i++){
            res[i] = resTmp.get(i);
        }
        return res;

    }

    private void generateNum(char[] nums,ArrayList<Integer> tmpRes,int idx ){
        if(idx==nums.length-1){
            int num = parseNum(nums);
            if(num>0){
                tmpRes.add(num);
            }
            return ;
        }

        for(int i= 0;i<=9;i++){
            nums[idx+1] = (char)('0'+i);
            generateNum(nums,tmpRes,idx+1);
        }
    }

    // 两个正数相加
    // 两个字符串从个位开始逐个相加，逢十进一，最后反转
    private String add(String str1, String str2){
        StringBuilder res = new StringBuilder();
        int idx1 = str1.length()-1;
        int idx2 = str2.length()-1;

        int carry =0;
        while(idx1>=0 || idx2>=0||carry > 0){

            int num1 = idx1>=0?str1.charAt(idx1--)-'0':0;
            int num2 = idx2>=0?str2.charAt(idx2--)-'0':0;

            int sum = num1+num2+carry;
            res.append(sum%10);
            carry = sum/10;

        }
        return res.reverse().toString();
    }

    /**
     * 两个字符串大数相减 要考虑字符串一正一负的情况，
     * 为了始终保持大数-小数，先比较大小，看比较结果决定是否置换位置。再相减。
     * 最后，去掉前缀 多余的0。
     * 如果大数是负数，则在返回结果前加上负号
     */

    public String subtract(String str1,String str2){

        if(!compare(str1,str2)){
            String tmp =str1;
            str1=str2;
            str2=tmp;
        }

        StringBuilder sb = new StringBuilder();
        int borrow =0; // 记录借位表示
        int idx1 = str1.length()-1;
        int idx2 = str2.length()-1;

        while(idx1>= 0 || idx2>=0){
            if ( (idx1==0 &&str1.charAt(idx1)=='-') || (idx2==0 &&str2.charAt(idx2)=='-')){
                break;
            }

            int num1 = idx1>=0?str1.charAt(idx1--)-'0':0;
            int num2 = idx2>=0?str2.charAt(idx2--)-'0':0;
            int sub = num1-num2-borrow;
            if(sub<0){
                borrow =1;
                sub +=10;
            }
            sb.append(sub);
        }


        sb = sb.reverse();
        int idx=0;
        while(idx<=sb.length()-1 && sb.charAt(idx)=='0'){
            idx++;
        }

        if(idx==sb.length()){
            return "0";
        }

        String res= sb.substring(idx);
        if(str1.charAt(0)=='-'){
            return "-"+res;
        }
        return res;
    }

    private boolean compare(String str1, String str2) {
        str1 = str1.charAt(0)=='-'? str1.substring(1):str1;
        str2 = str2.charAt(0)=='-'? str2.substring(1):str2;

        if(str1.length() > str2.length()){
            return true;
        }else if(str1.length() < str2.length()){
            return false;
        }else{
            return str1.compareTo(str2)>0;
        }
    }

    // 结合add , subtract 方法 可以实现 两个正数的加减操作，
    // 两个正数，或两个负数 使用add方法， 一正一负使用subtract方法

    @Test
    public void test(){
        printNumbers(1);
    }

    @Test
    public void test2(){
        System.out.println(add("123","995"));
        System.out.println(add("123","0"));
        System.out.println(add("0","2"));
    }

    @Test
    public void test3(){
        System.out.println(subtract("-123","995"));
        System.out.println(subtract("-123","0"));
        System.out.println(subtract("0","-2"));
    }
}

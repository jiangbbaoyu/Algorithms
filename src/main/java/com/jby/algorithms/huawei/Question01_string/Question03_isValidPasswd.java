package com.jby.algorithms.huawei.Question01_string;

import java.util.Scanner;

/**
 * HJ20 密码验证合格程序
 * 密码要求:
 * 1.长度超过8位
 * 2.包括大小写字母.数字.其它符号,以上四种至少三种
 * 3.不能有长度大于2的包含公共元素的子串重复 （注：其他符号不含空格或换行）
 */
public class Question03_isValidPasswd {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String line = in.nextLine();
            boolean res = lenValid(line) && charKindsValid(line) && repeatSubStrValid(line);
            String resStr = res?"OK":"NG";
            System.out.println(resStr);
        }
    }

    private static boolean repeatSubStrValid(String line) {
        int left=0;
        int right =left+ 2;
        while(right<line.length()-1){
            String substring = line.substring(left, right + 1);
            if(line.substring(right+1).contains(substring)){
                return false;
            }else{
                left ++;
                right = left+2;
            }
        }
        return true;
    }

    private static boolean charKindsValid(String line) {

        boolean hasUpperAlpha= false;
        boolean hasLowerAlpha= false;
        boolean hasNumberic= false;
        boolean hasOtherChars= false;
        int kinds =0;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if(c>='0' && c<='9'){
                if (!hasNumberic){
                    kinds++;
                }
                hasNumberic =true;

            }else if (c>='a' && c<='z'){
                if (!hasLowerAlpha){
                    kinds++;
                }
                hasLowerAlpha =true;
            }else if(c>='A' && c<='Z'){
                if (!hasUpperAlpha){
                    kinds++;
                }
                hasUpperAlpha = true;
            }else if(!hasOtherChars){
                hasOtherChars = true;
                kinds++;
            }
        }

        return kinds>=3;
    }

    private static boolean lenValid(String line) {
        return line!=null && line.length()>=8;
    }
}

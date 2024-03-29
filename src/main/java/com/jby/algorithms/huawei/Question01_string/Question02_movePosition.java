package com.jby.algorithms.huawei.Question01_string;

import java.util.Scanner;

/**
 * HJ17 坐标移动
 * 开发一个坐标计算工具， A表示向左移动，D表示向右移动，W表示向上移动，S表示向下移动。从（0,0）点开始移动，从输入字符串里面读取一些坐标，并将最终输入结果输出到输出文件里面。
 * 输入：
 * 合法坐标为A(或者D或者W或者S) + 数字（两位以内）
 * 坐标之间以;分隔。
 * 非法坐标点需要进行丢弃。如AA10;  A1A;  $%$;  YAD; 等。
 * 下面是一个简单的例子 如：
 * A10;S20;W10;D30;X;A1A;B10A11;;A10;
 * 处理过程：
 * 起点（0,0）
 * +   A10   =  （-10,0）
 * +   S20   =  (-10,-20)
 * +   W10  =  (-10,-10)
 * +   D30  =  (20,-10)
 * +   x    =  无效
 * +   A1A   =  无效
 * +   B10A11   =  无效
 * +  一个空 不影响
 * +   A10  =  (10,-10)
 * 结果 （10， -10）
 */
public class Question02_movePosition {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            int x=0,y=0;
            String[] ops = in.nextLine().trim().split(";");
            for (int i = 0; i < ops.length; i++) {
                if(isValid(ops[i])){
                    char op= ops[i].charAt(0);
                    int offset = Integer.parseInt(ops[i].substring(1));
                    switch (op){
                        case 'A':
                            x -=offset;
                            break;
                        case 'D':
                            x+=offset;
                            break;
                        case  'W':
                            y+=offset;
                            break;
                        case 'S':
                            y-= offset;
                            break;
                    }
                }
            }
            System.out.println(x+","+y);
        }
    }

    private static boolean isValid(String op) {
        if(op==null){
            return false;
        }
        if(!op.startsWith("A") &&!op.startsWith("D")&&!op.startsWith("W")&&!op.startsWith("S")){
            return false;
        }
        try{
            Integer.parseInt(op.substring(1));
            return true;
        }catch (Exception e){
            return false;
        }
    }
}

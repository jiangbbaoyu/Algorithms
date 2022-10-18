package com.jby.algorithms.huawei.Question06_stack_queue;

import java.util.*;

public class Question01_unzip_str {
    /**
     * 报文解压缩  （stack） todo
     * 为了提升数据传输的效率，会对传输的报文进行压缩处理。
     * 输入一个压缩后的报文，请返回它解压后的原始报文。
     * 压缩规则：n[str]，表示方括号内部的 str 正好重复 n 次。
     *
     * 示例
     * 输入  3[m2[c]]
     * 输出  mccmccmcc
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        Stack<Character> stack = new Stack<>();
        String res = "";
        char[] ch = str.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == ']') {
                // 解压缩
                String tmpStr = "";
                while(!stack.isEmpty()) {
                    char poll = stack.pop();
                    if (poll >= 'a' && poll <= 'z') {  // 如果出栈的为字母
                        tmpStr = String.valueOf(poll) + tmpStr;
                    } else if (Character.isDigit(poll)) {  // 如果出栈的为数字
                        int num = 0;
                        if (!stack.isEmpty() && Character.isDigit(stack.peek())) {
                            num = (stack.pop() - '0') * 10 + (poll - '0');
                        } else {
                            num = poll - '0';
                        }
                        String waitStr = tmpStr; // 需要将tempStr暂存起来
                        for (int j = 0; j < num - 1; j++) { // 这里做 num -1 次的字符串相
                            tmpStr += waitStr;
                        }
                    }
                }
                res += tmpStr;
            }
            stack.push(ch[i]);
        }
        System.out.println(res);
    }


}

package com.jby.algorithms.huawei.Question01_string;

import java.util.*;

public class Question11_maxDistance {
    /**
     * 最远足迹
     * 题目描述：
     * 某探险队负责对地下洞穴进行探险。 探险队成员在进行探险任务时，随身携带的记录器会不定期地记录自身的坐标，但在记录的间隙中也会记录其他数据。 探索工作结束后，探险队需要获取到某成员在探险过程中相对于探险队总部的最远的足迹位置。
     * 仪器记录坐标时，坐标的数据格式为(x,y)，如(1,2)、(100,200)，其中0<x<1000，0<y<1000。同时存在非法坐标，如(01,1)、(1,01)，(0,100)属于非法坐标。
     * 设定探险队总部的坐标为(0,0)，某位置相对总部的距离为：x * x+ y * y。
     * 若两个座标的相对总部的距离相同，则第一次到达的坐标为最远的足迹。
     * 若记录仪中的坐标都不合法，输出总部坐标（0,0）。 备注：不需要考虑双层括号嵌套的情况，比如sfsdfsd((1,2))。
     * 输入描述:
     * 字符串，表示记录仪中的数据。
     * 如：ferga13fdsf3(100,200)f2r3rfasf(300,400)
     * 输出描述：
     * 字符串，表示最远足迹到达的坐标。
     * 如： (300,400)
     */

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String res = in.nextLine();
        char[] str = res.toCharArray();
        int max = 0;
        String ans = "(0,0)";  // 默认值，非法时的结果

        // 定位输入字符串中的 左右括号在字符串中的位置 ， (对于复杂的字符处理，可以通过遍历的方式来解决)
        ArrayList<Integer> left = new ArrayList<>();
        ArrayList<Integer> right = new ArrayList<>();
        for (int i = 0; i < str.length; i++) {  // 统计左右括号的位置，不考虑嵌套，所以两个list一一对应
            if (str[i] == '(') {
                left.add(i);
            }
            if (str[i] == ')') {
                right.add(i);
            }
        }
        for (int i = 0; i < left.size(); i++) {
            String[] s = res.substring(left.get(i) + 1, right.get(i)).split(",");  // 记录两个坐标
            // 判断是否非法
            if (s[0].charAt(0) != '0' && s[1].charAt(0) != '0') { //(01,0xx) 为非法位置
                int num1 = Integer.parseInt(s[0]);
                int num2 = Integer.parseInt(s[1]);
                if (num1 < 1000 && num2 < 1000 && num1 * num1 + num2 * num2 > max) {
                    max = num1 * num1 + num2 * num2;
                    ans = "(" + s[0] + "," + s[1] + ")";
                }
            }
        }
        System.out.println(ans);
    }
}

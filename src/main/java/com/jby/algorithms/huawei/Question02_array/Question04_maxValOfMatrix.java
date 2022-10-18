package com.jby.algorithms.huawei.Question02_array;

import java.util.Scanner;

public class Question04_maxValOfMatrix {
    /**
     * 矩阵最大值
     * 给定一个仅包含0和1的N*N二维矩阵，请计算二维矩阵的最大值，计算规则如下：
     * 每行元素按下标顺序组成一个二进制数（下标越大越排在低位），二进制数的值就是该行的值。矩阵各行值之和为矩阵的值。
     * 允许通过向左或向右整体循环移动每行元素来改变各元素在行中的位置。 比如：
     * [1,0,1,1,1]向右整体循环移动2位变为[1,1,1,0,1]，二进制数为11101，值为29。
     * [1,0,1,1,1]向左整体循环移动2位变为[1,1,1,1,0]，二进制数为11110，值为30
     *
     * 5
     * 1,0,0,0,1
     * 0,0,0,1,1
     * 0,1,0,1,0
     * 1,0,0,1,1
     * 1,0,1,0,1
     *
     * 输出 122
     * 第一行向右整体循环移动1位，得到本行的最大值[1,1,0,0,0]，二进制为11000，十进制为24。
     * 第二行向右整体循环移动2位，得到本行的最大值[1,1,0,0,0]，二进制为11000，十进制为24。
     * 第三行向左整体循环移动1位，得到本行的最大值[1,0,1,0,0]，二进制为10100，十进制为20。
     * 第四行向右整体循环移动2位，得到本行的最大值[1,1,1,0,0]，二进制为11100，十进制为28。
     * 第五行向右整体循环移动1位，得到本行的最大值[1,1,0,1,0]，二进制为11010，十进制为26。
     * 总和为24+24+20+28+26=122。
     *
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.nextLine();
        int total = 0;
        while (n > 0) {
            String[] row = in.nextLine().split(",");
            total += deal(row);
            n--;
        }
        System.out.println(total);
    }
    private static int deal(String[] row) {
        int max = 0;
        // 遍历 row中的每一个元素，如果碰到为1的列，则将该列前面的元素 旋转到后面
        // 计算 旋转后row 表示的二进制字符串的 数字，更新该行 可能的 最大值
        for (int i = 0; i < row.length; i++) {
            if ("1".equals(row[i])) {
                StringBuilder sb = new StringBuilder();
                for (int m = i; m < row.length; m++) {  // 先加后半部分
                    sb.append(row[m]);
                }
                for (int n = 0; n < i; n++) {  // 再加前半部分
                    sb.append(row[n]);
                }
                max = Math.max(max, Integer.parseInt(sb.toString(),2));  // 字符串转化为相对应的数字
            }
        }
        return max;
    }
}

package com.jby.algorithms.Q09_mathAlgorithms;

public class Question01_rand10 {
    class SolBase{
        public int rand7(){
            return 0;
        }
    }

    /**
     * leetcode 470. 用 Rand7() 实现 Rand10()
     * 给定方法 rand7 可生成 [1,7] 范围内的均匀随机整数，试写一个方法 rand10 生成 [1,10] 范围内的均匀随机整数。
     * 你只能调用 rand7() 且不能调用其他方法。请不要使用系统的 Math.random() 方法。
     * 每个测试用例将有一个内部参数 n，即你实现的函数 rand10() 在测试时将被调用的次数。请注意，这不是传递给 rand10() 的参数。
     * 思路：
     *   把已知的范围[1,7]按等概率划分，分别返回0和1 ([1,3] 返回0 ，[5,7]返回1 )。 然后通过0和1组合得到任意范围的结果。
     *   此题范围1-7，可以划分为生成1,2,3时返回0，生成5,6,7返回1，随机到4就重新生成。
     *   1-10的范围可以表示成1 + (0到9) 二进制的0000-1111范围为0-15，如果范围超过了9就重新生成。 一定可以返回一个0-9的数
     */
    class Solution extends SolBase {
        public int rand10() {
            int num;
            for (; ; ) {
                num = (getOneOrZero() << 3) + (getOneOrZero() << 2) + (getOneOrZero() << 1) + getOneOrZero();
                if (num <= 9) {
                    break;
                }

            }
            return num + 1;
        }

        private int getOneOrZero() {
            int random = rand7();
            for (; ; ) {
                random = rand7();
                if (random > 4) {
                    return 0;
                } else if (random < 4) {
                    return 1;
                }
            }
        }
    }

}

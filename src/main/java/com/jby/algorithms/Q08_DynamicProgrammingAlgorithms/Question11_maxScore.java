package com.jby.algorithms.Q08_DynamicProgrammingAlgorithms;

import org.junit.Test;

/**
 * leetcode 1799. N 次操作后的最大分数和
 * 给你 nums ，它是一个大小为 2 * n 的正整数数组。你必须对这个数组执行 n 次操作。
 * 在第 i 次操作时（操作编号从 1 开始），你需要：
 * 选择两个元素 x 和 y 。
 * 获得分数 i * gcd(x, y) 。
 * 将 x 和 y 从 nums 中删除。
 * 请你返回 n 次操作后你能获得的分数和最大为多少。
 * 函数 gcd(x, y) 是 x 和 y 的最大公约数。
 *
 * 输入：nums = [1,2,3,4,5,6]
 * 输出：14
 * 解释：最优操作是：
 * (1 * gcd(1, 5)) + (2 * gcd(2, 4)) + (3 * gcd(3, 6)) = 1 + 4 + 9 = 14
 *
 * 思路：  dp的状态压缩
 * 状态压缩动态规划。每一次操作，都是由上一次操作的基础上多抹去两个位转移而来的，
 * 可以用二进制枚举来代表当前2 * n个数中已经抹去的数的状态，1表示已经抹去，0表示还没有抹去。
 * 用dp[mask]表示操作至状态mask时的最大分数。
 * 对于枚举的任意一个状态k，可以再枚举两个包含在状态k中的1（假设分别在i和j为位上），
 * 去掉这两个1的状态s = k - (1 << i) - (1 << j)就应该是状态k的一个前态。状态k可以从所有这样的状态s转移而来，
 *
 * 为了加快计算，可以预处理所有可能用到的最大公约数，保存在矩阵中；
 * 并且在枚举的过程中保证 0<i<j<n≪10<i<j<n\ll10<i<j<n≪1，减少重复计算。
 */
public class Question11_maxScore {
    private int gcd(int i,int j){
        return j==0?i:gcd(j,i%j);
    }
    public int maxScore(int[] nums) {
        int m = nums.length;
        int[][] gcdArr = new int[m][m];
        for(int i=0;i<m;i++){// 预处理所有可能的最大公约数（只处理 i 小于 j 的情况）
            for(int j=0;j<m;j++){
                gcdArr[i][j] = gcd(nums[i],nums[j]);
            }
        }

        int mask = 1<<m;
        int[] dp = new int[mask];
        for(int i=0;i<mask;i++){
            int bits = Integer.bitCount(i);
            // 如果 1 的位数不是偶数就跳过(因为每次除去连个数，即将bits中的1减少两个，不可能出现奇数个1的情况)，当前的操作次数等于 bits / 2
            if(bits%2!=0) continue;
            // 在当前状态的基础上任意去掉两个位上的 1，则 dp[k] 可以从 dp[k \ (1 << i) \ (1 << j)] 转移而来
            for(int a=0;a<m-1;a++){
                if ((i&(1<<a))==0) continue;
                for(int b=a+1;b<m;b++){
                    if ((i&(1<<b))==0) continue;
                    dp[i] = Math.max(dp[i],dp[i-(1<<a)-(1<<b)] + gcdArr[a][b]*bits/2);
                }
            }
        }


        return dp[mask-1];
    }

    @Test
    public void test(){
        int i = maxScore(new int[]{1, 2, 3, 4, 5, 6});
        System.out.println(i);
    }
}

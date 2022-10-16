package com.jby.algorithms.Q00_JianZhiOffer;

public class Question10 {

    /**
     *  斐波那契数列问题
     *  f(0)=0; f(1)=1; f(n) = f(n-1) + f(n-2)
     * @param n
     * @return
     */
    public int fib(int n) {
        if(n==0){
            return 0;
        }
        if (n==1){
            return 1;
        }

        int prev1 = 1;
        int prev2 = 0;
        int res = 0;
        for(int i=2;i<=n;i++){
            res = prev1 + prev2;
            if(res>1000000007){
                res = res%1000000007;
            }

            prev2 = prev1;
            prev1 = res;
        }

        return res;
    }
    /**
     * 剑指 Offer 10- II. 青蛙跳台阶问题
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法
     * 思路： f(1) =1 : 只有一个台阶则总共有一种跳法； f(2)=2  两个台阶则总共有两种跳法
     *       f(n) = f(n-1) + f(n-2) , n > 2 ;   第一步有两种跳法， 如果跳一阶，则共有f(n-1)种跳法；如果跳两阶，则共有f(n-2)种跳法
     */
    public int numWays(int n) {


        int prev2 = 1;
        int prev1 = 2;
        if(n <=1 ){
            return prev2;
        }
        if (n ==2){
            return prev1;
        }
        int res =0;
        for(int i=3;i<=n;i++){
            res = prev1 + prev2;
            if(res>1000000007){
                res = res% 1000000007;
            }
            prev2=prev1;
            prev1=res;

        }
        return res;
    }

    /**
     *  拓展： 对于n个台阶， 如果一次可以跳 1，2，3 ... n阶， 则f(n) = 2^ n-1 ， 此时可以根据 ``乘方的性质`` 在O(logn)时间复杂度下求出
     *  a^n  =  a ^ n/2 *  a ^ n/2  , n为偶数
     *  a^n  =  a^(n-1)/2  *  a^(n-1）/2  * a , n为奇数
     */
}

package com.jby.algorithms.Q00_JianZhiOffer;

/**
 * 剑指 Offer 56 - I. 数组中数字出现的次数
 * 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)
 * 思路： 基于异或运算
 *       num& ((~num)+1)  可以得到num中最右侧的一个为1的bit位
 */
public class Question56 {
    public int[] singleNumbers(int[] nums) {
        if(nums==null || nums.length==0){
            throw new RuntimeException("invalid input");
        }

        int xXORy =0;
        for(int i=0;i<nums.length;i++){
            xXORy ^= nums[i];
        }

        int mostRightOneBit = xXORy & ((~xXORy)+1);

        int x=0;
        int y=0;
        for(int i=0;i<nums.length;i++){
            if( (nums[i] & mostRightOneBit)==0){
                x ^= nums[i];
            }else{
                y ^= nums[i];
            }
        }

        return new int[]{x,y};
    }


    /**
     * 剑指 Offer 56 - II. 数组中数字出现的次数 II
     * 在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字
     *
     * 思路： 位运算， 使用一个长度为32的数组，保存数组中所有数字 每个位上1的个数。 某个位上1的个数%3 即为只出现一次的数在该位上的值 。 空间复杂度O(1)
     */
    public int singleNumber(int[] nums) {
        if(nums==null || nums.length==0){
            throw new RuntimeException("invalid input");
        }

        int[] digits =new int[32];
        for(int i=0;i<nums.length;i++){

            int mask =1;
            for(int j=0;j<32;j++){

                if((nums[i]&mask)!=0){
                    digits[j] +=1;
                }
                mask = mask <<1;
            }
        }
        int res =0;
        for(int i=31;i>=0;i--){
            res = res<<1;
            res += digits[i]%3;
        }
        return res;
    }
}

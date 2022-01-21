package com.jby.algorithms.XORAlgorithms;


import org.junit.Test;

import java.util.Arrays;

/**
 *  异或操作满足交换律和结合律； 0^N = N ; N^N =0 ;
 *  异或操作可以看做是 无进位的二进制相加操作 ； 多个数异或，同一个位置如果1的个数为奇数，则该位的结果为1，且不影响其他位
 *
 *      1 0 1 1
 *      0 0 1 1
 *      0 1 0 1
 *      -------
 *      1 1 0 1
 *
 */
public class Question01 {


    /**
     * 数组中只有一个数出现了奇数次，其他数出现了偶数次，如何找出该出现了奇数次的数？ 时间复杂度 O(N), 空间复杂度O(1)
     * @param arr
     * @return
     */
    public int findOddNum(int[] arr){
        int res = 0;
        for (int i = 0; i <arr.length ; i++) {
            res = res ^ arr[i];
        }
        return res;
    }

    /**
     * 数组中只有两个数出现了奇数次，其他数出现了偶数次，如何找出该出现了奇数次的这两个数？时间复杂度 O(N), 空间复杂度O(1)
     * @param arr
     * @return
     */
    public int[] findTwoOddNums(int[] arr){
        //1. 假设两个出现了奇数次的数为a,b。首先计算a异或b的值，且该值一定不为0
        int aXORb = 0;
        for (int i = 0; i <arr.length ; i++) {
            aXORb ^= arr[i];
        }

        //2. 由于a异或b的值不为0 ，因此aXORb的二进制表示中一定存在一个或多个值为1的位。
        //   对于任意一个值为1的二进制位，该二进制位要么在a的二进制表示中为1， 要么在b的二进制表示中为1； 不可能a,b的二进制表示中该二进制位同时为1
        //   因此可以使用aXORb的二进制表示中一个值为1的位来区分开a,b两个数

        int rightOneBit = aXORb & ( (~aXORb) + 1); // 计算结果值的二进制表示中只有一个bit位为1， 该bit位为aXORb中最右侧为1的bit位

        int aORb =0;
        for (int i = 0; i < arr.length ; i++) {
//            if ((arr[i] & rightOneBit) == 0){
            if ((arr[i] & rightOneBit) != 0){ // 根据该判断条件数组分为了两部分，a,b分别在这两部分中。 对于任意一部分，其中除了a或b出现了一次，其他数都出现了两次
                aORb ^= arr[i];
            }
        }

        //3. 此时 aORb 为 a或b , 若aORb为a,则 aXORb^aORb 为b
        return new int[]{aORb,aXORb^aORb};
    }

    @Test
    public void test1(){

//        int[] arr = {1, 2, 3, 4, 2, 3, 4, 5};
        int[] arr = {1, 2,2,1,3,4,4};
        int oddNum = findOddNum(arr);
        System.out.println(oddNum);

    }

    @Test
    public void test2(){

//        int[] arr = {1, 2, 3, 4, 2, 3, 4, 5};
        int[] arr = {1, 2};
        int[] twoOddNums = findTwoOddNums(arr);
        System.out.println(Arrays.toString(twoOddNums));

    }


}

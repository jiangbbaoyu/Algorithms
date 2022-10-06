package com.jby.algorithms.JianZhiOffer;

/**
 * 剑指 Offer 66. 构建乘积数组
 * 给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，其中 B[i] 的值是数组 A 中除了下标 i 以外的元素的积,
 * 即 B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。不能使用除法
 */
public class Question66 {

    // 双重循环， 时间复杂度O(N*N)  ，超时
    public int[] constructArr(int[] a) {
        if(a==null || a.length==0){
            return new int[0];
        }

        int[] b = new int[a.length];
        for(int i=0;i<b.length;i++){
            b[i] =1;
        }

        for(int i=0;i<a.length;i++){
            for(int j=0;j<a.length;j++){
                if(j!=i){
                    b[i] *= a[j];
                }
            }
        }

        return b;
    }

    /**
     * 方法2：
     * 定义2个数组：rightPart, rightPart
     * leftPart[i] 表示数组 a[i] 左边的数的乘积 :a[0] * a[1]... * a[i-1]
     * rightPart[i] 表示数组 a[i] 右边的数的乘积: a[i+1] * a[i+2]... * a[n-1]
     * 最终 b[i] = left[i] * right[i]
     */
    public int[] constructArr2(int[] a) {
        if(a==null || a.length==0){
            return new int[0];
        }
        int[] b = new int[a.length];

        // 初始化 rightPart
        int[] rightPart = new int[a.length];
        rightPart[rightPart.length-1]=1;
        for(int i=rightPart.length-2;i>=0;i--){
            rightPart[i] = a[i+1] * rightPart[i+1];
        }

        // leftPart 可以和 返回结果数组合并
        int partialMultiplyValue = 1;
        for(int i=0;i<b.length;i++){
            b[i] = partialMultiplyValue * rightPart[i];
            partialMultiplyValue *= a[i];
        }

        return b ;
    }

}

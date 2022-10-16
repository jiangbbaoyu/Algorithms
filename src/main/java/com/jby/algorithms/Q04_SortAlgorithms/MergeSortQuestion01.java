package com.jby.algorithms.Q04_SortAlgorithms;


import com.jby.algorithms.Util.NumberUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class MergeSortQuestion01 {

    /**
     * 小和问题
     * 在一个数组中，每一个数左边比当前数小的数累加起来，叫做这个数组的小和。求一个数组的小和。
     * 例子:[1,3, 4,2, 5]
     *          1左边比1小的数，没有;              另个一解释     （1 右边比1大的数4个）
     *          3左边比3小的数，1;                另个一解释     （3 右边比3大的数2个）
     *          4左边比4小的数，1、3;             另个一解释     （4 右边比4大的数1个）
     *          2左边比2小的数，1;                另个一解释     （2 右边比2大的数1个）
     *          5左边比5小的数，1、3、4、2;        另个一解释     （5 右边比5大的数0个）
     *          所以小和为1+1+3+1+1+3+4+2=16     另个一解释      （所以小和为1*4 + 3*2 + 4*1 + 2*1 +5*0 =16 ）
     *
     * 遍历依次求每个数的小和过程中有很多比较是重复的 （O(N*N)），而利用归并排序，在合并两个有序部分序列时，
     * 对于一个左侧部分的数，可以快速得出右侧部分数值中大于该数的个数。然后 个数*该数值，即可得到该数对最终小和所做的贡献值
     * 在基于归并排序的流程中，大于一个数的元素的个数不是一下就算出来的 。 eg: 第一个元素1， 第二三个元素大于1是在一次小规模的归并过程中知道的； 第四五个元素大于1是在一次大规模的归并过程中知道的
     *
     *
     */
    public int leftSmallSum(int[] arr){

        if (arr==null || arr.length<=1){
            return 0;
        }


        int[] tempArr = new int[arr.length];
        return leftSmallSum(arr,tempArr,0,arr.length-1);
    }

    private int leftSmallSum(int[] arr,int[] tempArr, int left, int right) {


        if (left==right){ // 这一部分数组中只有一个元素，因此不需要左右划分，且没有比该数大的数
            return 0;
        }

        int middle= left + ((right-left)>>1);

        int leftPartialSum = leftSmallSum(arr,tempArr,left,middle);
        int rightPartialSum = leftSmallSum(arr,tempArr,middle+1,right);
        int leftSum = merge(arr,tempArr,left,middle,right); // 此时左边部分元素和右边部分元素分别为有序的
        return leftPartialSum+rightPartialSum+leftSum;
    }

    private int merge(int[] arr,int[] tempArr,int left, int middle, int right) {

        int k=left ;
        int i=left;
        int j=middle+1;

        int partialSum=0;
        while (i<=middle && j<=right){
            // 此处要确保arr[i]==arr[j]时，先移动右侧指针，这样才方便通过索引边界值计算右侧大于左侧arr[i]值的元素个数
            if (arr[i]<arr[j]){
                partialSum+=arr[i]*(right-j+1);
                tempArr[k++]=arr[i++];
            }else{
                tempArr[k++]=arr[j++];
            }

        }
        while(i<=middle){
            tempArr[k++]=arr[i++];
        }
        while (j<=right){
            tempArr[k++]=arr[j++];
        }

        for (k = left;  k<=right ; k++) {
            arr[k]=tempArr[k];
        }

        return partialSum;

    }

    @Test
    public void test(){
//        int[] arr = NumberUtils.generateRandomIntArr();
        int[] arr =NumberUtils.generateRandomIntArr(1,3, 4,2, 5);
        System.out.println(Arrays.toString(arr));
        System.out.println(leftSmallSum(arr));

//        ArrayList<Integer> integers = new ArrayList<Integer>(3);
//        System.out.println(integers.get(0));

        ArrayList<Integer> integers = new ArrayList<Integer>();
        integers.set(0,1);
        System.out.println(integers);
    }

}

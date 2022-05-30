package com.jby.algorithms.JianZhiOffer;

/**
 * 剑指 Offer 11. 旋转数组的最小数字
 * 把一个数组最开始的若干个元素(可以是0个)搬到数组的末尾，我们称之为数组的旋转。 数组可能存在 重复 元素值
 *
 */
public class Question11 {
    // 解法一 ： 剑指offer解法
    // left 指针向左侧部分的最后一个元素靠近； right 指针向右侧部分的第一个元素靠近；
    public int minArray(int[] numbers) {

        int left =0;
        int right = numbers.length-1;

        if(numbers[left]<numbers[right] || numbers.length==1){
            return numbers[left];
        }

        while(left<=right){

            if(left+1==right){
                return numbers[right];
            }

            int mid =  left + ((right-left)>>1);

            if(numbers[left]== numbers[mid] && numbers[left]== numbers[mid] ){
                return linearScanAndFindMinium(numbers,left,right);
            }

            if(numbers[mid]>=numbers[left]){
                left = mid;
            }else if(numbers[mid]<=numbers[right]){
                right = mid;
            }
        }

        return -1;
    }
    private int linearScanAndFindMinium(int[] arr, int start, int end){
        int minium =arr[start];
        for(int i= start +1;i<=end;i++){
            if(arr[i]<minium){
                minium = arr[i];
            }
        }
        return minium;
    }

    // 解法二

    public int minArray2(int[] numbers) {
        int l = 0;
        int r = numbers.length - 1;
        while (l <= r) {
            if(l==r){
                return numbers[l];
            }

            int mid = ((r - l) >> 1) + l;
            //右边界值比中间值大，mid一定在右侧数组中。 那[mid,right]一定是有序数组, 最小值不可能存在于[mid-1,right]中
            if (numbers[r] > numbers[mid]) {
                r = mid;
            } else if (numbers[r] < numbers[mid]) {
                //右边界值比中间值小，mid一定在左侧数组中。 那[left,mid]一定是左侧数组的一部分, 最小值不可能存在于[left,mid]中
                l = mid+1;
            } else {
                // numbers[r] == numbers[mid] , 此时无法判断mid在左侧还是右侧数组中。
                // 由于mid <r ， 且numbers[r] == numbers[mid]  ,因此通过缩减右边界r的值不会导致 最小值的丢失 （最小值还可以通过numbers[mid）取到
                r--;
            }
        }
        return numbers[l];
    }
}

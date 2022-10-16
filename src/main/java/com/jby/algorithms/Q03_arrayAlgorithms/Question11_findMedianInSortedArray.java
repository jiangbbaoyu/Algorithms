package com.jby.algorithms.Q03_arrayAlgorithms;

import org.junit.Test;

public class Question11_findMedianInSortedArray {
    /**
     * leetcode 4. 寻找两个正序数组的中位数
     * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
     * 算法的时间复杂度应该为 O(log (m+n)) 。
     */

    /**
     * 思路；
     * 使用二分查找法来求解。
     * 下中位数的定义：如果某个有序数组长度是奇数，那么其中位数就是最中间那个，
     *              如果是偶数，那么就是最中间两个数字的平均值。
     *
     * 假设两个有序数组的长度分别为m和n，由于两个数组长度之和 m+n 的奇偶不确定，因此需要分情况来讨论，
     *      对于奇数的情况，直接找到最中间的数即可，
     *      偶数的话需要求最中间两个数的平均值。
     *      为了简化代码，不分情况讨论，我们使用一个小trick，我们分别找第 (m+n+1) / 2 个，和 (m+n+2) / 2 个，然后求其平均值即可，
     *      这对奇偶数均适用。加入 m+n 为奇数的话，那么其实 (m+n+1) / 2 和 (m+n+2) / 2 的值相等，相当于两个相同的数字相加再除以2，还是其本身。
     * 定义一个函数（findKthInTwoSortedArrays）来在两个有序数组中找到第K个元素，
     *      当某一个数组的startIdx > endIdx时，说明其所有数字均已经被淘汰了，实际上就变成了在另一个数组中找数字
     *      如果target=1的话，我们只要比较nums1和nums2的起始位置startIdx1和startIdx2上的数字就可以
     *      一般的情况:
     *          对 target进行二分，``分别在nums1和nums2中查找第target/2个元素``
     *          由于两个数组的长度不定，可能某个数组没有第target/2个数字, 如果一个数组在[startIdx,endIdx]的元素个数不足 target/2个，则取最后一个元素
     *          ``比较这两个数组的nums1[midIdx1],nums2[midIdx2]的大小，``
     *              如果第一个数组的nums1[midIdx1]小的话，那么说明我们要找的数字肯定不在nums1中的前target/2个数字中([startIdx1,midIdx1]),可以将其淘汰，将nums1的起始位置向后移动，并且此时的target也自减去target/2
     *              反之，我们淘汰nums2中的前target/2个数字，并将nums2的起始位置向后移动target/2个，并且此时的target也自减去target/2
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int prev = (nums1.length+nums2.length+1)/2;
        int next = (nums1.length+nums2.length+2)/2;

        int leftVal = findKthInTwoSortedArrays(nums1,0,nums1.length-1,nums2,0,nums2.length-1,prev);
        int rightVal = findKthInTwoSortedArrays(nums1,0,nums1.length-1,nums2,0,nums2.length-1,next);
        return (leftVal+rightVal)/2.0;
    }
    // start ,end 为数组下标， target表示 数组中的第几个元素： 第target个元素 为nums[target-1]
    private int findKthInTwoSortedArrays(int[] nums1,int start1Idx,int end1Idx,int nums2[],int start2Idx,int end2Idx,int target){
        int len1 = end1Idx -start1Idx+1;
        int len2 = end2Idx- start2Idx+1;
        if(len1<=0) return nums2[start2Idx + target-1];
        if(len2<=0) return nums1[start1Idx+target-1];

        if(target == 1) return Math.min(nums1[start1Idx],nums2[start2Idx]);

        int midIdx1 = start1Idx + Math.min(target/2,len1)-1;
        int midIdx2 = start2Idx + Math.min(target/2, len2)-1;

        if(nums1[midIdx1]<nums2[midIdx2]){
            return findKthInTwoSortedArrays(nums1,midIdx1+1,end1Idx,nums2,start2Idx,end2Idx,target-(midIdx1-start1Idx+1));
        }else{
            return findKthInTwoSortedArrays(nums1,start1Idx,end1Idx,nums2,midIdx2+1,end2Idx,target-(midIdx2-start2Idx+1));
        }
    }


    @Test
    public void test(){
//        double medianSortedArrays = findMedianSortedArrays(new int[]{1, 3}, new int[]{2});
//        double medianSortedArrays = findMedianSortedArrays(new int[]{0,0,0,0,0}, new int[]{-1,0,0,0,0,0,1});
        double medianSortedArrays = findMedianSortedArrays(new int[]{1,3}, new int[]{2,7});
        System.out.println(medianSortedArrays);
    }

}

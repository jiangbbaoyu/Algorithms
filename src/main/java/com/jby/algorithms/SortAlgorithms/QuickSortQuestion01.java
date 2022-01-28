package com.jby.algorithms.SortAlgorithms;

import com.jby.algorithms.Util.NumberUtils;
import org.junit.Test;

import java.util.Arrays;

/**
 *
 * leetcode 75 : https://leetcode-cn.com/problems/sort-colors   颜色分类/ 荷兰国旗
 * 给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * 使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 *
 */
public class QuickSortQuestion01 {


    /**
     * 定义两个指针，left 和right,
     * left左侧（包括left）为小于target， left~right 为等于target， right(包括right)右侧为大于target
     * 指针i从左到右遍历数组
     * @param nums
     * @param target
     */
    public void sortColors(int[] nums,int target) {

        if(nums==null || nums.length==1){
            return;
        }
        int left =0 ;
        int right = nums.length-1;
        int i=0;

        while(i<=right){ // 由于i++, 或right-- 导致i==right时，此时仍然需要进入循环去比较nums[i]与target的大小
            if(nums[i]==target){
                i++;
            }else if(nums[i]>target){ // 由于不知道从右侧交换过来的数字具体情况，因此i不变，需要在下一轮循环中将nums[i]与target进行比较
                swap(nums,i,right);
                right--;
            }else if(nums[i]<target){
                swap(nums,left,i); // left位置交换过来的一定等于target,因此i++, 不需要在下一轮循环中将nums[i]与target进行比较
                left++;
                i++;
            }
        }

//        System.out.println("left:"+left);
//        System.out.println("right:"+right);
    }

    private void swap(int[] nums, int i,int j){
        if(i!=j){
            nums[i]=nums[i]^nums[j];
            nums[j]=nums[i]^nums[j];
            nums[i]=nums[i]^nums[j];
        }

    }

    @Test
    public void test(){
//        int[] arr = NumberUtils.generateRandomIntArr(10,10);
        int[] arr = NumberUtils.generateRandomIntArr(2,3,1,6,7,5);
        System.out.println(Arrays.toString(arr));
        sortColors(arr,4);
        System.out.println(Arrays.toString(arr));
    }

}

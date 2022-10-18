package com.jby.algorithms.huawei.Question01_string;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class Question09_vlanResourcePool {
    /**
     * 定义一个VLAN ID的资源池(下称VLAN资源池)，资源池中连续的VLAN用开始VLAN-结束VLAN表示，不连续的用单个整数表示，所有的VLAN用英文逗号连接起来。
     * 现在有一个VLAN资源池，业务需要从资源池中申请一个VLAN，需要你输出从VLAN资源池中移除申请的VLAN后的资源池
     * 输入描述:
     * 第一行为字符串格式的VLAN资源池，第二行为业务要申请的VLAN，VLAN的取值范围为[1,4094]之间的整数。
     * 输出描述:
     * 从输入VLAN资源池中移除申请的VLAN后字符串格式的VLAN资源池，输出要求满足题目描述中的格式，并且按照VLAN从小到大升序输出
     * eg:
         * 输入
         * 1-5
         * 2
         * 输出
         * 1,3-5
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] VLANPool = in.nextLine().split(",");
        int VLANNeed = in.nextInt();
        // 将字符串中所有包含的数字加入list
        List<Integer> list = new ArrayList<>();  // 存储数字数组
        for (String vlan : VLANPool) {
            String[] tmp = vlan.split("-");
            if (tmp.length > 1) {
                for (int i = Integer.parseInt(tmp[0]); i <= Integer.parseInt(tmp[1]); i++) {
                    list.add(i);
                }
            } else {
                list.add(Integer.parseInt(tmp[0]));
            }
        }
        // 如果list包含申请的VLAN，则从list删除
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == VLANNeed) {  // list是按照索引删除的，先找到对应索引，字符串的话用equals判断相等
                list.remove(i);
            }
        }
        // 将数组排完序后输出
        Collections.sort(list);
        StringBuilder sb = new StringBuilder();
        // 对排序后的数组进行重组，一一遍历，如果下个数等于上个数加1，继续遍历，否则直接加入sb   （双指针）
        int rangeStart =0;
        int prev=0;
        int cur =1;
        while(cur<=list.size()){
            if(cur==list.size() || list.get(cur)>list.get(prev)+1){
                if(rangeStart==prev){
                    sb.append(list.get(rangeStart)+",");
                }else{
                    sb.append(list.get(rangeStart)+"-"+list.get(prev)+",");
                }

                rangeStart = cur;
                prev = cur;
                cur ++;
            }else{
                cur++;
                prev++;
            }
        }

        System.out.println(sb.toString().substring(0, sb.length() - 1));  // 去除最后一个","
    }
}

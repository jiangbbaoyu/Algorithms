package com.jby.algorithms.greedyAlgorithms;

import org.junit.Test;
import org.omg.CORBA.INTERNAL;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 贪心策略往往会涉及到 按某个指标排序、  使用到堆结构等技巧来解决问题
 */
public class Question01 {

    /**
     *  安排最多的会议， 一个会议event的起止时间为 event[0] ~ event[1]
     *  todo 进阶问题 leetcode 1353
     * @param events
     * @return
     */
    public int maxEvents(int[][] events) {

        Arrays.sort(events, (x,y)->x[1]-y[1]); // 根据会议的结束时间排序, 优先安排结束时间最早的会议

        int currTime=-1;

        int res=0;
        for(int i=0;i<events.length;i++){
            int[] event = events[i];
            if(currTime<=event[0]){ // 当前时间小于该会议的起始时间，可以安排该会议
                currTime=event[1];  // 更新当前时间为该会议的结束时间
                res++;
            }
        }

        return res;
    }


    /**
     * leetcode 179. 最大数
     * 给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。输出结果返回一个字符串而不是整数
     * 思路：自定义排序算法中两个数的比较规则：比较两个数a,b的两种拼接方式，如果 ab>=ba,则a在前；否则b在前
     *
     * @param nums
     * @return
     */

    // 基于Comparator的简单解法
    public String largestNumber(int[] nums) {
        String[] strs =new String[nums.length];
        for (int i=0;i<nums.length;i++){
            strs[i]=String.valueOf(nums[i]);
        }
        Arrays.sort(strs,new Comparator<String>(){
            public int compare(String s1,String s2){
                return -(s1+s2).compareTo(s2+s1);
            }
        });

        String res ="";
        for (int i=0;i<strs.length;i++){
            res = res+ strs[i];
        }

        if (res.charAt(0)=='0'){
            return "0";
        }
        return res;
    }

    /**
     * 分金条问题 （哈夫曼编码问题）
     * 块金条切成两半，是需要花费和长度数值一样的铜板的。比如长度为20的金条，不管切成长度多大的两半，都要花费20个铜板。
     * 给定数组{10,20,30}，代表一共三个人，整块金条长度为10+20+30=60. 金条要分成10,20,30三个部分。
     * 如果，先把长度60的金条分成10和50，花费60 再把长度50的金条分成20和30，花费50 一共花费110铜板。
     * 如果，先把长度60的金条分成30和30，花费60 再把长度30金条分成10和20，花费30 一共花费90铜板。
     * 输入一个数组，返回分割的最小代价
     *
     *
     * 为了装修新房，你需要加工一些长度为正整数的棒材 sticks。
     * 如果要将长度分别为 X 和 Y 的两根棒材连接在一起，你需要支付 X + Y 的费用。 由于施工需要，你必须将所有棒材连接成一根。
     * 返回你把所有棒材 sticks 连成一根所需要的最低费用。注意你可以任意选择棒材连接的顺序。
     */

    public int lessCostSplitGolden(int[] nums){

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for(int i=0;i<nums.length;i++){
            queue.add(nums[i]);
        }

        int res=0;
        while(queue.size()>1){
            int tmpCost = queue.poll()+queue.poll();
            res+=tmpCost;
            queue.add(tmpCost);
        }

        return 0;
    }



    static class Project{
        int profit;
        int capital;
        public Project(int profit,int capital){
            this.profit=profit;
            this.capital=capital;
        }
    }

    /**
     * leetcode 502  寻找最佳利润的项目投资组合
     * 思路： 小顶堆配合大顶堆来实现
     * @param k 投资k个项目
     * @param w 初始资金
     * @param profits 每个项目的利润
     * @param capital 每个项目的成本
     * @return
     */
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {

        // 按项目成本从小到大排序
        PriorityQueue<Project> minCaptialQueue = new PriorityQueue<Project>((p1, p2) -> p1.capital-p2.capital);
        for(int i=0;i<capital.length;i++){
            minCaptialQueue.add(new Project(profits[i],capital[i]));
        }
        // 按项目利润从大到小排序
        PriorityQueue<Project> maxProfitQueue = new PriorityQueue<Project>(new Comparator<Project>(){
            public int compare(Project p1,Project p2){
                return p2.profit-p1.profit;
            }
        });


        while( k>0 ){// 每轮挑选一个项目
            while(!minCaptialQueue.isEmpty() && minCaptialQueue.peek().capital<=w){
                maxProfitQueue.add(minCaptialQueue.poll()); //将目前资金可以cover住的项目加入到maxProfitQueue队列，并按利润多少排序
            }

            if(maxProfitQueue.isEmpty()){
                return w;// 当前本金不足以拿下任何项目
            }
            Project currMaxProfit =maxProfitQueue.poll(); // 选择一个本金足够，且可以获取最大利润的项目
            w+=currMaxProfit.profit;
            k--;

        }
        return w;
    }


    /**
     * leetcode 295. 数据流的中位数
     * 思路： 可以将数据分为左右两边，一边以最大堆的形式实现，可以快速获得左侧最大数， 另一边则以最小堆的形式实现，可以快速获得右侧最小值
     *       插入过程中需要动态保证两边堆中的数据量只差不超过1
     */
    class MedianFinder {
        PriorityQueue<Integer> biggerHeap ; // small part
        PriorityQueue<Integer> smallHeap ; // bigger part
        public MedianFinder() {
            this.biggerHeap = new PriorityQueue<Integer>((x,y)->y-x); // 大根堆存放较小的部分数据
            this.smallHeap = new PriorityQueue<Integer>(); // 小根堆存放较大的部分数据
        }

        public void addNum(int num) {
            if(biggerHeap.isEmpty()){
                biggerHeap.add(num);
            }else{
                if (num > biggerHeap.peek()){
                    smallHeap.add(num);
                }else{
                    biggerHeap.add(num);
                }
            }
            int size1 = smallHeap.size();
            int size2 = biggerHeap.size();

            // 如果两边堆中的数据量相差超过1， 则移动数据
            if(size1-size2>1){
                biggerHeap.add(smallHeap.poll());
            }

            if(size2-size1>1){
                smallHeap.add(biggerHeap.poll());
            }

        }

        public double findMedian() {
            int size1 = smallHeap.size();
            int size2 = biggerHeap.size();
            if(size1-size2==1){
                return smallHeap.peek();
            }

            if(size2-size1==1){
                return biggerHeap.peek();
            }
            return (smallHeap.peek()+biggerHeap.peek())/2.0;
        }
    }

    @Test
    public void test2(){
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(-1);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(-2);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(-3);
        System.out.println(medianFinder.findMedian());
    }


    @Test
    public void test(){
        int maximizedCapital = findMaximizedCapital(2, 0, new int[]{1, 2, 3}, new int[]{0, 1, 1});
        System.out.println(maximizedCapital);
    }

}

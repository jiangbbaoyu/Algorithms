package com.jby.algorithms.JianZhiOffer;

/**
 * 剑指 Offer 62. 圆圈中最后剩下的数字 (约瑟夫环问题)
 * 0,1,···,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字（删除后从下一个数字开始计数）。求出这个圆圈里剩下的最后一个数字。
 * 例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。
 */
public class Question62 {


    // 方法1 ： 基于环形链表， 时间复杂度 O(M*N) , 超时
    class Node {
        int num;
        Node next;
        Node(int num){
            this.num =num;
        }
    }
    public int lastRemaining(int n, int m) {

        if(n<=0 || m <=0){
            throw new RuntimeException("invalid input");
        }

        if(m==1){ // 特殊场景
            return n-1;
        }

        Node head=null;
        Node tail =null;
        for(int i=0;i<n;i++){
            Node node = new Node(i);
            if(head==null){
                head =node;
                tail =node;
            }else{
                tail.next =node;
                tail =node;
            }
        }
        tail.next =head;

        Node cur =head;
        while(n>1){

            for(int i=1;i<m-1;i++){// cur 定位到待删除节点的前一个节点
                cur =cur.next;
            }
            cur.next =cur.next.next;
            cur =cur.next;
            n--;
        }

        return cur.num;
    }

    /**
     * f(n,m) 表示当有n个人，每隔m个人杀掉一个时，最后幸存者在数组中的下标
     *
     * f(n,m) = 0 , n=1  // 当只剩一个人时，他的编号必定为0
     * f(n,m) =(f(n-1,m)+m)%n  n>1  // 根据n-1个人的情况反推 n个人的情况
     *
     * 时间复杂度O(N)
     */

    public int lastRemaining2(int n, int m) {
        if(n<=0 || m <=0){
            throw new RuntimeException("invalid input");
        }

        int lastIdx =0;  // n ==1
        for(int i=2;i<=n ;i++){
            lastIdx = (lastIdx+m)%i;
        }
        return lastIdx;
    }





}

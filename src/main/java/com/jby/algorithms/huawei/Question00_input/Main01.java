package com.jby.algorithms.huawei.Question00_input;

import java.util.*;

public class Main01 {

    public static void main1(String[] args){
        Scanner s =  new Scanner(System.in);
        while(s.hasNextLine()){
            String line = s.nextLine();
            String[] fields = line.split(",");
            Arrays.sort(fields);
            System.out.println(String.join(",",fields));
        }
    }

    public static void main2(String[] args){
        Scanner s =new Scanner(System.in);
        int num = Integer.parseInt(s.nextLine());
        for(int i=0;i<num;i++){
            String[] fields = s.nextLine().split(" ");
            System.out.println(Integer.parseInt(fields[0])+Integer.parseInt(fields[1]));
        }

    }

    public static void main3(String[] args){
        Scanner s =new Scanner(System.in);
        while (s.hasNextLine()){
            String line = s.nextLine();
            if(line.equals("0 0")) break;
            String[] fields = line.split(" ");
            System.out.println(Integer.parseInt(fields[0])+Integer.parseInt(fields[1]));
        }

    }

    public static void main4(String[] args){
        Scanner s =new Scanner(System.in);
        while (s.hasNextLine()){
            String line = s.nextLine();
            if(line.equals("0")) break;
            String[] fields = line.split(" ");
            int num = Integer.parseInt(fields[0]);
            int sum =0;
            for (int i = 1; i <= num; i++) {
                sum += Integer.parseInt(fields[i]);
            }
            System.out.println(sum);
        }

    }

    public static void main(String[] args){
        Scanner s =new Scanner(System.in);
        int num = Integer.parseInt(s.nextLine()) ;
        for (int i = 1; i <= num; i++) {
            String line = s.nextLine();
            String[] fields = line.split(" ");
            int count = Integer.parseInt(fields[0]);
            int sum =0;
            for (int j = 1; j <=count ; j++) {
                sum += Integer.parseInt(fields[j]);
            }
            System.out.println(sum);

        }
    }

}

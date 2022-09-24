package com.sean.lintcode.LintCode1881;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-24 22:19
 * @Description: https://www.lintcode.com/problem/1881/?showListFe=true&page=1&pageSize=50
 * 1881 · 飞机座位
 * 描述
 * 您的任务是为四口之家尽可能多的分配座位。
 * 一个四口之家必须占领一排当中连续的四个座位。
 * 过道上的座位（例如2C和2D）不被认为是彼此相邻的。
 * 一家人被过道分开是可以的，但在这种情况下必须每一边坐两个。
 * 编写一个函数
 * class Solution {public int solution(int N,String S};
 * 函数中N表示有N排座位，S表示已经提供出去的座位，函数返回剩下的座位能坐四口之家的最大值。
 * 例如， N = 2，S = '1A 2F 1C’,你的函数应该返回2.下图给出了做法。
 */
public class Solution {

    public int solution(int n, String s) {
        if ("".equals(s)) {
            return 2 * n;
        }
        int[][] seats = new int[n][10];
        for (String str : s.split(" ")) {
            int row = Integer.valueOf(str.substring(0, str.length() - 1)) - 1;
            int column = Integer.valueOf(str.substring(str.length() - 1, str.length()).charAt(0) - 'A');
            seats[row][column] = 1;
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (seats[i][1] == 0 && seats[i][2] == 0 && seats[i][3] == 0 && seats[i][4] == 0) {
                res++;
                seats[i][1] = 1;
                seats[i][2] = 1;
                seats[i][3] = 1;
                seats[i][4] = 1;
            }
            if (seats[i][3] == 0 && seats[i][4] == 0 && seats[i][5] == 0 && seats[i][6] == 0) {
                res++;
                seats[i][3] = 1;
                seats[i][4] = 1;
                seats[i][5] = 1;
                seats[i][6] = 1;
            }
            if (seats[i][5] == 0 && seats[i][6] == 0 && seats[i][7] == 0 && seats[i][8] == 0) {
                res++;
                seats[i][5] = 1;
                seats[i][6] = 1;
                seats[i][7] = 1;
                seats[i][8] = 1;
            }
        }
        return res;
    }

}

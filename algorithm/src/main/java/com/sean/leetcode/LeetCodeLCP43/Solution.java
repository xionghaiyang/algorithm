package com.sean.leetcode.LeetCodeLCP43;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-26 14:40
 * @Description: https://leetcode.cn/problems/Y1VbOX/description/
 * LCP 43. 十字路口的交通
 * 前往「力扣挑战赛」场馆的道路上，有一个拥堵的十字路口，该十字路口由两条双向两车道的路交叉构成。
 * 由于信号灯故障，交警需要手动指挥拥堵车辆。
 * 假定路口没有新的来车且一辆车从一个车道驶入另一个车道所需的时间恰好为一秒钟，
 * 长度为 4 的一维字符串数组 directions 中按照 东、南、西、北 顺序记录了四个方向从最靠近路口到最远离路口的车辆计划开往的方向。其中：
 * "E" 表示向东行驶；
 * "S" 表示向南行驶；
 * "W" 表示向西行驶；
 * "N" 表示向北行驶。
 * 交警每秒钟只能指挥各个车道距离路口最近的一辆车，且每次指挥需要满足如下规则：
 * 同一秒钟内，一个方向的车道只允许驶出一辆车；
 * 同一秒钟内，一个方向的车道只允许驶入一辆车；
 * 同一秒钟内，车辆的行驶路线不可相交。
 * 请返回最少需要几秒钟，该十字路口等候的车辆才能全部走完。
 * 各个车道驶出的车辆可能的行驶路线如图所示：
 * 注意：
 * 测试数据保证不会出现掉头行驶指令，即某一方向的行驶车辆计划开往的方向不会是当前车辆所在的车道的方向;
 * 表示堵塞车辆行驶方向的字符串仅用大写字母 "E"，"N"，"W"，"S" 表示。
 */
public class Solution {

    public int trafficCommand(String[] directions) {
        int E = directions[0].length(), S = directions[1].length(), W = directions[2].length(), N = directions[3].length();
        int[][][][] dp = new int[E + 1][S + 1][W + 1][N + 1];
        for (int e = 0; e <= E; e++) {
            for (int s = 0; s <= S; s++) {
                for (int w = 0; w <= W; w++) {
                    for (int n = 0; n <= N; n++) {
                        if (e == 0 && s == 0 && w == 0 && n == 0) {
                            continue;
                        }
                        dp[e][s][w][n] = Integer.MAX_VALUE;
                        //只走一辆车，肯定没有冲突
                        if (e > 0) {
                            dp[e][s][w][n] = Math.min(dp[e][s][w][n], dp[e - 1][s][w][n] + 1);
                        }
                        if (s > 0) {
                            dp[e][s][w][n] = Math.min(dp[e][s][w][n], dp[e][s - 1][w][n] + 1);
                        }
                        if (w > 0) {
                            dp[e][s][w][n] = Math.min(dp[e][s][w][n], dp[e][s][w - 1][n] + 1);
                        }
                        if (n > 0) {
                            dp[e][s][w][n] = Math.min(dp[e][s][w][n], dp[e][s][w][n - 1] + 1);
                        }
                        //走两辆车
                        boolean es = false, ew = false, en = false, sw = false, sn = false, wn = false;
                        if (e > 0 && s > 0) {
                            char de = directions[0].charAt(e - 1), ds = directions[1].charAt(s - 1);
                            boolean cross = de == 'W' && ds == 'N' || de == 'S' && ds == 'N' || de == 'S' && ds == 'W';
                            es = de != ds && !cross;
                        }
                        if (e > 0 && w > 0) {
                            char de = directions[0].charAt(e - 1), dw = directions[2].charAt(w - 1);
                            boolean cross = de == 'W' && dw == 'N' || de == 'S' && dw == 'E';
                            ew = de != dw && !cross;
                        }
                        if (e > 0 && n > 0) {
                            char de = directions[0].charAt(e - 1), dn = directions[3].charAt(n - 1);
                            boolean cross = de == 'W' && dn == 'S' || de == 'W' && dn == 'E' || de == 'S' && dn == 'E';
                            en = de != dn && !cross;
                        }
                        if (s > 0 && w > 0) {
                            char ds = directions[1].charAt(s - 1), dw = directions[2].charAt(w - 1);
                            boolean cross = ds == 'N' && dw == 'E' || ds == 'W' && dw == 'E' || ds == 'W' && dw == 'N';
                            sw = ds != dw && !cross;
                        }
                        if (s > 0 && n > 0) {
                            char ds = directions[1].charAt(s - 1), dn = directions[3].charAt(n - 1);
                            boolean cross = ds == 'N' && dn == 'E' || ds == 'W' && dn == 'S';
                            sn = ds != dn && !cross;
                        }
                        if (w > 0 && n > 0) {
                            char dw = directions[2].charAt(w - 1), dn = directions[3].charAt(n - 1);
                            boolean cross = dw == 'E' && dn == 'S' || dw == 'N' && dn == 'S' || dw == 'N' && dn == 'E';
                            wn = dw != dn && !cross;
                        }
                        if (es) {
                            dp[e][s][w][n] = Math.min(dp[e][s][w][n], dp[e - 1][s - 1][w][n] + 1);
                        }
                        if (ew) {
                            dp[e][s][w][n] = Math.min(dp[e][s][w][n], dp[e - 1][s][w - 1][n] + 1);
                        }
                        if (en) {
                            dp[e][s][w][n] = Math.min(dp[e][s][w][n], dp[e - 1][s][w][n - 1] + 1);
                        }
                        if (sw) {
                            dp[e][s][w][n] = Math.min(dp[e][s][w][n], dp[e][s - 1][w - 1][n] + 1);
                        }
                        if (sn) {
                            dp[e][s][w][n] = Math.min(dp[e][s][w][n], dp[e][s - 1][w][n - 1] + 1);
                        }
                        if (wn) {
                            dp[e][s][w][n] = Math.min(dp[e][s][w][n], dp[e][s][w - 1][n - 1] + 1);
                        }
                        // 走三辆车
                        boolean esw = es && ew && sw, esn = es && en && sn;
                        boolean ewn = ew && en && wn, swn = sw && sn && wn;
                        if (esw) {
                            dp[e][s][w][n] = Math.min(dp[e][s][w][n], dp[e - 1][s - 1][w - 1][n] + 1);
                        }
                        if (esn) {
                            dp[e][s][w][n] = Math.min(dp[e][s][w][n], dp[e - 1][s - 1][w][n - 1] + 1);
                        }
                        if (ewn) {
                            dp[e][s][w][n] = Math.min(dp[e][s][w][n], dp[e - 1][s][w - 1][n - 1] + 1);
                        }
                        if (swn) {
                            dp[e][s][w][n] = Math.min(dp[e][s][w][n], dp[e][s - 1][w - 1][n - 1] + 1);
                        }
                        // 走四辆车
                        boolean eswn = es && ew && en && sw && sn && wn;
                        if (eswn) {
                            dp[e][s][w][n] = Math.min(dp[e][s][w][n], dp[e - 1][s - 1][w - 1][n - 1] + 1);
                        }
                    }
                }
            }
        }
        return dp[E][S][W][N];
    }

}

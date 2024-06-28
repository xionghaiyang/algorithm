package com.sean.leetcode.LeetCode1620;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-11-02 08:32
 * @Description: https://leetcode.cn/problems/coordinate-with-maximum-network-quality/
 * 1620. 网络信号最好的坐标
 * 给你一个数组 towers 和一个整数 radius 。
 * 数组  towers  中包含一些网络信号塔，其中 towers[i] = [xi, yi, qi] 表示第 i 个网络信号塔的坐标是 (xi, yi) 且信号强度参数为 qi 。
 * 所有坐标都是在  X-Y 坐标系内的 整数 坐标。
 * 两个坐标之间的距离用 欧几里得距离 计算。
 * 整数 radius 表示一个塔 能到达 的 最远距离 。
 * 如果一个坐标跟塔的距离在 radius 以内，那么该塔的信号可以到达该坐标。
 * 在这个范围以外信号会很微弱，所以 radius 以外的距离该塔是 不能到达的 。
 * 如果第 i 个塔能到达 (x, y) ，那么该塔在此处的信号为 ⌊qi / (1 + d)⌋ ，其中 d 是塔跟此坐标的距离。
 * 一个坐标的 信号强度 是所有 能到达 该坐标的塔的信号强度之和。
 * 请你返回数组 [cx, cy] ，表示 信号强度 最大的 整数 坐标点 (cx, cy) 。如果有多个坐标网络信号一样大，请你返回字典序最小的 非负 坐标。
 * 注意：
 * 坐标 (x1, y1) 字典序比另一个坐标 (x2, y2) 小，需满足以下条件之一：
 * 要么 x1 < x2 ，
 * 要么 x1 == x2 且 y1 < y2 。
 * ⌊val⌋ 表示小于等于 val 的最大整数（向下取整函数）。
 */
public class Solution {

    public int[] bestCoordinate(int[][] towers, int radius) {
        int xMax = Integer.MIN_VALUE, yMax = Integer.MIN_VALUE;
        for (int[] tower : towers) {
            xMax = Math.max(xMax, tower[0]);
            yMax = Math.max(yMax, tower[1]);
        }
        int cx = 0, cy = 0, maxQuality = 0;
        for (int x = 0; x <= xMax; x++) {
            for (int y = 0; y <= yMax; y++) {
                int quality = 0;
                for (int[] tower : towers) {
                    int d2 = (tower[0] - x) * (tower[0] - x) + (tower[1] - y) * (tower[1] - y);
                    if (d2 <= radius * radius) {
                        double d = Math.sqrt(d2);
                        quality += (int) Math.floor(tower[2] / (1 + d));
                    }
                }
                if (quality > maxQuality) {
                    cx = x;
                    cy = y;
                    maxQuality = quality;
                }
            }
        }
        return new int[]{cx, cy};
    }

}

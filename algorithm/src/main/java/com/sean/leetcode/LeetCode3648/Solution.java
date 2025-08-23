package com.sean.leetcode.LeetCode3648;

/**
 * @Author xionghaiyang
 * @Date 2025-08-23 17:24
 * @Description https://leetcode.cn/problems/minimum-sensors-to-cover-grid
 * 3648. 覆盖网格的最少传感器数目
 * 给你一个 n × m 的网格和一个整数 k。
 * 一个放置在单元格 (r, c) 的传感器可以覆盖所有与 (r, c) 的 切比雪夫距离不超过 k 的单元格。
 * 两个单元格 (r1, c1) 和 (r2, c2) 之间的 切比雪夫距离 为 max(|r1 − r2|,|c1 − c2|)。
 * 你的任务是返回覆盖整个网格所需传感器的 最少 数量。
 * 1 <= n <= 10^3
 * 1 <= m <= 10^3
 * 0 <= k <= 10^3
 */
public class Solution {

    public int minSensors(int n, int m, int k) {
        int size = k * 2 + 1;
        return ((n - 1) / size + 1) * ((m - 1) / size + 1);
    }

}

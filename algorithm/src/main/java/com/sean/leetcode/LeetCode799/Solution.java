package com.sean.leetcode.LeetCode799;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-11-20 23:07
 * @Description: https://leetcode.cn/problems/champagne-tower/
 * 799. 香槟塔
 * 我们把玻璃杯摆成金字塔的形状，其中 第一层 有 1 个玻璃杯， 第二层 有 2 个，依次类推到第 100 层， 每个玻璃杯 (250ml) 将盛有香槟。
 * 从顶层的第一个玻璃杯开始倾倒一些香槟，当顶层的杯子满了，任何溢出的香槟都会立刻等流量的流向左右两侧的玻璃杯。
 * 当左右两边的杯子也满了，就会等流量的流向它们左右两边的杯子，依次类推。
 * （当最底层的玻璃杯满了，香槟会流到地板上）
 * 现在当倾倒了非负整数杯香槟后，返回第 i 行 j 个玻璃杯所盛放的香槟占玻璃杯容积的比例（ i 和 j 都从0开始）。
 */
public class Solution {

    public double champagneTower(int poured, int query_row, int query_glass) {
        double[] row = {poured};
        for (int i = 1; i <= query_row; i++) {
            double[] nextRow = new double[i + 1];
            for (int j = 0; j < i; j++) {
                double volume = row[j];
                if (volume > 1) {
                    nextRow[j] += (volume - 1) / 2;
                    nextRow[j + 1] += (volume - 1) / 2;
                }
            }
            row = nextRow;
        }
        return Math.min(1, row[query_glass]);
    }

}

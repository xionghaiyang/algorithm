package com.sean.leetcode.LeetCode1401;

/**
 * @Author: xionghaiyang
 * @Date: 2026-09-19 16:05
 * @Description: https://leetcode.cn/problems/circle-and-rectangle-overlapping
 * 1401. 圆和矩形是否有重叠
 * 给你一个以 (radius, xCenter, yCenter) 表示的圆和一个与坐标轴平行的矩形 (x1, y1, x2, y2) ，其中 (x1, y1) 是矩形左下角的坐标，而 (x2, y2) 是右上角的坐标。
 * 如果圆和矩形有重叠的部分，请你返回 true ，否则返回 false 。
 * 换句话说，请你检测是否 存在 点 (xi, yi) ，它既在圆上也在矩形上（两者都包括点落在边界上的情况）。
 * 1 <= radius <= 2000
 * -10^4 <= xCenter, yCenter <= 10^4
 * -10^4 <= x1 < x2 <= 10^4
 * -10^4 <= y1 < y2 <= 10^4
 */
public class Solution {

    public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        int x = Math.max(x1, Math.min(xCenter, x2));
        int y = Math.max(y1, Math.min(yCenter, y2));
        return (x - xCenter) * (x - xCenter) + (y - yCenter) * (y - yCenter) <= radius * radius;
    }

}

package com.sean.leetcode.LeetCode1779;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-01 08:28
 * @Description: https://leetcode.cn/problems/find-nearest-point-that-has-the-same-x-or-y-coordinate/
 * 1779. 找到最近的有相同 X 或 Y 坐标的点
 * 给你两个整数 x 和 y ，表示你在一个笛卡尔坐标系下的 (x, y) 处。
 * 同时，在同一个坐标系下给你一个数组 points ，其中 points[i] = [ai, bi] 表示在 (ai, bi) 处有一个点。
 * 当一个点与你所在的位置有相同的 x 坐标或者相同的 y 坐标时，我们称这个点是 有效的 。
 * 请返回距离你当前位置 曼哈顿距离 最近的 有效 点的下标（下标从 0 开始）。
 * 如果有多个最近的有效点，请返回下标 最小 的一个。如果没有有效点，请返回 -1 。
 * 两个点 (x1, y1) 和 (x2, y2) 之间的 曼哈顿距离 为 abs(x1 - x2) + abs(y1 - y2) 。
 */
public class Solution {

    public int nearestValidPoint(int x, int y, int[][] points) {
        if (points == null || points.length == 0) {
            return -1;
        }
        int n = points.length;
        int res = -1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (points[i][0] == x || points[i][1] == y) {
                int cur = Math.abs(points[i][0] - x) + Math.abs(points[i][1] - y);
                if (cur < min) {
                    min = cur;
                    res = i;
                }
            }
        }
        return res;
    }

}

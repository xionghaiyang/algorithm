package com.sean.leetcode.LeetCode3025;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2025-09-02 07:11
 * @Description https://leetcode.cn/problems/find-the-number-of-ways-to-place-people-i
 * 3025. 人员站位的方案数 I
 * 给你一个  n x 2 的二维数组 points ，它表示二维平面上的一些点坐标，其中 points[i] = [xi, yi] 。
 * 计算点对 (A, B) 的数量，其中A 在 B 的左上角，并且它们形成的长方形中（或直线上）没有其它点（包括边界）。
 * 返回数量。
 * 2 <= n <= 50
 * points[i].length == 2
 * 0 <= points[i][0], points[i][1] <= 50
 * points[i] 点对两两不同。
 */
public class Solution {

    public int numberOfPairs(int[][] points) {
        //x升序，y降序
        Arrays.sort(points, (a, b) -> a[0] != b[0] ? a[0] - b[0] : b[1] - a[1]);
        int res = 0;
        int n = points.length;
        for (int i = 0; i < n; i++) {
            int y1 = points[i][1], maxY = Integer.MIN_VALUE;
            for (int j = i + 1; j < n; j++) {
                int y2 = points[j][1];
                if (y2 <= y1 && y2 > maxY) {
                    maxY = y2;
                    res++;
                }
            }
        }
        return res;
    }

}

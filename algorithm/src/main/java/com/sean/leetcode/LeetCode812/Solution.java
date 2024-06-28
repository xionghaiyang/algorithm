package com.sean.leetcode.LeetCode812;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-01-09 13:09
 * @Description: https://leetcode.cn/problems/largest-triangle-area/
 * 812. 最大三角形面积
 * 给你一个由 X-Y 平面上的点组成的数组 points ，其中 points[i] = [xi, yi] 。
 * 从其中取任意三个不同的点组成三角形，返回能组成的最大三角形的面积。
 * 与真实值误差在 10^(-5) 内的答案将会视为正确答案。
 */
public class Solution {

    public double largestTriangleArea(int[][] points) {
        int n = points.length;
        double res = 0.0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    res = Math.max(res, triangleArea(points[i][0], points[i][1], points[j][0], points[j][1], points[k][0], points[k][1]));
                }
            }
        }
        return res;
    }

    private double triangleArea(int x1, int y1, int x2, int y2, int x3, int y3) {
        return 0.5 * Math.abs(x1 * y2 + x2 * y3 + x3 * y1 - x1 * y3 - x2 * y1 - x3 * y2);
    }

}

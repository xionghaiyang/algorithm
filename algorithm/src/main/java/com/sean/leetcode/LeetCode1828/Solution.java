package com.sean.leetcode.LeetCode1828;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-01-29 10:47
 * @Description: https://leetcode.cn/problems/queries-on-number-of-points-inside-a-circle/
 * 1828. 统计一个圆中点的数目
 * 给你一个数组 points ，其中 points[i] = [xi, yi] ，表示第 i 个点在二维平面上的坐标。
 * 多个点可能会有 相同 的坐标。
 * 同时给你一个数组 queries ，其中 queries[j] = [xj, yj, rj] ，表示一个圆心在 (xj, yj) 且半径为 rj 的圆。
 * 对于每一个查询 queries[j] ，计算在第 j 个圆 内 点的数目。如果一个点在圆的 边界上 ，我们同样认为它在圆 内 。
 * 请你返回一个数组 answer ，其中 answer[j]是第 j 个查询的答案。
 */
public class Solution {

    public int[] countPoints(int[][] points, int[][] queries) {
        int n = queries.length;
        int[] answer = new int[n];
        for (int j = 0; j < n; j++) {
            int xj = queries[j][0];
            int yj = queries[j][1];
            int rj = queries[j][2];
            for (int[] point : points) {
                int xi = point[0];
                int yi = point[1];
                if (Math.pow(xi - xj, 2) + Math.pow(yi - yj, 2) <= Math.pow(rj, 2)) {
                    answer[j]++;
                }
            }
        }
        return answer;
    }

}

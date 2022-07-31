package com.sean.leetcode;

/**
 * 访问所有点的最小时间
 * https://leetcode-cn.com/problems/minimum-time-visiting-all-points/
 */
public class LeetCode1266 {

    public static int minTimeToVisitAllPoints(int[][] points) {
        int res = 0;
        for (int i = 0; i < points.length - 1; i++) {
            res += Math.max(Math.abs(points[i][0] - points[i + 1][0]), Math.abs(points[i][1] - points[i + 1][1]));
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(minTimeToVisitAllPoints(new int[][]{{1, 1}, {3, 4}, {-1, 0}}));
        System.out.println(minTimeToVisitAllPoints(new int[][]{{3, 2}, {-2, 2}}));
    }
}

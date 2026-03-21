package com.sean.leetcode.LeetCodeInterview1603;

/**
 * @Author xionghaiyang
 * @Date 2026-03-21 19:10
 * @Description https://leetcode.cn/problems/intersection-lcci
 * 面试题 16.03. 交点
 * 给定两条线段（表示为起点start = {X1, Y1}和终点end = {X2, Y2}），如果它们有交点，请计算其交点，没有交点则返回空值。
 * 要求浮点型误差不超过10^-6。
 * 若有多个交点（线段重叠）则返回 X 值最小的点，X 坐标相同则返回 Y 值最小的点。
 * 坐标绝对值不会超过 2^7
 * 输入的坐标均是有效的二维坐标
 */
public class Solution {

    public double[] intersection(int[] start1, int[] end1, int[] start2, int[] end2) {
        int x1 = start1[0], y1 = start1[1], x2 = end1[0], y2 = end1[1];
        int x3 = start2[0], y3 = start2[1], x4 = end2[0], y4 = end2[1];
        int d = det(x1 - x2, x4 - x3, y1 - y2, y4 - y3);
        int p = det(x4 - x2, x4 - x3, y4 - y2, y4 - y3);
        int q = det(x1 - x2, x4 - x2, y1 - y2, y4 - y2);
        if (d != 0) {
            double lam = (double) p / d, eta = (double) q / d;
            if (!(0 <= lam && lam <= 1 && 0 <= eta && eta <= 1)) {
                return new double[0];
            }
            double x = lam * x1 + (1 - lam) * x2;
            double y = lam * y1 + (1 - lam) * y2;
            return new double[]{x, y};
        }
        if (p != 0 || q != 0) {
            return new double[0];
        }
        int[][] t1 = sort(start1, end1), t2 = sort(start2, end2);
        if (compare(t1[1], t2[0]) < 0 || compare(t2[1], t1[0]) < 0) {
            return new double[0];
        }
        return compare(t1[0], t2[0]) > 0 ? new double[]{t1[0][0], t1[0][1]} : new double[]{t2[0][0], t2[0][1]};
    }

    private int det(int a, int b, int c, int d) {
        return a * d - b * c;
    }

    private int[][] sort(int[] start, int[] end) {
        if (compare(start, end) <= 0) {
            return new int[][]{start, end};
        } else {
            return new int[][]{end, start};
        }
    }

    private int compare(int[] a, int[] b) {
        return a[0] != b[0] ? a[0] - b[0] : a[1] - b[1];
    }

}

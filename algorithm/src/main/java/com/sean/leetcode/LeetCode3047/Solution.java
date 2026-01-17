package com.sean.leetcode.LeetCode3047;

/**
 * @Author xionghaiyang
 * @Date 2026-01-17 12:35
 * @Description https://leetcode.cn/problems/find-the-largest-area-of-square-inside-two-rectangles
 * 3047. 求交集区域内的最大正方形面积
 * 在二维平面上存在 n 个矩形。给你两个下标从 0 开始的二维整数数组 bottomLeft 和 topRight，两个数组的大小都是 n x 2 ，其中 bottomLeft[i] 和 topRight[i] 分别代表第 i 个矩形的 左下角 和 右上角 坐标。
 * 我们定义 向右 的方向为 x 轴正半轴（x 坐标增加），向左 的方向为 x 轴负半轴（x 坐标减少）。
 * 同样地，定义 向上 的方向为 y 轴正半轴（y 坐标增加），向下 的方向为 y 轴负半轴（y 坐标减少）。
 * 你可以选择一个区域，该区域由两个矩形的 交集 形成。
 * 你需要找出能够放入该区域 内 的 最大 正方形面积，并选择最优解。
 * 返回能够放入交集区域的正方形的 最大 可能面积，如果矩形之间不存在任何交集区域，则返回 0。
 * n == bottomLeft.length == topRight.length
 * 2 <= n <= 10^3
 * bottomLeft[i].length == topRight[i].length == 2
 * 1 <= bottomLeft[i][0], bottomLeft[i][1] <= 10^7
 * 1 <= topRight[i][0], topRight[i][1] <= 10^7
 * bottomLeft[i][0] < topRight[i][0]
 * bottomLeft[i][1] < topRight[i][1]
 */
public class Solution {

    public long largestSquareArea(int[][] bottomLeft, int[][] topRight) {
        int maxSide = 0;
        for (int i = 0; i < bottomLeft.length; i++) {
            int[] b1 = bottomLeft[i];
            int[] t1 = topRight[i];
            if (t1[0] - b1[0] <= maxSide || t1[1] - b1[1] <= maxSide) {
                continue;
            }
            for (int j = 0; j < i; j++) {
                int[] b2 = bottomLeft[j];
                int[] t2 = topRight[j];
                int width = Math.min(t1[0], t2[0]) - Math.max(b1[0], b2[0]);
                int height = Math.min(t1[1], t2[1]) - Math.max(b1[1], b2[1]);
                int side = Math.min(width, height);
                maxSide = Math.max(maxSide, side);
            }
        }
        return (long) maxSide * maxSide;
    }

}

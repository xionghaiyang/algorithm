package com.sean.leetcode.LeetCode3453;

/**
 * @Author xionghaiyang
 * @Date 2026-01-13 11:04
 * @Description https://leetcode.cn/problems/separate-squares-i
 * 3453. 分割正方形 I
 * 给你一个二维整数数组 squares ，其中 squares[i] = [xi, yi, li] 表示一个与 x 轴平行的正方形的左下角坐标和正方形的边长。
 * 找到一个最小的 y 坐标，它对应一条水平线，该线需要满足它以上正方形的总面积 等于 该线以下正方形的总面积。
 * 答案如果与实际答案的误差在 10^-5 以内，将视为正确答案。
 * 注意：正方形 可能会 重叠。
 * 重叠区域应该被 多次计数 。
 * 1 <= squares.length <= 5 * 10^4
 * squares[i] = [xi, yi, li]
 * squares[i].length == 3
 * 0 <= xi, yi <= 10^9
 * 1 <= li <= 10^9
 * 所有正方形的总面积不超过 10^12。
 */
public class Solution {

    public double separateSquares(int[][] squares) {
        long totalArea = 0;
        int maxY = 0;
        for (int[] square : squares) {
            int l = square[2];
            totalArea += (long) l * l;
            maxY = Math.max(maxY, square[1] + l);
        }
        double left = 0, right = maxY;
        for (int i = 0; i < 47; i++) {
            double mid = (left + right) / 2;
            if (check(squares, mid, totalArea)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return (left + right) / 2;
    }

    private boolean check(int[][] squares, double y, long totalArea) {
        double area = 0;
        for (int[] square : squares) {
            double yi = square[1];
            if (yi < y) {
                double l = square[2];
                area += l * Math.min(y - yi, l);
            }
        }
        return area >= totalArea / 2.0;
    }

}

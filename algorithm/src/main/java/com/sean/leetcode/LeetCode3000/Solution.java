package com.sean.leetcode.LeetCode3000;

/**
 * @Author xionghaiyang
 * @Date 2025-08-26 06:36
 * @Description https://leetcode.cn/problems/maximum-area-of-longest-diagonal-rectangle
 * 3000. 对角线最长的矩形的面积
 * 给你一个下标从 0 开始的二维整数数组 dimensions。
 * 对于所有下标 i（0 <= i < dimensions.length），dimensions[i][0] 表示矩形 i 的长度，而 dimensions[i][1] 表示矩形 i 的宽度。
 * 返回对角线最 长 的矩形的 面积 。
 * 如果存在多个对角线长度相同的矩形，返回面积最 大 的矩形的面积。
 * 1 <= dimensions.length <= 100
 * dimensions[i].length == 2
 * 1 <= dimensions[i][0], dimensions[i][1] <= 100
 */
public class Solution {

    public int areaOfMaxDiagonal(int[][] dimensions) {
        int maxD2 = 0, res = 0;
        for (int[] dimension : dimensions) {
            int x = dimension[0], y = dimension[1];
            int d2 = x * x + y * y;
            if (d2 == maxD2) {
                if (x * y > res) {
                    res = x * y;
                }
            } else if (d2 > maxD2) {
                maxD2 = d2;
                res = x * y;
            }
        }
        return res;
    }

}

package com.sean.leetcode.LeetCode3128;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2024-08-03 11:39
 * @Description https://leetcode.cn/problems/right-triangles/description
 * 3128. 直角三角形
 * 给你一个二维 boolean 矩阵 grid 。
 * 请你返回使用 grid 中的 3 个元素可以构建的 直角三角形 数目，且满足 3 个元素值 都 为 1 。
 * 注意：
 * 如果 grid 中 3 个元素满足：一个元素与另一个元素在 同一行，同时与第三个元素在 同一列 ，那么这 3 个元素称为一个 直角三角形 。
 * 这 3 个元素互相之间不需要相邻。
 */
public class Solution {

    public long numberOfRightTriangles(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] col = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                col[j] += grid[i][j];
            }
        }
        long res = 0;
        for (int i = 0; i < m; i++) {
            int row = Arrays.stream(grid[i]).sum();
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    res += (row - 1) * (col[j] - 1);
                }
            }
        }
        return res;
    }

}

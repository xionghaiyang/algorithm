package com.sean.leetcode.LeetCode2639;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-04-27 17:46
 * @Description: https://leetcode.cn/problems/find-the-width-of-columns-of-a-grid/
 * 2639. 查询网格图中每一列的宽度
 * 给你一个下标从 0 开始的 m x n 整数矩阵 grid 。
 * 矩阵中某一列的宽度是这一列数字的最大 字符串长度 。
 * 比方说，如果 grid = [[-10], [3], [12]] ，那么唯一一列的宽度是 3 ，因为 -10 的字符串长度为 3 。
 * 请你返回一个大小为 n 的整数数组 ans ，其中 ans[i] 是第 i 列的宽度。
 * 一个有 len 个数位的整数 x ，如果是非负数，那么 字符串长度 为 len ，否则为 len + 1 。
 */
public class Solution {

    public int[] findColumnWidth(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] res = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //res[j] = Math.max(res[j], String.valueOf(grid[i][j]).length());
                res[j] = Math.max(res[j], getLength(grid[i][j]));
            }
        }
        return res;
    }

    private int getLength(int x) {
        int res = 0;
        if (x <= 0) {
            res++;
            x = -x;
        }
        while (x != 0) {
            res++;
            x /= 10;
        }
        return res;
    }

}

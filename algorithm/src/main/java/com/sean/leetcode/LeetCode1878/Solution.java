package com.sean.leetcode.LeetCode1878;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2026-03-16 07:53
 * @Description https://leetcode.cn/problems/get-biggest-three-rhombus-sums-in-a-grid
 * 1878. 矩阵中最大的三个菱形和
 * 给你一个 m x n 的整数矩阵 grid 。
 * 菱形和 指的是 grid 中一个正菱形 边界 上的元素之和。
 * 本题中的菱形必须为正方形旋转45度，且四个角都在一个格子当中。
 * 下图是四个可行的菱形，每个菱形和应该包含的格子都用了相应颜色标注在图中。
 * 注意，菱形可以是一个面积为 0 的区域，如上图中右下角的紫色菱形所示。
 * 请你按照 降序 返回 grid 中三个最大的 互不相同的菱形和 。
 * 如果不同的和少于三个，则将它们全部返回。
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 100
 * 1 <= grid[i][j] <= 10^5
 */
public class Solution {

    public int[] getBiggestThree(int[][] grid) {
        TreeSet<Integer> ts = new TreeSet<>();
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                add(ts, grid[i][j]);
                for (int k = 1; i + k < m && i + 2 * k < m && j - k >= 0 && j + k < n; k++) {
                    add(ts, getSum(grid, i, j, k));
                }
            }
        }
        return toArr(ts);
    }

    private void add(TreeSet<Integer> ts, int sum) {
        ts.add(sum);
        if (ts.size() > 3) {
            ts.pollFirst();
        }
    }

    private int getSum(int[][] grid, int i, int j, int k) {
        int res = 0;
        for (int x = i, y = j; x < i + k && y < j + k; x++, y++) {
            res += grid[x][y];
        }
        for (int x = i + k, y = j + k; x < i + 2 * k && y > j; x++, y--) {
            res += grid[x][y];
        }
        for (int x = i + 2 * k, y = j; x > i + k && y > j - k; x--, y--) {
            res += grid[x][y];
        }
        for (int x = i + k, y = j - k; x > i && y < j; x--, y++) {
            res += grid[x][y];
        }
        return res;
    }

    private int[] toArr(TreeSet<Integer> ts) {
        List<Integer> list = new ArrayList<>();
        while (ts.size() > 0) {
            list.add(ts.pollLast());
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

}

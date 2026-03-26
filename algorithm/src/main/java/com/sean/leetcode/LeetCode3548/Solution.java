package com.sean.leetcode.LeetCode3548;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author xionghaiyang
 * @Date 2026-03-26 07:04
 * @Description https://leetcode.cn/problems/equal-sum-grid-partition-ii
 * 3548. 等和矩阵分割 II
 * 给你一个由正整数组成的 m x n 矩阵 grid。
 * 你的任务是判断是否可以通过 一条水平或一条垂直分割线 将矩阵分割成两部分，使得：
 * 分割后形成的每个部分都是 非空 的。
 * 两个部分中所有元素的和 相等 ，或者总共 最多移除一个单元格 （从其中一个部分中）的情况下可以使它们相等。
 * 如果移除某个单元格，剩余部分必须保持 连通 。
 * 如果存在这样的分割，返回 true；否则，返回 false。
 * 注意： 如果一个部分中的每个单元格都可以通过向上、向下、向左或向右移动到达同一部分中的其他单元格，则认为这一部分是 连通 的。
 * 1 <= m == grid.length <= 10^5
 * 1 <= n == grid[i].length <= 10^5
 * 2 <= m * n <= 10^5
 * 1 <= grid[i][j] <= 10^5
 */
public class Solution {

    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        long[] rows = new long[m];
        long[] cols = new long[n];
        Map<Long, List<int[]>> map = new HashMap<>();
        long all = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                all += grid[i][j];
                cols[j] += grid[i][j];
                if (!map.containsKey((long) grid[i][j])) {
                    map.put((long) grid[i][j], new ArrayList<>());
                }
                map.get((long) grid[i][j]).add(new int[]{i, j});
            }
            rows[i] = all;
        }
        for (int i = 0; i < m - 1; i++) {
            long up = rows[i], down = all - up;
            if (up == down) {
                return true;
            } else if (up > down) {
                long diff = up - down;
                if (map.containsKey(diff)) {
                    for (int[] arr : map.get(diff)) {
                        int x = arr[0], y = arr[1];
                        if (x <= i) {
                            if (!((i == 0 && 0 < y && y < n - 1) || (n == 1 && 0 < x && x < i))) {
                                return true;
                            }
                        }
                    }
                }
            } else {
                long diff = down - up;
                if (map.containsKey(diff)) {
                    for (int[] arr : map.get(diff)) {
                        int x = arr[0], y = arr[1];
                        if (x > i) {
                            if (!((i == m - 2 && 0 < y && y < n - 1) || (n == 1 && i + 1 < x && x < m - 1))) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        long left = 0;
        for (int j = 0; j < n - 1; j++) {
            left += cols[j];
            long right = all - left;
            if (left == right) {
                return true;
            } else if (left > right) {
                long diff = left - right;
                if (map.containsKey(diff)) {
                    for (int[] arr : map.get(diff)) {
                        int x = arr[0], y = arr[1];
                        if (y <= j) {
                            if (!((j == 0 && 0 < x && x < m - 1) || (m == 1 && 0 < y && y < j))) {
                                return true;
                            }
                        }
                    }
                }
            } else {
                long diff = right - left;
                if (map.containsKey(diff)) {
                    for (int[] arr : map.get(diff)) {
                        int x = arr[0], y = arr[1];
                        if (y > j) {
                            if (!((j == n - 2 && 0 < x && x < m - 1) || (m == 1 && j + 1 < y && y < n - 1))) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

}

package com.sean.leetcode.LeetCode764;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-11-09 09:22
 * @Description: https://leetcode.cn/problems/largest-plus-sign/
 * 764. 最大加号标志
 * 在一个 n x n 的矩阵 grid 中，除了在数组 mines 中给出的元素为 0，其他每个元素都为 1。
 * mines[i] = [xi, yi]表示 grid[xi][yi] == 0
 * 返回  grid 中包含 1 的最大的 轴对齐 加号标志的阶数 。
 * 如果未找到加号标志，则返回 0 。
 * 一个 k 阶由 1 组成的 “轴对称”加号标志 具有中心网格 grid[r][c] == 1 ，
 * 以及4个从中心向上、向下、向左、向右延伸，长度为 k-1，由 1 组成的臂。
 * 注意，只有加号标志的所有网格要求为 1 ，别的网格可能为 0 也可能为 1 。
 */
public class Solution {

    public int orderOfLargestPlusSign1(int n, int[][] mines) {
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], n);
        }
        Set<Integer> set = new HashSet<>();
        for (int[] mine : mines) {
            set.add(mine[0] * n + mine[1]);
        }
        int res = 0;
        int count = 0;
        for (int i = 0; i < n; i++) {
            count = 0;
            for (int j = 0; j < n; j++) {
                if (set.contains(i * n + j)) {
                    count = 0;
                } else {
                    count++;
                }
                dp[i][j] = Math.min(dp[i][j], count);
            }
            count = 0;
            for (int j = n - 1; j >= 0; j--) {
                if (set.contains(i * n + j)) {
                    count = 0;
                } else {
                    count++;
                }
                dp[i][j] = Math.min(dp[i][j], count);
            }
        }
        for (int i = 0; i < n; i++) {
            count = 0;
            for (int j = 0; j < n; j++) {
                if (set.contains(j * n + i)) {
                    count = 0;
                } else {
                    count++;
                }
                dp[j][i] = Math.min(dp[j][i], count);
            }
            count = 0;
            for (int j = n - 1; j >= 0; j--) {
                if (set.contains(j * n + i)) {
                    count = 0;
                } else {
                    count++;
                }
                dp[j][i] = Math.min(dp[j][i], count);
                res = Math.max(res, dp[j][i]);
            }
        }
        return res;
    }

    public int orderOfLargestPlusSign(int n, int[][] mines) {
        Set<Integer> set = new HashSet<>();
        for (int[] mine : mines) {
            set.add(mine[0] * n + mine[1]);
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                res = Math.max(res, process(set, n, i, j));
            }
        }
        return res;
    }

    private int process(Set<Integer> set, int n, int x, int y) {
        int res = n;
        int count = 0;
        for (int i = x; i < n; i++) {
            if (set.contains(i * n + y)) {
                break;
            }
            count++;
        }
        res = Math.min(res, count);
        count = 0;
        for (int i = x; i >= 0; i--) {
            if (set.contains(i * n + y)) {
                break;
            }
            count++;
        }
        res = Math.min(res, count);
        count = 0;
        for (int i = y; i < n; i++) {
            if (set.contains(x * n + i)) {
                break;
            }
            count++;
        }
        res = Math.min(res, count);
        count = 0;
        for (int i = y; i >= 0; i--) {
            if (set.contains(x * n + i)) {
                break;
            }
            count++;
        }
        res = Math.min(res, count);
        return res;
    }

}

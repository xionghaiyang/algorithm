package com.sean.leetcode.LeetCode1931;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2025-05-18 06:10
 * @Description https://leetcode.cn/problems/painting-a-grid-with-three-different-colors
 * 1931. 用三种不同颜色为网格涂色
 * 给你两个整数 m 和 n 。
 * 构造一个 m x n 的网格，其中每个单元格最开始是白色。
 * 请你用 红、绿、蓝 三种颜色为每个单元格涂色。
 * 所有单元格都需要被涂色。
 * 涂色方案需要满足：不存在相邻两个单元格颜色相同的情况 。
 * 返回网格涂色的方法数。
 * 因为答案可能非常大， 返回 对 10^9 + 7 取余 的结果。
 * 1 <= m <= 5
 * 1 <= n <= 1000
 */
public class Solution {

    private static final int MOD = 1_000_000_007;

    public int colorTheGrid(int m, int n) {
        int[] pow3 = new int[m];
        pow3[0] = 1;
        for (int i = 1; i < m; i++) {
            pow3[i] = pow3[i - 1] * 3;
        }
        List<Integer> valid = new ArrayList<>();
        out:
        for (int color = 0; color < pow3[m - 1] * 3; color++) {
            for (int i = 1; i < m; i++) {
                if (color / pow3[i] % 3 == color / pow3[i - 1] % 3) {
                    continue out;
                }
            }
            valid.add(color);
        }
        int size = valid.size();
        List<Integer>[] next = new ArrayList[size];
        Arrays.setAll(next, i -> new ArrayList<>());
        for (int i = 0; i < size; i++) {
            out2:
            for (int j = 0; j < size; j++) {
                for (int p3 : pow3) {
                    if (valid.get(i) / p3 % 3 == valid.get(j) / p3 % 3) {
                        continue out2;
                    }
                }
                next[i].add(j);
            }
        }
        int[][] memo = new int[n][size];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        long res = 0;
        for (int j = 0; j < size; j++) {
            res += process(n - 1, j, next, memo);
        }
        return (int) (res % MOD);
    }

    private int process(int i, int j, List<Integer>[] next, int[][] memo) {
        if (i == 0) {
            return 1;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        long res = 0;
        for (int k : next[j]) {
            res += process(i - 1, k, next, memo);
        }
        memo[i][j] = (int) (res % MOD);
        return memo[i][j];
    }

}

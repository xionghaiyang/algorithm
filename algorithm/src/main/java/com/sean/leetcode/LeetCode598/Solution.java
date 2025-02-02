package com.sean.leetcode.LeetCode598;

/**
 * @Author xionghaiyang
 * @Date 2025-02-02 15:24
 * @Description https://leetcode.cn/problems/range-addition-ii/
 * 598. 区间加法 II
 * 给你一个 m x n 的矩阵 M 和一个操作数组 op 。
 * 矩阵初始化时所有的单元格都为 0 。
 * ops[i] = [ai, bi] 意味着当所有的 0 <= x < ai 和 0 <= y < bi 时， M[x][y] 应该加 1。
 * 在 执行完所有操作后 ，计算并返回 矩阵中最大整数的个数 。
 * 1 <= m, n <= 4 * 10^4
 * 0 <= ops.length <= 10^4
 * ops[i].length == 2
 * 1 <= ai <= m
 * 1 <= bi <= n
 */
public class Solution {

    public int maxCount(int m, int n, int[][] ops) {
        int minA = m, minB = n;
        for (int[] op : ops) {
            minA = Math.min(minA, op[0]);
            minB = Math.min(minB, op[1]);
        }
        return minA * minB;
    }

}

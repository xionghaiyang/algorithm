package com.sean.leetcode.LeetCode2713;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2024-06-19 20:00
 * @Description https://leetcode.cn/problems/maximum-strictly-increasing-cells-in-a-matrix/
 * 2713. 矩阵中严格递增的单元格数
 * 给你一个下标从 1 开始、大小为 m x n 的整数矩阵 mat，你可以选择任一单元格作为 起始单元格 。
 * 从起始单元格出发，你可以移动到 同一行或同一列 中的任何其他单元格，但前提是目标单元格的值 严格大于 当前单元格的值。
 * 你可以多次重复这一过程，从一个单元格移动到另一个单元格，直到无法再进行任何移动。
 * 请你找出从某个单元开始访问矩阵所能访问的 单元格的最大数量 。
 * 返回一个表示可访问单元格最大数量的整数。
 */
public class Solution {

    int m;
    int n;
    int[][] dp;
    int[][] mat;

    //超时
    public int maxIncreasingCells(int[][] mat) {
        m = mat.length;
        n = mat[0].length;
        int res = 0;
        dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], -1);
        }
        this.mat = mat;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res = Math.max(res, process(i, j));
            }
        }
        return res;
    }

    private int process(int x, int y) {
        if (dp[x][y] != -1) {
            return dp[x][y];
        }
        int res = 1;
        for (int i = 0; i < m; i++) {
            if (i != x && mat[i][y] > mat[x][y]) {
                res = Math.max(res, 1 + process(i, y));
            }
        }
        for (int j = 0; j < n; j++) {
            if (j != y && mat[x][j] > mat[x][y]) {
                res = Math.max(res, 1 + process(x, j));
            }
        }
        dp[x][y] = res;
        return res;
    }

    public int maxIncreasingCells1(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        Map<Integer, List<int[]>> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                map.putIfAbsent(mat[i][j], new ArrayList<>());
                map.get(mat[i][j]).add(new int[]{i, j});
            }
        }
        //每一行d的最大值
        int[] row = new int[m];
        //每一列d的最大值
        int[] col = new int[n];
        List<Integer> keys = new ArrayList<>(map.keySet());
        Collections.sort(keys);
        for (int key : keys) {
            List<int[]> pos = map.get(key);
            List<Integer> res = new ArrayList<>();
            for (int[] arr : pos) {
                res.add(Math.max(row[arr[0]], col[arr[1]]) + 1);
            }
            for (int i = 0; i < pos.size(); i++) {
                int[] arr = pos.get(i);
                int d = res.get(i);
                row[arr[0]] = Math.max(row[arr[0]], d);
                col[arr[1]] = Math.max(col[arr[1]], d);
            }
        }
        return Arrays.stream(row).max().getAsInt();
    }

}

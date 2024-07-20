package com.sean.leetcode.LeetCode2850;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2024-07-20 08:32
 * @Description https://leetcode.cn/problems/minimum-moves-to-spread-stones-over-grid/description/
 * 2850. 将石头分散到网格图的最少移动次数
 * 给你一个大小为 3 * 3 ，下标从 0 开始的二维整数矩阵 grid ，分别表示每一个格子里石头的数目。
 * 网格图中总共恰好有 9 个石头，一个格子里可能会有 多个 石头。
 * 每一次操作中，你可以将一个石头从它当前所在格子移动到一个至少有一条公共边的相邻格子。
 * 请你返回每个格子恰好有一个石头的 最少移动次数 。
 */
public class Solution {

    public int minimumMoves(int[][] grid) {
        List<int[]> from = new ArrayList<>();
        List<int[]> to = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] > 1) {
                    for (int k = 1; k < grid[i][j]; k++) {
                        from.add(new int[]{i, j});
                    }
                } else if (grid[i][j] == 0) {
                    to.add(new int[]{i, j});
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for (List<int[]> from2 : permutations(from)) {
            int total = 0;
            for (int i = 0; i < from2.size(); i++) {
                int[] f = from2.get(i);
                int[] t = to.get(i);
                total += Math.abs(f[0] - t[0]) + Math.abs(f[1] - t[1]);
            }
            res = Math.min(res, total);
        }
        return res;
    }

    private List<List<int[]>> permutations(List<int[]> list) {
        List<List<int[]>> res = new ArrayList<>();
        permute(list, 0, res);
        return res;
    }

    private void permute(List<int[]> list, int start, List<List<int[]>> res) {
        if (start == list.size()) {
            res.add(new ArrayList<>(list));
        }
        for (int i = start; i < list.size(); i++) {
            swap(list, start, i);
            permute(list, start + 1, res);
            swap(list, start, i);
        }
    }

    private void swap(List<int[]> list, int i, int j) {
        int[] temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    public int minimumMoves1(int[][] grid) {
        return process(grid, 0, 0);
    }

    private int process(int[][] grid, int x, int y) {
        if (x >= 3) {
            return 0;
        }
        if (y >= 3) {
            return process(grid, x + 1, 0);
        }
        if (grid[x][y] != 0) {
            return process(grid, x, y + 1);
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == x && j == y) {
                    continue;
                }
                if (grid[i][j] <= 1) {
                    continue;
                }
                grid[i][j]--;
                res = Math.min(res, process(grid, x, y + 1) + Math.abs(i - x) + Math.abs(j - y));
                grid[i][j]++;
            }
        }
        return res;
    }

}

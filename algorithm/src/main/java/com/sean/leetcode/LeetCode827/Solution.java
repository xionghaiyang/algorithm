package com.sean.leetcode.LeetCode827;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author xionghaiyang
 * @Date 2022-09-18 13:15
 * @Description https://leetcode.cn/problems/making-a-large-island/
 * 827. 最大人工岛
 * 给你一个大小为 n x n 二进制矩阵 grid 。最多 只能将一格 0 变成 1 。
 * 返回执行此操作后，grid 中最大的岛屿面积是多少？
 * 岛屿 由一组上、下、左、右四个方向相连的 1 形成。
 */
public class Solution {

    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int largestIsland(int[][] grid) {
        int n = grid.length;
        int res = 0;
        //岛屿的标号
        int index = 1;
        //(i,j) 的所属岛屿标号
        int[][] tag = new int[n][n];
        //每一个岛屿的面积
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && tag[i][j] == 0) {
                    map.put(index, dfs(grid, i, j, tag, index));
                    res = Math.max(res, map.get(index));
                    index++;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    int area = 1;
                    Set<Integer> set = new HashSet<>();
                    for (int[] dir : dirs) {
                        int ni = i + dir[0];
                        int nj = j + dir[1];
                        if (!(ni >= 0 && ni < n && nj >= 0 && nj < n) || tag[ni][nj] == 0 || set.contains(tag[ni][nj])) {
                            continue;
                        }
                        area += map.get(tag[ni][nj]);
                        set.add(tag[ni][nj]);
                    }
                    res = Math.max(res, area);
                }
            }
        }
        return res;
    }

    private int dfs(int[][] grid, int x, int y, int[][] tag, int index) {
        int n = grid.length;
        tag[x][y] = index;
        int res = 1;
        for (int[] dir : dirs) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            if (nx >= 0 && nx < n && ny >= 0 && ny < n && grid[nx][ny] == 1 && tag[nx][ny] == 0) {
                res += dfs(grid, nx, ny, tag, index);
            }
        }
        return res;
    }

}

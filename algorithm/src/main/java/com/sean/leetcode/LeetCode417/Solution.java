package com.sean.leetcode.LeetCode417;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-16 23:06
 * @Description: https://leetcode.cn/problems/pacific-atlantic-water-flow/?plan=graph&plan_progress=zq56763
 * 417. 太平洋大西洋水流问题
 * 有一个 m × n 的矩形岛屿，与 太平洋 和 大西洋 相邻。 “太平洋” 处于大陆的左边界和上边界，而 “大西洋” 处于大陆的右边界和下边界。
 * 这个岛被分割成一个由若干方形单元格组成的网格。给定一个 m x n 的整数矩阵 heights ， heights[r][c] 表示坐标 (r, c) 上单元格 高于海平面的高度 。
 * 岛上雨水较多，如果相邻单元格的高度 小于或等于 当前单元格的高度，雨水可以直接向北、南、东、西流向相邻单元格。水可以从海洋附近的任何单元格流入海洋。
 * 返回网格坐标 result 的 2D 列表 ，其中 result[i] = [ri, ci] 表示雨水从单元格 (ri, ci) 流动 既可流向太平洋也可流向大西洋 。
 */
public class Solution {

    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            dfs(heights, i, 0, pacific);
        }
        for (int j = 1; j < n; j++) {
            dfs(heights, 0, j, pacific);
        }
        for (int i = 0; i < m; i++) {
            dfs(heights, i, n - 1, atlantic);
        }
        for (int j = 0; j < n - 1; j++) {
            dfs(heights, m - 1, j, atlantic);
        }
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    List<Integer> cell = new ArrayList<>();
                    cell.add(i);
                    cell.add(j);
                    res.add(cell);
                }
            }
        }
        return res;
    }

    private void dfs(int[][] heights, int x, int y, boolean[][] ocean) {
        if (ocean[x][y]) {
            return;
        }
        ocean[x][y] = true;
        int m = heights.length;
        int n = heights[0].length;
        for (int[] dir : dirs) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            if (nx >= 0 && nx < m && ny >= 0 && ny < n && heights[nx][ny] >= heights[x][y]) {
                dfs(heights, nx, ny, ocean);
            }
        }
    }

}

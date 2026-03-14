package com.sean.leetcode.LeetCodeInterview0802;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2026-03-14 12:28
 * @Description https://leetcode.cn/problems/robot-in-a-grid-lcci
 * 面试题 08.02. 迷路的机器人
 * 设想有个机器人坐在一个网格的左上角，网格 r 行 c 列。
 * 机器人只能向下或向右移动，但不能走到一些被禁止的网格（有障碍物）。
 * 设计一种算法，寻找机器人从左上角移动到右下角的路径。
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。
 * 返回一条可行的路径，路径由经过的网格的行号和列号组成。
 * 左上角为 0 行 0 列。
 * 如果没有可行的路径，返回空数组。
 * 说明：r 和 c 的值均不超过 100。
 */
public class Solution {

    List<List<Integer>> res = new ArrayList<>();
    private int[][] obstacleGrid;
    private int m;
    private int n;
    private boolean[][] visited;

    public List<List<Integer>> pathWithObstacles(int[][] obstacleGrid) {
        this.obstacleGrid = obstacleGrid;
        m = obstacleGrid.length;
        n = obstacleGrid[0].length;
        visited = new boolean[m][n];
        process(0, 0);
        return res;
    }

    private boolean process(int i, int j) {
        if (i >= m || j >= n || obstacleGrid[i][j] == 1 || visited[i][j]) {
            return false;
        }
        res.add(Arrays.asList(i, j));
        if (i == m - 1 && j == n - 1) {
            return true;
        }
        visited[i][j] = true;
        if (process(i + 1, j) || process(i, j + 1)) {
            return true;
        }
        res.remove(res.size() - 1);
        return false;
    }

}

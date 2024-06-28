package com.sean.leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * @Auther: xionghaiyang
 * @Date: 2022/1/29 15:56
 * @Description: https://leetcode-cn.com/problems/map-of-highest-peak/
 * 地图中的最高点
 * @version: 1.0
 */
public class LeetCode1765 {

    public int[][] highestPeak(int[][] isWater) {
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int m = isWater.length;
        int n = isWater[0].length;
        int[][] res = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(res[i], -1); //-1表示该格子尚未被访问过
        }
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (isWater[i][j] == 1) {
                    res[i][j] = 0;
                    queue.offer(new int[]{i, j}); //将所有水域入队
                }
            }
        }
        while (!queue.isEmpty()) {
            int[] p = queue.poll();
            for (int[] dir : dirs) {
                int x = p[0] + dir[0];
                int y = p[1] + dir[1];
                if (0 <= x && x < m && 0 <= y && y < n && res[x][y] == -1) {
                    res[x][y] = res[p[0]][p[1]] + 1;
                    queue.offer(new int[]{x, y});
                }
            }
        }
        return res;
    }

}

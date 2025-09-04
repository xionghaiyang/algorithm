package com.sean.leetcode.LeetCode778;

import java.util.PriorityQueue;

/**
 * @Author xionghaiyang
 * @Date 2025-09-04 13:09
 * @Description https://leetcode.cn/problems/swim-in-rising-water
 * 778. 水位上升的泳池中游泳
 * 在一个 n x n 的整数矩阵 grid 中，每一个方格的值 grid[i][j] 表示位置 (i, j) 的平台高度。
 * 当开始下雨时，在时间为 t 时，水池中的水位为 t 。
 * 你可以从一个平台游向四周相邻的任意一个平台，但是前提是此时水位必须同时淹没这两个平台。
 * 假定你可以瞬间移动无限距离，也就是默认在方格内部游动是不耗时的。
 * 当然，在你游泳的时候你必须待在坐标方格里面。
 * 你从坐标方格的左上平台 (0，0) 出发。
 * 返回 你到达坐标方格的右下平台 (n-1, n-1) 所需的最少时间 。
 * n == grid.length
 * n == grid[i].length
 * 1 <= n <= 50
 * 0 <= grid[i][j] < n^2
 * grid[i][j] 中每个值 均无重复
 */
public class Solution {

    public int swimInWater(int[][] grid) {
        int n = grid.length;
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        heap.offer(new int[]{0, 0, grid[0][0]});
        boolean[][] visited = new boolean[n][n];
        visited[0][0] = true;
        int t = 0;
        while (true) {
            while (!heap.isEmpty() && heap.peek()[2] <= t) {
                int[] arr = heap.poll();
                if (arr[0] == n - 1 && arr[1] == n - 1) {
                    return t;
                }
                for (int[] dir : dirs) {
                    int x = arr[0] + dir[0], y = arr[1] + dir[1];
                    if (x >= 0 && x < n && y >= 0 && y < n && !visited[x][y]) {
                        heap.offer(new int[]{x, y, grid[x][y]});
                        visited[x][y] = true;
                    }
                }
            }
            t++;
        }
    }

}

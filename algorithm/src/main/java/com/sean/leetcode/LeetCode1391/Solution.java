package com.sean.leetcode.LeetCode1391;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author xionghaiyang
 * @Date 2025-09-18 17:16
 * @Description https://leetcode.cn/problems/check-if-there-is-a-valid-path-in-a-grid
 * 1391. 检查网格中是否存在有效路径
 * 给你一个 m x n 的网格 grid。网格里的每个单元都代表一条街道。
 * grid[i][j] 的街道可以是：
 * 1 表示连接左单元格和右单元格的街道。
 * 2 表示连接上单元格和下单元格的街道。
 * 3 表示连接左单元格和下单元格的街道。
 * 4 表示连接右单元格和下单元格的街道。
 * 5 表示连接左单元格和上单元格的街道。
 * 6 表示连接右单元格和上单元格的街道。
 * 你最开始从左上角的单元格 (0,0) 开始出发，网格中的「有效路径」是指从左上方的单元格 (0,0) 开始、一直到右下方的 (m-1,n-1) 结束的路径。
 * 该路径必须只沿着街道走。
 * 注意：你 不能 变更街道。
 * 如果网格中存在有效的路径，则返回 true，否则返回 false 。
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * 1 <= grid[i][j] <= 6
 */
public class Solution {

    public boolean hasValidPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        visited[0][0] = true;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int x = arr[0], y = arr[1];
            if (x == m - 1 && y == n - 1) {
                return true;
            }
            int status = grid[x][y];
            //上
            if ((status == 2 || status == 5 || status == 6) && x - 1 >= 0 && !visited[x - 1][y] && (grid[x - 1][y] == 2 || grid[x - 1][y] == 3 || grid[x - 1][y] == 4)) {
                visited[x - 1][y] = true;
                queue.offer(new int[]{x - 1, y});
            }
            //下
            if ((status == 2 || status == 3 || status == 4) && x + 1 < m && !visited[x + 1][y] && (grid[x + 1][y] == 2 || grid[x + 1][y] == 5 || grid[x + 1][y] == 6)) {
                visited[x + 1][y] = true;
                queue.offer(new int[]{x + 1, y});
            }
            //左
            if ((status == 1 || status == 3 || status == 5) && y - 1 >= 0 && !visited[x][y - 1] && (grid[x][y - 1] == 1 || grid[x][y - 1] == 4 || grid[x][y - 1] == 6)) {
                visited[x][y - 1] = true;
                queue.offer(new int[]{x, y - 1});
            }
            //右
            if ((status == 1 || status == 4 || status == 6) && y + 1 < n && !visited[x][y + 1] && (grid[x][y + 1] == 1 || grid[x][y + 1] == 3 || grid[x][y + 1] == 5)) {
                visited[x][y + 1] = true;
                queue.offer(new int[]{x, y + 1});
            }
        }
        return false;
    }

}

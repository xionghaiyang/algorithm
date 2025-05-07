package com.sean.leetcode.LeetCode3341;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2025-05-07 07:38
 * @Description https://leetcode.cn/problems/find-minimum-time-to-reach-last-room-i/
 * 3341. 到达最后一个房间的最少时间 I
 * 有一个地窖，地窖中有 n x m 个房间，它们呈网格状排布。
 * 给你一个大小为 n x m 的二维数组 moveTime ，其中 moveTime[i][j] 表示在这个时刻 以后 你才可以 开始 往这个房间 移动 。
 * 你在时刻 t = 0 时从房间 (0, 0) 出发，每次可以移动到 相邻 的一个房间。
 * 在 相邻 房间之间移动需要的时间为 1 秒。
 * 请你返回到达房间 (n - 1, m - 1) 所需要的 最少 时间。
 * 如果两个房间有一条公共边（可以是水平的也可以是竖直的），那么我们称这两个房间是 相邻 的。
 * 2 <= n == moveTime.length <= 50
 * 2 <= m == moveTime[i].length <= 50
 * 0 <= moveTime[i][j] <= 10^9
 */
public class Solution {

    public int minTimeToReach(int[][] moveTime) {
        int n = moveTime.length;
        int m = moveTime[0].length;
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int[][] dis = new int[n][m];
        for (int[] row : dis) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        dis[0][0] = 0;
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        heap.add(new int[]{0, 0, 0});
        while (true) {
            int[] cur = heap.poll();
            int d = cur[0];
            int i = cur[1];
            int j = cur[2];
            if (i == n - 1 && j == m - 1) {
                return d;
            }
            if (d > dis[i][j]) {
                continue;
            }
            for (int[] dir : dirs) {
                int x = i + dir[0];
                int y = j + dir[1];
                if (x >= 0 && x < n && y >= 0 && y < m) {
                    int newDis = Math.max(d, moveTime[x][y]) + 1;
                    if (newDis < dis[x][y]) {
                        dis[x][y] = newDis;
                        heap.add(new int[]{newDis, x, y});
                    }
                }
            }
        }
    }

}

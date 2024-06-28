package com.sean.leetcode.LeetCode2258;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-11-09 20:46
 * @Description: https://leetcode.cn/problems/escape-the-spreading-fire/description/
 * 2258. 逃离火灾
 * 给你一个下标从 0 开始大小为 m x n 的二维整数数组 grid ，它表示一个网格图。每个格子为下面 3 个值之一：
 * 0 表示草地。
 * 1 表示着火的格子。
 * 2 表示一座墙，你跟火都不能通过这个格子。
 * 一开始你在最左上角的格子 (0, 0) ，你想要到达最右下角的安全屋格子 (m - 1, n - 1) 。
 * 每一分钟，你可以移动到 相邻 的草地格子。每次你移动 之后 ，着火的格子会扩散到所有不是墙的 相邻 格子。
 * 请你返回你在初始位置可以停留的 最多 分钟数，且停留完这段时间后你还能安全到达安全屋。
 * 如果无法实现，请你返回 -1 。
 * 如果不管你在初始位置停留多久，你 总是 能到达安全屋，请你返回 109 。
 * 注意，如果你到达安全屋后，火马上到了安全屋，这视为你能够安全到达安全屋。
 * 如果两个格子有共同边，那么它们为 相邻 格子。
 */
public class Solution {

    int INF = 1000000000;
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int maximumMinutes(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] fireTime = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(fireTime[i], INF);
        }
        //通过bfs求出每个格子着火的时间
        bfs(grid, fireTime);
        //二分查找找到最大停留时间
        int res = -1;
        int low = 0, high = m * n;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (check(fireTime, grid, mid)) {
                res = mid;
                low = mid - 1;
            } else {
                high = mid - 1;
            }
        }
        return res >= m * n ? INF : res;
    }

    private void bfs(int[][] grid, int[][] fireTime) {
        int m = grid.length;
        int n = grid[0].length;
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    queue.offer(new int[]{i, j});
                    fireTime[i][j] = 0;
                }
            }
        }
        int time = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] arr = queue.poll();
                int cx = arr[0];
                int cy = arr[1];
                for (int j = 0; j < 4; j++) {
                    int nx = cx + dirs[j][0];
                    int ny = cy + dirs[j][1];
                    if (nx >= 0 && ny >= 0 && nx < m && ny < n) {
                        if (grid[nx][ny] == 2 || fireTime[nx][ny] != INF) {
                            continue;
                        }
                        queue.offer(new int[]{nx, ny});
                        fireTime[nx][ny] = time;
                    }
                }
            }
            time++;
        }
    }

    private boolean check(int[][] fireTime, int[][] grid, int stayTime) {
        int m = fireTime.length;
        int n = fireTime[0].length;
        boolean[][] visit = new boolean[m][n];
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0, stayTime});
        visit[0][0] = true;
        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int cx = arr[0];
            int cy = arr[1];
            int time = arr[2];
            for (int i = 0; i < 4; i++) {
                int nx = cx + dirs[i][0];
                int ny = cy + dirs[i][1];
                if (nx >= 0 && ny >= 0 && nx < m && ny < n) {
                    if (visit[nx][ny] || grid[nx][ny] == 2) {
                        continue;
                    }
                    //到达安全屋
                    if (nx == m - 1 && ny == n - 1) {
                        return fireTime[nx][ny] >= time + 1;
                    }
                    //火未到达当前位置
                    if (fireTime[nx][ny] > time + 1) {
                        queue.offer(new int[]{nx, ny, time + 1});
                        visit[nx][ny] = true;
                    }
                }
            }
        }
        return false;
    }

    public int maximumMinutes1(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] fileTime = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(fileTime[i], INF);
        }
        //通过bfs求出每个格子着火的时间
        bfs(grid, fileTime);
        //找到起点到终点的最短时间
        int arriveTime = getArriveTime(grid, fileTime, 0);
        //安全屋不可达
        if (arriveTime < 0) {
            return -1;
        }
        if (fileTime[m - 1][n - 1] == INF) {
            return INF;
        }
        int res = fileTime[m - 1][n - 1] - arriveTime;
        return getArriveTime(grid, fileTime, res) >= 0 ? res : res - 1;
    }

    private int getArriveTime(int[][] grid, int[][] fireTime, int stayTime) {
        int m = fireTime.length;
        int n = fireTime[0].length;
        boolean[][] visit = new boolean[m][n];
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0, stayTime});
        visit[0][0] = true;
        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int cx = arr[0];
            int cy = arr[1];
            int time = arr[2];
            for (int j = 0; j < 4; j++) {
                int nx = cx + dirs[j][0];
                int ny = cy + dirs[j][1];
                if (nx >= 0 && ny >= 0 && nx < m && ny < n) {
                    if (grid[nx][ny] == 2 || visit[nx][ny]) {
                        continue;
                    }
                    if (nx == m - 1 && ny == n - 1) {
                        return time + 1;
                    }
                    if (fireTime[nx][ny] > time + 1) {
                        visit[nx][ny] = true;
                        queue.offer(new int[]{nx, ny, time + 1});
                    }
                }
            }
        }
        return -1;
    }

}

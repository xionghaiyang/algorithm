package com.sean.leetcode.LeetCode864;

import java.util.*;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-11-10 08:26
 * @Description: https://leetcode.cn/problems/shortest-path-to-get-all-keys/submissions/
 * 864. 获取所有钥匙的最短路径
 * 给定一个二维网格 grid ，其中：
 * '.' 代表一个空房间
 * '#' 代表一堵
 * '@' 是起点
 * 小写字母代表钥匙
 * 大写字母代表锁
 * 我们从起点开始出发，一次移动是指向四个基本方向之一行走一个单位空间。
 * 我们不能在网格外面行走，也无法穿过一堵墙。
 * 如果途经一个钥匙，我们就把它捡起来。
 * 除非我们手里有对应的钥匙，否则无法通过锁。
 * 假设 k 为 钥匙/锁 的个数，且满足 1 <= k <= 6，字母表中的前 k 个字母在网格中都有自己对应的一个小写和一个大写字母。
 * 换言之，每个锁有唯一对应的钥匙，每个钥匙也有唯一对应的锁。
 * 另外，代表钥匙和锁的字母互为大小写并按字母顺序排列。
 * 返回获取所有钥匙所需要的移动的最少次数。如果无法获取所有钥匙，返回 -1 。
 */
public class Solution {

    public int shortestPathAllKeys1(String[] grid) {
        int m = grid.length;
        int n = grid[0].length();
        int sx = 0;
        int sy = 0;
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Map<Character, Integer> key2Index = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i].charAt(j) == '@') {
                    sx = i;
                    sy = j;
                } else if (Character.isLowerCase(grid[i].charAt(j))) {
                    if (!key2Index.containsKey(grid[i].charAt(j))) {
                        key2Index.put(grid[i].charAt(j), key2Index.size());
                    }
                }
            }
        }
        Queue<int[]> queue = new LinkedList<>();
        int[][][] dist = new int[m][n][1 << key2Index.size()];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dist[i][j], -1);
            }
        }
        queue.offer(new int[]{sx, sy, 0});
        dist[sx][sy][0] = 0;
        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int x = arr[0], y = arr[1], mask = arr[2];
            for (int i = 0; i < 4; i++) {
                int nx = x + dirs[i][0];
                int ny = y + dirs[i][1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx].charAt(ny) != '#') {
                    if (grid[nx].charAt(ny) == '.' || grid[nx].charAt(ny) == '@') {
                        if (dist[nx][ny][mask] == -1) {
                            dist[nx][ny][mask] = dist[x][y][mask] + 1;
                            queue.offer(new int[]{nx, ny, mask});
                        }
                    } else if (Character.isLowerCase(grid[nx].charAt(ny))) {
                        int index = key2Index.get(grid[nx].charAt(ny));
                        if (dist[nx][ny][mask | (1 << index)] == -1) {
                            dist[nx][ny][mask | (1 << index)] = dist[x][y][mask] + 1;
                            if ((mask | (1 << index)) == (1 << key2Index.size()) - 1) {
                                return dist[nx][ny][mask | (1 << index)];
                            }
                            queue.offer(new int[]{nx, ny, mask | (1 << index)});
                        }
                    } else {
                        int index = key2Index.get(Character.toLowerCase(grid[nx].charAt(ny)));
                        if ((mask & (1 << index)) != 0 && dist[nx][ny][mask] == -1) {
                            dist[nx][ny][mask] = dist[x][y][mask] + 1;
                            queue.offer(new int[]{nx, ny, mask});
                        }
                    }
                }
            }
        }
        return -1;
    }

}

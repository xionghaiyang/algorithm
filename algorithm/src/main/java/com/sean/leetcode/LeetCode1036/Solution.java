package com.sean.leetcode.LeetCode1036;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @Author xionghaiyang
 * @Date 2025-08-24 17:28
 * @Description https://leetcode.cn/problems/escape-a-large-maze
 * 1036. 逃离大迷宫
 * 在一个 10^6 x 10^6 的网格中，每个网格上方格的坐标为 (x, y) 。
 * 现在从源方格 source = [sx, sy] 开始出发，意图赶往目标方格 target = [tx, ty] 。
 * 数组 blocked 是封锁的方格列表，其中每个 blocked[i] = [xi, yi] 表示坐标为 (xi, yi) 的方格是禁止通行的。
 * 每次移动，都可以走到网格中在四个方向上相邻的方格，只要该方格 不 在给出的封锁列表 blocked 上。
 * 同时，不允许走出网格。
 * 只有在可以通过一系列的移动从源方格 source 到达目标方格 target 时才返回 true。
 * 否则，返回 false。
 * 0 <= blocked.length <= 200
 * blocked[i].length == 2
 * 0 <= xi, yi < 10^6
 * source.length == target.length == 2
 * 0 <= sx, sy, tx, ty < 10^6
 * source != target
 * 题目数据保证 source 和 target 不在封锁列表内
 */
public class Solution {

    final int SIDE = 1_000_000;
    final long BASE = 131L;
    Set<Long> set = new HashSet<>();
    int MAX;
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        for (int[] block : blocked) {
            set.add(getHash(block));
        }
        int n = blocked.length;
        MAX = n * (n - 1) / 2;
        return check(source, target) && check(target, source);
    }

    private boolean check(int[] s, int[] t) {
        Set<Long> visited = new HashSet<>();
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(s);
        visited.add(getHash(s));
        while (!queue.isEmpty() && visited.size() <= MAX) {
            int[] arr = queue.poll();
            int x = arr[0], y = arr[1];
            if (x == t[0] && y == t[1]) {
                return true;
            }
            for (int[] dir : dirs) {
                int nx = x + dir[0], ny = y + dir[1];
                if (nx < 0 || nx >= SIDE || ny < 0 || ny >= SIDE) {
                    continue;
                }
                long hash = getHash(nx, ny);
                if (set.contains(hash)) {
                    continue;
                }
                if (visited.contains(hash)) {
                    continue;
                }
                queue.offer(new int[]{nx, ny});
                visited.add(hash);
            }
        }
        return visited.size() > MAX;
    }

    private long getHash(int x, int y) {
        return x * BASE + y;
    }

    private long getHash(int[] arr) {
        return getHash(arr[0], arr[1]);
    }

}

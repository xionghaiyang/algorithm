package com.sean.leetcode.LeetCode2101;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2024-07-22 08:48
 * @Description https://leetcode.cn/problems/detonate-the-maximum-bombs/
 * 2101. 引爆最多的炸弹
 * 给你一个炸弹列表。
 * 一个炸弹的 爆炸范围 定义为以炸弹为圆心的一个圆。
 * 炸弹用一个下标从 0 开始的二维整数数组 bombs 表示，其中 bombs[i] = [xi, yi, ri] 。
 * xi 和 yi 表示第 i 个炸弹的 X 和 Y 坐标，ri 表示爆炸范围的 半径 。
 * 你需要选择引爆 一个 炸弹。
 * 当这个炸弹被引爆时，所有 在它爆炸范围内的炸弹都会被引爆，这些炸弹会进一步将它们爆炸范围内的其他炸弹引爆。
 * 给你数组 bombs ，请你返回在引爆 一个 炸弹的前提下，最多 能引爆的炸弹数目。
 */
public class Solution {

    public int maximumDetonation(int[][] bombs) {
        int n = bombs.length;
        Map<Integer, List<Integer>> edges = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && isConnected(bombs, i, j)) {
                    edges.putIfAbsent(i, new ArrayList<>());
                    edges.get(i).add(j);
                }
            }
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            boolean[] visited = new boolean[n];
            int cnt = 1;
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(i);
            visited[i] = true;
            while (!queue.isEmpty()) {
                int j = queue.poll();
                for (int k : edges.getOrDefault(j, new ArrayList<>())) {
                    if (visited[k]) {
                        continue;
                    }
                    cnt++;
                    queue.offer(k);
                    visited[k] = true;
                }
            }
            res = Math.max(res, cnt);
        }
        return res;
    }

    private boolean isConnected(int[][] bombs, int u, int v) {
        long dx = bombs[u][0] - bombs[v][0];
        long dy = bombs[u][1] - bombs[v][1];
        return (long) bombs[u][2] * bombs[u][2] >= dx * dx + dy * dy;
    }

}

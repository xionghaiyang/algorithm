package com.sean.leetcode.LeetCode3607;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @Author xionghaiyang
 * @Date 2025-07-07 13:03
 * @Description https://leetcode.cn/problems/power-grid-maintenance
 * 3607. 电网维护
 * 给你一个整数 c，表示 c 个电站，每个电站有一个唯一标识符 id，从 1 到 c 编号。
 * 这些电站通过 n 条 双向 电缆互相连接，表示为一个二维数组 connections，其中每个元素 connections[i] = [ui, vi] 表示电站 ui 和电站 vi 之间的连接。
 * 直接或间接连接的电站组成了一个 电网 。
 * 最初，所有 电站均处于在线（正常运行）状态。
 * 另给你一个二维数组 queries，其中每个查询属于以下 两种类型之一 ：
 * [1, x]：请求对电站 x 进行维护检查。
 * 如果电站 x 在线，则它自行解决检查。
 * 如果电站 x 已离线，则检查由与 x 同一 电网 中 编号最小 的在线电站解决。
 * 如果该电网中 不存在 任何 在线 电站，则返回 -1。
 * [2, x]：电站 x 离线（即变为非运行状态）。
 * 返回一个整数数组，表示按照查询中出现的顺序，所有类型为 [1, x] 的查询结果。
 * 注意：电网的结构是固定的；离线（非运行）的节点仍然属于其所在的电网，且离线操作不会改变电网的连接性。
 * 1 <= c <= 10^5
 * 0 <= n == connections.length <= min(10^5, c * (c - 1) / 2)
 * connections[i].length == 2
 * 1 <= ui, vi <= c
 * ui != vi
 * 1 <= queries.length <= 2 * 10^5
 * queries[i].length == 2
 * queries[i][0] 为 1 或 2。
 * 1 <= queries[i][1] <= c
 */
public class Solution {

    public int[] processQueries(int c, int[][] connections, int[][] queries) {
        List<Integer>[] g = new ArrayList[c + 1];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int[] connection : connections) {
            int u = connection[0], v = connection[1];
            g[u].add(v);
            g[v].add(u);
        }
        //记录每个节点属于哪一个堆
        int[] belong = new int[c + 1];
        Arrays.fill(belong, -1);
        List<PriorityQueue<Integer>> heaps = new ArrayList<>();
        PriorityQueue<Integer> heap;
        for (int i = 1; i <= c; i++) {
            if (belong[i] >= 0) {
                continue;
            }
            heap = new PriorityQueue<>();
            process(i, g, belong, heaps.size(), heap);
            heaps.add(heap);
        }
        int resSize = 0;
        for (int[] query : queries) {
            if (query[0] == 1) {
                resSize++;
            }
        }
        int[] res = new int[resSize];
        int index = 0;
        boolean[] offline = new boolean[c + 1];
        for (int[] query : queries) {
            int x = query[1];
            if (query[0] == 2) {
                offline[x] = true;
                continue;
            }
            if (!offline[x]) {
                res[index++] = x;
                continue;
            }
            heap = heaps.get(belong[x]);
            while (!heap.isEmpty() && offline[heap.peek()]) {
                heap.poll();
            }
            res[index++] = heap.isEmpty() ? -1 : heap.peek();
        }
        return res;
    }

    private void process(int x, List<Integer>[] g, int[] belong, int heapId, PriorityQueue<Integer> heap) {
        belong[x] = heapId;
        heap.offer(x);
        for (int y : g[x]) {
            if (belong[y] == -1) {
                process(y, g, belong, heapId, heap);
            }
        }
    }

}

package com.sean.leetcode.LeetCode882;

import java.util.*;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-11-26 21:50
 * @Description: https://leetcode.cn/problems/reachable-nodes-in-subdivided-graph/
 * 882. 细分图中的可到达节点
 * 给你一个无向图（原始图），图中有 n 个节点，编号从 0 到 n - 1 。
 * 你决定将图中的每条边 细分 为一条节点链，每条边之间的新节点数各不相同。
 * 图用由边组成的二维数组 edges 表示，其中 edges[i] = [ui, vi, cnti] 表示原始图中节点 ui 和 vi 之间存在一条边，
 * cnti 是将边 细分 后的新节点总数。
 * 注意，cnti == 0 表示边不可细分。
 * 要 细分 边 [ui, vi] ，需要将其替换为 (cnti + 1) 条新边，和 cnti 个新节点。
 * 新节点为 x1, x2, ..., xcnti ，新边为 [ui, x1], [x1, x2], [x2, x3], ..., [xcnti+1, xcnti], [xcnti, vi] 。
 * 现在得到一个 新的细分图 ，请你计算从节点 0 出发，可以到达多少个节点？如果节点间距离是 maxMoves 或更少，则视为 可以到达 。
 * 给你原始图和 maxMoves ，返回 新的细分图中从节点 0 出发 可到达的节点数 。
 */
public class Solution {

    public int reachableNodes(int[][] edges, int maxMoves, int n) {
        List<int[]>[] adList = new List[n];
        for (int i = 0; i < n; i++) {
            adList[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], nodes = edge[2];
            adList[u].add(new int[]{v, nodes});
            adList[v].add(new int[]{u, nodes});
        }
        Map<Integer, Integer> used = new HashMap<>();
        Set<Integer> visited = new HashSet<>();
        int reachableNodes = 0;
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        heap.offer(new int[]{0, 0});
        while (!heap.isEmpty() && heap.peek()[0] <= maxMoves) {
            int[] pair = heap.poll();
            int step = pair[0], u = pair[1];
            if (!visited.add(u)) {
                continue;
            }
            reachableNodes++;
            for (int[] next : adList[u]) {
                int v = next[0], nodes = next[1];
                if (nodes + step + 1 <= maxMoves && !visited.contains(v)) {
                    heap.offer(new int[]{nodes + step + 1, v});
                }
                used.put(encode(u, v, n), Math.min(nodes, maxMoves - step));
            }
        }
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], nodes = edge[2];
            reachableNodes += Math.min(nodes, used.getOrDefault(encode(u, v, n), 0) + used.getOrDefault(encode(v, u, n), 0));
        }
        return reachableNodes;
    }

    private int encode(int u, int v, int n) {
        return u * n + v;
    }

}

package com.sean.leetcode.LeetCode1617;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-03-13 08:31
 * @Description: https://leetcode.cn/problems/count-subtrees-with-max-distance-between-cities/
 * 1617. 统计子树中城市之间最大距离
 * 给你 n 个城市，编号为从 1 到 n 。
 * 同时给你一个大小为 n-1 的数组 edges ，其中 edges[i] = [ui, vi] 表示城市 ui 和 vi 之间有一条双向边。
 * 题目保证任意城市之间只有唯一的一条路径。
 * 换句话说，所有城市形成了一棵 树 。
 * 一棵 子树 是城市的一个子集，且子集中任意城市之间可以通过子集中的其他城市和边到达。
 * 两个子树被认为不一样的条件是至少有一个城市在其中一棵子树中存在，但在另一棵子树中不存在。
 * 对于 d 从 1 到 n-1 ，请你找到城市间 最大距离 恰好为 d 的所有子树数目。
 * 请你返回一个大小为 n-1 的数组，其中第 d 个元素（下标从 1 开始）是城市间 最大距离 恰好等于 d 的子树数目。
 * 请注意，两个城市间距离定义为它们之间需要经过的边的数目。
 */
public class Solution {

    int mask;
    int diameter;

    public int[] countSubgraphsForEachDiameter(int n, int[][] edges) {
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int x = edge[0] - 1;
            int y = edge[1] - 1;
            adj[x].add(y);
            adj[y].add(x);
        }
        int[] res = new int[n - 1];
        for (int i = 1; i < (1 << n); i++) {
            int root = 32 - Integer.numberOfLeadingZeros(i) - 1;
            mask = i;
            diameter = 0;
            dfs(root, adj);
            if (mask == 0 && diameter > 0) {
                res[diameter - 1]++;
            }
        }
        return res;
    }

    private int dfs(int root, List<Integer>[] adj) {
        int first = 0, second = 0;
        mask &= ~(1 << root);
        for (int vertex : adj[root]) {
            if ((mask & (1 << vertex)) != 0) {
                mask &= ~(1 << vertex);
                int distance = 1 + dfs(vertex, adj);
                if (distance > first) {
                    second = first;
                    first = distance;
                } else if (distance > second) {
                    second = distance;
                }
            }
        }
        diameter = Math.max(diameter, first + second);
        return first;
    }

}

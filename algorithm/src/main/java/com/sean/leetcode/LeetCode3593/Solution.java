package com.sean.leetcode.LeetCode3593;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2025-07-02 19:45
 * @Description https://leetcode.cn/problems/minimum-increments-to-equalize-leaf-paths
 * 3593. 使叶子路径成本相等的最小增量
 * 给你一个整数 n，以及一个无向树，该树以节点 0 为根节点，包含 n 个节点，节点编号从 0 到 n - 1。
 * 这棵树由一个长度为 n - 1 的二维数组 edges 表示，其中 edges[i] = [ui, vi] 表示节点 ui 和节点 vi 之间存在一条边。
 * 每个节点 i 都有一个关联的成本 cost[i]，表示经过该节点的成本。
 * 路径得分 定义为路径上所有节点成本的总和。
 * 你的目标是通过给任意数量的节点 增加 成本（可以增加任意非负值），使得所有从根节点到叶子节点的路径得分 相等 。
 * 返回需要增加成本的节点数的 最小值 。
 * 2 <= n <= 10^5
 * edges.length == n - 1
 * edges[i] == [ui, vi]
 * 0 <= ui, vi < n
 * cost.length == n
 * 1 <= cost[i] <= 10^9
 * 输入保证 edges 表示一棵合法的树。
 */
public class Solution {

    private int res = 0;

    public int minIncrease(int n, int[][] edges, int[] cost) {
        List<Integer>[] g = new ArrayList[n];
        Arrays.setAll(g, i -> new ArrayList());
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            g[u].add(v);
            g[v].add(u);
        }
        //避免误把根节点当作叶节点
        g[0].add(-1);
        process(0, -1, 0, g, cost);
        return res;
    }

    //返回从根经过x到叶子节点的最大路径和
    //对于x的儿子y返回最大的路径和，统计其中的最大值，以及等于最大值的个数cnt
    //需要修改的节点个数就是x的儿子个数减去cnt
    private long process(int x, int fa, long pathSum, List<Integer>[] g, int[] cost) {
        pathSum += cost[x];
        if (g[x].size() == 1) {
            return pathSum;
        }
        //根到叶节点的pathSum中有cnt个pathSum等于maxSum
        long maxSum = 0;
        int cnt = 0;
        for (int y : g[x]) {
            if (y == fa) {
                continue;
            }
            long max = process(y, x, pathSum, g, cost);
            if (max > maxSum) {
                maxSum = max;
                cnt = 1;
            } else if (max == maxSum) {
                cnt++;
            }
        }
        //其余小于maxSum的pathSum可以通过增大cost[y]的值，改成maxSum
        //-1表示减去父节点，g[x].size() - 1是儿子个数
        res += g[x].size() - 1 - cnt;
        return maxSum;
    }

}

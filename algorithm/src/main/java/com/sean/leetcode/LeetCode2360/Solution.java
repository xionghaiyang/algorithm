package com.sean.leetcode.LeetCode2360;

/**
 * @Author xionghaiyang
 * @Date 2025-03-29 08:42
 * @Description https://leetcode.cn/problems/longest-cycle-in-a-graph
 * 2360. 图中的最长环
 * 给你一个 n 个节点的 有向图 ，节点编号为 0 到 n - 1 ，其中每个节点 至多 有一条出边。
 * 图用一个大小为 n 下标从 0 开始的数组 edges 表示，节点 i 到节点 edges[i] 之间有一条有向边。
 * 如果节点 i 没有出边，那么 edges[i] == -1 。
 * 请你返回图中的 最长 环，如果没有任何环，请返回 -1 。
 * 一个环指的是起点和终点是 同一个 节点的路径。
 * n == edges.length
 * 2 <= n <= 10^5
 * -1 <= edges[i] < n
 * edges[i] != i
 */
public class Solution {

    public int longestCycle(int[] edges) {
        int n = edges.length;
        int res = -1;
        int curTime = 1;
        int[] visTime = new int[n];
        for (int i = 0; i < n; i++) {
            int x = i;
            int startTime = curTime;
            while (x != -1 && visTime[x] == 0) {
                visTime[x] = curTime++;
                x = edges[x];
            }
            //x = -1表示没有形成环
            if (x != -1 && visTime[x] >= startTime) {
                res = Math.max(res, curTime - visTime[x]);
            }
        }
        return res;
    }

}

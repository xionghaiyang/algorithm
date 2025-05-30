package com.sean.leetcode.LeetCode2359;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2025-05-30 07:53
 * @Description https://leetcode.cn/problems/find-closest-node-to-given-two-nodes
 * 2359. 找到离给定两个节点最近的节点
 * 给你一个 n 个节点的 有向图 ，节点编号为 0 到 n - 1 ，每个节点 至多 有一条出边。
 * 有向图用大小为 n 下标从 0 开始的数组 edges 表示，表示节点 i 有一条有向边指向 edges[i] 。
 * 如果节点 i 没有出边，那么 edges[i] == -1 。
 * 同时给你两个节点 node1 和 node2 。
 * 请你返回一个从 node1 和 node2 都能到达节点的编号，使节点 node1 和节点 node2 到这个节点的距离 较大值最小化。
 * 如果有多个答案，请返回 最小 的节点编号。
 * 如果答案不存在，返回 -1 。
 * 注意 edges 可能包含环。
 */
public class Solution {

    public int closestMeetingNode(int[] edges, int node1, int node2) {
        int n = edges.length;
        int[] dis1 = process(edges, node1);
        int[] dis2 = process(edges, node2);
        int d = Integer.MAX_VALUE, res = -1;
        for (int i = 0; i < n; i++) {
            int cur = Math.max(dis1[i], dis2[i]);
            if (cur < d) {
                d = cur;
                res = i;
            }
        }
        return res;
    }

    private int[] process(int[] edges, int x) {
        int n = edges.length;
        int[] dis = new int[n];
        Arrays.fill(dis, Integer.MAX_VALUE);
        for (int d = 0; x >= 0 && dis[x] == Integer.MAX_VALUE; x = edges[x]) {
            dis[x] = d++;
        }
        return dis;
    }

}

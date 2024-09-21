package com.sean.leetcode.LeetCode2374;

/**
 * @Author xionghaiyang
 * @Date 2024-09-21 21:52
 * @Description https://leetcode.cn/problems/node-with-highest-edge-score/
 * 2374. 边积分最高的节点
 * 给你一个有向图，图中有 n 个节点，节点编号从 0 到 n - 1 ，其中每个节点都 恰有一条 出边。
 * 图由一个下标从 0 开始、长度为 n 的整数数组 edges 表示，其中 edges[i] 表示存在一条从节点 i 到节点 edges[i] 的 有向 边。
 * 节点 i 的 边积分 定义为：所有存在一条指向节点 i 的边的节点的 编号 总和。
 * 返回 边积分 最高的节点。
 * 如果多个节点的 边积分 相同，返回编号 最小 的那个。
 */
public class Solution {

    public int edgeScore(int[] edges) {
        int n = edges.length;
        long[] score = new long[n + 1];
        for (int i = 0; i < n; i++) {
            score[edges[i]] += i;
        }
        long maxScore = -1;
        int res = -1;
        for (int i = 0; i < n; i++) {
            if (score[i] > maxScore) {
                maxScore = score[i];
                res = i;
            }
        }
        return res;
    }

}

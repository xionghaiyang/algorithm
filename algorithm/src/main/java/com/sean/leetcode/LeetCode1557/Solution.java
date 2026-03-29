package com.sean.leetcode.LeetCode1557;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2026-03-29 19:09
 * @Description https://leetcode.cn/problems/minimum-number-of-vertices-to-reach-all-nodes
 * 1557. 可以到达所有点的最少点数目
 * 给你一个 有向无环图 ， n 个节点编号为 0 到 n-1 ，以及一个边数组 edges ，其中 edges[i] = [fromi, toi] 表示一条从点  fromi 到点 toi 的有向边。
 * 找到最小的点集使得从这些点出发能到达图中所有点。
 * 题目保证解存在且唯一。
 * 你可以以任意顺序返回这些节点编号。
 * 2 <= n <= 10^5
 * 1 <= edges.length <= min(10^5, n * (n - 1) / 2)
 * edges[i].length == 2
 * 0 <= fromi, toi < n
 * 所有点对 (fromi, toi) 互不相同。
 */
public class Solution {

    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        List<Integer> res = new ArrayList<>();
        int[] inDegree = new int[n];
        for (List<Integer> edge : edges) {
            inDegree[edge.get(1)]++;
        }
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                res.add(i);
            }
        }
        return res;
    }

}

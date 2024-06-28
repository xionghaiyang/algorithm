package com.sean.leetcode.LeetCode797;

import java.util.*;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-17 10:01
 * @Description: https://leetcode.cn/problems/all-paths-from-source-to-target/?plan=graph&plan_progress=zq56763
 * 797. 所有可能的路径
 * 给你一个有 n 个节点的 有向无环图（DAG），请你找出所有从节点 0 到节点 n-1 的路径并输出（不要求按特定顺序）
 *  graph[i] 是一个从节点 i 可以访问的所有节点的列表（即从节点 i 到节点 graph[i][j]存在一条有向边）。
 */
public class Solution {

    List<List<Integer>> res = new ArrayList<>();
    List<Integer> temp = new ArrayList<>();

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        temp.add(0);
        dfs(graph, 0);
        return res;
    }

    private void dfs(int[][] graph, int index) {
        int n = graph.length;
        if (index == n - 1) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int num : graph[index]) {
            temp.add(num);
            dfs(graph, num);
            temp.remove(temp.size() - 1);
        }
    }

}

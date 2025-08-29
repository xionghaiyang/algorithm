package com.sean.leetcode.LeetCode1192;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2025-08-29 12:41
 * @Description https://leetcode.cn/problems/critical-connections-in-a-network
 * 1192. 查找集群内的关键连接
 * 力扣数据中心有 n 台服务器，分别按从 0 到 n-1 的方式进行了编号。
 * 它们之间以 服务器到服务器 的形式相互连接组成了一个内部集群，连接是无向的。
 * 用  connections 表示集群网络，connections[i] = [a, b] 表示服务器 a 和 b 之间形成连接。
 * 任何服务器都可以直接或者间接地通过网络到达任何其他服务器。
 * 关键连接 是在该集群中的重要连接，假如我们将它移除，便会导致某些服务器无法访问其他服务器。
 * 请你以任意顺序返回该集群内的所有 关键连接 。
 * 2 <= n <= 10^5
 * n - 1 <= connections.length <= 10^5
 * 0 <= ai, bi <= n - 1
 * ai != bi
 * 不存在重复的连接
 */
public class Solution {

    //结论:
    //1、所有在环上的边都不是关键连接
    //2、所有不在环上的边都是关键连接
    private List<Integer>[] g;
    private int[] id;
    private List<List<Integer>> res;

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        g = new ArrayList[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (List<Integer> connection : connections) {
            int u = connection.get(0), v = connection.get(1);
            g[u].add(v);
            g[v].add(u);
        }
        id = new int[n];
        Arrays.fill(id, -1);
        res = new ArrayList<>();
        process(0, 0, -1);
        return res;
    }

    private int process(int u, int uid, int p) {
        id[u] = uid;
        for (int v : g[u]) {
            if (v == p) {
                continue;
            } else if (id[v] == -1) {
                id[u] = Math.min(id[u], process(v, uid + 1, u));
            } else {
                id[u] = Math.min(id[u], id[v]);
            }
        }
        if (id[u] == uid && u != 0) {
            res.add(Arrays.asList(p, u));
        }
        return id[u];
    }

}

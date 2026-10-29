package com.sean.leetcode.LeetCode3841;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: xionghaiyang
 * @Date: 2026-10-29 06:14
 * @Description: https://leetcode.cn/problems/palindromic-path-queries-in-a-tree
 * 3841. 查询树上回文路径
 * 给你一棵包含 n 个节点的无向树，节点编号从 0 到 n - 1。
 * 树由一个长度为 n - 1 的二维数组 edges 表示，其中 edges[i] = [ui, vi] 表示节点 ui 和 vi 之间存在一条无向边。
 * 另给你一个长度为 n 且由小写英文字母组成的字符串 s，其中 s[i] 表示分配给节点 i 的字符。
 * 还给你一个字符串数组 queries，其中每个 queries[i] 为以下形式之一：
 * "update ui c"：将节点 ui 处的字符更改为 c。
 * 正式地，更新 s[ui] = c。
 * "query ui vi"：判断从 ui 到 vi 的 唯一 路径（含两端点）上的字符所组成的字符串，是否可以 重新排列 成一个 回文串 。
 * 返回一个布尔数组 answer，如果第 i 个类型为 "query ui vi" 的查询可以重新排列成 回文串 ，则 answer[i] 为 true，否则为 false。
 * 回文串 是指正读和反读都相同的字符串。
 * 1 <= n == s.length <= 5 * 10^4
 * edges.length == n - 1
 * edges[i] = [ui, vi]
 * 0 <= ui, vi <= n - 1
 * s 由小写英文字母组成。
 * 输入生成的 edges 表示一棵有效的树。
 * 1 <= queries.length <= 5 * 10^4
 * queries[i] = "update ui c" 或
 * queries[i] = "query ui vi"
 * 0 <= ui, vi <= n - 1
 * c 是一个小写英文字母。
 */
public class Solution {

    public class FenwickTree {
        private final int[] tree;

        public FenwickTree(int n) {
            tree = new int[n + 1];
        }

        //a[i]^=val
        //1<=i<=n
        public void update(int i, int val) {
            while (i < tree.length) {
                tree[i] ^= val;
                i += i & -i;
            }
        }

        //求前缀异或和a[1] ^ ...^ a[i]
        //1 <= i <= n
        public int pre(int i) {
            int res = 0;
            while (i > 0) {
                res ^= tree[i];
                i &= i - 1;
            }
            return res;
        }
    }

    public class LcaBinaryLifting {
        private final int[] depth;
        private final int[][] parent;
        private int clock = 0;
        public final int[] tin;
        public final int[] tout;
        public final int[] pathXorFromRoot;

        public LcaBinaryLifting(int[][] edges, char[] s) {
            int n = edges.length + 1;
            int m = 32 - Integer.numberOfLeadingZeros(n);
            List<Integer>[] g = new ArrayList[n];
            Arrays.setAll(g, e -> new ArrayList<>());
            for (int[] edge : edges) {
                int x = edge[0], y = edge[1];
                g[x].add(y);
                g[y].add(x);
            }
            depth = new int[n];
            parent = new int[m][n];
            tin = new int[n];
            tout = new int[n];
            pathXorFromRoot = new int[n];
            pathXorFromRoot[0] = 1 << (s[0] - 'a');
            dfs(g, s, 0, -1);
            for (int i = 0; i < m - 1; i++) {
                for (int x = 0; x < n; x++) {
                    int p = parent[i][x];
                    parent[i + 1][x] = p < 0 ? -1 : parent[i][p];
                }
            }
        }

        private void dfs(List<Integer>[] g, char[] s, int x, int p) {
            parent[0][x] = p;
            tin[x] = ++clock;
            for (int y : g[x]) {
                if (y != p) {
                    depth[y] = depth[x] + 1;
                    pathXorFromRoot[y] = pathXorFromRoot[x] ^ (1 << (s[y] - 'a'));
                    dfs(g, s, y, x);
                }
            }
            tout[x] = clock;
        }

        //返回node的第k个祖先节点
        //如果不存在，返回-1
        private int getKthAncestor(int node, int k) {
            while (k > 0 && node >= 0) {
                node = parent[Integer.numberOfTrailingZeros(k)][node];
                k &= k - 1;
            }
            return node;
        }

        //返回x和y的最近公共祖先
        public int getLCA(int x, int y) {
            if (depth[x] > depth[y]) {
                return getLCA(y, x);
            }
            y = getKthAncestor(y, depth[y] - depth[x]);
            if (y == x) {
                return x;
            }
            for (int i = parent.length - 1; i >= 0; i--) {
                int px = parent[i][x], py = parent[i][y];
                if (px != py) {
                    x = px;
                    y = py;
                }
            }
            return parent[0][x];
        }
    }

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        char[] t = s.toCharArray();
        LcaBinaryLifting g = new LcaBinaryLifting(edges, t);
        FenwickTree f = new FenwickTree(n);
        List<Boolean> res = new ArrayList<>();
        for (String query : queries) {
            String[] parts = query.split(" ");
            int x = Integer.parseInt(parts[1]);
            if (parts[0].charAt(0) == 'u') {
                char c = parts[2].charAt(0);
                int val = (1 << (t[x] - 'a')) ^ (1 << (c - 'a'));
                t[x] = c;
                f.update(g.tin[x], val);
                f.update(g.tout[x] + 1, val);
            } else {
                int y = Integer.parseInt(parts[2]);
                int lca = g.getLCA(x, y);
                int ans = g.pathXorFromRoot[x] ^ g.pathXorFromRoot[y] ^ f.pre(g.tin[x]) ^ f.pre(g.tin[y]) ^ (1 << (t[lca] - 'a'));
                res.add((ans & (ans - 1)) == 0);
            }
        }
        return res;
    }

}

package com.sean.leetcode.LeetCode1766;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-04-11 23:07
 * @Description: https://leetcode.cn/problems/tree-of-coprimes/
 * 1766. 互质树
 * 给你一个 n 个节点的树（也就是一个无环连通无向图），节点编号从 0 到 n - 1 ，且恰好有 n - 1 条边，每个节点有一个值。
 * 树的 根节点 为 0 号点。
 * 给你一个整数数组 nums 和一个二维数组 edges 来表示这棵树。
 * nums[i] 表示第 i 个点的值，edges[j] = [uj, vj] 表示节点 uj 和节点 vj 在树中有一条边。
 * 当 gcd(x, y) == 1 ，我们称两个数 x 和 y 是 互质的 ，其中 gcd(x, y) 是 x 和 y 的 最大公约数 。
 * 从节点 i 到 根 最短路径上的点都是节点 i 的祖先节点。
 * 一个节点 不是 它自己的祖先节点。
 * 请你返回一个大小为 n 的数组 ans ，其中 ans[i]是离节点 i 最近的祖先节点且满足 nums[i] 和 nums[ans[i]] 是 互质的 ，
 * 如果不存在这样的祖先节点，ans[i] 为 -1 。
 */
public class Solution {

    List<Integer>[] gcds;
    List<Integer>[] tmp;
    List<Integer>[] g;
    int[] dep;
    int[] res;

    public int[] getCoprimes(int[] nums, int[][] edges) {
        int n = nums.length;
        gcds = new List[51];
        tmp = new List[51];
        for (int i = 0; i < 51; i++) {
            gcds[i] = new ArrayList<>();
            tmp[i] = new ArrayList<>();
        }
        res = new int[n];
        Arrays.fill(res, -1);
        dep = new int[n];
        Arrays.fill(dep, -1);
        g = new List[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            g[edge[0]].add(edge[1]);
            g[edge[1]].add(edge[0]);
        }
        for (int i = 1; i < 51; i++) {
            for (int j = 1; j < 51; j++) {
                if (gcd(i, j) == 1) {
                    gcds[i].add(j);
                }
            }
        }
        dfs(nums, 0, 1);
        return res;
    }

    private int gcd(int x, int y) {
        while (y != 0) {
            int temp = x;
            x = y;
            y = temp % y;
        }
        return x;
    }

    private void dfs(int[] nums, int x, int depth) {
        dep[x] = depth;
        for (int val : gcds[nums[x]]) {
            if (tmp[val].isEmpty()) {
                continue;
            }
            int las = tmp[val].get(tmp[val].size() - 1);
            if (res[x] == -1 || dep[las] > dep[res[x]]) {
                res[x] = las;
            }
        }
        tmp[nums[x]].add(x);
        for (int val : g[x]) {
            if (dep[val] == -1) {//被访问过的dep不为-1
                dfs(nums, val, depth + 1);
            }
        }
        tmp[nums[x]].remove(tmp[nums[x]].size() - 1);
    }

}

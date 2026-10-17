package com.sean.leetcode.LeetCode3939;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: xionghaiyang
 * @Date: 2026-10-17 12:46
 * @Description: https://leetcode.cn/problems/count-non-adjacent-subsets-in-a-rooted-tree
 * 3939. 统计有根树中不相邻子集的数目
 * 给你一棵有 n 个节点的有根树，节点编号从 0 到 n - 1 ，由一个长度为 n 的整数数组 parent 表示，其中：
 * parent[0] = -1 （节点 0 是根节点）。
 * 对于每个 1 <= i < n ，parent[i] 是节点 i 的父节点（0 <= parent[i] < i）。
 * 另外给你一个长度为 n 的整数数组 nums ，其中 nums[i] 是节点 i 的值，以及一个整数 k。
 * 如果节点的一个非空子集满足以下条件，则称为 有效 子集：
 * 所选节点的值之 和 可以被 k 整除 。
 * 所选节点中没有 两 个节点在树中是 相邻 的（即没有节点及其直接父节点同时包含在子集中）。
 * 返回有效子集的数量对 10^9 + 7 取余的结果。
 * n == parent.length == nums.length
 * 1 <= n <= 1000
 * parent[0] == -1
 * 对于所有的 1 <= i < n：
 * 0 <= parent[i] < i
 * 1 <= nums[i] <= 10^9
 * 1 <= k <= 100​​​​​​​​​​​​​​
 * parent 表示一棵有效的有根树。
 */
public class Solution {

    private static final int MOD = 1_000_000_007;

    public int countValidSubsets(int[] parent, int[] nums, int k) {
        int n = parent.length;
        List<Integer>[] g = new ArrayList[n];
        Arrays.setAll(g, e -> new ArrayList());
        for (int i = 1; i < n; i++) {
            g[parent[i]].add(i);
        }
        long[][] res = dfs(g, nums, k, 0);
        return (int) ((res[0][0] + res[1][0] - 1 + MOD) % MOD);
    }

    private long[][] dfs(List<Integer>[] g, int[] nums, int k, int x) {
        //f0[i]表示不选x时，子树x的子集点权和模k为i的方案数
        long[] f0 = new long[k];
        //f1[i]表示选x时，子树x的子集点权和模k为i的方案数
        long[] f1 = new long[k];
        f0[0] = 1;
        f1[nums[x] % k] = 1;
        for (int y : g[x]) {
            long[][] resY = dfs(g, nums, k, y);
            long[] fy0 = resY[0];
            long[] fy1 = resY[1];
            //不选x，那么y可选可不选
            long[] nf0 = new long[k];
            for (int i = 0; i < k; i++) {
                long v = fy0[i] + fy1[i];
                if (v == 0) {
                    continue;
                }
                for (int j = 0; j < k; j++) {
                    int s = (i + j) % k;
                    nf0[s] = (nf0[s] + v * f0[j]) % MOD;
                }
            }
            //选x,那么y不能选
            long[] nf1 = new long[k];
            for (int i = 0; i < k; i++) {
                long v = fy0[i];
                if (v == 0) {
                    continue;
                }
                for (int j = 0; j < k; j++) {
                    int s = (i + j) % k;
                    nf1[s] = (nf1[s] + v * f1[j]) % MOD;
                }
            }
            f0 = nf0;
            f1 = nf1;
        }
        return new long[][]{f0, f1};
    }

}

package com.sean.leetcode.LeetCode3068;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2025-05-23 06:07
 * @Description https://leetcode.cn/problems/find-the-maximum-sum-of-node-values
 * 3068. 最大节点价值之和
 * 给你一棵 n 个节点的 无向 树，节点从 0 到 n - 1 编号。
 * 树以长度为 n - 1 下标从 0 开始的二维整数数组 edges 的形式给你，其中 edges[i] = [ui, vi] 表示树中节点 ui 和 vi 之间有一条边。
 * 同时给你一个 正 整数 k 和一个长度为 n 下标从 0 开始的 非负 整数数组 nums ，其中 nums[i] 表示节点 i 的 价值 。
 * Alice 想 最大化 树中所有节点价值之和。
 * 为了实现这一目标，Alice 可以执行以下操作 任意 次（包括 0 次）：
 * 选择连接节点 u 和 v 的边 [u, v] ，并将它们的值更新为：
 * nums[u] = nums[u] XOR k
 * nums[v] = nums[v] XOR k
 * 请你返回 Alice 通过执行以上操作 任意次 后，可以得到所有节点 价值之和 的 最大值 。
 * 2 <= n == nums.length <= 2 * 10^4
 * 1 <= k <= 10^9
 * 0 <= nums[i] <= 10^9
 * edges.length == n - 1
 * edges[i].length == 2
 * 0 <= edges[i][0], edges[i][1] <= n - 1
 * 输入保证 edges 构成一棵合法的树。
 */
public class Solution {

    private List<Integer>[] g;
    private int[] nums;
    private int k;

    public long maximumValueSum(int[] nums, int k, int[][] edges) {
        int n = nums.length;
        g = new ArrayList[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            g[u].add(v);
            g[v].add(u);
        }
        this.nums = nums;
        this.k = k;
        return process(0, -1)[0];
    }

    //对于以x为根的子树，考虑x和它的儿子y之间的边是否操作
    //f[x][0]表示x操作偶数次时，子树x的除去x的最大价值
    //f[x][1]表示x操作奇数次时，子树x的除去x的最大价值
    private long[] process(int x, int fa) {
        long fx0 = 0;
        long fx1 = Long.MIN_VALUE;
        for (int y : g[x]) {
            if (y != fa) {
                long[] r = process(y, x);
                long t = Math.max(fx1 + r[0], fx0 + r[1]);
                fx0 = Math.max(fx0 + r[0], fx1 + r[1]);
                fx1 = t;
            }
        }
        return new long[]{Math.max(fx0 + nums[x], fx1 + (nums[x] ^ k)), Math.max(fx1 + nums[x], fx0 + (nums[x] ^ k))};
    }

    public long maximumValueSum1(int[] nums, int k, int[][] edges) {
        long f0 = 0;
        long f1 = Long.MIN_VALUE;
        for (int num : nums) {
            long t = Math.max(f1 + num, f0 + (num ^ k));
            f0 = Math.max(f0 + num, f1 + (num ^ k));
            f1 = t;
        }
        return f0;
    }

}

package com.sean.leetcode.LeetCode2322;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2025-07-24 06:13
 * @Description https://leetcode.cn/problems/minimum-score-after-removals-on-a-tree
 * 2322. 从树中删除边的最小分数
 * 存在一棵无向连通树，树中有编号从 0 到 n - 1 的 n 个节点， 以及 n - 1 条边。
 * 给你一个下标从 0 开始的整数数组 nums ，长度为 n ，其中 nums[i] 表示第 i 个节点的值。
 * 另给你一个二维整数数组 edges ，长度为 n - 1 ，其中 edges[i] = [ai, bi] 表示树中存在一条位于节点 ai 和 bi 之间的边。
 * 删除树中两条 不同 的边以形成三个连通组件。
 * 对于一种删除边方案，定义如下步骤以计算其分数：
 * 分别获取三个组件 每个 组件中所有节点值的异或值。
 * 最大 异或值和 最小 异或值的 差值 就是这一种删除边方案的分数。
 * 例如，三个组件的节点值分别是：[4,5,7]、[1,9] 和 [3,3,3] 。
 * 三个异或值分别是 4 ^ 5 ^ 7 = 6、1 ^ 9 = 8 和 3 ^ 3 ^ 3 = 3 。
 * 最大异或值是 8 ，最小异或值是 3 ，分数是 8 - 3 = 5 。
 * 返回在给定树上执行任意删除边方案可能的 最小 分数。
 * n == nums.length
 * 3 <= n <= 1000
 * 1 <= nums[i] <= 10^8
 * edges.length == n - 1
 * edges[i].length == 2
 * 0 <= ai, bi < n
 * ai != bi
 * edges 表示一棵有效的树
 */
public class Solution {

    public int minimumScore(int[] nums, int[][] edges) {
        int n = nums.length;
        List<Integer>[] g = new ArrayList[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int[] edge : edges) {
            int x = edge[0], y = edge[1];
            g[x].add(y);
            g[y].add(x);
        }
        int[] xor = new int[n];
        int[] in = new int[n];
        int[] out = new int[n];
        dfs(0, -1, g, nums, xor, in, out);
        int res = Integer.MAX_VALUE;
        //删除x与x父节点之间的边，删除y与y父节点的边
        for (int x = 1; x < n; x++) {
            for (int y = x + 1; y < n; y++) {
                int part1, part2, part3;
                if (isAncestor(x, y, in, out)) {//x是y的祖先
                    part1 = xor[y];
                    part2 = xor[x] ^ xor[y];
                    part3 = xor[0] ^ xor[x];
                } else if (isAncestor(y, x, in, out)) {//y是x的祖先
                    part1 = xor[x];
                    part2 = xor[y] ^ xor[x];
                    part3 = xor[0] ^ xor[y];
                } else {
                    part1 = xor[x];
                    part2 = xor[y];
                    part3 = xor[0] ^ xor[x] ^ xor[y];
                }
                res = Math.min(res, Math.max(part1, Math.max(part2, part3)) - Math.min(part1, Math.min(part2, part3)));
                if (res == 0) {
                    return 0;
                }
            }
        }
        return res;
    }

    private int clock = 0;

    private void dfs(int x, int fa, List<Integer>[] g, int[] nums, int[] xor, int[] in, int[] out) {
        in[x] = ++clock;
        xor[x] = nums[x];
        for (int y : g[x]) {
            if (y != fa) {
                dfs(y, x, g, nums, xor, in, out);
                xor[x] ^= xor[y];
            }
        }
        out[x] = clock;
    }

    //判断x是否为y的祖先
    private boolean isAncestor(int x, int y, int[] in, int[] out) {
        return in[x] < in[y] && in[y] <= out[x];
    }

}

package com.sean.leetcode.LeetCode3575;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2025-06-08 17:17
 * @Description https://leetcode.cn/problems/maximum-good-subtree-score/
 * 3575. 最大好子树分数
 * 给你一个根节点为 0 的无向树，包含 n 个节点，编号从 0 到 n - 1。
 * 每个节点 i 都有一个整数值 vals[i]，其父节点为 par[i] 。
 * 从一个节点 子树 内选取部分节点，它们的数值组成一个 子集 ，如果所选数值的十进制表示中，
 * 从 0 到 9 每个数字在所有数的数位最多出现一次，那么我们称它是 好 子集。
 * 一个好子集的 分数 是其节点值的总和。
 * 定义一个长度为 n 的数组 maxScore，其中 maxScore[u] 表示以节点 u 为根的子树（包括 u 本身及其所有后代）中，好子集的最大可能值总和。
 * 返回 maxScore 中所有值的总和。
 * 由于答案可能很大，请将其对 10^9 + 7 取模 后返回。
 * 数组的 子集 是选取数组中元素得到的集合（可能为空）。
 * 1 <= n == vals.length <= 500
 * 1 <= vals[i] <= 10^9
 * par.length == n
 * par[0] == -1
 * 对于 [1, n - 1] 中的每一个 i ，都有 0 <= par[i] < n 。
 * 输入生成保证父数组 par 表示一棵有效的树。
 */
public class Solution {

    public class Node {
        public int index;
        public int val;
        public int mask;

        public Node(int index, int val) {
            this.index = index;
            this.val = val;
            mask = 0;
            while (val > 0) {
                int digit = val % 10;
                if (((mask >> digit) & 1) > 0) {
                    mask = -1;//数字重复
                    break;
                }
                mask |= 1 << digit;
                val /= 10;
            }
        }
    }

    private static final int MOD = 1_000_000_007;
    private List<Integer>[] g;
    private Node[] nodes;
    private int[] maxScore;

    public int goodSubtreeSum(int[] vals, int[] par) {
        int n = par.length;
        g = new ArrayList[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int i = 1; i < n; i++) {
            g[par[i]].add(i);
        }
        nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i, vals[i]);
        }
        maxScore = new int[n];
        process(0);
        int res = 0;
        for (int score : maxScore) {
            res = (res + score) % MOD;
        }
        return res;
    }

    private Map<Integer, Integer> process(int x) {
        Map<Integer, Integer> fx = new HashMap<>();
        int mask = nodes[x].mask;
        if (mask != -1) {
            fx.put(mask, nodes[x].val);
            maxScore[x] = nodes[x].val;
        }
        for (int y : g[x]) {
            Map<Integer, Integer> fy = process(y);
            Map<Integer, Integer> nfx = new HashMap<>(fx);
            for (Map.Entry<Integer, Integer> entry1 : fy.entrySet()) {
                int mask1 = entry1.getKey();
                if (entry1.getValue() > nfx.getOrDefault(mask1, 0)) {
                    nfx.put(mask1, entry1.getValue());
                }
                for (Map.Entry<Integer, Integer> entry2 : fx.entrySet()) {
                    int mask2 = entry2.getKey();
                    if ((mask1 & mask2) == 0) {
                        int combinedMask = mask1 | mask2;
                        int combinedVal = entry1.getValue() + entry2.getValue();
                        if (combinedVal > nfx.getOrDefault(combinedMask, 0)) {
                            nfx.put(combinedMask, combinedVal);
                        }
                    }
                }
            }
            fx = nfx;
        }
        if (!fx.isEmpty()) {
            maxScore[x] = Collections.max(fx.values());
        }
        return fx;
    }

}

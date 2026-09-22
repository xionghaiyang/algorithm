package com.sean.leetcode.LeetCode3525;

/**
 * @Author: xionghaiyang
 * @Date: 2026-09-22 06:38
 * @Description: https://leetcode.cn/problems/find-x-value-of-array-ii
 * 3525. 求出数组的 X 值 II
 * 给你一个由 正整数 组成的数组 nums 和一个 正整数 k。
 * 同时给你一个二维数组 queries，其中 queries[i] = [indexi, valuei, starti, xi]。
 * 你可以对 nums 执行 一次 操作，移除 nums 的任意 后缀 ，使得 nums 仍然非空。
 * 给定一个 x，nums 的 x值 定义为执行以上操作后剩余元素的 乘积 除以 k 的 余数 为 x 的方案数。
 * 对于 queries 中的每个查询，你需要执行以下操作，然后确定 xi 对应的 nums 的 x值：
 * 将 nums[indexi] 更新为 valuei。
 * 仅这个更改在接下来的所有查询中保留。
 * 移除 前缀 nums[0..(starti - 1)]（nums[0..(-1)] 表示 空前缀 ）。
 * 返回一个长度为 queries.length 的数组 result，其中 result[i] 是第 i 个查询的答案。
 * 数组的一个 前缀 是从数组开始位置到任意位置的子数组。
 * 数组的一个 后缀 是从数组中任意位置开始直到结束的子数组。
 * 子数组 是数组中一段连续的元素序列。
 * 注意：操作中所选的前缀或后缀可以是 空的 。
 * 注意：x值在本题中与问题 I 有不同的定义。
 * 1 <= nums[i] <= 10^9
 * 1 <= nums.length <= 10^5
 * 1 <= k <= 5
 * 1 <= queries.length <= 2 * 10^4
 * queries[i] == [indexi, valuei, starti, xi]
 * 0 <= indexi <= nums.length - 1
 * 1 <= valuei <= 10^9
 * 0 <= starti <= nums.length - 1
 * 0 <= xi <= k - 1
 */
public class Solution {

    public class SegmentTree {
        public class Node {
            private int p;
            private int[] cnt;

            public Node(int p, int k) {
                p %= k;
                this.p = p;
                cnt = new int[k];
                cnt[p] = 1;
            }

            public Node(int p, int[] cnt) {
                this.p = p;
                this.cnt = cnt;
            }
        }

        private final int n;
        private final int k;
        private final Node[] tree;

        public SegmentTree(int[] nums, int k) {
            n = nums.length;
            this.k = k;
            tree = new Node[n * 4];
            build(nums, 1, 0, n - 1);
        }

        private void build(int[] nums, int node, int left, int right) {
            if (left == right) {
                tree[node] = new Node(nums[left], k);
                return;
            }
            int mid = left + ((right - left) >> 1);
            build(nums, node * 2, left, mid);
            build(nums, node * 2 + 1, mid + 1, right);
            maintain(node);
        }

        private void maintain(int node) {
            tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
        }

        private Node merge(Node node1, Node node2) {
            int[] cnt = node1.cnt.clone();
            for (int i = 0; i < k; i++) {
                cnt[node1.p * i % k] += node2.cnt[i];
            }
            return new Node(node1.p * node2.p % k, cnt);
        }

        public void update(int index, int value) {
            update(1, 0, n - 1, index, value);
        }

        private void update(int node, int left, int right, int index, int value) {
            if (left == right) {
                tree[node] = new Node(value, k);
                return;
            }
            int mid = left + ((right - left) >> 1);
            if (index <= mid) {
                update(node * 2, left, mid, index, value);
            } else {
                update(node * 2 + 1, mid + 1, right, index, value);
            }
            maintain(node);
        }

        public int query(int queryLeft, int queryRight, int x) {
            System.out.println(x);
            return query(1, 0, n - 1, queryLeft, queryRight).cnt[x];
        }

        private Node query(int node, int left, int right, int queryLeft, int queryRight) {
            if (queryLeft <= left && right <= queryRight) {
                return tree[node];
            }
            int mid = left + ((right - left) >> 1);
            if (queryRight <= mid) {
                return query(node * 2, left, mid, queryLeft, queryRight);
            }
            if (queryLeft > mid) {
                return query(node * 2 + 1, mid + 1, right, queryLeft, queryRight);
            }
            Node leftRes = query(node * 2, left, mid, queryLeft, queryRight);
            Node rightRes = query(node * 2 + 1, mid + 1, right, queryLeft, queryRight);
            return merge(leftRes, rightRes);
        }
    }

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        SegmentTree segmentTree = new SegmentTree(nums, k);
        int n = nums.length, m = queries.length;
        int[] res = new int[m];
        for (int i = 0; i < m; i++) {
            int index = queries[i][0], value = queries[i][1], start = queries[i][2], x = queries[i][3];
            segmentTree.update(index, value);
            res[i] = segmentTree.query(start, n - 1, x);
        }
        return res;
    }

}

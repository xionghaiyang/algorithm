package com.sean.leetcode.LeetCode3721;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author xionghaiyang
 * @Date 2026-02-11 07:23
 * @Description https://leetcode.cn/problems/longest-balanced-subarray-ii
 * 3721. 最长平衡子数组 II
 * 给你一个整数数组 nums。
 * 如果子数组中 不同偶数 的数量等于 不同奇数 的数量，则称该 子数组 是 平衡的 。
 * 返回 最长 平衡子数组的长度。
 * 子数组 是数组中连续且 非空 的一段元素序列。
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^5
 */
public class Solution {

    public class Node {
        int min;
        int max;
        int todo;
    }

    public class LazySegmentTree {
        private int n;
        private Node[] tree;

        public LazySegmentTree(int n) {
            this.n = n;
            tree = new Node[n * 4];
            Arrays.setAll(tree, e -> new Node());
        }

        //用val更新[queryLeft,queryRight]中的每个sum[i]
        //0 <= queryLeft <= queryRight <= n-1
        public void update(int queryLeft, int queryRight, int val) {
            update(1, 0, n - 1, queryLeft, queryRight, val);
        }

        public void update(int index, int left, int right, int queryLeft, int queryRight, int val) {
            if (queryLeft <= left && right <= queryRight) {
                apply(index, val);
                return;
            }
            spread(index);
            int mid = left + ((right - left) >> 1);
            if (queryLeft <= mid) {
                update(index << 1, left, mid, queryLeft, queryRight, val);
            }
            if (queryRight > mid) {
                update((index << 1) | 1, mid + 1, right, queryLeft, queryRight, val);
            }
            maintain(index);
        }

        //把懒标记作用到node子树
        private void apply(int index, int todo) {
            Node cur = tree[index];
            cur.min += todo;
            cur.max += todo;
            cur.todo += todo;
        }

        //把当前节点的懒标记下传给左右儿子
        private void spread(int index) {
            int todo = tree[index].todo;
            if (todo == 0) {
                return;
            }
            apply(index << 1, todo);
            apply((index << 1) | 1, todo);
            tree[index].todo = 0;
        }

        //合并左右儿子的val到当前节点的val
        private void maintain(int index) {
            tree[index].min = Math.min(tree[index << 1].min, tree[(index << 1) | 1].min);
            tree[index].max = Math.max(tree[index << 1].max, tree[(index << 1) | 1].max);
        }

        public int findFirst(int queryLeft, int queryRight, int target) {
            return findFirst(1, 0, n - 1, queryLeft, queryRight, target);
        }

        private int findFirst(int index, int left, int right, int queryLeft, int queryRight, int target) {
            if (left > queryRight || right < queryLeft || target < tree[index].min || target > tree[index].max) {
                return -1;
            }
            if (left == right) {
                return left;
            }
            spread(index);
            int mid = left + ((right - left) >> 1);
            int node = findFirst(index << 1, left, mid, queryLeft, queryRight, target);
            if (node < 0) {
                node = findFirst((index << 1) + 1, mid + 1, right, queryLeft, queryRight, target);
            }
            return node;
        }
    }

    public int longestBalanced(int[] nums) {
        int n = nums.length;
        LazySegmentTree lazySegmentTree = new LazySegmentTree(n + 1);
        Map<Integer, Integer> last = new HashMap<>();
        int res = 0, curSum = 0;
        for (int i = 1; i <= n; i++) {
            int x = nums[i - 1];
            int v = x % 2 > 0 ? 1 : -1;
            Integer j = last.get(x);
            if (j == null) {
                curSum += v;
                lazySegmentTree.update(i, n, v);
            } else {
                lazySegmentTree.update(j, i - 1, -v);
            }
            last.put(x, i);
            int l = lazySegmentTree.findFirst(0, i - 1 - res, curSum);
            if (l >= 0) {
                res = i - l;
            }
        }
        return res;
    }

}

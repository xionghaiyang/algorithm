package com.sean.leetcode.LeetCode3479;

/**
 * @Author xionghaiyang
 * @Date 2025-08-06 06:21
 * @Description https://leetcode.cn/problems/fruits-into-baskets-iii
 * 3479. 水果成篮 III
 * 给你两个长度为 n 的整数数组，fruits 和 baskets，其中 fruits[i] 表示第 i 种水果的 数量，baskets[j] 表示第 j 个篮子的 容量。
 * 你需要对 fruits 数组从左到右按照以下规则放置水果：
 * 每种水果必须放入第一个 容量大于等于 该水果数量的 最左侧可用篮子 中。
 * 每个篮子只能装 一种 水果。
 * 如果一种水果 无法放入 任何篮子，它将保持 未放置。
 * 返回所有可能分配完成后，剩余未放置的水果种类的数量。
 * n == fruits.length == baskets.length
 * 1 <= n <= 10^5
 * 1 <= fruits[i], baskets[i] <= 10^9
 */
public class Solution {

    public class SegmentTree {
        private int n;
        private int[] tree;

        public SegmentTree(int[] nums) {
            n = nums.length;
            tree = new int[n * 4];
            build(nums, 0, n - 1, 0);
        }

        private void build(int[] nums, int left, int right, int index) {
            if (left == right) {
                tree[index] = nums[left];
                return;
            }
            int mid = left + ((right - left) >> 1);
            build(nums, left, mid, index * 2 + 1);
            build(nums, mid + 1, right, index * 2 + 2);
            tree[index] = Math.max(tree[index * 2 + 1], tree[index * 2 + 2]);
        }

        public int findFirst(int target) {
            return findFirst(target, 0, n - 1, 0);
        }

        public void update(int rangeIndex) {
            update(rangeIndex, 0, n - 1, 0);
        }

        private int findFirst(int target, int left, int right, int index) {
            if (tree[index] < target) {
                return -1;
            }
            if (left == right) {
                return left;
            }
            int mid = left + ((right - left) >> 1);
            if (tree[index * 2 + 1] >= target) {
                int res1 = findFirst(target, left, mid, index * 2 + 1);
                if (res1 >= 0) {
                    return res1;
                }
            }
            if (tree[index * 2 + 2] >= target) {
                int res2 = findFirst(target, mid + 1, right, index * 2 + 2);
                if (res2 >= 0) {
                    return res2;
                }
            }
            return -1;
        }

        private void update(int rangeIndex, int left, int right, int index) {
            if (left == right) {
                tree[index] = 0;
                return;
            }
            int mid = left + ((right - left) >> 1);
            if (rangeIndex <= mid) {
                update(rangeIndex, left, mid, index * 2 + 1);
            } else {
                update(rangeIndex, mid + 1, right, index * 2 + 2);
            }
            tree[index] = Math.max(tree[index * 2 + 1], tree[index * 2 + 2]);
        }
    }

    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        int n = fruits.length;
        SegmentTree st = new SegmentTree(baskets);
        int res = 0;
        for (int i = 0; i < n; i++) {
            int index = st.findFirst(fruits[i]);
            if (index >= 0) {
                st.update(index);
            } else {
                res++;
            }
        }
        return res;
    }

}

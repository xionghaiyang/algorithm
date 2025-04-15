package com.sean.leetcode.LeetCode2179;

/**
 * @Author xionghaiyang
 * @Date 2025-04-15 08:49
 * @Description https://leetcode.cn/problems/count-good-triplets-in-an-array
 * 2179. 统计数组中好三元组数目
 * 给你两个下标从 0 开始且长度为 n 的整数数组 nums1 和 nums2 ，两者都是 [0, 1, ..., n - 1] 的 排列 。
 * 好三元组 指的是 3 个 互不相同 的值，且它们在数组 nums1 和 nums2 中出现顺序保持一致。
 * 换句话说，如果我们将 pos1v 记为值 v 在 nums1 中出现的位置，pos2v 为值 v 在 nums2 中的位置，
 * 那么一个好三元组定义为 0 <= x, y, z <= n - 1 ，且 pos1x < pos1y < pos1z 和 pos2x < pos2y < pos2z 都成立的 (x, y, z) 。
 * 请你返回好三元组的 总数目 。
 * n == nums1.length == nums2.length
 * 3 <= n <= 10^5
 * 0 <= nums1[i], nums2[i] <= n - 1
 * nums1 和 nums2 是 [0, 1, ..., n - 1] 的排列。
 */
public class Solution {

    public long goodTriplets(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] index = new int[n];
        for (int i = 0; i < n; i++) {
            index[nums2[i]] = i;
        }
        int[] cnt = new int[n];
        long res = 0;
        for (int j = 1; j < n; j++) {
            int y = index[nums1[j]];
            for (int i = 0; i < j; i++) {
                int x = index[nums1[i]];
                if (x < y) {
                    cnt[j]++;
                    res += cnt[i];
                }
            }
        }
        return res;
    }

    public class Tree {
        private int[] tree;

        public Tree(int n) {
            //使用下标1到n
            tree = new int[n + 1];
        }

        // a[index]增加value
        // 1 <= index <= n
        public void add(int index, int value) {
            for (; index < tree.length; index += index & -index) {
                tree[index] += value;
            }
        }

        //求前缀和 a[1]+...+a[index]
        // 1 <= index <= n
        public int query(int index) {
            int res = 0;
            for (; index > 0; index &= index - 1) {
                res += tree[index];
            }
            return res;
        }
    }

    public long goodTriplets1(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] index = new int[n];
        for (int i = 0; i < n; i++) {
            index[nums1[i]] = i;
        }
        Tree tree = new Tree(n);
        long res = 0;
        for (int x = 0; x < n - 1; x++) {
            int y = index[nums2[x]];
            //设x左侧有left个比y小
            int left = tree.query(y);
            //那么左侧有x-left个比y大,而比y大的总共有(n-1-y)个，所以右侧有(n-1-y)-(x-left)个比y大
            int right = (n - 1 - y) - (x - left);
            res += (long) left * right;
            tree.add(y + 1, 1);
        }
        return res;
    }

}

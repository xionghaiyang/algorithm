package com.sean.leetcode.LeetCode315;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2026-04-05 20:16
 * @Description https://leetcode.cn/problems/count-of-smaller-numbers-after-self
 * 315. 计算右侧小于当前元素的个数
 * 给你一个整数数组 nums ，按要求返回一个新数组 counts 。
 * 数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 */
public class Solution {

    public class FenwickTree {
        private int n;
        private int[] tree;

        public FenwickTree(int n) {
            this.n = n;
            tree = new int[n];
        }

        //单点更新：将i这个位置，+delta
        public void update(int i, int delta) {
            while (i < n) {
                tree[i] += delta;
                i += lowbit(i);
            }
        }

        //区间查询：查询小于等于i的元素个数
        public int query(int i) {
            int res = 0;
            while (i > 0) {
                res += tree[i];
                i -= lowbit(i);
            }
            return res;
        }

        private int lowbit(int x) {
            return x & (-x);
        }
    }

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int n = nums.length;
        if (n == 0) {
            return res;
        }
        Set<Integer> set = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            set.add(nums[i]);
        }
        Map<Integer, Integer> map = new HashMap<>();
        int rank = 1;
        for (int num : set) {
            map.put(num, rank++);
        }
        FenwickTree fenwickTree = new FenwickTree(set.size() + 1);
        for (int i = n - 1; i >= 0; i--) {
            rank = map.get(nums[i]);
            fenwickTree.update(rank, 1);
            res.add(fenwickTree.query(rank - 1));
        }
        Collections.reverse(res);
        return res;
    }

}

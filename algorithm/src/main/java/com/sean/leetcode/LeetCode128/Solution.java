package com.sean.leetcode.LeetCode128;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2025-05-27 18:28
 * @Description https://leetcode.cn/problems/longest-consecutive-sequence
 * 128. 最长连续序列
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 * 0 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 */
public class Solution {

    public class UnionFind {
        Map<Integer, Integer> parents;
        Map<Integer, Integer> size;
        int maxSize;

        public UnionFind(int[] nums) {
            parents = new HashMap<>();
            size = new HashMap<>();
            for (int num : nums) {
                parents.put(num, num);
                size.put(num, 1);
            }
            maxSize = 1;
        }

        public void union(int x, int y) {
            if (!parents.containsKey(y)) {
                return;
            }
            int fx = find(x);
            int fy = find(y);
            if (fx == fy) {
                return;
            }
            int sx = size.get(fx);
            int sy = size.get(fy);
            if (sx < sy) {
                parents.put(fx, fy);
                size.put(fy, sx + sy);
            } else {
                parents.put(fy, fx);
                size.put(fx, sx + sy);
            }
            maxSize = Math.max(maxSize, sx + sy);
        }

        public int find(int x) {
            List<Integer> list = new ArrayList<>();
            while (parents.get(x) != x) {
                list.add(x);
                x = parents.get(x);
            }
            for (int num : list) {
                parents.put(num, x);
            }
            return x;
        }

        public int getMaxSize() {
            return maxSize;
        }
    }

    public int longestConsecutive(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }
        UnionFind unionFind = new UnionFind(nums);
        for (int num : nums) {
            unionFind.union(num, num + 1);
        }
        return unionFind.getMaxSize();
    }

}

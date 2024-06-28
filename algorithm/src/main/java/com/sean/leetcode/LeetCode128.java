package com.sean.leetcode;


import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: xionghaiyang
 * @Date: 2022/7/27 16:50
 */
public class LeetCode128 {

    /**
     * https://leetcode.cn/problems/longest-consecutive-sequence/
     * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
     * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
     */

    public int longestConsecutive(int[] nums) {
        if (nums == null) {
            return 0;
        }
        if (nums.length < 2) {
            return nums.length;
        }
        UnionFind unionFind = new UnionFind(nums);
        for (int num : nums) {
            unionFind.union(num, num + 1);
        }
        int res = 1;
        for (int num : nums) {
            res = Math.max(res, unionFind.find(num) - num + 1);
        }
        return res;
    }

    public class UnionFind {
        Map<Integer, Integer> parents;

        public UnionFind(int[] nums) {
            this.parents = new HashMap<>();
            for (int num : nums) {
                parents.put(num, num);
            }
        }

        public int find(int x) {
            if (parents.get(x) == x) {
                return x;
            }
            parents.put(x, find(parents.get(x)));
            return parents.get(x);
        }

        public void union(int x, int y) {
            if (parents.containsKey(y)) {
                parents.put(x, y);
            }
        }

    }

}

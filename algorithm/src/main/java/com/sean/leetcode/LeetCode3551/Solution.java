package com.sean.leetcode.LeetCode3551;

import java.util.Arrays;

/**
 * @Author: xionghaiyang
 * @Date: 2026-10-17 06:54
 * @Description: https://leetcode.cn/problems/minimum-swaps-to-sort-by-digit-sum
 * 3551. 数位和排序需要的最小交换次数
 * 给你一个由 互不相同 的正整数组成的数组 nums，需要根据每个数字的数位和（即每一位数字相加求和）按 升序 对数组进行排序。
 * 如果两个数字的数位和相等，则较小的数字排在前面。
 * 返回将 nums 排列为上述排序顺序所需的 最小 交换次数。
 * 一次 交换 定义为交换数组中两个不同位置的值。
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * nums 由 互不相同 的正整数组成。
 */
public class Solution {

    public class UnionFind {
        private int[] parent;
        private int[] size;
        private int[] help;
        private int set;

        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            help = new int[n];
            set = n;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int find(int x) {
            int index = 0;
            while (x != parent[x]) {
                help[index++] = x;
                x = parent[x];
            }
            for (index--; index >= 0; index--) {
                parent[help[index]] = x;
            }
            return x;
        }

        public void union(int x, int y) {
            int fx = find(x);
            int fy = find(y);
            if (fx == fy) {
                return;
            }
            if (size[fx] >= size[fy]) {
                size[fx] += size[fy];
                parent[fy] = fx;
            } else {
                size[fy] += size[fx];
                parent[fx] = fy;
            }
            set--;
        }

        public int getSet() {
            return set;
        }
    }

    public int minSwaps(int[] nums) {
        int n = nums.length;
        int[][] arr = new int[n][3];
        for (int i = 0; i < n; i++) {
            int s = 0;
            for (int x = nums[i]; x > 0; x /= 10) {
                s += x % 10;
            }
            arr[i][0] = s;
            arr[i][1] = nums[i];
            arr[i][2] = i;
        }
        Arrays.sort(arr, (p, q) -> p[0] != q[0] ? p[0] - q[0] : p[1] - q[1]);
        UnionFind unionFind = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            unionFind.union(i, arr[i][2]);
        }
        return n - unionFind.getSet();
    }

}

package com.sean.leetcode.LeetCode1569;

/**
 * @Author: xionghaiyang
 * @Date: 2023-12-29 14:10
 * @Description: https://leetcode.cn/problems/number-of-ways-to-reorder-array-to-get-same-bst
 * 1569. 将子数组重新排序得到同一个二叉搜索树的方案数
 * 给你一个数组 nums 表示 1 到 n 的一个排列。
 * 我们按照元素在 nums 中的顺序依次插入一个初始为空的二叉搜索树（BST）。
 * 请你统计将 nums 重新排序后，统计满足如下条件的方案数：重排后得到的二叉搜索树与 nums 原本数字顺序得到的二叉搜索树相同。
 * 比方说，给你 nums = [2,1,3]，我们得到一棵 2 为根，1 为左孩子，3 为右孩子的树。
 * 数组 [2,3,1] 也能得到相同的 BST，但 [3,2,1] 会得到一棵不同的 BST 。
 * 请你返回重排 nums 后，与原数组 nums 得到相同二叉搜索树的方案数。
 * 由于答案可能会很大，请将结果对 10^9 + 7 取余数。
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= nums.length
 * nums 中所有数 互不相同 。
 */
public class Solution {

    private static final int MOD = 1_000_000_007;

    public int numOfWays(int[] nums) {
        int n = nums.length;
        int[] prev = new int[n + 2];
        int[] next = new int[n + 2];
        int[] size = new int[n + 2];
        for (int i = 0; i < n + 2; i++) {
            prev[i] = i - 1;
            next[i] = i + 1;
        }
        long fac = 1, prod = 1;
        for (int i = n - 1; i >= 0; i--) {
            fac = fac * (i + 1) % MOD;
            int x = nums[i];
            size[x] = 1;
            int left = prev[x];
            if (size[left] > 0) {
                size[x] += size[left];
                int pl = prev[left];
                prev[x] = pl;
                next[pl] = x;
            }
            int right = next[x];
            if (size[right] > 0) {
                size[x] += size[right];
                int nr = next[right];
                prev[nr] = x;
                next[x] = nr;
            }
            prod = prod * size[x] % MOD;
        }
        return (int) ((fac * pow(prod, MOD - 2) - 1 + MOD) % MOD);
    }

    private long pow(long x, int n) {
        long res = 1;
        while (n > 0) {
            if ((n & 1) != 0) {
                res = res * x % MOD;
            }
            x = x * x % MOD;
            n >>= 1;
        }
        return res;
    }

}

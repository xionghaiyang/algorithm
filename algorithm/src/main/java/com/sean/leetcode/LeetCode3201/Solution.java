package com.sean.leetcode.LeetCode3201;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2025-07-16 06:10
 * @Description https://leetcode.cn/problems/find-the-maximum-length-of-valid-subsequence-i
 * 3201. 找出有效子序列的最大长度 I
 * 给你一个整数数组 nums。
 * nums 的子序列 sub 的长度为 x ，如果其满足以下条件，则称其为 有效子序列：
 * (sub[0] + sub[1]) % 2 == (sub[1] + sub[2]) % 2 == ... == (sub[x - 2] + sub[x - 1]) % 2
 * 返回 nums 的 最长的有效子序列 的长度。
 * 一个 子序列 指的是从原数组中删除一些元素（也可以不删除任何元素），剩余元素保持原来顺序组成的新数组。
 * 2 <= nums.length <= 2 * 10^5
 * 1 <= nums[i] <= 10^7
 */
public class Solution {

    public int maximumLength(int[] nums) {
        int n = nums.length;
        int[][] nextIndex = new int[n][2];
        for (int i = n - 1, oddIndex = -1, evenIndex = -1; i >= 0; i--) {
            nextIndex[i] = new int[]{oddIndex, evenIndex};
            if (nums[i] % 2 == 0) {
                evenIndex = i;
            } else {
                oddIndex = i;
            }
        }
        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        int res = 0;
        res = Math.max(res, Math.max(process(nums, nextIndex, 0, 0), process(nums, nextIndex, 1, 0)));
        if (nextIndex[0][nums[0] % 2 == 0 ? 0 : 1] != -1) {
            res = Math.max(res, Math.max(process(nums, nextIndex, 0, nextIndex[0][nums[0] % 2 == 0 ? 0 : 1]), process(nums, nextIndex, 1, nextIndex[0][nums[0] % 2 == 0 ? 0 : 1])));
        }
        return res;
    }

    private int process(int[] nums, int[][] index, int flag, int i) {
        int res = 1;
        int x = nums[i] % 2;
        if (index[i][flag == 0 ? 1 - x : x] != -1) {
            res += process(nums, index, flag, index[i][flag == 0 ? 1 - x : x]);
        }
        return res;
    }

    public int maximumLength1(int[] nums) {
        int res = 0;
        int[][] f = new int[2][2];
        for (int x : nums) {
            x %= 2;
            for (int y = 0; y < 2; y++) {
                f[y][x] = f[x][y] + 1;
                res = Math.max(res, f[y][x]);
            }
        }
        return res;
    }

    public int maximumLength2(int[] nums) {
        int res = 0;
        for (int m = 0; m < 2; m++) {
            int[] f = new int[2];
            for (int x : nums) {
                x %= 2;
                f[x] = f[(m - x + 2) % 2] + 1;
                res = Math.max(res, f[x]);
            }
        }
        return res;
    }

}

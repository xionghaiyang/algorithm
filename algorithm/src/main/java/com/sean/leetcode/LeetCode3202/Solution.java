package com.sean.leetcode.LeetCode3202;

/**
 * @Author xionghaiyang
 * @Date 2025-07-17 05:37
 * @Description https://leetcode.cn/problems/find-the-maximum-length-of-valid-subsequence-ii
 * 3202. 找出有效子序列的最大长度 II
 * 给你一个整数数组 nums 和一个 正 整数 k 。
 * nums 的一个 子序列 sub 的长度为 x ，如果其满足以下条件，则称其为 有效子序列 ：
 * (sub[0] + sub[1]) % k == (sub[1] + sub[2]) % k == ... == (sub[x - 2] + sub[x - 1]) % k
 * 返回 nums 的 最长有效子序列 的长度。
 * 2 <= nums.length <= 10^3
 * 1 <= nums[i] <= 10^7
 * 1 <= k <= 10^3
 */
public class Solution {

    public int maximumLength(int[] nums, int k) {
        int res = 0;
        int[][] f = new int[k][k];
        for (int x : nums) {
            x = x % k;
            for (int y = 0; y < k; y++) {
                f[y][x] = f[x][y] + 1;
                res = Math.max(res,f[y][x]);
            }
        }
        return res;
    }

}

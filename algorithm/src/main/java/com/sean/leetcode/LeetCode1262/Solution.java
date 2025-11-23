package com.sean.leetcode.LeetCode1262;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-06-19 08:14
 * @Description: https://leetcode.cn/problems/greatest-sum-divisible-by-three
 * 1262. 可被三整除的最大和
 * 给你一个整数数组 nums，请你找出并返回能被三整除的元素最大和。
 * 1 <= nums.length <= 4 * 10^4
 * 1 <= nums[i] <= 10^4
 */
public class Solution {

    public int maxSumDivThree(int[] nums) {
        int n = nums.length;
        int[][] f = new int[2][3];
        f[0][1] = f[0][2] = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                f[(i + 1) % 2][j] = Math.max(f[i % 2][j], f[i % 2][(j + nums[i]) % 3] + nums[i]);
            }
        }
        return f[n % 2][0];
    }

}

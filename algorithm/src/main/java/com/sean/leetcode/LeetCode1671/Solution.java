package com.sean.leetcode.LeetCode1671;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-22 11:25
 * @Description: https://leetcode.cn/problems/minimum-number-of-removals-to-make-mountain-array/
 * 1671. 得到山形数组的最少删除次数
 * 我们定义 arr 是 山形数组 当且仅当它满足：
 * arr.length >= 3
 * 存在某个下标 i （从 0 开始） 满足 0 < i < arr.length - 1 且：
 * arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
 * arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
 * 给你整数数组 nums​ ，请你返回将 nums 变成 山形状数组 的​ 最少 删除次数。
 */
public class Solution {

    public int minimumMountainRemovals(int[] nums) {
        int n = nums.length;
        int[] pre = getLISArray(nums);
        int[] suf = reverse(getLISArray(reverse(nums)));
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (pre[i] > 1 && suf[i] > 1) {
                res = Math.max(res, pre[i] + suf[i] - 1);
            }
        }
        return n - res;
    }

    private int[] getLISArray(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return dp;
    }

    private int[] reverse(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = nums[n - 1 - i];
        }
        return res;
    }

}

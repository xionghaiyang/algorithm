package com.sean.leetcode.LeetCode1658;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-01-09 09:44
 * @Description: https://leetcode.cn/problems/minimum-operations-to-reduce-x-to-zero/
 * 1658. 将 x 减到 0 的最小操作数
 * 给你一个整数数组 nums 和一个整数 x 。
 * 每一次操作时，你应当移除数组 nums 最左边或最右边的元素，然后从 x 中减去该元素的值。
 * 请注意，需要 修改 数组以供接下来的操作使用。
 * 如果可以将 x 恰好 减到 0 ，返回 最小操作数 ；否则，返回 -1 。
 */
public class Solution {

    public int minOperations(int[] nums, int x) {
        if (nums == null || nums.length == 0) {
            return x == 0 ? 0 : -1;
        }
        int n = nums.length;
        int sum = Arrays.stream(nums).sum();
        if (sum < x) {
            return -1;
        }
        int right = 0;
        int lsum = 0, rsum = sum;
        int res = n + 1;
        for (int left = -1; left < n; left++) {
            if (left != -1) {
                lsum += nums[left];
            }
            while (right < n && lsum + rsum > x) {
                rsum -= nums[right];
                right++;
            }
            if (lsum + rsum == x) {
                res = Math.min(res, (left + 1) + (n - right));
            }
        }
        return res > n ? -1 : res;
    }

}

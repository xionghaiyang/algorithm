package com.sean.leetcode.LeetCode1658;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-01-09 09:44
 * @Description: https://leetcode.cn/problems/minimum-operations-to-reduce-x-to-zero
 * 1658. 将 x 减到 0 的最小操作数
 * 给你一个整数数组 nums 和一个整数 x 。
 * 每一次操作时，你应当移除数组 nums 最左边或最右边的元素，然后从 x 中减去该元素的值。
 * 请注意，需要 修改 数组以供接下来的操作使用。
 * 如果可以将 x 恰好 减到 0 ，返回 最小操作数 ；否则，返回 -1 。
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^4
 * 1 <= x <= 10^9
 */
public class Solution {

    public int minOperations(int[] nums, int x) {
        int n = nums.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }
        if (sum < x) {
            return -1;
        }
        int res = n + 1;
        for (int left = -1, right = 0, leftSum = 0, rightSum = sum; left < n; left++) {
            if (left >= 0) {
                leftSum += nums[left];
            }
            while (right < n && leftSum + rightSum > x) {
                rightSum -= nums[right++];
            }
            if (leftSum + rightSum == x) {
                res = Math.min(res, (left + 1) + (n - right));
            }
        }
        return res > n ? -1 : res;
    }

}

package com.sean.leetcode.LeetCode2834;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-03-08 14:16
 * @Description: https://leetcode.cn/problems/find-the-minimum-possible-sum-of-a-beautiful-array/
 * 2834. 找出美丽数组的最小和
 * 给你两个正整数：n 和 target 。
 * 如果数组 nums 满足下述条件，则称其为 美丽数组 。
 * nums.length == n.
 * nums 由两两互不相同的正整数组成。
 * 在范围 [0, n-1] 内，不存在 两个 不同 下标 i 和 j ，使得 nums[i] + nums[j] == target 。
 * 返回符合条件的美丽数组所可能具备的 最小 和，并对结果进行取模 10^9 + 7。
 */
public class Solution {

    public int minimumPossibleSum(int n, int target) {
        int mod = 1_000_000_007;
        int m = target / 2;
        if (n <= m) {
            return (int) ((long) (1 + n) * n / 2 % mod);
        }
        return (int) (((long) (1 + m) * m / 2 + ((long) target + (target + (n - m) - 1)) * (n - m) / 2) % mod);
    }

}

package com.sean.leetcode.LeetCode2518;

/**
 * @Author: xionghaiyang
 * @Date: 2026-10-14 05:44
 * @Description: https://leetcode.cn/problems/number-of-great-partitions
 * 2518. 好分区的数目
 * 给你一个正整数数组 nums 和一个整数 k 。
 * 分区 的定义是：将数组划分成两个有序的 组 ，并满足每个元素 恰好 存在于 某一个 组中。
 * 如果分区中每个组的元素和都大于等于 k ，则认为分区是一个好分区。
 * 返回 不同 的好分区的数目。
 * 由于答案可能很大，请返回对 10^9 + 7 取余 后的结果。
 * 如果在两个分区中，存在某个元素 nums[i] 被分在不同的组中，则认为这两个分区不同。
 * 1 <= nums.length, k <= 1000
 * 1 <= nums[i] <= 10^9
 */
public class Solution {

    private static final int MOD = 1_000_000_007;

    public int countPartitions(int[] nums, int k) {
        long sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum < k * 2) {
            return 0;
        }
        int res = 1;
        int[] f = new int[k];
        f[0] = 1;
        for (int num : nums) {
            res = res * 2 % MOD;
            for (int j = k - 1; j >= num; j--) {
                f[j] = (f[j] + f[j - num]) % MOD;
            }
        }
        for (int num : f) {
            res = (res - num * 2 % MOD + MOD) % MOD;
        }
        return res;
    }

}

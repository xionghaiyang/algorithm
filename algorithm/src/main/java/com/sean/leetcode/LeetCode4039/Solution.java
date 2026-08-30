package com.sean.leetcode.LeetCode4039;

/**
 * @Author: xionghaiyang
 * @Date: 2026-08-30 17:35
 * @Description: https://leetcode.cn/problems/sum-of-decoded-numbers
 * 4039. 解码值之和
 * 给你一个整数数组 nums。
 * 每个 nums[i] 都是一个 编码后的 整数，表示两个正整数 xi 和 yi。要解码 nums[i]，定义：
 * widthi = nums[i] % 10。
 * di = floor(nums[i] / 10)。
 * xi 为由 di 的十进制表示中前 widthi 位数字组成的整数。
 * yi 为由 di 的十进制表示中剩余所有数字组成的整数。
 * 保证 di 的十进制表示包含的数字位数大于 widthi。
 * 因此，xi 和 yi 都至少包含一位数字。
 * nums[i] 的 解码值 为 xi^yi。
 * 返回 nums 中所有元素的解码值之和，并对 10^9 + 7 取模。
 * floor() 函数返回除法结果的整数部分。
 * 1 <= nums.length <= 10^5
 * 100 < nums[i] < 10^15
 * 1 <= widthi <= 9
 * 1 <= xi, yi < 10^9
 * 用于构成 xi 和 yi 的数字序列均不包含前导零。
 * 保证 nums 中的每个元素都是有效的编码整数。
 */
public class Solution {

    private static final int MOD = 1_000_000_007;

    public int sumDecoded(long[] nums) {
        long res = 0;
        for (long num : nums) {
            long d = num / 10;
            int length = 0;
            for (long v = d; v > 0; v /= 10) {
                length++;
            }
            long pow10 = (long) Math.pow(10, length - num % 10);
            res += pow(d / pow10, d % pow10);
        }
        return (int) (res % MOD);
    }

    private long pow(long x, long n) {
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

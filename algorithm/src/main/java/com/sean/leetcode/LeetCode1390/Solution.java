package com.sean.leetcode.LeetCode1390;

/**
 * @Author xionghaiyang
 * @Date 2026-01-04 09:19
 * @Description https://leetcode.cn/problems/four-divisors
 * 1390. 四因数
 * 给你一个整数数组 nums，请你返回该数组中恰有四个因数的这些整数的各因数之和。
 * 如果数组中不存在满足题意的整数，则返回 0 。
 * 1 <= nums.length <= 10^4
 * 1 <= nums[i] <= 10^5
 */
public class Solution {

    private static final int MAX = 100_001;
    private static final int[] divisorNum = new int[MAX];
    private static final int[] divisorSum = new int[MAX];
    private static boolean initialized = false;

    public Solution() {
        if (initialized) {
            return;
        }
        initialized = true;
        for (int i = 1; i < MAX; i++) {
            for (int j = i; j < MAX; j += i) {
                divisorNum[j]++;
                divisorSum[j] += i;
            }
        }
    }

    public int sumFourDivisors(int[] nums) {
        int res = 0;
        for (int num : nums) {
            if (divisorNum[num] == 4) {
                res += divisorSum[num];
            }
        }
        return res;
    }

}

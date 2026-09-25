package com.sean.leetcode.LeetCode3984;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author: xionghaiyang
 * @Date: 2026-09-25 11:22
 * @Description: https://leetcode.cn/problems/divisible-game
 * 3984. 可整除游戏
 * 给你一个长度为 n 的整数数组 nums。
 * Alice 和 Bob 正在玩一个游戏。
 * Alice 会选择：
 * 一个整数 k，满足 k > 1。
 * 两个整数 l 和 r，满足 0 <= l <= r < n。
 * 初始时，Alice 和 Bob 的分数都为 0。
 * 对于区间 [l, r]（包含两端）中的每个下标 i：
 * 如果 nums[i] 能被 k 整除，则 Alice 的分数 增加 nums[i]。
 * 否则，Bob 的分数 增加 nums[i]。
 * 分数差 定义为 Alice 的分数 减去 Bob 的分数。
 * Alice 希望 最大化 分数差。
 * 如果有多个 k 可以达到 最大 分数差，她会选择其中 最小 的 k。
 * 返回 最大 分数差与所选 k 的 乘积 。
 * 由于结果可能很大，请返回其对 10^9 + 7 取余数后的结果。
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 10^6
 */
public class Solution {

    private static final int MOD = 1_000_000_007;

    public int divisibleGame(int[] nums) {
        //收集所有质因子
        List<Integer> allPrimeDivisors = new ArrayList<>();
        for (int x : nums) {
            for (int p = 2; p * p <= x; p++) {
                if (x % p == 0) {
                    allPrimeDivisors.add(p);
                    do {
                        x /= p;
                    } while (x % p == 0);
                }
            }
            if (x > 1) {
                allPrimeDivisors.add(x);
            }
        }
        if (allPrimeDivisors.isEmpty()) {
            return MOD - 2;
        }
        Collections.sort(allPrimeDivisors);
        int maxDiff = Integer.MIN_VALUE, bestK = 0, preK = 0;
        for (int k : allPrimeDivisors) {
            if (k == preK) {
                continue;
            }
            int diff = maxSubArray(nums, k);
            if (diff > maxDiff) {
                maxDiff = diff;
                bestK = k;
            }
            preK = k;
        }
        return (int) ((long) maxDiff * bestK % MOD);
    }

    private int maxSubArray(int[] nums, int k) {
        int res = Integer.MIN_VALUE, f = 0;
        for (int x : nums) {
            f = Math.max(f, 0) + (x % k == 0 ? x : -x);
            res = Math.max(res, f);
        }
        return res;
    }

}

package com.sean.leetcode.LeetCode368;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2025-04-06 08:18
 * @Description https://leetcode.cn/problems/largest-divisible-subset
 * 368. 最大整除子集
 * 给你一个由 无重复 正整数组成的集合 nums ，请你找出并返回其中最大的整除子集 answer ，子集中每一元素对 (answer[i], answer[j]) 都应当满足：
 * answer[i] % answer[j] == 0 ，或
 * answer[j] % answer[i] == 0
 * 如果存在多个有效解子集，返回其中任何一个均可。
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 2 * 10^9
 * nums 中的所有整数 互不相同
 */
public class Solution {

    //如何从一个小的整除子集扩充成为更大的整除子集?
    //整除关系具有传递性,a|b,b|c => a|c
    //如果整数 a 是整除子集 S
    //如果整数a是整除子集S1的最小整数b的约数，a|b，那么可以将a添加到S1中得到一个更大的整除子集
    //如果整数c是整除子集S2的最大整数d的倍数(d|c),那么可以将c添加到S2中得到一个更大的整除子集
    //状态定义:dp[i]表示在输入数组nums升序排列的前提下，以nums[i]为最大整数的整除子集的大小（num[i]必须被选择）
    //状态转移方程：枚举j=0...i-1的所有整数nums[j],如果nums[j]能整除nums[i],说明nums[i]可以扩充在以nums[j]为最大整数的整除子集里成为一个更大的整除子集
    public List<Integer> largestDivisibleSubset(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int maxSize = 1;
        int maxVal = dp[0];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            if (dp[i] > maxSize) {
                maxSize = dp[i];
                maxVal = nums[i];
            }
        }
        List<Integer> res = new ArrayList<>();
        if (maxSize == 1) {
            res.add(nums[0]);
            return res;
        }
        for (int i = n - 1; i >= 0 && maxSize > 0; i--) {
            if (dp[i] == maxSize && maxVal % nums[i] == 0) {
                res.add(nums[i]);
                maxVal = nums[i];
                maxSize--;
            }
        }
        return res;
    }

}

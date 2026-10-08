package com.sean.leetcode.LeetCode2501;

import java.util.*;

/**
 * @Author: xionghaiyang
 * @Date: 2022-12-15 17:10
 * @Description: https://leetcode.cn/problems/longest-square-streak-in-an-array
 * 2501. 数组中最长的方波
 * 给你一个整数数组 nums 。
 * 如果 nums 的子序列满足下述条件，则认为该子序列是一个 方波 ：
 * 子序列的长度至少为 2 ，并且
 * 将子序列从小到大排序 之后 ，除第一个元素外，每个元素都是前一个元素的 平方 。
 * 返回 nums 中 最长方波 的长度，如果不存在 方波 则返回 -1 。
 * 子序列 也是一个数组，可以由另一个数组删除一些或不删除元素且不改变剩余元素的顺序得到。
 * 2 <= nums.length <= 10^5
 * 2 <= nums[i] <= 10^5
 */
public class Solution {

    public int longestSquareStreak(int[] nums) {
        Set<Long> set = new HashSet<>();
        for (long num : nums) {
            set.add(num);
        }
        int res = -1;
        for (long num : nums) {
            int len = 1;
            num *= num;
            while (set.contains(num)) {
                len++;
                num *= num;
            }
            if (len > 1 && len > res) {
                res = len;
            }
        }
        return res;
    }

    public int longestSquareStreak1(int[] nums) {
        int max = 0;
        for (int num : nums) {
            max = Math.max(max, num);
        }
        int[] dp = new int[max + 1];
        for (int num : nums) {
            dp[num] = 1;
        }
        int res = 0;
        for (int i = 0; i <= max; i++) {
            if ((long) i * i > max) {
                break;
            }
            int j = i * i;
            if (dp[i] == 0 || dp[j] == 0) {
                continue;
            }
            dp[j] = Math.max(dp[j], dp[i] + 1);
            res = Math.max(res, dp[j]);
        }
        return res > 1 ? res : -1;
    }

}

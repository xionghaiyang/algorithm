package com.sean.leetcode.LeetCode2501;

import java.util.*;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-15 17:10
 * @Description: https://leetcode.cn/problems/longest-square-streak-in-an-array/
 * 2501. 数组中最长的方波
 * 给你一个整数数组 nums 。如果 nums 的子序列满足下述条件，则认为该子序列是一个 方波 ：
 * 子序列的长度至少为 2 ，并且
 * 将子序列从小到大排序 之后 ，除第一个元素外，每个元素都是前一个元素的 平方 。
 * 返回 nums 中 最长方波 的长度，如果不存在 方波 则返回 -1 。
 * 子序列 也是一个数组，可以由另一个数组删除一些或不删除元素且不改变剩余元素的顺序得到。
 */
public class Solution {

    public int longestSquareStreak(int[] nums) {
        if (nums == null || nums.length < 2) {
            return -1;
        }
        int n = nums.length;
        Set<Long> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add((long) nums[i]);
        }
        int res = -1;
        for (Long num : set) {
            int cnt = 0;
            while (set.contains(num)) {
                num *= num;
                cnt++;
            }
            res = Math.max(res, cnt);
        }
        return res <= 1 ? -1 : res;
    }

}

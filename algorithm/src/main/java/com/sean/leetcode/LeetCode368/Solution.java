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

    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int[] f = new int[n];
        int[] from = new int[n];
        Arrays.fill(from, -1);
        int maxI = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0 && f[j] > f[i]) {
                    f[i] = f[j];
                    from[i] = j;
                }
            }
            f[i]++;
            if (f[i] > f[maxI]) {
                maxI = i;
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int i = maxI; i >= 0; i = from[i]) {
            res.add(nums[i]);
        }
        return res;
    }

}

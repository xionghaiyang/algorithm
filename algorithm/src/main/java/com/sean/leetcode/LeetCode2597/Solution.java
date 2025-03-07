package com.sean.leetcode.LeetCode2597;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xionghaiyang
 * @Date 2025-03-07 08:49
 * @Description https://leetcode.cn/problems/the-number-of-beautiful-subsets/
 * 2597. 美丽子集的数目
 * 给你一个由正整数组成的数组 nums 和一个 正 整数 k 。
 * 如果 nums 的子集中，任意两个整数的绝对差均不等于 k ，则认为该子数组是一个 美丽 子集。
 * 返回数组 nums 中 非空 且 美丽 的子集数目。
 * nums 的子集定义为：可以经由 nums 删除某些元素（也可能不删除）得到的一个数组。
 * 只有在删除元素时选择的索引不同的情况下，两个子集才会被视作是不同的子集。
 * 1 <= nums.length <= 18
 * 1 <= nums[i], k <= 1000
 */
public class Solution {

    private int res = 0;
    private Map<Integer, Integer> map = new HashMap<>();

    public int beautifulSubsets(int[] nums, int k) {
        process(nums, k, 0);
        return res - 1;
    }

    private void process(int[] nums, int k, int i) {
        if (i == nums.length) {
            res++;
            return;
        }
        process(nums, k, i + 1);
        if (map.getOrDefault(nums[i] - k, 0) == 0 && map.getOrDefault(nums[i] + k, 0) == 0) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            process(nums, k, i + 1);
            map.put(nums[i], map.getOrDefault(nums[i], 0) - 1);
        }
    }

    private int[] nums;
    private int k;
    private int n;
    private int ans = 0;

    public int beautifulSubsets1(int[] nums, int k) {
        this.nums = nums;
        n = nums.length;
        this.k = k;
        int[] cnt = new int[1001];
        dfs(cnt, 0);
        return ans - 1;
    }

    private void dfs(int[] cnt, int i) {
        if (i == n) {
            ans++;
            return;
        }
        dfs(cnt, i + 1);
        if ((nums[i] + k >= 1001 || cnt[nums[i] + k] == 0) && (nums[i] - k < 0 || cnt[nums[i] - k] == 0)) {
            cnt[nums[i]]++;
            dfs(cnt, i + 1);
            cnt[nums[i]]--;
        }
    }

}

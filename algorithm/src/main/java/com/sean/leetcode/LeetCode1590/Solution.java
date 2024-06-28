package com.sean.leetcode.LeetCode1590;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xionghaiyang
 * @Date 2024-05-13 20:57
 * @Description https://leetcode.cn/problems/make-sum-divisible-by-p/
 * 1590. 使数组和能被 P 整除
 * 给你一个正整数数组 nums，请你移除 最短 子数组（可以为 空），使得剩余元素的 和 能被 p 整除。
 * 不允许 将整个数组都移除。
 * 请你返回你需要移除的最短子数组的长度，如果无法满足题目要求，返回 -1 。
 * 子数组 定义为原数组中连续的一组元素。
 */
public class Solution {

    public int minSubarray(int[] nums, int p) {
        int sum = 0;
        for (int num : nums) {
            sum = (sum + num) % p;
        }
        if (sum == 0) {
            return 0;
        }
        int q = sum;
        sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        int n = nums.length;
        int res = n;
        for (int i = 0; i < n; i++) {
            map.put(sum, i);
            sum = (sum + nums[i]) % p;
            int key = (sum - q + p) % p;
            if (map.containsKey(key)) {
                int prev = map.get(key);
                res = Math.min(res, i - prev + 1);
            }
        }
        return res == n ? -1 : res;
    }

}

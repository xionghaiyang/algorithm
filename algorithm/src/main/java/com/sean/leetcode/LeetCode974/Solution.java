package com.sean.leetcode.LeetCode974;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xionghaiyang
 * @Date 2024-05-08 20:35
 * @Description https://leetcode.cn/problems/subarray-sums-divisible-by-k/
 * 974. 和可被 K 整除的子数组
 * 给定一个整数数组 nums 和一个整数 k ，返回其中元素之和可被 k 整除的（连续、非空） 子数组 的数目。
 * 子数组 是数组的 连续 部分。
 */
public class Solution {

    public int subarraysDivByK(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int preSum = 0;
        int res = 0;
        for (int num : nums) {
            preSum += num;
            int key = (preSum % k + k) % k;
            res += map.getOrDefault(key, 0);
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
        return res;
    }

}

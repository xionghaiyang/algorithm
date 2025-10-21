package com.sean.leetcode.LeetCode3347;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Author xionghaiyang
 * @Date 2025-10-21 16:55
 * @Description https://leetcode.cn/problems/maximum-frequency-of-an-element-after-performing-operations-ii
 * 3347. 执行操作后元素的最高频率 II
 * 给你一个整数数组 nums 和两个整数 k 和 numOperations 。
 * 你必须对 nums 执行 操作  numOperations 次。
 * 每次操作中，你可以：
 * 选择一个下标 i ，它在之前的操作中 没有 被选择过。
 * 将 nums[i] 增加范围 [-k, k] 中的一个整数。
 * 在执行完所有操作以后，请你返回 nums 中出现 频率最高 元素的出现次数。
 * 一个元素 x 的 频率 指的是它在数组中出现的次数。
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 0 <= k <= 10^9
 * 0 <= numOperations <= nums.length
 */
public class Solution {

    public int maxFrequency(int[] nums, int k, int numOperations) {
        Map<Integer, Integer> cnt = new HashMap<>();
        Map<Integer, Integer> diff = new TreeMap<>();
        for (int num : nums) {
            cnt.put(num, cnt.getOrDefault(num, 0) + 1);
            diff.putIfAbsent(num, 0);
            diff.put(num - k, diff.getOrDefault(num - k, 0) + 1);
            diff.put(num + k + 1, diff.getOrDefault(num + k + 1, 0) - 1);
        }
        int res = 0, sum = 0;
        for (Map.Entry<Integer, Integer> entry : diff.entrySet()) {
            sum += entry.getValue();
            res = Math.max(res, Math.min(sum, cnt.getOrDefault(entry.getKey(), 0) + numOperations));
        }
        return res;
    }

}

package com.sean.leetcode.LeetCode3712;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xionghaiyang
 * @Date 2025-10-12 16:54
 * @Description https://leetcode.cn/problems/sum-of-elements-with-frequency-divisible-by-k
 * 3712. 出现次数能被 K 整除的元素总和
 * 给你一个整数数组 nums 和一个整数 k。
 * 请返回一个整数，表示 nums 中所有其 出现次数 能被 k 整除的元素的总和；如果没有这样的元素，则返回 0 。
 * 注意： 若某个元素在数组中的总出现次数能被 k 整除，则它在求和中会被计算 恰好 与其出现次数相同的次数。
 * 元素 x 的 出现次数 指它在数组中出现的次数。
 * 1 <= nums.length <= 100
 * 1 <= nums[i] <= 100
 * 1 <= k <= 100
 */
public class Solution {

    public int sumDivisibleByK(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int res = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int val = entry.getValue();
            if (val % k == 0) {
                res += entry.getKey() * val;
            }
        }
        return res;
    }

}

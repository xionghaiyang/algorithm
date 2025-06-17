package com.sean.leetcode.LeetCode3583;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xionghaiyang
 * @Date 2025-06-17 12:27
 * @Description https://leetcode.cn/problems/count-special-triplets/
 * 3583. 统计特殊三元组
 * 给你一个整数数组 nums。
 * 特殊三元组 定义为满足以下条件的下标三元组 (i, j, k)：
 * 0 <= i < j < k < n，其中 n = nums.length
 * nums[i] == nums[j] * 2
 * nums[k] == nums[j] * 2
 * 返回数组中 特殊三元组 的总数。
 * 由于答案可能非常大，请返回结果对 10^9 + 7 取余数后的值。
 * <= n == nums.length <= 10^5
 * 0 <= nums[i] <= 10^5
 */
public class Solution {

    public int specialTriplets(int[] nums) {
        final int MOD = 1_000_000_007;
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        int[] left = new int[n];
        int[] right = new int[n];
        for (int i = 0; i < n; i++) {
            if (map.containsKey(nums[i] * 2)) {
                left[i] = map.get(nums[i] * 2);
            }
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        map.clear();
        for (int i = n - 1; i >= 0; i--) {
            if (map.containsKey(nums[i] * 2)) {
                right[i] = map.get(nums[i] * 2);
            }
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        long res = 0;
        for (int i = 1; i <= n - 2; i++) {
            res = (res + (long) left[i] * right[i] % MOD) % MOD;
        }
        return (int) res;
    }

}

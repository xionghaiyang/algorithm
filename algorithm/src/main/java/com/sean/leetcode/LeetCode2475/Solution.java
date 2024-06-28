package com.sean.leetcode.LeetCode2475;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-06-13 09:29
 * @Description: https://leetcode.cn/problems/number-of-unequal-triplets-in-array/
 * 2475. 数组中不等三元组的数目
 * 给你一个下标从 0 开始的正整数数组 nums 。请你找出并统计满足下述条件的三元组 (i, j, k) 的数目：
 * 0 <= i < j < k < nums.length
 * nums[i]、nums[j] 和 nums[k] 两两不同 。
 * 换句话说：nums[i] != nums[j]、nums[i] != nums[k] 且 nums[j] != nums[k] 。
 * 返回满足上述条件三元组的数目。
 */
public class Solution {

    public int unequalTriplets1(int[] nums) {
        int n = nums.length;
        int res = 0;
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                if (nums[i] != nums[j]) {
                    for (int k = j + 1; k < n; k++) {
                        if (nums[i] != nums[k] && nums[j] != nums[k]) {
                            res++;
                        }
                    }
                }
            }
        }
        return res;
    }

    public int unequalTriplets2(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int res = 0;
        for (int i = 0, j = 0; i < n; i = j) {
            while (j < n && nums[j] == nums[i]) {
                j++;
            }
            res += i * (j - i) * (n - j);
        }
        return res;
    }

    public int unequalTriplets(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int res = 0;
        int n = nums.length;
        int t = 0;
        for (int value : map.values()) {
            res += t * value * (n - t - value);
            t += value;
        }
        return res;
    }

}

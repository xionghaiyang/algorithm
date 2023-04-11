package com.sean.leetcode.LeetCode2367;

import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-03-31 08:12
 * @Description: https://leetcode.cn/problems/number-of-arithmetic-triplets/
 * 2367. 算术三元组的数目
 * 给你一个下标从 0 开始、严格递增 的整数数组 nums 和一个正整数 diff 。
 * 如果满足下述全部条件，则三元组 (i, j, k) 就是一个 算术三元组 ：
 * i < j < k ，
 * nums[j] - nums[i] == diff 且
 * nums[k] - nums[j] == diff
 * 返回不同 算术三元组 的数目。
 */
public class Solution {

    public int arithmeticTriplets1(int[] nums, int diff) {
        if (nums == null || nums.length < 3) {
            return 0;
        }
        int n = nums.length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n && nums[j] - nums[i] <= diff; j++) {
                if (nums[j] - nums[i] == diff) {
                    for (int k = j + 1; k < n && nums[k] - nums[j] <= diff; k++) {
                        if (nums[k] - nums[j] == diff) {
                            res++;
                        }
                    }
                }
            }
        }
        return res;
    }

    public int arithmeticTriplets2(int[] nums, int diff) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int res = 0;
        for (int num : nums) {
            if (set.contains(num + diff) && set.contains(num + 2 * diff)) {
                res++;
            }
        }
        return res;
    }

    public int arithmeticTriplets(int[] nums, int diff) {
        if (nums == null || nums.length < 3) {
            return 0;
        }
        int res = 0;
        int n = nums.length;
        for (int i = 0, j = 1, k = 2; i < n - 2 && j < n - 1 && k < n; i++) {
            j = Math.max(j, i + 1);
            while (j < n - 1 && nums[j] - nums[i] < diff) {
                j++;
            }
            if (j >= n - 1 || nums[j] - nums[i] > diff) {
                continue;
            }
            k = Math.max(k, j + 1);
            while (k < n && nums[k] - nums[j] < diff) {
                k++;
            }
            if (k < n && nums[k] - nums[j] == diff) {
                res++;
            }
        }
        return res;
    }

}

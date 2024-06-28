package com.sean.leetcode.LeetCode930;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xionghaiyang
 * @Date 2024-05-08 12:26
 * @Description https://leetcode.cn/problems/binary-subarrays-with-sum/
 * 930. 和相同的二元子数组
 * 给你一个二元数组 nums ，和一个整数 goal ，请你统计并返回有多少个和为 goal 的 非空 子数组。
 * 子数组 是数组的一段连续部分。
 */
public class Solution {

    public int numSubarraysWithSum(int[] nums, int goal) {
        int n = nums.length;
        int[] preSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }
        int res = 0;
        for (int len = 1; len <= n; len++) {
            for (int left = 0, right = left + len - 1; right < n; left++, right++) {
                if (preSum[right + 1] - preSum[left] == goal) {
                    res++;
                }
            }
        }
        return res;
    }

    public int numSubarraysWithSum1(int[] nums, int goal) {
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int num : nums) {
            map.put(sum, map.getOrDefault(sum, 0) + 1);
            sum += num;
            res += map.getOrDefault(sum - goal, 0);
        }
        return res;
    }

    public int numSubarraysWithSum2(int[] nums, int goal) {
        int n = nums.length;
        int left1 = 0, left2 = 0, right = 0;
        int sum1 = 0, sum2 = 0;
        int res = 0;
        while (right < n) {
            sum1 += nums[right];
            while (left1 <= right && sum1 > goal) {
                sum1 -= nums[left1];
                left1++;
            }
            sum2 += nums[right];
            while (left2 <= right && sum2 >= goal) {
                sum2 -= nums[left2];
                left2++;
            }
            res += left2 - left1;
            right++;
        }
        return res;
    }

}

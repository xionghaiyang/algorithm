package com.sean.leetcode.LeetCode2962;

/**
 * @Author xionghaiyang
 * @Date 2025-04-29 06:01
 * @Description https://leetcode.cn/problems/count-subarrays-where-max-element-appears-at-least-k-times
 * 2962. 统计最大元素出现至少 K 次的子数组
 * 给你一个整数数组 nums 和一个 正整数 k 。
 * 请你统计有多少满足 「 nums 中的 最大 元素」至少出现 k 次的子数组，并返回满足这一条件的子数组的数目。
 * 子数组是数组中的一个连续元素序列。
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^6
 * 1 <= k <= 10^5
 */
public class Solution {

    public long countSubarrays(int[] nums, int k) {
        long res = 0;
        int max = 0;
        for (int num : nums) {
            max = Math.max(max, num);
        }
        int cnt = 0, left = 0;
        for (int num : nums) {
            if (num == max) {
                cnt++;
            }
            while (cnt == k) {
                if (nums[left] == max) {
                    cnt--;
                }
                left++;
            }
            res += left;
        }
        return res;
    }

}

package com.sean.leetcode.LeetCode2616;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2025-06-13 05:08
 * @Description https://leetcode.cn/problems/minimize-the-maximum-difference-of-pairs
 * 2616. 最小化数对的最大差值
 * 给你一个下标从 0 开始的整数数组 nums 和一个整数 p 。
 * 请你从 nums 中找到 p 个下标对，每个下标对对应数值取差值，你需要使得这 p 个差值的 最大值 最小。
 * 同时，你需要确保每个下标在这 p 个下标对中最多出现一次。
 * 对于一个下标对 i 和 j ，这一对的差值为 |nums[i] - nums[j]| ，其中 |x| 表示 x 的 绝对值 。
 * 请你返回 p 个下标对对应数值 最大差值 的 最小值 。
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^9
 * 0 <= p <= (nums.length)/2
 */
public class Solution {

    public int minimizeMax(int[] nums, int p) {
        int n = nums.length;
        if (p == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int min = nums[1] - nums[0];
        int max = min;
        for (int i = 1; i < n; i++) {
            min = Math.min(min, nums[i] - nums[i - 1]);
            max = Math.max(max, nums[i] - nums[i - 1]);
        }
        int left = min, right = max;
        int res = 0;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (check(nums, p, mid)) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

    private boolean check(int[] nums, int p, int target) {
        int n = nums.length;
        for (int i = 0; i < n - 1 && p > 0; i++) {
            if (nums[i + 1] - nums[i] <= target) {
                p--;
                i++;
            }
        }
        return p == 0;
    }

}

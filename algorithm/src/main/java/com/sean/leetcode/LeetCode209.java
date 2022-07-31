package com.sean.leetcode;

public class LeetCode209 {

    public int minSubArrayLen(int target, int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }
        int left = 0, right = 0, sum = 0, res = Integer.MAX_VALUE;
        while (right < len) {
            sum += nums[right];
            while (sum >= target) {
                res = Math.min(res, right - left + 1);
                sum -= nums[left];
                left++;
            }
            right++;
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

}



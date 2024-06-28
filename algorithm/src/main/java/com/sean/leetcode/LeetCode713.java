package com.sean.leetcode;

public class LeetCode713 {

    public static int numSubarrayProductLessThanK(int[] nums, int k) {
        int ans = 0, prod = 1, j = 0;
        for (int i = 0; i < nums.length; i++) {
            prod *= nums[i];
            while (j <= i && prod >= k) {
                prod /= nums[j];
                j++;
            }
            ans += i - j + 1;
        }
        return ans;
    }

}

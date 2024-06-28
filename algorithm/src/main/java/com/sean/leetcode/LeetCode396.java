package com.sean.leetcode;

public class LeetCode396 {

    public int maxRotateFunction(int[] nums) {
        int sum = 0, n = nums.length, f = 0;
        for (int num : nums) {
            sum += num;
        }
        for (int i = 0; i < n; i++) {
            f += i * nums[i]; //f(0)
        }
        int ans = f;
        for (int i = n - 1; i > 0; i--) {
            f += sum - n * nums[i]; //f(n-i)
            ans = Math.max(ans, f);
        }
        return ans;
    }
}

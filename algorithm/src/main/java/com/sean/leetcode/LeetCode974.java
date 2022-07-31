package com.sean.leetcode;

/**
 * 和可被 K 整除的子数组
 * https://leetcode-cn.com/problems/subarray-sums-divisible-by-k/
 */
public class LeetCode974 {

    //超出时间限制
    public static int subarraysDivByK1(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int res = 0;
        int length = nums.length;
        int[] preSum = new int[length];
        preSum[0] = nums[0];
        for (int i = 1; i < length; i++) {
            preSum[i] = preSum[i - 1] + nums[i];
        }
        for (int left = 0; left < length; left++) {
            for (int right = left; right < length; right++) {
                if ((preSum[right] - preSum[left] + nums[left]) % k == 0) {
                    res++;
                }
            }
        }
        return res;
    }

    public static int subarraysDivByK(int[] nums, int k) {
        int[] map = new int[k];
        map[0] = 1;
        int res = 0, sum = 0;
        for (int num : nums) {
            sum += num;
            int K = (sum % k + k) % k;
            res += map[K];
            map[K]++;
        }
        return res;
    }

}

package com.sean.leetcode;

import jdk.nashorn.internal.objects.NativeUint16Array;

/**
 * 乘积最大子数组
 * https://leetcode-cn.com/problems/maximum-product-subarray/
 */
public class LeetCode152 {

    public static int maxProduct(int[] nums) {
        int n = nums.length;
        int[] maxdp = new int[n];
        for (int i = 0; i < n; i++) {
            maxdp[i] = nums[i];
        }
        int[] mindp = new int[n];
        for (int i = 0; i < n; i++) {
            mindp[i] = nums[i];
        }
        for (int i = 1; i < n; i++) {
            maxdp[i] = Math.max(maxdp[i - 1] * nums[i], Math.max(nums[i], mindp[i - 1] * nums[i]));
            mindp[i] = Math.min(mindp[i - 1] * nums[i], Math.min(nums[i], maxdp[i - 1] * nums[i]));
        }
        int res = maxdp[0];
        for (int i = 1; i < n; i++) {
            res = Math.max(res, maxdp[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(maxProduct(new int[]{2, 3, -2, 4}));
        System.out.println(maxProduct(new int[]{-2, 0, -1}));
    }

}

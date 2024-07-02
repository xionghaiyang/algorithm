package com.sean.leetcode.LeetCode3115;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2024-07-02 09:46
 * @Description https://leetcode.cn/problems/maximum-prime-difference/
 * 3115. 质数的最大距离
 * 给你一个整数数组 nums。
 * 返回两个（不一定不同的）质数在 nums 中 下标 的 最大距离。
 */
public class Solution {

    private boolean[] prime;

    public int maximumPrimeDifference(int[] nums) {
        prime = new boolean[101];
        Arrays.fill(prime, true);
        process();
        int n = nums.length;
        int first = -1, res = 0;
        for (int i = 0; i < n; i++) {
            if (prime[nums[i]]) {
                if (first == -1) {
                    first = i;
                } else {
                    res = Math.max(res, i - first);
                }
            }
        }
        return res;
    }

    private void process() {
        prime[0] = false;
        prime[1] = false;
        for (int i = 2; i < 101; i++) {
            if (prime[i]) {
                for (int j = i + i; j < 101; j += i) {
                    prime[j] = false;
                }
            }
        }
    }

}

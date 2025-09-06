package com.sean.leetcode.LeetCode1304;

/**
 * @Author xionghaiyang
 * @Date 2025-09-07 06:52
 * @Description https://leetcode.cn/problems/find-n-unique-integers-sum-up-to-zero
 * 1304. 和为零的 N 个不同整数
 * 给你一个整数 n，请你返回 任意 一个由 n 个 各不相同 的整数组成的数组，并且这 n 个数相加和为 0 。
 * 1 <= n <= 1000
 */
public class Solution {

    public int[] sumZero(int n) {
        int[] res = new int[n];
        int half = n / 2;
        if ((n & 1) == 0) {
            int i = 0;
            for (int j = -half; j < 0; j++) {
                res[i++] = j;
            }
            for (int j = 1; j <= half; j++) {
                res[i++] = j;
            }
        } else {
            for (int i = 0, j = -half; i < n; i++, j++) {
                res[i] = j;
            }
        }
        return res;
    }

}

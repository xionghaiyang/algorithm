package com.sean.leetcode.LeetCode1340;

/**
 * @Author: xionghaiyang
 * @Date: 2026-05-24 05:33
 * @Description: https://leetcode.cn/problems/jump-game-v
 * 1340. 跳跃游戏 V
 * 给你一个整数数组 arr 和一个整数 d 。
 * 每一步你可以从下标 i 跳到：
 * i + x ，其中 i + x < arr.length 且 0 < x <= d 。
 * i - x ，其中 i - x >= 0 且 0 < x <= d 。
 * 除此以外，你从下标 i 跳到下标 j 需要满足：arr[i] > arr[j] 且 arr[i] > arr[k] ，其中下标 k 是所有 i 到 j 之间的数字（更正式的，min(i, j) < k < max(i, j)）。
 * 你可以选择数组的任意下标开始跳跃。
 * 请你返回你 最多 可以访问多少个下标。
 * 请注意，任何时刻你都不能跳到数组的外面。
 * 1 <= arr.length <= 1000
 * 1 <= arr[i] <= 10^5
 * 1 <= d <= arr.length
 */
public class Solution {

    public int maxJumps(int[] arr, int d) {
        int n = arr.length;
        int[] memo = new int[n];
        int res = 0;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, process(arr, d, memo, i));
        }
        return res;
    }

    private int process(int[] arr, int d, int[] memo, int i) {
        if (memo[i] != 0) {
            return memo[i];
        }
        int res = 1;
        for (int j = i - 1; j >= Math.max(i - d, 0) && arr[i] > arr[j]; j--) {
            res = Math.max(res, process(arr, d, memo, j) + 1);
        }
        for (int j = i + 1; j <= Math.min(i + d, arr.length - 1) && arr[i] > arr[j]; j++) {
            res = Math.max(res, process(arr, d, memo, j) + 1);
        }
        return memo[i] = res;
    }

}

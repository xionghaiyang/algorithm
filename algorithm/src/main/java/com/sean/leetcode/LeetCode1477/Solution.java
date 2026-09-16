package com.sean.leetcode.LeetCode1477;

/**
 * @Author: xionghaiyang
 * @Date: 2026-09-17 06:13
 * @Description: https://leetcode.cn/problems/find-two-non-overlapping-sub-arrays-each-with-target-sum
 * 1477. 找两个和为目标值且不重叠的子数组
 * 给你一个整数数组 arr 和一个整数值 target 。
 * 请你在 arr 中找 两个互不重叠的子数组 且它们的和都等于 target 。
 * 可能会有多种方案，请你返回满足要求的两个子数组长度和的 最小值 。
 * 请返回满足要求的最小长度和，如果无法找到这样的两个子数组，请返回 -1 。
 * 1 <= arr.length <= 10^5
 * 1 <= arr[i] <= 1000
 * 1 <= target <= 10^8
 */
public class Solution {

    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        //sufMin[i]表示左端点>=i的和为target的最短子数组长度
        //不存在子数组时，长度设为n+1
        int[] sufMin = new int[n];
        int minLen = n + 1;
        int sum = 0;
        int right = n - 1;
        for (int left = n - 1; left > 0; left--) {
            sum += arr[left];
            while (sum > target) {
                sum -= arr[right--];
            }
            if (sum == target) {
                minLen = Math.min(minLen, right - left + 1);
            }
            sufMin[left] = minLen;
        }
        int res = n + 1;
        sum = 0;
        int left = 0;
        for (right = 0; right < n - 1; right++) {
            sum += arr[right];
            while (sum > target) {
                sum -= arr[left++];
            }
            if (sum == target) {
                res = Math.min(res, right - left + 1 + sufMin[right + 1]);
            }
        }
        return res > n ? -1 : res;
    }

}

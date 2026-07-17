package com.sean.leetcode.LeetCode3312;

/**
 * @Author: xionghaiyang
 * @Date: 2026-07-17 10:47
 * @Description: https://leetcode.cn/problems/sorted-gcd-pair-queries
 * 3312. 查询排序后的最大公约数
 * 给你一个长度为 n 的整数数组 nums 和一个整数数组 queries 。
 * gcdPairs 表示数组 nums 中所有满足 0 <= i < j < n 的数对 (nums[i], nums[j]) 的 最大公约数 升序 排列构成的数组。
 * 对于每个查询 queries[i] ，你需要找到 gcdPairs 中下标为 queries[i] 的元素。
 * 请你返回一个整数数组 answer ，其中 answer[i] 是 gcdPairs[queries[i]] 的值。
 * gcd(a, b) 表示 a 和 b 的 最大公约数 。
 * 2 <= n == nums.length <= 10^5
 * 1 <= nums[i] <= 5 * 10^4
 * 1 <= queries.length <= 10^5
 * 0 <= queries[i] < n * (n - 1) / 2
 */
public class Solution {

    public int[] gcdValues(int[] nums, long[] queries) {
        int max = 0;
        for (int num : nums) {
            max = Math.max(max, num);
        }
        long[] cnt = new long[max + 1];
        for (int num : nums) {
            cnt[num]++;
        }
        long[] cntGcd = new long[max + 1];
        for (int i = max; i > 0; i--) {
            int c = 0;
            for (int j = i; j <= max; j += i) {
                c += cnt[j];
                cntGcd[i] -= cntGcd[j];
            }
            cntGcd[i] += (long) c * (c - 1) / 2;
        }
        for (int i = 2; i <= max; i++) {
            cntGcd[i] += cntGcd[i - 1];
        }
        int m = queries.length;
        int[] res = new int[m];
        for (int i = 0; i < m; i++) {
            res[i] = binarySearch(cntGcd, queries[i]);
        }
        return res;
    }

    private int binarySearch(long[] nums, long target) {
        int left = 0, right = nums.length - 1, res = right;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] > target) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

}

package com.sean.leetcode.LeetCode3164;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xionghaiyang
 * @Date 2024-10-12 17:14
 * @Description https://leetcode.cn/problems/find-the-number-of-good-pairs-ii
 * 3164. 优质数对的总数 II
 * 给你两个整数数组 nums1 和 nums2，长度分别为 n 和 m。
 * 同时给你一个正整数 k。
 * 如果 nums1[i] 可以被 nums2[j] * k 整除，则称数对 (i, j) 为 优质数对（0 <= i <= n - 1, 0 <= j <= m - 1）。
 * 返回 优质数对 的总数。
 * 1 <= n, m <= 10^5
 * 1 <= nums1[i], nums2[j] <= 10^6
 * 1 <= k <= 10^3
 */
public class Solution {

    public long numberOfPairs(int[] nums1, int[] nums2, int k) {
        Map<Integer, Integer> cnt1 = new HashMap<>();
        Map<Integer, Integer> cnt2 = new HashMap<>();
        int max1 = 0;
        for (int num : nums1) {
            cnt1.put(num, cnt1.getOrDefault(num, 0) + 1);
            max1 = Math.max(max1, num);
        }
        for (int num : nums2) {
            cnt2.put(num, cnt2.getOrDefault(num, 0) + 1);
        }
        long res = 0;
        for (int a : cnt2.keySet()) {
            for (int b = a * k; b <= max1; b += a * k) {
                if (cnt1.containsKey(b)) {
                    res += 1L * cnt1.get(b) * cnt2.get(a);
                }
            }
        }
        return res;
    }

}

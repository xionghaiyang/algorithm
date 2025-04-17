package com.sean.leetcode.LeetCode2176;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2025-04-17 09:56
 * @Description https://leetcode.cn/problems/count-equal-and-divisible-pairs-in-an-array
 * 2176. 统计数组中相等且可以被整除的数对
 * 给你一个下标从 0 开始长度为 n 的整数数组 nums 和一个整数 k ，
 * 请你返回满足 0 <= i < j < n ，nums[i] == nums[j] 且 (i * j) 能被 k 整除的数对 (i, j) 的 数目 。
 * 1 <= nums.length <= 100
 * 1 <= nums[i], k <= 100
 */
public class Solution {

    public int countPairs(int[] nums, int k) {
        int n = nums.length;
        int res = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if ((i * j) % k == 0 && nums[i] == nums[j]) {
                    res++;
                }
            }
        }
        return res;
    }

    public int countPairs1(int[] nums, int k) {
        List<Integer>[] div = new ArrayList[101];
        Arrays.setAll(div, i -> new ArrayList<>());
        for (int i = 1; i < 101; i++) {
            for (int j = i; j < 101; j += i) {
                div[j].add(i);
            }
        }
        int n = nums.length;
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int j = 1; j < n; j++) {
            int x = nums[j];
            if (x == nums[0]) {
                res++;
            }
            int k1 = k / gcd(k, j);
            res += map.getOrDefault(k1 << 10 | x, 0);
            for (int d : div[j]) {
                map.put(d << 10 | x, map.getOrDefault(d << 10 | x, 0) + 1);
            }
        }
        return res;
    }

    private int gcd(int a, int b) {
        while (a != 0) {
            int tmp = b % a;
            b = a;
            a = tmp;
        }
        return b;
    }

}

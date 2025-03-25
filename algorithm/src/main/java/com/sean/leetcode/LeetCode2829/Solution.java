package com.sean.leetcode.LeetCode2829;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author xionghaiyang
 * @Date 2025-03-26 07:46
 * @Description https://leetcode.cn/problems/determine-the-minimum-sum-of-a-k-avoiding-array
 * 2829. k-avoiding 数组的最小总和
 * 给你两个整数 n 和 k 。
 * 对于一个由 不同 正整数组成的数组，如果其中不存在任何求和等于 k 的不同元素对，则称其为 k-avoiding 数组。
 * 返回长度为 n 的 k-avoiding 数组的可能的最小总和。
 * 1 <= n, k <= 50
 */
public class Solution {

    public int minimumSum(int n, int k) {
        int res = 0;
        Set<Integer> set = new HashSet<>();
        for (int i = 1, j = 0; j < n; i++) {
            if (set.contains(k - i)) {
                continue;
            }
            set.add(i);
            res += i;
            j++;
        }
        return res;
    }

    public int minimumSum1(int n, int k) {
        int m = Math.min(k / 2, n);
        return (m * (m + 1) + (k * 2 + n - m - 1) * (n - m)) / 2;
    }

}

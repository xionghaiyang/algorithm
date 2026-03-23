package com.sean.leetcode.LeetCodeInterview1606;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2026-03-23 11:55
 * @Description https://leetcode.cn/problems/smallest-difference-lcci
 * 面试题 16.06. 最小差
 * 给定两个整数数组a和b，计算具有最小差绝对值的一对数值（每个数组中取一个值），并返回该对数值的差
 * 1 <= a.length, b.length <= 100000
 * -2147483648 <= a[i], b[i] <= 2147483647
 * 正确结果在区间 [0, 2147483647] 内
 */
public class Solution {

    public int smallestDifference(int[] a, int[] b) {
        Arrays.sort(a);
        Arrays.sort(b);
        int m = a.length, n = b.length;
        int i = 0, j = 0;
        long res = Long.MAX_VALUE;
        while (i < m && j < n) {
            if (a[i] == b[j]) {
                return 0;
            } else if (a[i] < b[j]) {
                while (i < m && a[i] < b[j]) {
                    i++;
                }
                res = Math.min(res, (long) b[j] - a[i - 1]);
            } else {
                while (j < n && b[j] < a[i]) {
                    j++;
                }
                res = Math.min(res, (long) a[i] - b[j - 1]);
            }
        }
        return (int) res;
    }

}

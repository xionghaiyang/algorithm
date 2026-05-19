package com.sean.leetcode.LeetCode2657;

/**
 * @Autnor: xionghaiyang
 * @Date: 2026-05-20 05:57
 * @Description: https://leetcode.cn/problems/find-the-prefix-common-array-of-two-arrays
 * 2657. 找到两个数组的前缀公共数组
 * 给你两个下标从 0 开始长度为 n 的整数排列 A 和 B 。
 * A 和 B 的 前缀公共数组 定义为数组 C ，其中 C[i] 是数组 A 和 B 到下标为 i 之前公共元素的数目。
 * 请你返回 A 和 B 的 前缀公共数组 。
 * 如果一个长度为 n 的数组包含 1 到 n 的元素恰好一次，我们称这个数组是一个长度为 n 的 排列 。
 * 1 <= A.length == B.length == n <= 50
 * 1 <= A[i], B[i] <= n
 * 题目保证 A 和 B 两个数组都是 n 个元素的排列。
 */
public class Solution {

    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        int n = A.length;
        int[] res = new int[n];
        long p = 0, q = 0;
        for (int i = 0; i < n; i++) {
            p |= 1L << A[i];
            q |= 1L << B[i];
            res[i] = Long.bitCount(p & q);
        }
        return res;
    }

}

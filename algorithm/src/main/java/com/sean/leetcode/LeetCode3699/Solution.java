package com.sean.leetcode.LeetCode3699;

import java.util.Arrays;

/**
 * @Author: xionghaiyang
 * @Date: 2026-06-23 08:48
 * @Description: https://leetcode.cn/problems/number-of-zigzag-arrays-i
 * 3699. 锯齿形数组的总数 I
 * 给你 三个整数 n、l 和 r。
 * 长度为 n 的锯齿形数组定义如下：
 * 每个元素的取值范围为 [l, r]。
 * 任意 两个 相邻的元素都不相等。
 * 任意 三个 连续的元素不能构成一个 严格递增 或 严格递减 的序列。
 * 返回满足条件的锯齿形数组的总数。
 * 由于答案可能很大，请将结果对 10^9 + 7 取余数。
 * 序列 被称为 严格递增 需要满足：当且仅当每个元素都严格大于它的前一个元素（如果存在）。
 * 序列 被称为 严格递减 需要满足，当且仅当每个元素都严格小于它的前一个元素（如果存在）。
 * 3 <= n <= 2000
 * 1 <= l < r <= 2000
 */
public class Solution {

    private static final int MOD = 1_000_000_007;

    public int zigZagArrays(int n, int l, int r) {
        int k = r - l + 1;
        //后两个数递增
        int[] f0 = new int[k];
        //后两个数递减
        int[] f1 = new int[k];
        Arrays.fill(f0, 1);
        Arrays.fill(f1, 1);
        long[] s0 = new long[k + 1];
        long[] s1 = new long[k + 1];
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < k; j++) {
                s0[j + 1] = s0[j] + f0[j];
                s1[j + 1] = s1[j] + f1[j];
            }
            for (int j = 0; j < k; j++) {
                f0[j] = (int) (s1[j] % MOD);
                f1[j] = (int) ((s0[k] - s0[j + 1]) % MOD);
            }
        }
        long res = 0;
        for (int j = 0; j < k; j++) {
            res += f0[j] + f1[j];
        }
        return (int) (res % MOD);
    }

}

package com.sean.leetcode.LeetCode2338;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2025-04-22 08:38
 * @Description https://leetcode.cn/problems/count-the-number-of-ideal-arrays
 * 2338. 统计理想数组的数目
 * 给你两个整数 n 和 maxValue ，用于描述一个 理想数组 。
 * 对于下标从 0 开始、长度为 n 的整数数组 arr ，如果满足以下条件，则认为该数组是一个 理想数组 ：
 * 每个 arr[i] 都是从 1 到 maxValue 范围内的一个值，其中 0 <= i < n 。
 * 每个 arr[i] 都可以被 arr[i - 1] 整除，其中 0 < i < n 。
 * 返回长度为 n 的 不同 理想数组的数目。
 * 由于答案可能很大，返回对 10^9 + 7 取余的结果。
 * 2 <= n <= 10^4
 * 1 <= maxValue <= 10^4
 */
public class Solution {

    private static final int MOD = 1_000_000_007;
    private static final int MAX_N = 10_000;
    private static final int MAX_E = 13;

    private static final List<Integer>[] EXP = new ArrayList[MAX_N + 1];
    private static final int[][] C = new int[MAX_N + MAX_E][MAX_E + 1];

    private static boolean done = false;

    private void init() {
        if (done) {
            return;
        }
        done = true;
        //EXP[x]为x分解质因数后，每个质因数的指数
        for (int x = 1; x < EXP.length; x++) {
            EXP[x] = new ArrayList<>();
            int t = x;
            for (int i = 2; i * i <= t; i++) {
                int e = 0;
                for (; t % i == 0; t /= i) {
                    e++;
                }
                if (e > 0) {
                    EXP[x].add(e);
                }
            }
            if (t > 1) {
                EXP[x].add(1);
            }
        }
        for (int i = 0; i < MAX_N + MAX_E; i++) {
            C[i][0] = 1;
            for (int j = 1; j <= Math.min(i, MAX_E); j++) {
                C[i][j] = (C[i - 1][j] + C[i - 1][j - 1]) % MOD;
            }
        }
    }

    public int idealArrays(int n, int maxValue) {
        init();
        long res = 0;
        for (int x = 1; x <= maxValue; x++) {
            long mul = 1;
            for (int e : EXP[x]) {
                mul = mul * C[n + e - 1][e] % MOD;
            }
            res += mul;
        }
        return (int) (res % MOD);
    }

}

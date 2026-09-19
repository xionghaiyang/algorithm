package com.sean.leetcode.LeetCode1175;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: xionghaiyang
 * @Date: 2026-09-20 06:30
 * @Description: https://leetcode.cn/problems/prime-arrangements
 * 1175. 质数排列
 * 请你帮忙给从 1 到 n 的数设计排列方案，使得所有的「质数」都应该被放在「质数索引」（索引从 1 开始）上；你需要返回可能的方案总数。
 * 让我们一起来回顾一下「质数」：质数一定是大于 1 的，并且不能用两个小于它的正整数的乘积来表示。
 * 由于答案可能会很大，所以请你返回答案 模 mod 10^9 + 7 之后的结果即可。
 * 1 <= n <= 100
 */
public class Solution {

    private static final int MOD = 1_000_000_007;
    private static final int MAX = 101;
    private static final List<Integer> primes = new ArrayList<>();
    private static final boolean[] isPrime = new boolean[MAX];
    private static final int[] numPrime = new int[MAX];
    private static final int[] a = new int[MAX];
    private static boolean initialized = false;

    public Solution() {
        if (initialized) {
            return;
        }
        initialized = true;
        Arrays.fill(isPrime, true);
        for (int i = 2, cnt = 0; i < MAX; i++) {
            if (isPrime[i]) {
                primes.add(i);
                cnt++;
            }
            numPrime[i] = cnt;
            for (int j = 0; j < primes.size() && i * primes.get(j) < MAX; j++) {
                isPrime[i * primes.get(j)] = false;
                if (i % primes.get(j) == 0) {
                    break;
                }
            }
        }
        a[0] = 1;
        for (int i = 1; i < MAX; i++) {
            a[i] = (int) ((long) a[i - 1] * i % MOD);
        }
    }

    public int numPrimeArrangements(int n) {
        int numPrimes = numPrime[n];
        return (int) ((long) a[numPrimes] * a[n - numPrimes] % MOD);
    }

}

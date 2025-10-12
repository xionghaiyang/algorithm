package com.sean.leetcode.LeetCode3539;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2025-10-12 14:15
 * @Description https://leetcode.cn/problems/find-sum-of-array-product-of-magical-sequences
 * 3539. 魔法序列的数组乘积之和
 * 给你两个整数 M 和 K，和一个整数数组 nums。
 * 一个整数序列 seq 如果满足以下条件，被称为 魔法 序列：
 * seq 的序列长度为 M。
 * 0 <= seq[i] < nums.length
 * 2seq[0] + 2seq[1] + ... + 2seq[M - 1] 的 二进制形式 有 K 个 置位。
 * 这个序列的 数组乘积 定义为 prod(seq) = (nums[seq[0]] * nums[seq[1]] * ... * nums[seq[M - 1]])。
 * 返回所有有效 魔法 序列的 数组乘积 的 总和 。
 * 由于答案可能很大，返回结果对 10^9 + 7 取模。
 * 置位 是指一个数字的二进制表示中值为 1 的位。
 * 1 <= K <= M <= 30
 * 1 <= nums.length <= 50
 * 1 <= nums[i] <= 10^8
 */
public class Solution {

    private static final int MOD = 1_000_000_007;
    private static final int MAX = 31;
    //F[i] = i!
    private static final long[] F = new long[MAX];
    //INV_F[i] =  (i!)^(-1)
    private static final long[] INV_F = new long[MAX];
    private static boolean initialized = false;

    private void init() {
        if (initialized) {
            return;
        }
        initialized = true;
        F[0] = 1;
        for (int i = 1; i < MAX; i++) {
            F[i] = F[i - 1] * i % MOD;
        }
        INV_F[MAX - 1] = pow(F[MAX - 1], MOD - 2);
        for (int i = MAX - 1; i > 0; i--) {
            INV_F[i - 1] = INV_F[i] * i % MOD;
        }
    }

    private long pow(long x, int n) {
        long res = 1;
        for (; n > 0; n >>= 1) {
            if (n % 2 > 0) {
                res = res * x % MOD;
            }
            x = x * x % MOD;
        }
        return res;
    }

    public int magicalSum(int m, int k, int[] nums) {
        init();
        int n = nums.length;
        int[][] powV = new int[n][m + 1];
        for (int i = 0; i < n; i++) {
            powV[i][0] = 1;
            for (int j = 1; j <= m; j++) {
                powV[i][j] = (int) ((long) powV[i][j - 1] * nums[i] % MOD);
            }
        }
        int[][][][] memo = new int[n][m + 1][m / 2 + 1][k + 1];
        for (int[][][] a : memo) {
            for (int[][] b : a) {
                for (int[] c : b) {
                    Arrays.fill(c, -1);
                }
            }
        }
        return (int) (dfs(0, m, 0, k, powV, memo) * F[m] % MOD);
    }

    private long dfs(int i, int leftM, int x, int leftK, int[][] powV, int[][][][] memo) {
        int c1 = Integer.bitCount(x);
        //可行性剪枝
        if (c1 + leftM < leftK) {
            return 0;
        }
        //无法继续选数字
        if (i == powV.length || leftM == 0 || leftK == 0) {
            return leftM == 0 && c1 == leftK ? 1 : 0;
        }
        if (memo[i][leftM][x][leftK] != -1) {
            return memo[i][leftM][x][leftK];
        }
        long res = 0;
        //枚举I中有j个下标i
        for (int j = 0; j <= leftM; j++) {
            //这j个下标i对S的贡献是j * pow(2,i)
            //由于x = S>>i,转化成对x的贡献是j
            //取最低位，提前从leftK中减去，其余进位到x中
            int bit = (x + j) & 1;
            long r = dfs(i + 1, leftM - j, (x + j) >> 1, leftK - bit, powV, memo);
            res = (res + r * powV[i][j] % MOD * INV_F[j]) % MOD;
        }
        return memo[i][leftM][x][leftK] = (int) res;
    }

}

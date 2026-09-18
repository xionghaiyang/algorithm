package com.sean.leetcode.LeetCode2320;

/**
 * @Author: xionghaiyang
 * @Date: 2026-09-18 17:56
 * @Description: https://leetcode.cn/problems/count-number-of-ways-to-place-houses
 * 2320. 统计放置房子的方式数
 * 一条街道上共有 n * 2 个 地块 ，街道的两侧各有 n 个地块。
 * 每一边的地块都按从 1 到 n 编号。每个地块上都可以放置一所房子。
 * 现要求街道同一侧不能存在两所房子相邻的情况，请你计算并返回放置房屋的方式数目。
 * 由于答案可能很大，需要对 10^9 + 7 取余后再返回。
 * 注意，如果一所房子放置在这条街某一侧上的第 i 个地块，不影响在另一侧的第 i 个地块放置房子。
 * 1 <= n <= 10^4
 */
public class Solution {

    private static final int MOD = 1_000_000_007;
    private static final int MAX = 10_001;
    private static final int[] f = new int[MAX];
    private static boolean initialized = false;

    public Solution() {
        if (initialized) {
            return;
        }
        initialized = true;
        f[0] = 1;
        f[1] = 2;
        for (int i = 2; i < MAX; i++) {
            f[i] = (f[i - 1] + f[i - 2]) % MOD;
        }
    }

    public int countHousePlacements(int n) {
        return (int) ((long) f[n] * f[n] % MOD);
    }

}

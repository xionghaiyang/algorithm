package com.sean.leetcode.LeetCode3669;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2025-08-31 20:12
 * @Description https://leetcode.cn/problems/balanced-k-factor-decomposition
 * 3669. K 因数分解
 * 给你两个整数 n 和 k，将数字 n 恰好分割成 k 个正整数，使得这些整数的 乘积 等于 n。
 * 返回一个分割方案，使得这些数字中 最大值 和 最小值 之间的 差值 最小化。
 * 结果可以以 任意顺序 返回。
 * 4 <= n <= 10^5
 * 2 <= k <= 5
 * k 严格小于 n 的正因数的总数。
 */
public class Solution {

    private static final int MAX = 100_001;
    private static final List<Integer>[] divisors = new ArrayList[MAX];
    private static boolean initialized = false;
    private int minDiff = Integer.MAX_VALUE;
    private int[] res;

    private void init() {
        if (initialized) {
            return;
        }
        initialized = true;
        Arrays.setAll(divisors, i -> new ArrayList<>());
        for (int i = 1; i < MAX; i++) {
            for (int j = i; j < MAX; j += i) {
                divisors[j].add(i);
            }
        }
    }

    public int[] minDifference(int n, int k) {
        init();
        dfs(k - 1, n, Integer.MAX_VALUE, 0, new int[k]);
        return res;
    }

    private void dfs(int i, int n, int min, int max, int[] path) {
        if (i == 0) {
            int diff = Math.max(max, n) - Math.min(min, n);
            if (diff < minDiff) {
                minDiff = diff;
                path[i] = n;
                res = path.clone();
            }
            return;
        }
        for (int div : divisors[n]) {
            path[i] = div;
            dfs(i - 1, n / div, Math.min(min, div), Math.max(max, div), path);
        }
    }

}

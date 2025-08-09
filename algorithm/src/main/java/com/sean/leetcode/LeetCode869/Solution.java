package com.sean.leetcode.LeetCode869;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author xionghaiyang
 * @Date 2025-08-10 07:09
 * @Description https://leetcode.cn/problems/reordered-power-of-2
 * 869. 重新排序得到 2 的幂
 * 给定正整数 n ，我们按任何顺序（包括原始顺序）将数字重新排序，注意其前导数字不能为零。
 * 如果我们可以通过上述方式得到 2 的幂，返回 true；否则，返回 false。
 * 1 <= n <= 10^9
 */
public class Solution {

    private static final int MAX = 1_000_000_000;
    private static final Set<String> set = new HashSet<>();
    private static boolean initialized = false;

    private void init() {
        if (initialized) {
            return;
        }
        initialized = true;
        for (int i = 1; i <= MAX; i <<= 1) {
            set.add(countDigits(i));
        }
    }

    private String countDigits(int n) {
        char[] cnt = new char[10];
        while (n > 0) {
            cnt[n % 10]++;
            n /= 10;
        }
        return String.valueOf(cnt);
    }

    public boolean reorderedPowerOf2(int n) {
        init();
        return set.contains(countDigits(n));
    }

}

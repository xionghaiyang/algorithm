package com.sean.leetcode.LeetCode2466;

import java.util.Arrays;

/**
 * @Author: xionghaiyang
 * @Date: 2026-09-17 17:24
 * @Description: https://leetcode.cn/problems/count-ways-to-build-good-strings
 * 2466. 统计构造好字符串的方案数
 * 给你整数 zero ，one ，low 和 high ，我们从空字符串开始构造一个字符串，每一步执行下面操作中的一种：
 * 将 '0' 在字符串末尾添加 zero  次。
 * 将 '1' 在字符串末尾添加 one 次。
 * 以上操作可以执行任意次。
 * 如果通过以上过程得到一个 长度 在 low 和 high 之间（包含上下边界）的字符串，那么这个字符串我们称为 好 字符串。
 * 请你返回满足以上要求的 不同 好字符串数目。
 * 由于答案可能很大，请将结果对 10^9 + 7 取余 后返回。
 * 1 <= low <= high <= 10^5
 * 1 <= zero, one <= low
 */
public class Solution {

    private static final int MOD = 1_000_000_007;

    public int countGoodStrings(int low, int high, int zero, int one) {
        int[] memo = new int[high + 1];
        Arrays.fill(memo, -1);
        int res = 0;
        for (int i = low; i <= high; i++) {
            res = (res + process(zero, one, memo, i)) % MOD;
        }
        return res;
    }

    private int process(int zero, int one, int[] memo, int i) {
        if (i < 0) {
            return 0;
        }
        if (memo[i] != -1) {
            return memo[i];
        }
        if (i == 0) {
            return memo[i] = 1;
        }
        return memo[i] = (process(zero, one, memo, i - zero) + process(zero, one, memo, i - one)) % MOD;
    }

}

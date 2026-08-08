package com.sean.leetcode.LeetCode3348;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: xionghaiyang
 * @Date: 2026-08-08 09:59
 * @Description: https://leetcode.cn/problems/smallest-divisible-digit-product-ii
 * 3348. 最小可整除数位乘积 II
 * 给你一个字符串 num ，表示一个 正 整数，同时给你一个整数 t 。
 * 如果一个整数 没有 任何数位是 0 ，那么我们称这个整数是 无零 数字。
 * 请你返回一个字符串，这个字符串对应的整数是大于等于 num 的 最小无零 整数，且 各数位之积 能被 t 整除。
 * 如果不存在这样的数字，请你返回 "-1" 。
 * 2 <= num.length <= 2 * 10^5
 * num 只包含 ['0', '9'] 之间的数字。
 * num 不包含前导 0 。
 * 1 <= t <= 10^14
 */
public class Solution {

    public String smallestNumber(String num, long t) {
        long tmp = t;
        int cnt = 0;
        for (int p : new int[]{2, 3, 5, 7}) {
            while (tmp % p == 0) {
                tmp /= p;
                cnt++;
            }
        }
        if (tmp > 1) {
            return "-1";
        }
        cnt = Math.max(cnt - num.length() + 1, 1);
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < cnt; i++) {
            s.append("0");
        }
        s.append(num);
        String str = s.toString();
        int n = str.length();
        char[] res = new char[n];
        Arrays.fill(res, '0');
        Set<Long>[] vis = new HashSet[n];
        Arrays.setAll(vis, e -> new HashSet<>());
        dfs(0, t, true, cnt, str.toCharArray(), res, vis);
        for (int i = 0; ; i++) {
            if (res[i] != '0') {
                return new String(res, i, n - i);
            }
        }
    }

    private boolean dfs(int i, long t, boolean isLimit, int cnt, char[] s, char[] res, Set<Long>[] vis) {
        if (i == s.length) {
            return t == 1;
        }
        if (!isLimit && !vis[i].add(t)) {
            return false;
        }
        if (isLimit && i < cnt && dfs(i + 1, t, true, cnt, s, res, vis)) {
            return true;
        }
        int low = isLimit ? s[i] - '0' : 0;
        for (int d = Math.max(low, 1); d <= 9; d++) {
            if (dfs(i + 1, t / gcd(t, d), isLimit && d == low, cnt, s, res, vis)) {
                res[i] = (char) ('0' + d);
                return true;
            }
        }
        return false;
    }

    private long gcd(long a, long b) {
        return a != 0 ? gcd(b % a, a) : b;
    }

}

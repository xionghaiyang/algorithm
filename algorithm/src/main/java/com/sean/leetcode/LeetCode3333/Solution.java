package com.sean.leetcode.LeetCode3333;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2025-07-02 05:47
 * @Description https://leetcode.cn/problems/find-the-original-typed-string-ii
 * 3333. 找到初始输入字符串 II
 * Alice 正在她的电脑上输入一个字符串。
 * 但是她打字技术比较笨拙，她 可能 在一个按键上按太久，导致一个字符被输入 多次 。
 * 给你一个字符串 word ，它表示 最终 显示在 Alice 显示屏上的结果。
 * 同时给你一个 正 整数 k ，表示一开始 Alice 输入字符串的长度 至少 为 k 。
 * 请你返回 Alice 一开始可能想要输入字符串的总方案数。
 * 由于答案可能很大，请你将它对 10^9 + 7 取余 后返回。
 * 1 <= word.length <= 5 * 10^5
 * word 只包含小写英文字母。
 * 1 <= k <= 2000
 */
public class Solution {

    private static final int MOD = 1_000_000_007;

    public int possibleStringCount(String word, int k) {
        int n = word.length();
        if (n < k) {
            return 0;
        }
        List<Integer> list = new ArrayList<>();
        long res = 1;
        for (int i = 0, cnt = 0; i < n; i++) {
            cnt++;
            if (i == n - 1 || word.charAt(i) != word.charAt(i + 1)) {
                if (cnt > 1) {
                    if (k > 0) {
                        list.add(cnt - 1);
                    }
                    res = res * cnt % MOD;
                }
                k--;
                cnt = 0;
            }
        }
        if (k <= 0) {
            return (int) res;
        }
        int[] f = new int[k];
        Arrays.fill(f, 1);
        for (int c : list) {
            for (int j = 1; j < k; j++) {
                f[j] = (f[j] + f[j - 1]) % MOD;
            }
            for (int j = k - 1; j > c; j--) {
                f[j] = (f[j] - f[j - c - 1]) % MOD;
            }
        }
        return (int) ((res - f[k - 1] + MOD) % MOD);
    }

}

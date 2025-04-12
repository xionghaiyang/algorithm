package com.sean.leetcode.LeetCode3272;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author xionghaiyang
 * @Date 2025-04-12 06:35
 * @Description https://leetcode.cn/problems/find-the-count-of-good-integers
 * 3272. 统计好整数的数目
 * 给你两个 正 整数 n 和 k 。
 * 如果一个整数 x 满足以下条件，那么它被称为 k 回文 整数 。
 * x 是一个 回文整数 。
 * x 能被 k 整除。
 * 如果一个整数的数位重新排列后能得到一个 k 回文整数 ，那么我们称这个整数为 好 整数。
 * 比方说，k = 2 ，那么 2020 可以重新排列得到 2002 ，2002 是一个 k 回文串，所以 2020 是一个好整数。
 * 而 1010 无法重新排列数位得到一个 k 回文整数。
 * 请你返回 n 个数位的整数中，有多少个 好 整数。
 * 注意 ，任何整数在重新排列数位之前或者之后 都不能 有前导 0 。
 * 比方说 1010 不能重排列得到 101 。
 * 1 <= n <= 10
 * 1 <= k <= 9
 */
public class Solution {

    public long countGoodIntegers(int n, int k) {
        int[] factorial = new int[n + 1];
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }
        long res = 0;
        Set<String> set = new HashSet<>();
        int base = (int) Math.pow(10, (n - 1) & 1);
        for (int i = base; i < base * 10; i++) {
            String s = Integer.toString(i);
            s += new StringBuilder(s).reverse().substring(n & 1);
            if (Long.parseLong(s) % k != 0) {
                continue;
            }
            char[] sortedS = s.toCharArray();
            Arrays.sort(sortedS);
            if (!set.add(new String(sortedS))) {
                continue;
            }
            int[] cnt = new int[10];
            for (char c : sortedS) {
                cnt[c - '0']++;
            }
            //先填最高位，因为不能有前导0，所以最高位可以填的数有(n - cnt[0])个。
            //其余n-1个数随便排有(n-1)!种
            int ans = (n - cnt[0]) * factorial[n - 1];
            //去除重复的
            for (int c : cnt) {
                ans /= factorial[c];
            }
            res += ans;
        }
        return res;
    }

}

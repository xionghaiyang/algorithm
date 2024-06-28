package com.sean.leetcode.LeetCode1147;

import java.util.Arrays;
import java.util.Random;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-04-12 08:04
 * @Description: https://leetcode.cn/problems/longest-chunked-palindrome-decomposition/
 * 1147. 段式回文
 * 你会得到一个字符串 text 。你应该把它分成 k 个子字符串 (subtext1, subtext2，…， subtextk) ，要求满足:
 * subtexti 是 非空 字符串
 * 所有子字符串的连接等于 text ( 即subtext1 + subtext2 + ... + subtextk == text )
 * 对于所有 i 的有效值( 即 1 <= i <= k ) ，subtexti == subtextk - i + 1 均成立
 * 返回k可能最大值。
 */
public class Solution {

    public int longestDecomposition1(String text) {
        int n = text.length();
        int res = 0;
        int left = 0;
        int right = n - 1;
        while (left <= right) {
            int len = 1;
            while (left + len - 1 < right - len + 1) {
                if (judge(text, left, right - len + 1, len)) {
                    res += 2;
                    break;
                }
                len++;
            }
            if (left + len - 1 >= right - len + 1) {
                res++;
            }
            left += len;
            right -= len;
        }
        return res;
    }

    private boolean judge(String text, int left, int right, int len) {
        while (len > 0) {
            if (text.charAt(left) != text.charAt(right)) {
                return false;
            }
            left++;
            right++;
            len--;
        }
        return true;
    }

    Random random = new Random();
    int base1;
    int base2;
    long[] pow1;
    long[] pow2;
    long[] pre1;
    long[] pre2;
    int MOD1 = 1000000007;
    int MOD2 = 1000000009;

    public int longestDecomposition(String text) {
        init(text);
        int n = text.length();
        int res = 0;
        int left = 0;
        int right = n - 1;
        while (left <= right) {
            int len = 1;
            while (left + len - 1 < right - len + 1) {
                if (Arrays.equals(getHash(left, left + len - 1), getHash(right - len + 1, right))) {
                    res += 2;
                    break;
                }
                len++;
            }
            if (left + len - 1 >= right - len + 1) {
                res++;
            }
            left += len;
            right -= len;
        }
        return res;
    }

    private void init(String s) {
        base1 = 1000000 + random.nextInt(9000000);
        base2 = 1000000 + random.nextInt(9000000);
        while (base2 == base1) {
            base2 = 1000000 + random.nextInt(9000000);
        }
        int n = s.length();
        pow1 = new long[n];
        pow2 = new long[n];
        pre1 = new long[n + 1];
        pre2 = new long[n + 1];
        pow1[0] = 1;
        pow2[0] = 1;
        pre1[1] = s.charAt(0);
        pre2[1] = s.charAt(0);
        for (int i = 1; i < n; i++) {
            pow1[i] = (pow1[i - 1] * base1) % MOD1;
            pow2[i] = (pow2[i - 1] * base2) % MOD2;
            pre1[i + 1] = (pre1[i] * base1 + s.charAt(i)) % MOD1;
            pre2[i + 1] = (pre2[i] * base2 + s.charAt(i)) % MOD2;
        }
    }

    private long[] getHash(int left, int right) {
        return new long[]{(pre1[right + 1] - ((pre1[left] * pow1[right + 1 - left]) % MOD1) + MOD1) % MOD1, (pre2[right + 1] - ((pre2[left] * pow2[right + 1 - left]) % MOD2) + MOD2) % MOD2};
    }

}

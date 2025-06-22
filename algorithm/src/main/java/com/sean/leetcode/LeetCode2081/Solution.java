package com.sean.leetcode.LeetCode2081;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2025-06-23 05:18
 * @Description https://leetcode.cn/problems/sum-of-k-mirror-numbers
 * 2081. k 镜像数字的和
 * 一个 k 镜像数字 指的是一个在十进制和 k 进制下从前往后读和从后往前读都一样的 没有前导 0 的 正 整数。
 * 比方说，9 是一个 2 镜像数字。
 * 9 在十进制下为 9 ，二进制下为 1001 ，两者从前往后读和从后往前读都一样。
 * 相反地，4 不是一个 2 镜像数字。
 * 4 在二进制下为 100 ，从前往后和从后往前读不相同。
 * 给你进制 k 和一个数字 n ，请你返回 k 镜像数字中 最小 的 n 个数 之和 。
 * 2 <= k <= 9
 * 1 <= n <= 30
 */
public class Solution {

    private static final int MAX_N = 30;
    private static final List<Long>[] res = new ArrayList[10];
    private static boolean initialized = false;

    private void init() {
        if (initialized) {
            return;
        }
        initialized = true;
        Arrays.setAll(res, i -> new ArrayList<>());
        for (int base = 1; ; base *= 10) {
            //生成奇数长度回文数,例如 base = 10,生成的范围是101~999
            for (int i = base; i < base * 10; i++) {
                long x = i;
                for (int t = i / 10; t > 0; t /= 10) {
                    x = x * 10 + t % 10;
                }
                if (doPalindrome(x)) {
                    return;
                }
            }
            //生成偶数长度回文数,例如base = 10,生成的范围是1001~9999
            for (int i = base; i < base * 10; i++) {
                long x = i;
                for (int t = i; t > 0; t /= 10) {
                    x = x * 10 + t % 10;
                }
                if (doPalindrome(x)) {
                    return;
                }
            }
        }
    }

    private boolean doPalindrome(long x) {
        boolean done = true;
        for (int k = 2; k < 10; k++) {
            if (res[k].size() < MAX_N && isKPalindrome(x, k)) {
                res[k].add(x);
            }
            if (res[k].size() < MAX_N) {
                done = false;
            }
        }
        if (!done) {
            return false;
        }
        for (int k = 2; k < 10; k++) {
            List<Long> s = res[k];
            for (int i = 1; i < MAX_N; i++) {
                s.set(i, s.get(i) + s.get(i - 1));
            }
        }
        return true;
    }

    private boolean isKPalindrome(long x, int k) {
        if (x % k == 0) {
            return false;
        }
        int rev = 0;
        while (rev < x / k) {
            rev = rev * k + (int) (x % k);
            x /= k;
        }
        return rev == x || rev == x / k;
    }

    public long kMirror(int k, int n) {
        init();
        return res[k].get(n - 1);
    }

}

package com.sean.leetcode.LeetCode3646;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2025-08-12 12:28
 * @Description https://leetcode.cn/problems/next-special-palindrome-number
 * 3646. 下一个特殊回文数
 * 给你一个整数 n。
 * 如果一个数满足以下条件，那么它被称为 特殊数 ：
 * 它是一个 回文数 。
 * 数字中每个数字 k 出现 恰好 k 次。
 * 返回 严格 大于 n 的 最小 特殊数。
 * 如果一个整数正向读和反向读都相同，则它是 回文数 。
 * 例如，121 是回文数，而 123 不是。
 * 0 <= n <= 10^15
 */
public class Solution {

    private static final int ODD_MASK = 0x155;
    private static final int D = 9;
    private static final List<Long> specialNumbers = new ArrayList<>();
    private static boolean initialized = false;

    private void init() {
        if (initialized) {
            return;
        }
        initialized = true;
        for (int mask = 1; mask < (1 << D); mask++) {
            int t = mask & ODD_MASK;
            //至少有两个奇数
            if ((t & (t - 1)) > 0) {
                continue;
            }
            int size = 0;
            for (int i = 0; i < D; i++) {
                if ((mask >> i & 1) != 0) {
                    size += i + 1;
                }
            }
            //回文串太长了
            if (size > 16) {
                continue;
            }
            int[] perm = new int[size / 2];
            int index = 0, odd = 0;
            for (int x = 1; x <= D; x++) {
                if ((mask >> (x - 1) & 1) > 0) {
                    for (int k = 0; k < x / 2; k++) {
                        perm[index++] = x;
                    }
                    if ((x & 1) != 0) {
                        odd = x;
                    }
                }
            }
            boolean[] onPath = new boolean[perm.length];
            //枚举perm的所有排列，生成对应的回文数
            dfs(0, 0, onPath, perm, odd);
        }
        Collections.sort(specialNumbers);
    }

    private void dfs(int i, long res, boolean[] onPath, int[] perm, int odd) {
        if (i == perm.length) {
            long v = res;
            if (odd > 0) {
                res = res * 10 + odd;
            }
            //反转x的左半，拼在x后面
            while (v > 0) {
                res = res * 10 + v % 10;
                v /= 10;
            }
            specialNumbers.add(res);
            return;
        }
        for (int j = 0; j < perm.length; j++) {
            if (onPath[j] || (j > 0 && perm[j] == perm[j - 1] && !onPath[j - 1])) {
                continue;
            }
            onPath[j] = true;
            dfs(i + 1, res * 10 + perm[j], onPath, perm, odd);
            onPath[j] = false;
        }
    }

    public long specialPalindrome(long n) {
        init();
        return specialNumbers.get(upperBound(specialNumbers, n));
    }

    //第一个大于target的数的下标,如果不存在返回list.size()
    private int upperBound(List<Long> list, long target) {
        int left = 0, right = list.size() - 1, res = list.size();
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (list.get(mid) > target) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

}

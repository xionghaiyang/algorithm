package com.sean.leetcode.LeetCode1461;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author xionghaiyang
 * @Date 2026-02-23 07:42
 * @Description https://leetcode.cn/problems/check-if-a-string-contains-all-binary-codes-of-size-k
 * 1461. 检查一个字符串是否包含所有长度为 K 的二进制子串
 * 给你一个二进制字符串 s 和一个整数 k 。
 * 如果所有长度为 k 的二进制字符串都是 s 的子串，请返回 true ，否则请返回 false 。
 * 1 <= s.length <= 5 * 10^5
 * s[i] 不是'0' 就是 '1'
 * 1 <= k <= 20
 */
public class Solution {

    public boolean hasAllCodes(String s, int k) {
        int n = s.length();
        Set<String> set = new HashSet<>();
        for (int i = k; i <= n; i++) {
            set.add(s.substring(i - k, i));
        }
        return set.size() == (1 << k);
    }

    public boolean hasAllCodes1(String s, int k) {
        int n = s.length();
        if (n - k + 1 < (1 << k)) {
            return false;
        }
        boolean[] exist = new boolean[1 << k];
        int num = 0;
        for (int i = 0; i < k; i++) {
            num = (num << 1) | (s.charAt(i) - '0');
        }
        exist[num] = true;
        int mask = (1 << (k - 1)) - 1;
        for (int i = k; i < n; i++) {
            num = ((num & mask) << 1) | (s.charAt(i) - '0');
            exist[num] = true;
        }
        for (int i = 0; i < exist.length; i++) {
            if (!exist[i]) {
                return false;
            }
        }
        return true;
    }

}

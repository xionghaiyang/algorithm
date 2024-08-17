package com.sean.leetcode.LeetCode3137;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xionghaiyang
 * @Date 2024-08-17 10:56
 * @Description https://leetcode.cn/problems/minimum-number-of-operations-to-make-word-k-periodic/
 * 3137. K 周期字符串需要的最少操作次数
 * 给你一个长度为 n 的字符串 word 和一个整数 k ，其中 k 是 n 的因数。
 * 在一次操作中，你可以选择任意两个下标 i 和 j，其中 0 <= i, j < n ，
 * 且这两个下标都可以被 k 整除，然后用从 j 开始的长度为 k 的子串替换从 i 开始的长度为 k 的子串。
 * 也就是说，将子串 word[i..i + k - 1] 替换为子串 word[j..j + k - 1] 。
 * 返回使 word 成为 K 周期字符串 所需的 最少 操作次数。
 * 如果存在某个长度为 k 的字符串 s，使得 word 可以表示为任意次数连接 s ，则称字符串 word 是 K 周期字符串 。
 * 例如，如果 word == "ababab"，那么 word 就是 s = "ab" 时的 2 周期字符串 。
 */
public class Solution {

    public int minimumOperationsToMakeKPeriodic(String word, int k) {
        int n = word.length();
        Map<String, Integer> map = new HashMap<>();
        int max = 0;
        for (int i = k; i <= n; i += k) {
            String str = word.substring(i - k, i);
            int c = map.merge(str, 1, Integer::sum);//c = ++map[sub]
            max = Math.max(max, c);
        }
        return n / k - max;
    }

}

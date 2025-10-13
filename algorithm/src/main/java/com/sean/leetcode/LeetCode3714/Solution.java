package com.sean.leetcode.LeetCode3714;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xionghaiyang
 * @Date 2025-10-13 12:28
 * @Description https://leetcode.cn/problems/longest-balanced-substring-ii
 * 3714. 最长的平衡子串 II
 * 给你一个只包含字符 'a'、'b' 和 'c' 的字符串 s。
 * 如果一个 子串 中所有 不同 字符出现的次数都 相同，则称该子串为 平衡 子串。
 * 请返回 s 的 最长平衡子串 的 长度 。
 * 子串 是字符串中连续的、非空 的字符序列。
 * 1 <= s.length <= 10^5
 * s 仅包含字符 'a'、'b' 和 'c'。
 */
public class Solution {

    public int longestBalanced(String s) {
        char[] str = s.toCharArray();
        int n = str.length;
        int res = 0;
        //一种字母
        for (int i = 0; i < n; ) {
            int start = i;
            do {
                i++;
            } while (i < n && str[i] == str[i - 1]);
            res = Math.max(res, i - start);
        }
        //两种字母
        res = Math.max(res, f(str, 'a', 'b'));
        res = Math.max(res, f(str, 'a', 'c'));
        res = Math.max(res, f(str, 'b', 'c'));
        //三种字母
        Map<Long, Integer> map = new HashMap<>();
        map.put((long) n << 32 | n, -1);
        int[] cnt = new int[3];
        for (int i = 0; i < n; i++) {
            cnt[str[i] - 'a']++;
            long p = (long) (cnt[0] - cnt[1] + n) << 32 | (cnt[1] - cnt[2] + n);
            if (map.containsKey(p)) {
                res = Math.max(res, i - map.get(p));
            } else {
                map.put(p, i);
            }
        }
        return res;
    }

    private int f(char[] str, char x, char y) {
        int n = str.length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            Map<Integer, Integer> map = new HashMap<>();
            map.put(0, i - 1);
            int diff = 0;
            while (i < n && (str[i] == x || str[i] == y)) {
                diff += str[i] == x ? 1 : -1;
                if (map.containsKey(diff)) {
                    res = Math.max(res, i - map.get(diff));
                } else {
                    map.put(diff, i);
                }
                i++;
            }
        }
        return res;
    }

}

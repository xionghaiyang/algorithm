package com.sean.leetcode.LeetCode1542;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xionghaiyang
 * @Date 2024-05-20 10:02
 * @Description https://leetcode.cn/problems/find-longest-awesome-substring/
 * 1542. 找出最长的超赞子字符串
 * 给你一个字符串 s 。
 * 请返回 s 中最长的 超赞子字符串 的长度。
 * 「超赞子字符串」需满足满足下述两个条件：
 * 该字符串是 s 的一个非空子字符串
 * 进行任意次数的字符交换后，该字符串可以变成一个回文字符串
 */
public class Solution {

    public int longestAwesome(String s) {
        int n = s.length();
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int res = 0, status = 0;
        for (int i = 0; i < n; i++) {
            int digit = s.charAt(i) - '0';
            status ^= (1 << digit);
            if (map.containsKey(status)) {
                res = Math.max(res, i - map.get(status));
            } else {
                map.put(status, i);
            }
            for (int j = 0; j < 10; j++) {
                if (map.containsKey(status ^ (1 << j))) {
                    res = Math.max(res, i - map.get(status ^ (1 << j)));
                }
            }
        }
        return res;
    }

}

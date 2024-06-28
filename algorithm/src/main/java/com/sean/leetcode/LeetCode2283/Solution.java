package com.sean.leetcode.LeetCode2283;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-01-11 09:45
 * @Description: https://leetcode.cn/problems/check-if-number-has-equal-digit-count-and-digit-value/
 * 2283. 判断一个数的数字计数是否等于数位的值
 * 给你一个下标从 0 开始长度为 n 的字符串 num ，它只包含数字。
 * 如果对于 每个 0 <= i < n 的下标 i ，都满足数位 i 在 num 中出现了 num[i]次，那么请你返回 true ，否则返回 false 。
 */
public class Solution {

    public boolean digitCount(String num) {
        if (num == null || num.length() == 0) {
            return true;
        }
        int n = num.length();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int j = num.charAt(i) - '0';
            map.put(j, map.getOrDefault(j, 0) + 1);
        }
        for (int i = 0; i < n; i++) {
            int j = num.charAt(i) - '0';
            if (map.getOrDefault(i, 0) != j) {
                return false;
            }
        }
        return true;
    }

}

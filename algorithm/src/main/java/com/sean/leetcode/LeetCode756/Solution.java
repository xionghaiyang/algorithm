package com.sean.leetcode.LeetCode756;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2025-09-08 18:04
 * @Description https://leetcode.cn/problems/pyramid-transition-matrix
 * 756. 金字塔转换矩阵
 * 你正在把积木堆成金字塔。每个块都有一个颜色，用一个字母表示。
 * 每一行的块比它下面的行 少一个块 ，并且居中。
 * 为了使金字塔美观，只有特定的 三角形图案 是允许的。
 * 一个三角形的图案由 两个块 和叠在上面的 单个块 组成。
 * 模式是以三个字母字符串的列表形式 allowed 给出的，其中模式的前两个字符分别表示左右底部块，第三个字符表示顶部块。
 * 例如，"ABC" 表示一个三角形图案，其中一个 “C” 块堆叠在一个 'A' 块(左)和一个 'B' 块(右)之上。
 * 请注意，这与 "BAC" 不同，"B" 在左下角，"A" 在右下角。
 * 你从作为单个字符串给出的底部的一排积木 bottom 开始，必须 将其作为金字塔的底部。
 * 在给定 bottom 和 allowed 的情况下，如果你能一直构建到金字塔顶部，使金字塔中的 每个三角形图案 都是在 allowed 中的，则返回 true ，否则返回 false 。
 * 2 <= bottom.length <= 6
 * 0 <= allowed.length <= 216
 * allowed[i].length == 3
 * 所有输入字符串中的字母来自集合 {'A', 'B', 'C', 'D', 'E', 'F'}。
 * allowed 中所有值都是 唯一的
 */
public class Solution {

    private Map<String, List<String>> map;
    private Map<String, Boolean> memo;

    public boolean pyramidTransition(String bottom, List<String> allowed) {
        map = new HashMap<>();
        for (String s : allowed) {
            map.computeIfAbsent(s.substring(0, 2), i -> new ArrayList<>()).add(s.substring(2));
        }
        memo = new HashMap<>();
        return process(bottom, "");
    }

    private boolean process(String str1, String str2) {
        String key = str1 + ":" + str2;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        if (str2.length() >= 2 && !process(str2, "")) {
            return persist(key, false);
        }
        if (str1.length() == 2) {
            if (str2.isEmpty()) {
                return persist(key, map.containsKey(str1));
            }
            for (String t : map.getOrDefault(str1, new ArrayList<>())) {
                if (process(str2 + t, "")) {
                    return persist(key, true);
                }
            }
            return persist(key, false);
        }
        String sub = str1.substring(1);
        for (String t : map.getOrDefault(str1.substring(0, 2), new ArrayList<>())) {
            if (process(sub, str2 + t)) {
                return persist(key, true);
            }
        }
        return persist(key, false);
    }

    private boolean persist(String key, boolean value) {
        memo.put(key, value);
        return value;
    }

}

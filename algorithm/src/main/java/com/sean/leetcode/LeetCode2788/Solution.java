package com.sean.leetcode.LeetCode2788;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-01-20 12:32
 * @Description: https://leetcode.cn/problems/split-strings-by-separator/
 * 2788. 按分隔符拆分字符串
 * 给你一个字符串数组 words 和一个字符 separator ，请你按 separator 拆分 words 中的每个字符串。
 * 返回一个由拆分后的新字符串组成的字符串数组，不包括空字符串 。
 * 注意
 * separator 用于决定拆分发生的位置，但它不包含在结果字符串中。
 * 拆分可能形成两个以上的字符串。
 * 结果字符串必须保持初始相同的先后顺序。
 */
public class Solution {

    public List<String> splitWordsBySeparator(List<String> words, char separator) {
        List<String> res = new ArrayList<>();
        for (String word : words) {
            StringBuilder sb = new StringBuilder();
            int left = 0, right = 0, n = word.length();
            while (right < n) {
                char c = word.charAt(right);
                if (c == separator) {
                    if (left < right) {
                        res.add(sb.toString());
                        sb.setLength(0);
                    }
                    left = right + 1;
                } else {
                    sb.append(c);
                }
                right++;
            }
            if (left < right) {
                res.add(sb.toString());
            }
        }
        return res;
    }

    public List<String> splitWordsBySeparator1(List<String> words, char separator) {
        List<String> res = new ArrayList<>();
        for (String word : words) {
            int n = word.length();
            for (int left = 0, right = 0; right < n; right++) {
                if (word.charAt(right) == separator) {
                    if (right > left) {
                        res.add(word.substring(left, right));
                    }
                    left = right + 1;
                } else if (right == n - 1) {
                    res.add(word.substring(left, n));
                }
            }
        }
        return res;
    }

}

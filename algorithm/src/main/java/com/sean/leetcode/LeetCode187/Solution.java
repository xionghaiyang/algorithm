package com.sean.leetcode.LeetCode187;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author xionghaiyang
 * @Date 2025-08-19 20:39
 * @Description https://leetcode.cn/problems/repeated-dna-sequences
 * 187. 重复的DNA序列
 * DNA序列 由一系列核苷酸组成，缩写为 'A', 'C', 'G' 和 'T'.。
 * 例如，"ACGAATTCCG" 是一个 DNA序列 。
 * 在研究 DNA 时，识别 DNA 中的重复序列非常有用。
 * 给定一个表示 DNA序列 的字符串 s ，返回所有在 DNA 分子中出现不止一次的 长度为 10 的序列(子字符串)。
 * 你可以按 任意顺序 返回答案。
 * 0 <= s.length <= 10^5
 * s[i]=='A'、'C'、'G' or 'T'
 */
public class Solution {

    public List<String> findRepeatedDnaSequences(String s) {
        List<String> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        int n = s.length();
        for (int i = 0, L = 10; i <= n - L; i++) {
            String sub = s.substring(i, i + L);
            map.put(sub, map.getOrDefault(sub, 0) + 1);
            if (map.get(sub) == 2) {
                res.add(sub);
            }
        }
        return res;
    }

}

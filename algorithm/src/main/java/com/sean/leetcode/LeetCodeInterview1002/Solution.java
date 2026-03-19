package com.sean.leetcode.LeetCodeInterview1002;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author xionghaiyang
 * @Date 2026-03-19 12:37
 * @Description https://leetcode.cn/problems/group-anagrams-lcci
 * 面试题 10.02. 变位词组
 * 编写一种方法，对字符串数组进行排序，将所有变位词组合在一起。
 * 变位词是指字母相同，但排列不同的字符串。
 * 注意：本题相对原题稍作修改
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序。
 */
public class Solution {

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            String key = getKey(str);
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
        }
        return new ArrayList<>(map.values());
    }

    private String getKey(String str) {
        int[] cnt = new int[26];
        for (char c : str.toCharArray()) {
            cnt[c - 'a']++;
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (cnt[i] > 0) {
                res.append('a' + i).append(cnt[i]);
            }
        }
        return res.toString();
    }

}

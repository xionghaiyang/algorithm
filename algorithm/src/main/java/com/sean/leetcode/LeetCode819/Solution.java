package com.sean.leetcode.LeetCode819;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author xionghaiyang
 * @Date 2025-10-20 12:34
 * @Description https://leetcode.cn/problems/most-common-word
 * 819. 最常见的单词
 * 给你一个字符串 paragraph 和一个表示禁用词的字符串数组 banned ，返回出现频率最高的非禁用词。
 * 题目数据 保证 至少存在一个非禁用词，且答案 唯一 。
 * paragraph 中的单词 不区分大小写 ，答案应以 小写 形式返回。
 * 注意 单词不包含标点符号。
 * 1 <= paragraph.length <= 1000
 * paragraph 由英文字母、空格 ' '、和以下符号组成："!?',;."
 * 0 <= banned.length <= 100
 * 1 <= banned[i].length <= 10
 * banned[i] 仅由小写英文字母组成
 */
public class Solution {

    public String mostCommonWord(String paragraph, String[] banned) {
        Set<String> set = new HashSet<>();
        for (String s : banned) {
            set.add(s);
        }
        char[] str = paragraph.toCharArray();
        int n = str.length;
        Map<String, Integer> map = new HashMap<>();
        String res = null;
        for (int i = 0; i < n; ) {
            if (!Character.isLetter(str[i])) {
                i++;
                continue;
            }
            int j = i;
            while (j < n && Character.isLetter(str[j])) {
                j++;
            }
            String sub = paragraph.substring(i, j).toLowerCase();
            i = j + 1;
            if (set.contains(sub)) {
                continue;
            }
            map.put(sub, map.getOrDefault(sub, 0) + 1);
            if (res == null || map.get(sub) > map.get(res)) {
                res = sub;
            }
        }
        return res;
    }

}

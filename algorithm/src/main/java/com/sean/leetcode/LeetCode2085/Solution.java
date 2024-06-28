package com.sean.leetcode.LeetCode2085;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-01-12 10:20
 * @Description: https://leetcode.cn/problems/count-common-words-with-one-occurrence/
 * 2085. 统计出现过一次的公共字符串
 * 给你两个字符串数组 words1 和 words2 ，请你返回在两个字符串数组中 都恰好出现一次 的字符串的数目。
 */
public class Solution {

    public int countWords(String[] words1, String[] words2) {
        Map<String, Integer> map = new HashMap<>();
        for (String word : words1) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        for (String word : words2) {
            if (map.containsKey(word)) {
                if (map.get(word) > 1) {
                    map.remove(word);
                    continue;
                }
                map.put(word, map.get(word) - 1);
                if (map.get(word) < 0) {
                    map.remove(word);
                }
            }
        }
        int res = 0;
        for (int value : map.values()) {
            if (value == 0) {
                res++;
            }
        }
        return res;
    }

}

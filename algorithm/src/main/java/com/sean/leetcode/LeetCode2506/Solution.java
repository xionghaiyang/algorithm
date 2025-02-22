package com.sean.leetcode.LeetCode2506;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xionghaiyang
 * @Date 2025-02-22 09:02
 * @Description https://leetcode.cn/problems/count-pairs-of-similar-strings/
 * 2506. 统计相似字符串对的数目
 * 给你一个下标从 0 开始的字符串数组 words 。
 * 如果两个字符串由相同的字符组成，则认为这两个字符串 相似 。
 * 例如，"abca" 和 "cba" 相似，因为它们都由字符 'a'、'b'、'c' 组成。
 * 然而，"abacba" 和 "bcfd" 不相似，因为它们不是相同字符组成的。
 * 请你找出满足字符串 words[i] 和 words[j] 相似的下标对 (i, j) ，并返回下标对的数目，其中 0 <= i < j <= words.length - 1 。
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 100
 * words[i] 仅由小写英文字母组成
 */
public class Solution {

    public int similarPairs(String[] words) {
        Map<Integer, Integer> map = new HashMap<>();
        for (String word : words) {
            int key = 0;
            for (int i = 0; i < word.length(); i++) {
                key |= 1 << (word.charAt(i) - 'a');
            }
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
        int res = 0;
        for (int value : map.values()) {
            if (value > 1) {
                res += value * (value - 1) / 2;
            }
        }
        return res;
    }

}

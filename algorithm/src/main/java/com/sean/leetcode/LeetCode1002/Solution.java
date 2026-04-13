package com.sean.leetcode.LeetCode1002;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2026-04-13 19:04
 * @Description https://leetcode.cn/problems/find-common-characters
 * 1002. 查找共用字符
 * 给你一个字符串数组 words ，请你找出所有在 words 的每个字符串中都出现的共用字符（包括重复字符），并以数组形式返回。
 * 你可以按 任意顺序 返回答案。
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 100
 * words[i] 由小写英文字母组成
 */
public class Solution {

    public List<String> commonChars(String[] words) {
        int[] minFreq = new int[26];
        Arrays.fill(minFreq, Integer.MAX_VALUE);
        for (String word : words) {
            int[] freq = new int[26];
            for (char c : word.toCharArray()) {
                freq[c - 'a']++;
            }
            for (int i = 0; i < 26; i++) {
                minFreq[i] = Math.min(minFreq[i], freq[i]);
            }
        }
        List<String> res = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < minFreq[i]; j++) {
                res.add(String.valueOf((char) (i + 'a')));
            }
        }
        return res;
    }

}

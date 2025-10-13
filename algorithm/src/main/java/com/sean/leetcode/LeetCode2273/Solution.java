package com.sean.leetcode.LeetCode2273;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2025-10-13 09:07
 * @Description https://leetcode.cn/problems/find-resultant-array-after-removing-anagrams
 * 2273. 移除字母异位词后的结果数组
 * 给你一个下标从 0 开始的字符串 words ，其中 words[i] 由小写英文字符组成。
 * 在一步操作中，需要选出任一下标 i ，从 words 中 删除 words[i] 。
 * 其中下标 i 需要同时满足下述两个条件：
 * 0 < i < words.length
 * words[i - 1] 和 words[i] 是 字母异位词 。
 * 只要可以选出满足条件的下标，就一直执行这个操作。
 * 在执行所有操作后，返回 words 。
 * 可以证明，按任意顺序为每步操作选择下标都会得到相同的结果。
 * 字母异位词 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母通常恰好只用一次。
 * 例如，"dacb" 是 "abdc" 的一个字母异位词。
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 10
 * words[i] 由小写英文字母组成
 */
public class Solution {

    public List<String> removeAnagrams(String[] words) {
        List<String> res = new ArrayList<>();
        res.add(words[0]);
        int n = words.length;
        for (int i = 1; i < n; i++) {
            if (!compare(words[i - 1], words[i])) {
                res.add(words[i]);
            }
        }
        return res;
    }

    private boolean compare(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }
        int[] freq = new int[26];
        for (char c : word1.toCharArray()) {
            freq[c - 'a']++;
        }
        for (char c : word2.toCharArray()) {
            freq[c - 'a']--;
        }
        for (int x : freq) {
            if (x != 0) {
                return false;
            }
        }
        return true;
    }

}

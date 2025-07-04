package com.sean.leetcode.LeetCode30;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2025-07-04 10:10
 * @Description https://leetcode.cn/problems/substring-with-concatenation-of-all-words
 * 30. 串联所有单词的子串
 * 给定一个字符串 s 和一个字符串数组 words。
 * words 中所有字符串 长度相同。
 * s 中的 串联子串 是指一个包含  words 中所有字符串以任意顺序排列连接起来的子串。
 * 例如，如果 words = ["ab","cd","ef"]， 那么 "abcdef"， "abefcd"，"cdabef"， "cdefab"，"efabcd"， 和 "efcdab" 都是串联子串。
 * "acdbef" 不是串联子串，因为他不是任何 words 排列的连接。
 * 返回所有串联子串在 s 中的开始索引。
 * 你可以以 任意顺序 返回答案。
 * 1 <= s.length <= 10^4
 * 1 <= words.length <= 5000
 * 1 <= words[i].length <= 30
 * words[i] 和 s 由小写英文字母组成
 */
public class Solution {

    public List<Integer> findSubstring(String s, String[] words) {
        //一个单词的长度
        int wordLength = words[0].length();
        //所有单词的总长度，即窗口大小
        int windowLength = wordLength * words.length;
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        List<Integer> res = new ArrayList<>();
        //枚举窗口起点，做wordLength次滑动窗口
        for (int start = 0; start < wordLength; start++) {
            Map<String, Integer> cnt = new HashMap<>();
            int overload = 0;
            //枚举窗口窗口最后一个单词的右端点+1
            for (int right = start + wordLength; right <= s.length(); right += wordLength) {
                //inWord进入窗口
                String inWord = s.substring(right - wordLength, right);
                if (cnt.getOrDefault(inWord, 0).equals(map.getOrDefault(inWord, 0))) {
                    overload++;
                }
                cnt.put(inWord, cnt.getOrDefault(inWord, 0) + 1);
                //窗口第一个单词的左端点
                int left = right - windowLength;
                if (left < 0) {
                    continue;
                }
                //更新答案
                //如果没有超出map的单词，那么也不会有少于map的单词
                if (overload == 0) {
                    res.add(left);
                }
                //窗口最左边的单词outWord离开窗口，为下一轮循环做准备
                String outWord = s.substring(left, left + wordLength);
                cnt.put(outWord, cnt.getOrDefault(outWord, 0) - 1);
                if (cnt.getOrDefault(outWord, 0).equals(map.getOrDefault(outWord, 0))) {
                    overload--;
                }
            }
        }
        return res;
    }

}

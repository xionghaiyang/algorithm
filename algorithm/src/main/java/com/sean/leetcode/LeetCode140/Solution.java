package com.sean.leetcode.LeetCode140;

import java.util.*;

/**
 * @Author: xionghaiyang
 * @Date: 2026-09-04 15:58
 * @Description: https://leetcode.cn/problems/word-break-ii
 * 140. 单词拆分 II
 * 给定一个字符串 s 和一个字符串字典 wordDict ，在字符串 s 中增加空格来构建一个句子，使得句子中所有的单词都在词典中。
 * 以任意顺序 返回所有这些可能的句子。
 * 注意：词典中的同一个单词可能在分段中被重复使用多次。
 * 1 <= s.length <= 20
 * 1 <= wordDict.length <= 1000
 * 1 <= wordDict[i].length <= 10
 * s 和 wordDict[i] 仅有小写英文字母组成
 * wordDict 中所有字符串都 不同
 */
public class Solution {

    private List<String> res = new ArrayList<>();
    private List<String> temp = new ArrayList<>();

    public List<String> wordBreak(String s, List<String> wordDict) {
        dfs(s, new HashSet<>(wordDict), 0);
        return res;
    }

    private void dfs(String s, Set<String> wordSet, int index) {
        int n = s.length();
        if (index == n) {
            res.add(String.join(" ", temp));
            return;
        }
        for (int i = index + 1; i <= n; i++) {
            String word = s.substring(index, i);
            if (wordSet.contains(word)) {
                temp.add(word);
                dfs(s, wordSet, i);
                temp.remove(temp.size() - 1);
            }
        }
    }

}

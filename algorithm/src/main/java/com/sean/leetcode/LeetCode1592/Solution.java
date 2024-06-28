package com.sean.leetcode.LeetCode1592;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-07 08:23
 * @Description: https://leetcode.cn/problems/rearrange-spaces-between-words/
 * 1592. 重新排列单词间的空格
 * 给你一个字符串 text ，该字符串由若干被空格包围的单词组成。每个单词由一个或者多个小写英文字母组成，并且两个单词之间至少存在一个空格。
 * 题目测试用例保证 text 至少包含一个单词 。
 * 请你重新排列空格，使每对相邻单词之间的空格数目都 相等 ，并尽可能 最大化 该数目。
 * 如果不能重新平均分配所有空格，请 将多余的空格放置在字符串末尾 ，这也意味着返回的字符串应当与原 text 字符串的长度相等。
 * 返回 重新排列空格后的字符串 。
 */
public class Solution {

    public String reorderSpaces(String text) {
        String[] words = text.trim().split("\\s+");
        int n = words.length;
        int cnt = text.length();
        for (String word : words) {
            cnt -= word.length();
        }
        StringBuilder res = new StringBuilder();
        if (n == 1) {
            res.append(words[0]);
            for (int i = 0; i < cnt; i++) {
                res.append(' ');
            }
            return res.toString();
        }
        int perCnt = cnt / (n - 1);
        int restCnt = cnt % (n - 1);
        for (int i = 0; i < n; i++) {
            if (i > 0) {
                for (int j = 0; j < perCnt; j++) {
                    res.append(' ');
                }
            }
            res.append(words[i]);
        }
        for (int i = 0; i < restCnt; i++) {
            res.append(' ');
        }
        return res.toString();
    }

}

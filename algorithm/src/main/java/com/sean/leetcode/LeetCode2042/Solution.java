package com.sean.leetcode.LeetCode2042;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-01-03 09:20
 * @Description: https://leetcode.cn/problems/check-if-numbers-are-ascending-in-a-sentence/
 * 2042. 检查句子中的数字是否递增
 * 句子是由若干 token 组成的一个列表，token 间用 单个 空格分隔，句子没有前导或尾随空格。
 * 每个 token 要么是一个由数字 0-9 组成的不含前导零的 正整数 ，要么是一个由小写英文字母组成的 单词 。
 * 示例，"a puppy has 2 eyes 4 legs" 是一个由 7 个 token 组成的句子："2" 和 "4" 是数字，其他像 "puppy" 这样的 tokens 属于单词。
 * 给你一个表示句子的字符串 s ，你需要检查 s 中的 全部 数字是否从左到右严格递增（即，除了最后一个数字，s 中的 每个 数字都严格小于它 右侧 的数字）。
 * 如果满足题目要求，返回 true ，否则，返回 false 。
 */
public class Solution {

    public boolean areNumbersAscending1(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        String[] splits = s.split(" ");
        int i = -1;
        for (String split : splits) {
            if (Character.isDigit(split.charAt(0))) {
                int j = Integer.parseInt(split);
                if (j > i) {
                    i = j;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean areNumbersAscending(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        int n = s.length();
        int i = 0, pre = -1;
        while (i < n) {
            if (Character.isDigit(s.charAt(i))) {
                int cur = 0;
                while (i < n && Character.isDigit(s.charAt(i))) {
                    cur = cur * 10 + s.charAt(i) - '0';
                    i++;
                }
                if (cur <= pre) {
                    return false;
                }
                pre = cur;
            } else {
                i++;
            }
        }
        return true;
    }

}

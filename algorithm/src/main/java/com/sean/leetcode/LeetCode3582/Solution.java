package com.sean.leetcode.LeetCode3582;

/**
 * @Author xionghaiyang
 * @Date 2025-06-17 12:22
 * @Description https://leetcode.cn/problems/generate-tag-for-video-caption/
 * 3582. 为视频标题生成标签
 * 给你一个字符串 caption，表示一个视频的标题。
 * 需要按照以下步骤 按顺序 生成一个视频的 有效标签 ：
 * 将 所有单词 组合为单个 驼峰命名字符串 ，并在前面加上 '#'。
 * 驼峰命名字符串 指的是除第一个单词外，其余单词的首字母大写，且每个单词的首字母之后的字符必须是小写。
 * 移除 所有不是英文字母的字符，但 保留 第一个字符 '#'。
 * 将结果 截断 为最多 100 个字符。
 * 对 caption 执行上述操作后，返回生成的 标签 。
 * 1 <= caption.length <= 150
 * caption 仅由英文字母和 ' ' 组成。
 */
public class Solution {

    public String generateTag(String caption) {
        String[] words = caption.trim().split(" ");
        int n = words.length;
        StringBuilder res = new StringBuilder();
        res.append('#');
        int length = 1;
        for (int i = 0; i < n && length < 100; i++) {
            String word = words[i].toLowerCase();
            int m = word.length();
            for (int j = 0; j < m && length < 100; j++) {
                char c = word.charAt(j);
                if (Character.isLetter(c)) {
                    if (j == 0 && i != 0) {
                        res.append(Character.toUpperCase(c));
                    } else {
                        res.append(c);
                    }
                    length++;
                }
            }
        }
        return res.toString();
    }

}

package com.sean.leetcode.LeetCode68;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-15 18:07
 * @Description: https://leetcode.cn/problems/text-justification
 * 68. 文本左右对齐
 * 给定一个单词数组 words 和一个长度 maxWidth ，重新排版单词，
 * 使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。
 * 你应该使用 “贪心算法” 来放置给定的单词；也就是说，尽可能多地往每行中放置单词。
 * 必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。
 * 要求尽可能均匀分配单词间的空格数量。
 * 如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。
 * 文本的最后一行应为左对齐，且单词之间不插入额外的空格。
 * 注意:
 * 单词是指由非空格字符组成的字符序列。
 * 每个单词的长度大于 0，小于等于 maxWidth。
 * 输入单词数组 words 至少包含一个单词。
 * 1 <= words.length <= 300
 * 1 <= words[i].length <= 20
 * words[i] 由小写英文字母和符号组成
 * 1 <= maxWidth <= 100
 * words[i].length <= maxWidth
 */
public class Solution {

    public List<String> fullJustify(String[] words, int maxWidth) {
        int n = words.length;
        List<String> res = new ArrayList<>();
        for (int i = 0, start = 0, len = 0; i < n; i++) {
            len += words[i].length() + 1;
            //如果是最后一个单词，或者加上下一个词就超过长度了，即可凑成一行
            if (i + 1 == n || len + words[i + 1].length() > maxWidth) {
                res.add(fillWords(words, start, i, maxWidth, i + 1 == n));
                start = i + 1;
                len = 0;
            }
        }
        return res;
    }

    private String fillWords(String[] words, int start, int end, int maxWidth, boolean lastLine) {
        int wordCount = end - start + 1;
        //除去每个单词尾部空格，+1是最后一个单词的尾部空格特殊处理
        int spaceCount = maxWidth + 1 - wordCount;
        for (int i = start; i <= end; i++) {
            //除去所有单词的长度
            spaceCount -= words[i].length();
        }
        //词尾空格
        int spaceSuffix = 1;
        //额外空格的平均值=总空格数/间隙数
        int spaceAvg = wordCount == 1 ? 1 : spaceCount / (wordCount - 1);
        //额外空格的余数
        int spaceExtra = wordCount == 1 ? 0 : spaceCount % (wordCount - 1);
        //填入单词
        StringBuilder res = new StringBuilder();
        for (int i = start; i < end; i++) {
            res.append(words[i]);
            if (lastLine) {
                res.append(" ");
                continue;
            }
            int n = spaceSuffix + spaceAvg + (i - start < spaceExtra ? 1 : 0);
            while (n-- > 0) {
                res.append(" ");
            }
        }
        //填入最后一个单词
        res.append(words[end]);
        //不上这一行最后的空格
        int lastSpaceCnt = maxWidth - res.length();
        while (lastSpaceCnt-- > 0) {
            res.append(" ");
        }
        return res.toString();
    }

}

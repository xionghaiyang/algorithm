package com.sean.leetcode.LeetCode68;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-15 18:07
 * @Description: https://leetcode.cn/problems/text-justification/?envType=study-plan-v2&envId=top-interview-150
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
 */
public class Solution {

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        int right = 0, n = words.length;
        while (true) {
            //当前行的第一个单词在words的位置
            int left = right;
            //统计这一行单词长度之和
            int sumLen = 0;
            //循环确定当前行可以放多少单词，注意单词之间应至少有一个空格
            while (right < n && sumLen + words[right].length() + right - left <= maxWidth) {
                sumLen += words[right++].length();
            }
            //当前行是最后一行：单词左对其，且单词之间应只有一个空格，在行末填充剩余空格
            if (right == n) {
                StringBuffer sb = join(words, left, n, " ");
                sb.append(blank(maxWidth - sb.length()));
                res.add(sb.toString());
                return res;
            }
            int numWords = right - left;
            int numSpaces = maxWidth - sumLen;
            //当前行只有一个单词：该单词对齐，在行末填充剩余空格
            if (numWords == 1) {
                StringBuffer sb = new StringBuffer(words[left]);
                sb.append(blank(numSpaces));
                res.add(sb.toString());
                continue;
            }
            //当前行不只一个单词
            int avgSpaces = numSpaces / (numWords - 1);
            int extraSpaces = numSpaces % (numWords - 1);
            StringBuffer sb = new StringBuffer();
            //拼接额外加一个空格的单词
            sb.append(join(words, left, left + extraSpaces + 1, blank(avgSpaces + 1)));
            sb.append(blank(avgSpaces));
            //拼接其余单词
            sb.append(join(words, left + extraSpaces + 1, right, blank(avgSpaces)));
            res.add(sb.toString());
        }
    }

    //join返回用sep拼接[left,right)范围内的words组成的字符串
    private StringBuffer join(String[] words, int left, int right, String sep) {
        StringBuffer res = new StringBuffer(words[left]);
        for (int i = left + 1; i < right; i++) {
            res.append(sep);
            res.append(words[i]);
        }
        return res;
    }

    private String blank(int n) {
        StringBuffer res = new StringBuffer();
        for (int i = 0; i < n; i++) {
            res.append(' ');
        }
        return res.toString();
    }

    public List<String> fullJustify1(String[] words, int maxWidth) {
        int n = words.length;
        List<String> res = new ArrayList<>();
        int len = 0, start = 0;
        for (int i = 0; i < n; i++) {
            len += words[i].length() + 1;
            //如果是最后一个单词，或者加上下一个词就超过长度了，即可凑成一行
            if (i + 1 == words.length || len + words[i + 1].length() > maxWidth) {
                //对每行单词进行空格平均划分
                res.add(fillWords(words, start, i, maxWidth, i + 1 == n));
                start = i + 1;
                len = 0;
            }
        }
        return res;
    }

    //对每行单词进行空格平均划分
    private String fillWords(String[] words, int start, int end, int maxWidth, boolean lastLine) {
        int wordCount = end - start + 1;
        //除去每个单词尾部空格,+1是最后一个单词的尾部空格的特殊处理
        int spaceCount = maxWidth + 1 - wordCount;
        for (int i = start; i <= end; i++) {
            //除去所有单词的长度
            spaceCount -= words[i].length();
        }
        //词尾空格
        int spaceSuffix = 1;
        //额外空格的平均值=总空格数/间隙数
        int spaceAvg = (wordCount == 1) ? 1 : spaceCount / (wordCount - 1);
        //额外空格的余数
        int spaceExtra = (wordCount == 1) ? 0 : spaceCount % (wordCount - 1);
        //填入单词
        StringBuffer res = new StringBuffer();
        for (int i = start; i < end; i++) {
            res.append(words[i]);
            if (lastLine) {
                res.append(" ");
                continue;
            }
            int n = spaceSuffix + spaceAvg + ((i - start) < spaceExtra ? 1 : 0);
            while (n-- > 0) {
                res.append(" ");
            }
        }
        //填入最后一个单词
        res.append(words[end]);
        //补上这一行最后的空格
        int lastSpaceCnt = maxWidth - res.length();
        while (lastSpaceCnt-- > 0) {
            res.append(" ");
        }
        return res.toString();
    }

}

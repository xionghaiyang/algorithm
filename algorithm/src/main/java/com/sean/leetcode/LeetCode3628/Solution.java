package com.sean.leetcode.LeetCode3628;

/**
 * @Author xionghaiyang
 * @Date 2025-08-08 20:08
 * @Description https://leetcode.cn/problems/maximum-number-of-subsequences-after-one-inserting
 * 3628. 插入一个字母的最大子序列数
 * 给你一个由大写英文字母组成的字符串 s。
 * 你可以在字符串的 任意 位置（包括字符串的开头或结尾）最多插入一个 大写英文字母。
 * 返回在 最多插入一个字母 后，字符串中可以形成的 "LCT" 子序列的 最大 数量。
 * 子序列 是从另一个字符串中删除某些字符（可以不删除）且不改变剩余字符顺序后得到的一个 非空 字符串。
 * 1 <= s.length <= 10^5
 * s 仅由大写英文字母组成。
 */
public class Solution {

    public long numOfSubsequences(String s) {
        char[] str = s.toCharArray();
        int t = 0;
        for (char c : str) {
            if (c == 'T') {
                t++;
            }
        }
        long l = 0, lc = 0, lct = 0, c = 0, ct = 0, lt = 0;
        for (char b : str) {
            if (b == 'L') {
                l++;
            } else if (b == 'C') {
                lc += l;
                c++;
            } else if (b == 'T') {
                lct += lc;
                ct += c;
                t--;
            }
            lt = Math.max(lt, l * t);
        }
        return lct + Math.max(Math.max(ct, lc), lt);
    }

}

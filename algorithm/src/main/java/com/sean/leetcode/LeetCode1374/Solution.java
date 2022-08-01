package com.sean.leetcode.LeetCode1374;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-08-01 11:22
 * https://leetcode.cn/problems/generate-a-string-with-characters-that-have-odd-counts/
 * 给你一个整数 n，请你返回一个含 n 个字符的字符串，其中每种字符在该字符串中都恰好出现 奇数次 。
 * 返回的字符串必须只含小写英文字母。如果存在多个满足题目要求的字符串，则返回其中任意一个即可。
 */
public class Solution {

    public String generateTheString(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n - 1; i++) {
            sb.append("a");
        }
        if (n % 2 == 1) {
            sb.append("a");
        } else {
            sb.append("b");
        }
        return sb.toString();
    }

}

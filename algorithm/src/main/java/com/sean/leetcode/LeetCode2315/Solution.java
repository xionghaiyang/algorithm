package com.sean.leetcode.LeetCode2315;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-01-29 10:02
 * @Description: https://leetcode.cn/problems/count-asterisks/
 * 2315. 统计星号
 * 给你一个字符串 s ，每 两个 连续竖线 '|' 为 一对 。换言之，第一个和第二个 '|' 为一对，第三个和第四个 '|' 为一对，以此类推。
 * 请你返回 不在 竖线对之间，s 中 '*' 的数目。
 * 注意，每个竖线 '|' 都会 恰好 属于一个对。
 */
public class Solution {

    public int countAsterisks(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        boolean valid = true;
        int res = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '|') {
                valid = !valid;
            } else if (c == '*' && valid) {
                res++;
            }
        }
        return res;
    }

}

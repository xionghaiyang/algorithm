package com.sean.leetcode.LeetCode859;

/**
 * @Author xionghaiyang
 * @Date 2026-04-13 18:28
 * @Description https://leetcode.cn/problems/buddy-strings
 * 859. 亲密字符串
 * 给你两个字符串 s 和 goal ，只要我们可以通过交换 s 中的两个字母得到与 goal 相等的结果，就返回 true ；否则返回 false 。
 * 交换字母的定义是：取两个下标 i 和 j （下标从 0 开始）且满足 i != j ，接着交换 s[i] 和 s[j] 处的字符。
 * 例如，在 "abcd" 中交换下标 0 和下标 2 的元素可以生成 "cbad" 。
 * 1 <= s.length, goal.length <= 2 * 10^4
 * s 和 goal 由小写英文字母组成
 */
public class Solution {

    public boolean buddyStrings(String s, String goal) {
        if (s.length() != goal.length()) {
            return false;
        }
        int n = s.length();
        int[] cnt = new int[26];
        boolean flag = false;
        int first = -1, second = -1;
        for (int i = 0; i < n; i++) {
            if (++cnt[s.charAt(i) - 'a'] > 1) {
                flag = true;
            }
            if (s.charAt(i) != goal.charAt(i)) {
                if (first == -1) {
                    first = i;
                } else if (second == -1) {
                    second = i;
                } else {
                    return false;
                }
            }
        }
        return (first == -1 && flag)
                || (second != -1 && s.charAt(first) == goal.charAt(second) && s.charAt(second) == goal.charAt(first));
    }

}

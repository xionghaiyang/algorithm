package com.sean.leetcode.LeetCode2696;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-01-10 10:39
 * @Description: https://leetcode.cn/problems/minimum-string-length-after-removing-substrings/
 * 2696. 删除子串后的字符串最小长度
 * 给你一个仅由 大写 英文字符组成的字符串 s 。
 * 你可以对此字符串执行一些操作，在每一步操作中，你可以从 s 中删除 任一个 "AB" 或 "CD" 子字符串。
 * 通过执行操作，删除所有 "AB" 和 "CD" 子串，返回可获得的最终字符串的 最小 可能长度。
 * 注意，删除子串后，重新连接出的字符串可能会产生新的 "AB" 或 "CD" 子串。
 */
public class Solution {

    public int minLength(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == 'B' && !stack.isEmpty() && stack.peek() == 'A') {
                stack.pop();
            } else if (c == 'D' && !stack.isEmpty() && stack.peek() == 'C') {
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        return stack.size();
    }

}

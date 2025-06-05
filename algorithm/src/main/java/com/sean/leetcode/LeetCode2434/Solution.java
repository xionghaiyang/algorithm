package com.sean.leetcode.LeetCode2434;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author xionghaiyang
 * @Date 2025-06-06 06:19
 * @Description https://leetcode.cn/problems/using-a-robot-to-print-the-lexicographically-smallest-string
 * 2434. 使用机器人打印字典序最小的字符串
 * 给你一个字符串 s 和一个机器人，机器人当前有一个空字符串 t 。
 * 执行以下操作之一，直到 s 和 t 都变成空字符串：
 * 删除字符串 s 的 第一个 字符，并将该字符给机器人。 机器人把这个字符添加到 t 的尾部。
 * 删除字符串 t 的 最后一个 字符，并将该字符给机器人。机器人将该字符写到纸上。
 * 请你返回纸上能写出的字典序最小的字符串。
 * 1 <= s.length <= 10^5
 * s 只包含小写英文字母。
 */
public class Solution {

    public String robotWithString(String s) {
        int n = s.length();
        char[] sufMin = new char[n + 1];
        sufMin[n] = Character.MAX_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            sufMin[i] = (char) Math.min(sufMin[i + 1], s.charAt(i));
        }
        StringBuilder res = new StringBuilder();
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            stack.push(s.charAt(i));
            while (!stack.isEmpty() && stack.peek() <= sufMin[i + 1]) {
                res.append(stack.pop());
            }
        }
        return res.toString();
    }

}
